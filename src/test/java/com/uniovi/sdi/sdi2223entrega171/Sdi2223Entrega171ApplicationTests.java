package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.pageobjects.*;
import com.uniovi.sdi.sdi2223entrega171.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.uniovi.sdi.sdi2223entrega171.pageobjects.PO_View.getTimeout;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2223Entrega171ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

//    static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\jorge\\Desktop\\geckodriver-v0.30.0-win64\\geckodriver.exe";


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
        PO_SignUpView.fillForm(driver, "user15@gmail.com", "user15", "user15", "user15", "user15");
        String checkText = "Mis ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
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
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "user01");
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
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "contraseñaNoValida");
        String checkText = "Error al introducir las credenciales";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(9)
    public void PR09() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "user01");
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");
        String checkText2 = "Identificate";
        List<WebElement> result2 = PO_View.checkElementBy(driver, "id", "loginText");
        Assertions.assertEquals(checkText2, result2.get(0).getText());
    }

    @Test
    @Order(10)
    public void PR10() {
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Desconectar", getTimeout());
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
        Assertions.assertEquals(16, userList.size());
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
        Assertions.assertEquals(16, userList.size());

        PO_PrivateView.clickOption(driver,"user01");
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user01@gmail.com",PO_View.getTimeout());
        Assertions.assertEquals(15, userList2.size());
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
        Assertions.assertEquals(15, userList.size());

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        String id = result2.get(result2.size() - 1).getAttribute("id");
        PO_PrivateView.clickOption(driver,id);
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user15@gmail.com",PO_View.getTimeout());
        Assertions.assertEquals(14, userList2.size());
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
        Assertions.assertEquals(14, userList.size());
        ArrayList<String> deletedIds = new ArrayList<String>();

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        for(int i = result2.size() - 3; i < result2.size() ; i++) {
            String id = result2.get(i).getAttribute("id");
            deletedIds.add(id);
            PO_PrivateView.clickOption(driver, id);
        }
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        for(String id : deletedIds) {
            SeleniumUtils.waitTextIsNotPresentOnPage(driver, id,PO_View.getTimeout());
        }

        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user13@gmail.com",PO_View.getTimeout());
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user12@gmail.com",PO_View.getTimeout());
        Assertions.assertEquals(11, userList2.size());
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

    @Test
    @Order(31)
    public void PR31() {
        // en el pdf este test no es de este proyecto
        Assertions.assertTrue(true);
    }


    //    [Prueba32] Estando autenticado como usuario estándar intentar acceder a una opción disponible solo
//    para usuarios administradores (Añadir menú de auditoria (visualizar logs)).
    @Test
    @Order(32)
    public void PR32() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email32@gmail.com", "Josefo", "Perez", "11111", "11111");

        driver.navigate().to("http://localhost:8090/login");
//        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "email32@gmail.com", "11111");

        driver.navigate().to("http://localhost:8090/log/list");

        // No abre la seccion
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Registro de actividad", getTimeout());
    }

    //    [Prueba33] Estando autenticado como usuario administrador visualizar todos los logs generados en una
//    serie de interacciones. Esta prueba deberá generar al menos dos interacciones de cada tipo y comprobar
//    que el listado incluye los logs correspondientes.
//    @Test
//    @Order(33)
//    public void PR33() {
//        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
//        PO_SignUpView.fillForm(driver, "aaaaa@gmail.com", "aaaaa", "aaaaa aaaaa", "123456", "123456");
//        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
//
//        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
//        PO_SignUpView.fillForm(driver, "bbbbb@gmail.com", "bbbbb", "bbbbb bbbbb", "123456", "123456");
//
//
//        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "patata");
//        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "patata");
//
//        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "user01");
//        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
//
//        PO_LoginView.fillLoginForm(driver, "user02@gmail.com", "user02");
//        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
//
//        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
//        PO_HomeView.clickOption(driver, "/admin/logs", "class", "btn btn-primary");
//
//        //Abre la opcion x del menu
//        List<WebElement> logButton = SeleniumUtils.waitLoadElementsBy(driver, "id", "logsDropdown",
//                getTimeout());
//        logButton.get(0).click();
//        //Esperamos a que aparezca el menú de opciones.
//        SeleniumUtils.waitLoadElementsBy(driver, "id", "logDropdownMenuButton", getTimeout());
//        //CLickamos la opción Inglés partiendo de la opción Español
//        List<WebElement> opt = SeleniumUtils.waitLoadElementsBy(driver, "id", "showLogsList",
//                getTimeout());
//        opt.get(0).click();
//
//        List<WebElement> logList = SeleniumUtils.waitLoadElementsBy(driver, "class", "logList", getTimeout());
//        Assertions.assertTrue(logList.size() >10);
//
//    }

//    [Prueba33] Estando autenticado como usuario administrador visualizar todos los logs generados en una
//    serie de interacciones. Esta prueba deberá generar al menos dos interacciones de cada tipo y comprobar
//    que el listado incluye los logs correspondientes.
    @Test
    @Order(33)
    public void PR33() {
//        //+1 alta
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "aaaaa@gmail.com", "aaaaa", "aaaaa aaaaa", "123456", "123456");

        //+1 log-ex
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "aaaaa@gmail.com", "123456");

        // +1 Logout
        driver.navigate().to("http://localhost:8090/login?logout");

        // +1 alta
        driver.navigate().to("http://localhost:8090/signup");
        PO_SignUpView.fillForm(driver, "bbbbb@gmail.com", "bbbbb", "bbbbb bbbbb", "123456", "123456");

        driver.navigate().to("http://localhost:8090/login?logout");
        driver.navigate().to("http://localhost:8090/login");

        //+1 log-ex
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "bbbbb@gmail.com", "123456");

        // +1 Logout
        driver.navigate().to("http://localhost:8090/login?logout");

//        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        //2 log-err
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "wrongPass");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "wrongPass");


//        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
//        //+1 log-ex
//        PO_LoginView.fillLoginForm(driver, "aaaaa@gmail.com", "123456");
//        PO_HomeView.clickOption(driver, "/logout", "id", "logoutbtn");
//        PO_HomeView.clickOption(driver, "/logout", "id", "logout");
//        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        PO_HomeView.clickOption(driver, "/admin/logs", "class", "btn btn-primary");

        PO_NavView.clickOptionOfDropDown(driver,"id", "logsDropdown", "showLogsList");

        List<WebElement> logList = SeleniumUtils.waitLoadElementsBy(driver, "class", "logList", getTimeout());
        Assertions.assertTrue(logList.size() >10);
    }

    //    [Prueba34] Estando autenticado como usuario administrador, ir a visualización de logs, pulsar el
//    botón/enlace borrar logs y comprobar que se eliminan los logs de la base de datos.
    @Test
    @Order(34)
    public void PR34() {

        // +1 LOG-EX
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");

        //Borramos los logs
        PO_NavView.clickOptionOfDropDown(driver,"id", "logsDropdown", "deleteAllLogsBtn");

        // los vemos
        PO_NavView.clickOptionOfDropDown(driver,"id", "logsDropdown", "showLogsList");

        // no va a estar reflejado el LOG_EX del admin
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "LOG_EX", getTimeout());
    }

}

