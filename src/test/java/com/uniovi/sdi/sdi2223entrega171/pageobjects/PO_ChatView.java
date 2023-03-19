package com.uniovi.sdi.sdi2223entrega171.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ChatView extends PO_NavView{

    static public void fillChatForm(WebDriver driver, String messagep) {
        WebElement message = driver.findElement(By.name("message"));
        message.click();
        message.clear();
        message.sendKeys(messagep);

        //Pulsar el boton de Alta.
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
}
