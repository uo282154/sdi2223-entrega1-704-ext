package com.uniovi.sdi.sdi2223entrega171.pageobjects;

import com.uniovi.sdi.sdi2223entrega171.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView  {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
        //Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
        //Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
        //Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void loginWithCredentials(WebDriver driver, String DNI, String password, String textToCheck) {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, textToCheck, password);
        //Cmmprobamos que entramos en la pagina privada del Profesor
        PO_View.checkElementBy(driver, "text", textToCheck);
    }

    static public void logoutFromPage(WebDriver driver,int properties) {
        String loginText = PO_HomeView.getP().getString("signup.message", properties);
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    static public void clickInMenu(WebDriver driver, String xpath1, int option1,String mode) {
        List<WebElement> elements = PO_View.checkElementBy(driver, mode, xpath1);
        elements.get(option1).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
    }
}
