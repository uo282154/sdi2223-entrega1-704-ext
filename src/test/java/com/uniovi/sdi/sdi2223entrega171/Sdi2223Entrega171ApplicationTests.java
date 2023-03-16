package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.pageobjects.*;
import com.uniovi.sdi.sdi2223entrega171.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2223Entrega171ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    //Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    @Order(1)
    public void PR01() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        //Rellenamos el formulario.
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77777", "77777");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(2)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "", "", "", "77777", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(3)
    public void PR03() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77877", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(4)
    public void PR04() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "admin@gmail.com", "Josefo", "Perez", "77777", "77777");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(5)
    public void PR05() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(6)
    public void PR06() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "11111");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Mis ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(7)
    public void PR07() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "", "");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(8)
    public void PR08() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "contrasnaNoValida");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Error al introducir las credenciales";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(9)
    public void PR09() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "11111");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Mis ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
        String checkText2 = "Identificate";
        List<WebElement> result2 = PO_View.checkElementBy(driver, "text", checkText2);
        Assertions.assertEquals(checkText2, result2.get(0).getText());
    }

    @Test
    @Order(10)
    public void PR10() {
        //Vamos al formulario de logueo.
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Desconectar",PO_View.getTimeout());
    }

    @Test
    @Order(11)
    public void PR11() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","text","Usuarios");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",PO_View.getTimeout());
        Assertions.assertEquals(6, userList.size());
    }

    @Test
    @Order(12)
    public void PR12() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","text","Usuarios");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",PO_View.getTimeout());
        Assertions.assertEquals(6, userList.size());

        PO_PrivateView.clickOption(driver,"1");
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",PO_View.getTimeout());
        Assertions.assertEquals(5, userList2.size());
    }

    @Test
    @Order(13)
    public void PR13() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","text","Usuarios");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",PO_View.getTimeout());
        Assertions.assertEquals(5, userList.size());

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        String id = result2.get(result2.size() - 1).getAttribute("id");
        PO_PrivateView.clickOption(driver,id);
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",PO_View.getTimeout());
        Assertions.assertEquals(4, userList2.size());
    }
}
