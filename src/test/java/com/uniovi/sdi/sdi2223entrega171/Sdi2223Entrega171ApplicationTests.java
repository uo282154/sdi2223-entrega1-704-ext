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
//    static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    static String Geckodriver = "C:\\Users\\jorge\\Desktop\\geckodriver-v0.30.0-win64\\geckodriver.exe";

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

    @BeforeAll
    static public void begin() {
    }

    @AfterAll
    static public void end() {
        driver.quit();
    }

    @Test
    @Order(1)
    public void PR01() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77777", "77777");
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "loginbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(2)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "", "", "", "77777", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(3)
    public void PR03() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77877", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(4)
    public void PR04() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "admin@gmail.com", "Josefo", "Perez", "77777", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(5)
    public void PR05() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(6)
    public void PR06() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "11111");
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
    }

    @Test
    @Order(7)
    public void PR07() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "", "");
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "loginbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(8)
    public void PR08() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "contrasnaNoValida");
        String checkText = "Error al introducir las credenciales";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(9)
    public void PR09() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "11111");
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");
        String checkText2 = "Identificate";
        List<WebElement> result2 = PO_View.checkElementBy(driver, "id", "loginbtn");
        Assertions.assertEquals(checkText2, result2.get(0).getText());
    }

    @Test
    @Order(10)
    public void PR10() {
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Desconectar",PO_View.getTimeout());
    }

    @Test
    @Order(11)
    public void PR11() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(6, userList.size());
    }

    @Test
    @Order(12)
    public void PR12() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(6, userList.size());

        PO_PrivateView.clickOption(driver,"1");
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(5, userList2.size());
    }

    @Test
    @Order(13)
    public void PR13() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(5, userList.size());

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        String id = result2.get(result2.size() - 1).getAttribute("id");
        PO_PrivateView.clickOption(driver,id);
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(4, userList2.size());
    }

    @Test
    @Order(14)
    public void PR14() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(4, userList.size());

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        for(int i = 0; i < result2.size(); i++) {
            String id = result2.get(i).getAttribute("id");
            PO_PrivateView.clickOption(driver, id);
        }
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(1, userList2.size());
    }

//    [Prueba30] Intentar acceder sin estar autenticado a la opción de listado de usuarios. Se deberá volver al
//    formulario de login.
    @Test
    @Order(30)
    public void PR30() {
        driver.navigate().to("http://localhost:8090/user/list");

        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }




//    [Prueba32] Estando autenticado como usuario estándar intentar acceder a una opción disponible solo
//    para usuarios administradores (Añadir menú de auditoria (visualizar logs)). Se deberá indicar un mensaje
//    de acción prohibida.
    @Test
    @Order(32)
    public void PR32() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email32@gmail.com", "Josefo", "Perez", "77877", "77777");

        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "email32@gmail.com", "77877");

        driver.navigate().to("http://localhost:8090/log/list");

        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Logs", PO_View.getTimeout());
    }

//    [Prueba33] Estando autenticado como usuario administrador visualizar todos los logs generados en una
//    serie de interacciones. Esta prueba deberá generar al menos dos interacciones de cada tipo y comprobar
//    que el listado incluye los logs correspondientes.
    @Test
    @Order(33)
    public void PR33() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "aaaaa@gmail.com", "aaaaa", "aaaaa aaaaa", "123456", "123456");
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "bbbbb@gmail.com", "bbbbb", "bbbbb bbbbb", "123456", "123456");
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "patata");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "patata");

        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "user01");
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

        PO_LoginView.fillLoginForm(driver, "user02@gmail.com", "user02");
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        PO_HomeView.clickOption(driver, "/admin/logs", "class", "btn btn-primary");

        SeleniumUtils.textIsPresentOnPage(driver, "LOGIN-EX");
        SeleniumUtils.textIsPresentOnPage(driver, "LOGIN-ERR");
        SeleniumUtils.textIsPresentOnPage(driver, "PET");
        SeleniumUtils.textIsPresentOnPage(driver, "ALTA");
        SeleniumUtils.textIsPresentOnPage(driver, "user01@gmail.com");
        SeleniumUtils.textIsPresentOnPage(driver, "bbbbb@gmail.com");
        SeleniumUtils.textIsPresentOnPage(driver, "aaaaa@gmail.com");
        SeleniumUtils.textIsPresentOnPage(driver, "user02@gmail.com");
        SeleniumUtils.textIsPresentOnPage(driver, "GET");
        SeleniumUtils.textIsPresentOnPage(driver, "POST");
        SeleniumUtils.textIsPresentOnPage(driver, "POST");
        SeleniumUtils.textIsPresentOnPage(driver, "gmail=aaaaa@gmail.com&name=aaaaalastName=aaaaa aaaaa&password=*****&passwordConfirm=*****");
        SeleniumUtils.textIsPresentOnPage(driver, "Mapping: /signup Método: POST Parámetros: gmail=aaaaa@gmail.com&name=aaaaa&lastName=aaaaa aaaaa&password=*****&passwordConfirm=*****");
    }

//    [Prueba34] Estando autenticado como usuario administrador, ir a visualización de logs, pulsar el
//    botón/enlace borrar logs y comprobar que se eliminan los logs de la base de datos.
    @Test
    @Order(34)
    public void PR34() {

        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");

        PO_NavView.clickOption(driver, "/log/deleteAll", "id", "deleteAllLogsBtn");

//        PO_NavView.clickOption(driver, "/log/list", "class", "btn btn-primary");
//        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "LOGIN-EX", PO_View.getTimeout());

        PO_NavView.clickOption(driver,"/log/deleteAll","id","deleteAllLogsBtn");

        PO_NavView.clickOption(driver,"/log/list","id","showLogsList");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "logsList",PO_View.getTimeout());
        Assertions.assertEquals(2, userList.size());
    }

}
