import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KatyaKoksTest {

/*
TC_1_1  - Тест кейс:
1. Открыть страницу https://openweathermap.org/
2. Набрать в строке поиска город Paris
3. Нажать пункт меню Search
4. Из выпадающего списка выбрать Paris, FR
5. Подтвердить, что заголовок изменился на "Paris, FR"
 */

    @Test
    public void testH2TagText_WhenSearchingCityCountry () throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
    WebDriver driver = new ChromeDriver();

    String url = "https://openweathermap.org/";
    String cityName = "Paris";
    String expectedResult = "Paris, FR";

    driver.get(url);
    driver.manage().window().maximize();
    Thread.sleep(5000);

    WebElement searchCityField = driver.findElement(
            By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
    );
    searchCityField.click();
    searchCityField.sendKeys(cityName);

    WebElement searchButton = driver.findElement(
            By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
    );
    searchButton.click();
    Thread.sleep(1000);

    WebElement parisFRChoiceInDropdownMenu = driver.findElement(
            By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
    );
    parisFRChoiceInDropdownMenu.click();

    WebElement h2CityCountryHeader = driver.findElement(
             By.xpath("//div[@id = 'weather-widget']//h2")
    );
    Thread.sleep(2000);

    String actualResult = h2CityCountryHeader.getText();
    Assert.assertEquals(actualResult, expectedResult);

    driver.quit();

    }

/*
TC_11_01
1.  Открыть базовую ссылку
2.  Нажать на пункт меню Guide
3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
*/

    @Test
    public void testTitleAndUrl_NavigateToGuide () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuGuide = driver.findElement(
                By.xpath("//nav//a[@href='/guide']")
        );
        menuGuide.click();
        Thread.sleep(1000);

        String actualResultTitle = driver.getTitle();
        String actualResultUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertEquals(actualResultUrl, expectedResultUrl);

        driver.quit();

    }

/*
TC_11_02
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения
3.  Подтвердить, что температура для города показана в Фарингейтах
*/

    @Test
    public void testVerifyTemperature_IsFahrenheit () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String fSymbol = "F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement measureTemperatureImperial = driver.findElement(
                By.xpath("//div[.='Imperial: °F, mph']")
        );
        measureTemperatureImperial.click();

        WebElement temperatureInF = driver.findElement(
                By.xpath("//div[@class='current-temp']/span")
        );
        Thread.sleep(2000);

        Assert.assertTrue(temperatureInF.getText().contains(fSymbol),"Failed");

        driver.quit();

    }

/*
TC_11_03
1. Открыть базовую ссылку
2. Подтвердить, что внизу страницы есть панель с текстом
“We use cookies which are essential for the site to work.
We also use non-essential cookies to help us improve our services.
Any data collected is anonymised. You can allow all cookies or manage them individually.”
3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
*/
    @Test
    public void testCookies_CookiesButtonsExist () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement footerText = driver.findElement(
                By.xpath("//div/p[@class='stick-footer-panel__description']")
        );

        String actualResult = footerText.getText();
        Assert.assertEquals(actualResult, expectedResult);

        WebElement allowAll = driver.findElement(
                By.xpath("//div/button[@class='stick-footer-panel__link']"));
        WebElement manageCookies = driver.findElement(
                By.xpath("//div/a[@class='stick-footer-panel__link']"));

        Assert.assertTrue(allowAll.isDisplayed(),"Failed");
        Assert.assertTrue(manageCookies.isDisplayed(),"Failed");

        driver.quit();

}

/*
TC_11_04
1. Открыть базовую ссылку
2. Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
*/

    @Test
    public void testSupport_ContainThreeSubmenu () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String faqExpected = "FAQ";
        String howToStartExpected = "How to start";
        String askQuestionExpected = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportDropdown = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportDropdown.click();

        WebElement faqActual = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        WebElement howToStartActual = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']"));
        WebElement askQuestionActual = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']"
                        + "//a[@href='https://home.openweathermap.org/questions']"));

        Assert.assertEquals(faqActual.getText(), faqExpected);
        Assert.assertEquals(howToStartActual.getText(), howToStartExpected);
        Assert.assertEquals(askQuestionActual.getText(), askQuestionExpected);

        driver.quit();

    }

/*
TC_11_05
1. Открыть базовую ссылку
2. Нажать пункт меню Support → Ask a question
3. Заполнить поля Email, Subject, Message
4. Не подтвердив CAPTCHA, нажать кнопку Submit
5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
*/
@Test
public void testCaptchaFail() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
    WebDriver driver = new ChromeDriver();

    String url = "https://openweathermap.org/";
    String email = "example@example.com";
    String message = "Test message";
    String expectedResult = "reCAPTCHA verification failed, please try again.";

    driver.get(url);
    driver.manage().window().maximize();
    Thread.sleep(5000);

    WebElement supportDropdown = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
    supportDropdown.click();

    String originalWindow = driver.getWindowHandle();
    Thread.sleep(4000);
    WebElement selectAskAQuestion = driver.findElement(
            By.xpath("//ul[@id='support-dropdown-menu']"
                    + "//a[@href='https://home.openweathermap.org/questions']"));
    selectAskAQuestion.click();
    Thread.sleep(4500);

    for (String windowHandle : driver.getWindowHandles()) {
        if (!originalWindow.contentEquals(windowHandle)) {
            driver.switchTo().window(windowHandle);
            break;
        }
    }
    Thread.sleep(3000);

    WebElement subjectOption = driver.findElement(
            By.xpath("//select[@class='form-control select required']/option[@value='Sales']"));
    subjectOption.click();
    Thread.sleep(4000);

    WebElement messageField = driver.findElement(
            By.xpath("//textarea[@class='form-control text required']"));
    messageField.click();
    messageField.sendKeys(message);

    WebElement emailField = driver.findElement(
            By.xpath("//input[@class='form-control string email required']"));
    emailField.click();
    emailField.sendKeys(email);
    Thread.sleep(5000);

    WebElement submitButton = driver.findElement(
            By.xpath("//input[@data-disable-with='Create Question form']"));
    submitButton.click();
    Thread.sleep(5000);

    WebElement confirmCaptcha = driver.findElement(By.xpath("//div[@class='help-block']"));

    String actualResult = confirmCaptcha.getText();
    Assert.assertEquals(actualResult, expectedResult);

    driver.quit();

}

/*
TC_11_06
1. Открыть базовую ссылку
2. Нажать пункт меню Support → Ask a question
3. Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
4. Оставить пустым поле Email
5. Заполнить поля  Subject, Message
6. Подтвердить CAPTCHA
7. Нажать кнопку Submit
8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
 */

    @Test
    public void testWrongEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String message = "Test message";
        String expectedResult = "can't be blank";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportDropdown = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportDropdown.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(4000);
        WebElement selectAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']"
                        + "//a[@href='https://home.openweathermap.org/questions']"));
        selectAskAQuestion.click();
        Thread.sleep(4500);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement subjectOption = driver.findElement(
                By.xpath("//select[@class='form-control select required']/option[@value='Sales']"));
        subjectOption.click();
        Thread.sleep(4000);

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']"));
        messageField.click();
        messageField.sendKeys(message);
        Thread.sleep(5000);

        String windowPage = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));
        WebElement enterCaptcha = driver.findElement(
                    By.xpath("//span[@class='recaptcha-checkbox goog-inline-block"
                            + " recaptcha-checkbox-unchecked "
                            + "rc-anchor-checkbox']"));
        enterCaptcha.click();
        Thread.sleep(10000);
        driver.switchTo().window(windowPage);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@data-disable-with='Create Question form']"));
        submitButton.click();

        WebElement confirmEmail = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = confirmEmail.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

/*
TC_11_07
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Нажать на единицы измерения Metric: °C, m/s
4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
 */

    @Test
    public void testVerifyTemperatureMeasure () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultF = "F";
        String expectedResultC = "C";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement measureTemperatureImperial = driver.findElement(
                By.xpath("//div[.='Imperial: °F, mph']")
        );
        measureTemperatureImperial.click();

        WebElement temperatureInF = driver.findElement(
                By.xpath("//span[contains(text(),'°F')]")
        );
        Thread.sleep(2000);

        String actualResultF = temperatureInF.getText();
        Assert.assertTrue(actualResultF.contains(expectedResultF),"Failed");

        WebElement measureTemperatureMetric = driver.findElement(
                By.xpath("//div[.='Metric: °C, m/s']")
        );
        measureTemperatureMetric.click();

        WebElement temperatureInC = driver.findElement(
                By.xpath("//span[contains(text(),'°C')]")
        );
        Thread.sleep(2000);

        String actualResultC = temperatureInC.getText();
        Assert.assertTrue(actualResultC.contains(expectedResultC),"Failed");

        driver.quit();

    }

/*
TC_11_08
1. Открыть базовую ссылку
2. Нажать на лого компании
3. Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
 */

    @Test
    public void testClickLogo () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement logo = driver.findElement(By.xpath("//nav//li[@class='logo']"));
        logo.click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.getCurrentUrl(), url);

        driver.quit();

    }

/*
TC_11_09
1. Открыть базовую ссылку
2. В строке поиска в навигационной панели набрать “Rome”
3. Нажать клавишу Enter
4. Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
*/

    @Test
    public void testPageContainsText_WhenSearchCityInNavigationPanel () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        String find = "find";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchField = driver.findElement(By.xpath("//form//input[@type='text']"));
        searchField.click();
        searchField.sendKeys(cityName);
        searchField.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        WebElement searchFieldOnResultPage = driver.findElement(
                By.xpath("//form//input[@id='search_str']"));


        Assert.assertTrue(driver.getCurrentUrl().contains(cityName), "Failed");
        Assert.assertTrue(driver.getCurrentUrl().contains(find), "Failed");

        String actualResult = searchFieldOnResultPage.getAttribute("value");
        Assert.assertEquals(actualResult, cityName);

        driver.quit();

    }

/*
TC_11_10
1. Открыть базовую ссылку
2. Нажать на пункт меню API
3. Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
*/

    @Test
    public void testAPIPage_orangeButtonsAreDisplayed () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;


        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement api = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/api']"));
        api.click();
        Thread.sleep(5000);

        int actualResult = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange')]")).size();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

}
