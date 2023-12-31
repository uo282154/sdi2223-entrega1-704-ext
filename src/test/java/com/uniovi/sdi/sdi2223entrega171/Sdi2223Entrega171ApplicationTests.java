package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.pageobjects.*;
import com.uniovi.sdi.sdi2223entrega171.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.uniovi.sdi.sdi2223entrega171.pageobjects.PO_View.getTimeout;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2223Entrega171ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    //static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\uo282154\\Desktop\\geckodriver-v0.30.0-win64.exe";

    //static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
//    static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    //static String Geckodriver = "C:\\Users\\jorge\\Desktop\\geckodriver-v0.30.0-win64\\geckodriver.exe";

    //static String Geckodriver = "C:\\Users\\garci\\Desktop\\Uniovi\\Cuarto\\Segundo Semestre\\Sistemas Distribuidos e Internet\\Laboratorio\\Clase 5\\sesion06\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
//    static String Geckodriver = "C:\\Users\\garci\\Desktop\\Uniovi\\Cuarto\\Segundo Semestre\\Sistemas Distribuidos e Internet\\Laboratorio\\Clase 5\\sesion06\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

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

    // [Prueba1] Registro de Usuario con datos válidos.
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

    //[Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
    @Test
    @Order(2)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "", "", "", "77777", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    //  [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña inválida).
    @Test
    @Order(3)
    public void PR03() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77877", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    //[Prueba4] Registro de Usuario con datos inválidos (email existente).
    @Test
    @Order(4)
    public void PR04() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "admin@gmail.com", "Josefo", "Perez", "77777", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    // [Prueba5] Inicio de sesión con datos válidos (administrador).
    @Test
    @Order(5)
    public void PR05() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    // [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
    @Test
    @Order(6)
    public void PR06() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "user01");
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
    }
    // [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email y contraseña vacíos).
    @Test
    @Order(7)
    public void PR07() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "", "");
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "loginbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    // [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email existente, pero contraseña
    //incorrecta).
    @Test
    @Order(8)
    public void PR08() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "contraseñaNoValida");
        String checkText = "Error al introducir las credenciales";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    //[Prueba9] Hacer clic en la opción de salir de sesión y comprobar que se redirige a la página de inicio de
    //sesión (Login).
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
    //[Prueba10] Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado.
    @Test
    @Order(10)
    public void PR10() {
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Desconectar", getTimeout());
    }
    //[Prueba11] Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el
    //sistema.
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
    //[Prueba12] Ir a la lista de usuarios, borrar el primer usuario de la lista, comprobar que la lista se actualiza
    //y dicho usuario desaparece.
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
    //[Prueba13] Ir a la lista de usuarios, borrar el último usuario de la lista, comprobar que la lista se actualiza
    //y dicho usuario desaparece.
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
    //[Prueba14] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se actualiza y dichos
    //usuarios desaparecen.
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


//[Prueba15] Ir al formulario de alta de oferta, rellenarla con datos válidos y pulsar el botón Enviar.
//Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
    @Test
    @Order(15)
    public void PR15(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user03@gmail.com", "user03");
        driver.get("localhost:8090/offer/add");
        PO_AddOfferView.fillAddForm(driver,"Oferta de prueba","Esto es para una prueba","50");
        driver.get("localhost:8090/offer/my");
        List<WebElement> result=PO_View.checkElementBy(driver,"text","Oferta de prueba");

        Assertions.assertEquals("Oferta de prueba", result.get(0).getText());

    }

//[Prueba16] Ir al formulario de alta de oferta, rellenarla con datos inválidos (precio negativo) y pulsar el
//botón Enviar. Comprobar que se muestra el mensaje de campo inválido.
    @Test
    @Order(16)
    public void PR16(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user03@gmail.com", "user03");
        driver.get("localhost:8090/offer/add");
        PO_AddOfferView.fillAddForm(driver,"Oferta de prueba","Esto es para una prueba","-50");

        List<WebElement> result=PO_View.checkElementBy(driver,"text","El precio no puede ser negativo");

        Assertions.assertEquals("El precio no puede ser negativo", result.get(0).getText());

    }
    //   [Prueba17] Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran todas los que
//    existen para este usuario
    @Test
    @Order(17)
    public void PR17() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "pruebaOffer@gmail.com", "Josefo", "Perez", "77777", "77777");

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'myOffers')]/a");
        elements.get(0).click();

        List<WebElement> elements2 = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        elements2.get(0).click();

        PO_OfferView.fillOfferForm(driver, "OfertaPrueba", "Esta es una oferta de prueba", "10");

        elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), 'OfertaPrueba')]");
        String text = "OfertaPrueba";
        Assertions.assertEquals(text,elements.get(0).getText());
    }
    //[Prueba18] Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar que la lista se actualiza y
    //que la oferta desaparece.
    @Test
    @Order(18)
    public void PR18() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user03@gmail.com", "user03");
        driver.get("localhost:8090/offer/my");

        //List<WebElement> result=PO_View.checkElementBy(driver,"text","El precio no puede ser negativo");
        PO_PrivateView.clickOptionNoAssert(driver, "/offer/delete/84", "href", "Eliminar");
        boolean b = false;
        try {
            List<WebElement> result = PO_View.checkElementBy(driver, "href", "/offer/delete/7");
        } catch (Exception e) {
            b=true;
        }
        Assertions.assertTrue(b);
    }
//[Prueba19] Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar que la lista se actualiza y
//que la oferta desaparece.
    @Test
    @Order(19)
    public void PR19(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user03@gmail.com", "user03");

        driver.get("localhost:8090/offer/add");
        PO_AddOfferView.fillAddForm(driver,"Oferta test19","Esto es para una prueba","50");


        driver.get("localhost:8090/offer/my");

        List<WebElement> result=PO_View.checkElementBy(driver,"text","Eliminar");
        result.get(0).click();
        Boolean b=false;
        try{
        result=PO_View.checkElementBy(driver,"text","Eliminar");
        }catch(org.openqa.selenium.TimeoutException e){
            b=true;
        }
        Assertions.assertTrue(b);
    }

    //[Prueba20] Hacer una búsqueda con el campo vacío y comprobar que se muestra la página que
    //corresponde con el listado de las ofertas existentes en el sistema
    @Test
    @Order(20)
    public void PR20(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        PO_LoginView.fillLoginForm(driver, "user03@gmail.com", "user03");
        driver.get("localhost:8090/offer/listAll");

        List<WebElement> result =PO_PrivateView.checkElementBy(driver,"id","searchinput");
        result.get(0).click();
        result.get(0).sendKeys("");
        result =PO_PrivateView.checkElementBy(driver,"text","Buscar");
        result.get(0).click();
        result =PO_PrivateView.checkElementBy(driver,"id","rowtest");
        Assertions.assertTrue(result.size()>1);
    }
    //[Prueba21] Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar que se
    //muestra la página que corresponde, con la lista de ofertas vacía.
    @Test
    @Order(21)
    public void PR21(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        PO_LoginView.fillLoginForm(driver, "user03@gmail.com", "user03");
        driver.get("localhost:8090/offer/listAll");

        driver.get("http://localhost:8090/offer/listAll?searchText=123456789pruebatextosinsesentido");
        SeleniumUtils.textIsNotPresentOnPage(driver,"Vendido");
        SeleniumUtils.textIsNotPresentOnPage(driver,"Comprar");
    }
//[Prueba22] Sobre una búsqueda determinada (a elección del desarrollador), comprar una oferta que deja
//un saldo positivo en el contador del comprador. Comprobar que el contador se actualiza correctamente
//en la vista del comprador.
    @Test
    @Order(22)
    public void PR22() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user03@gmail.com", "user03");
        driver.get("http://localhost:8090/offer/buy/18");
        List<WebElement>result=PO_PrivateView.checkElementBy(driver,"id","actualmoney");
        Assertions.assertEquals("78.0",result.get(0).getText());

    }
    //[Prueba23] Sobre una búsqueda determinada (a elección del desarrollador), comprar una oferta que deja
    //un saldo 0 en el contador del comprador. Comprobar que el contador se actualiza correctamente en la
    //vista del comprador.
    @Test
    @Order(23)
    public void PR23() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user04@gmail.com", "user04");
        driver.get("http://localhost:8090/offer/buy/22");
        List<WebElement>result=PO_PrivateView.checkElementBy(driver,"id","actualmoney");
        Assertions.assertEquals("0.0",result.get(0).getText());

    }
    //[Prueba24] Sobre una búsqueda determinada (a elección del desarrollador), intentar comprar una oferta
    //que esté por encima de saldo disponible del comprador. Y comprobar que se muestra el mensaje de
    //saldo no suficiente
    @Test
    @Order(24)
    public void PR24() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-dark");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user04@gmail.com", "user04");
        driver.get("http://localhost:8090/offer/buy/101");
        List<WebElement>result=PO_PrivateView.checkElementBy(driver,"id","actualmoney");
        Assertions.assertEquals("0.0",result.get(0).getText());

    }

    //    [Prueba25] Ir a la opción de ofertas compradas del usuario y mostrar la lista. Comprobar que aparecen
//    las ofertas que deben aparecer.
    @Test
    @Order(25)
    public void PR25() {
        driver.navigate().to("http://localhost:8090/login");
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email25@gmail.com", "Josefo", "Perez", "11111", "11111");

        // Crear oferta
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'myOffers')]/a");
        elements.get(0).click();
        List<WebElement> elements2 = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        elements2.get(0).click();
        PO_OfferView.fillOfferForm(driver, "OfertaPrueba25", "Esta es una oferta de prueba25", "25");
        // salimos
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");

        // registrar user2
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email252@gmail.com", "Josefo", "Perez", "11111", "11111");

        // ver que no tiene compras
        elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'myOffers')]/a");
        elements.get(0).click();
        elements2 = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/myBoughts')]");
        elements2.get(0).click();



        //sacamos la id de
        elements2 = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/listAll')]");
        elements2.get(0).click();

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver, "class", "offerBotiItem", PO_View.getTimeout());
        String idOffer = list.get(list.size()-1).getAttribute("id");

        driver.get("http://localhost:8090/offer/buy/"+idOffer);


        // ver que tiene compras
        elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'myOffers')]/a");
        elements.get(0).click();
        elements2 = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/myBoughts')]");
        elements2.get(0).click();

        SeleniumUtils.textIsPresentOnPage(driver, "OfertaPrueba25");
    }

    //[Prueba26] Sobre una búsqueda determinada de ofertas (a elección de desarrollador), enviar un mensaje
    //a una oferta concreta. Se abriría dicha conversación por primera vez. Comprobar que el mensaje aparece
    //en la conversación
    @Test
    @Order(26)
    public void PR26() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "pruebaChat@gmail.com", "ChatName", "Perez", "77777", "77777");

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'allOffers')]/a");
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "free", "//td[contains(@id, 'OfertaPruebapruebaOffer@gmail.com')]/a");
        elements.get(0).click();

        String textSaludo = "Hola!";
        String persona = "ChatName Perez";
        PO_ChatView.fillChatForm(driver, textSaludo);

        elements = PO_View.checkElementBy(driver, "class", "mt-0 mb-1");
        Assertions.assertEquals(persona, elements.get(0).getText());

        elements = PO_View.checkElementBy(driver, "class", "mb-0");
        Assertions.assertEquals(textSaludo, elements.get(0).getText());
    }
    //[Prueba27] Enviar un mensaje a una conversación ya existente accediendo desde el botón/enlace
    //“Conversación”. Comprobar que el mensaje aparece en la conversación
    @Test
    @Order(27)
    public void PR27() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "pruebaChat@gmail.com", "77777");

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'navChats')]/a");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "free", "//td[contains(@id, 'OfertaPruebapruebaOffer@gmail.com')]/a");
        elements.get(0).click();
        String textSaludo = "Hola!";
        String textNuevo = "Que tal!";
        String persona = "ChatName Perez";

        PO_ChatView.fillChatForm(driver, textNuevo);
        elements = PO_View.checkElementBy(driver, "class", "mt-0 mb-1");
        Assertions.assertEquals(persona, elements.get(0).getText());

        elements = PO_View.checkElementBy(driver, "class", "mt-0 mb-1");
        Assertions.assertEquals(persona, elements.get(1).getText());

        elements = PO_View.checkElementBy(driver, "class", "mb-0");
        Assertions.assertEquals(textSaludo, elements.get(0).getText());

        Assertions.assertEquals(textNuevo, elements.get(1).getText());
    }
    //[Prueba28] Mostrar el listado de conversaciones ya abiertas. Comprobar que el listado contiene la
//cantidad correcta de conversaciones.
    @Test
    @Order(28)
    public void PR28() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "pruebaChat@gmail.com", "77777");
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'navChats')]/a");
        elements.get(0).click();

        List<WebElement> chatsList = SeleniumUtils.waitLoadElementsBy(driver, "class", "chatTrItem",PO_View.getTimeout());
        Assertions.assertEquals(1, chatsList.size());
    }

    @Test
    @Order(29)
    public void PR29() {
        driver.navigate().to("http://localhost:8090/");

        String checkText = "Bienvenidos a la pagina principal";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnEnglish");

        checkText = "Welcome to homepage";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnSpanish");

        checkText = "Bienvenidos a la pagina principal";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        driver.navigate().to("http://localhost:8090/login");

        checkText = "Correo electronico:";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnEnglish");

        checkText = "Email:";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnSpanish");

        checkText = "Correo electronico:";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");

        driver.navigate().to("http://localhost:8090/user/list");

        checkText = "Apellidos";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnEnglish");

        checkText = "Surnames";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnSpanish");

        checkText = "Apellidos";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        driver.navigate().to("http://localhost:8090/offer/listAll");

        checkText = "Todas las ofertas";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnEnglish" +
                "");

        checkText = "All offers";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnSpanish");

        checkText = "Todas las ofertas";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

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

    //[Prueba31] Intentar acceder sin estar autenticado a la opción de listado de conversaciones de un
    // usuario estándar. Se deberá volver al formulario de login.
    @Test
    @Order(31)
    public void PR31() {
        driver.navigate().to("http://localhost:8090/chat/list");

        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }


    //    [Prueba32] Estando autenticado como usuario estándar intentar acceder a una opción disponible solo
//    para usuarios administradores (Añadir menú de auditoria (visualizar logs)).
    @Test
    @Order(32)
    public void PR32() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email32@gmail.com", "Josefo", "Perez", "11111", "11111");

        driver.navigate().to("http://localhost:8090/log/list");

        // No abre la seccion
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Registro de actividad", getTimeout());
    }


//    [Prueba33] Estando autenticado como usuario administrador visualizar todos los logs generados en una
//    serie de interacciones. Esta prueba deberá generar al menos dos interacciones de cada tipo y comprobar
//    que el listado incluye los logs correspondientes.
    @Test
    @Order(33)
    public void PR33() {

        driver.navigate().to("http://localhost:8090/login");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        //Borramos los logs
        PO_NavView.clickOptionOfDropDown(driver,"id", "logsDropdown", "removeAllLogsBtn");

        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");

        //+1 alta
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "aaaaa@gmail.com", "aaaaa", "aaaaa aaaaa", "123456", "123456");

        // +1 Logout
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");

        //+1 log-ex
        PO_LoginView.fillLoginForm(driver, "aaaaa@gmail.com", "123456");

        // +1 Logout
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");


        // +1 alta
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "bbbbb@gmail.com", "bbbbb", "bbbbb bbbbb", "123456", "123456");


        // +1 Logout
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");

        //+1 log-ex
        PO_LoginView.fillLoginForm(driver, "bbbbb@gmail.com", "123456");

        // +1 Logout
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");

        //2 log-err
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "wrongPass");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "wrongPass");

        //+ 1 log ex
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");

        PO_NavView.clickOptionOfDropDown(driver,"id", "logsDropdown", "showLogsList");

        // generamos 12 peticiones de log + las 12 de tipo PET

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");

        int nRequests = 0;
        for(int i = 0; i<elements.size(); i++){
            List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver, "class", "logTrItem",PO_View.getTimeout());
            nRequests += list.size();
            if(i==0) {
                elements.get(2).click();
            } else {
                elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
                elements.get(3).click();
            }
        }
        Assertions.assertTrue(nRequests > 24);
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
        PO_NavView.clickOptionOfDropDown(driver,"id", "logsDropdown", "removeAllLogsBtn");

        // los vemos
        PO_NavView.clickOptionOfDropDown(driver,"id", "logsDropdown", "showLogsList");

        // no va a estar reflejado el LOG_EX del admin
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "LOG_EX", getTimeout());
    }

}

