import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.beans.binding.Bindings;
import jdk.nashorn.internal.objects.NativeString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FinalTest {
    Actions actions;
    public WebDriver driver;
    JavascriptExecutor js;
    private Bindings date;
    private NativeString flatRateDeliveryFull;

    @BeforeMethod
    public void bef() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
       new WebDriverWait(driver,10);
    }

    /*Actions actions;
    public WebDriver driver;
    JavascriptExecutor js;
    private Bindings date;
    private NativeString flatRateDeliveryFull;


    @BeforeMethod
    public void bef() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
        //driver = new ChromeDriver();
       // driver = new EdgeDriver();
      //  driver.manage().window().maximize();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }*/
/*
    @BeforeTest
   // @Parameters("browser")
    public void setup(@NotNull String browser) throws Exception{if(browser.equalsIgnoreCase("Chrome")){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


        }else if(browser.equalsIgnoreCase("Edge")){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        }else{
            throw new Exception("Browser is not correct");
        }
    }*/

    @Test
    public void register() {
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.get("http://tutorialsninja.com/demo/");
        driver.manage().window().maximize();
        WebElement myaccaunt = driver.findElement(By.cssSelector(".dropdown"));
        //new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(myaccaunt)).click();
        myaccaunt.click();
        actions.moveToElement(myaccaunt).perform();
        WebElement register = driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a"));
        //new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(register)).click();
        register.click();

        driver.findElement(By.name("firstname")).sendKeys("Ambartsum");
        driver.findElement(By.name("lastname")).sendKeys("Karapetyan");
        driver.findElement(By.name("email")).sendKeys("ambartsum@gmail.com");
        driver.findElement(By.name("telephone")).sendKeys("555444333");
        driver.findElement(By.name("password")).sendKeys("Ab123.bA.");
        driver.findElement(By.name("confirm")).sendKeys("Ab123.bA.");
        WebElement subscribe = driver.findElement(By.cssSelector(".radio-inline input[value='1']"));
        subscribe.click();
        WebElement agree = driver.findElement(By.name("agree"));
        agree.click();
        WebElement registersubmit = driver.findElement(By.cssSelector("input[value='Continue']"));
        registersubmit.click();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //System.out.println(driver.findElement(By.cssSelector("#account-register > div.alert.alert-danger.alert-dismissible")).getText());
        String wrong = driver.findElement(By.cssSelector("#account-register > div.alert.alert-danger.alert-dismissible")).getText();
        System.out.println(wrong);
        if (wrong == "Warning: E-Mail Address is already registered!") {
            WebElement Login = driver.findElement(By.cssSelector("#column-right > div > a:nth-child(1)"));
            Login.click();
            driver.findElement(By.cssSelector("#input-email")).sendKeys("ambartsum@gmail.com");
            driver.findElement(By.id("input-password")).sendKeys("Ab123.bA.");
            WebElement acclogin = driver.findElement(By.cssSelector("input[value='Login']"));
            acclogin.click();
        }
        driver.findElement(By.xpath("//a[text()='Phones & PDAs']")).click();
        WebElement phonehower = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div/div[1]/a/img"));
        actions.moveToElement(phonehower).perform();
        String title = phonehower.getAttribute("title");
        if (title.equalsIgnoreCase("Palm Treo Pro")) {
            System.out.println("Phone name is alredy " + phonehower.getAttribute("title"));
        } else {
            System.out.println("Phone`s name is incorrect");
        }
        phonehower.click();
        String[] imageCounter;
        int imgTotal, imgCurrent;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement productFirstImage = driver.findElement(By.xpath("//img[@title='" + title + "']"));
        //    JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", productFirstImage);//JS executor command 2
        wait.until(ExpectedConditions.visibilityOf(driver.findElement((By.className("mfp-img")))));
        imageCounter = driver.findElement(By.className("mfp-counter")).getText().split("\\s+");
        imgTotal = Integer.parseInt(imageCounter[imageCounter.length - 1]);
        imgCurrent = Integer.parseInt(imageCounter[0]);
        WebElement next = driver.findElement(By.cssSelector("button[title*='Next']"));
        for (int i = 0; i <= imgTotal; i++) {
            wait.until(ExpectedConditions.visibilityOf(next));
            if (imgCurrent == imgTotal) {
                System.out.println("last foto is " + imgTotal + "th");
                break;
            } else {
                new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(next)).click();
               // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("mfp-img")));
                //next.click();
                imageCounter = driver.findElement(By.className("mfp-counter")).getText().split("\\s+");
                imgCurrent = Integer.parseInt(imageCounter[0]);
            }
        }
        WebElement close = driver.findElement(By.cssSelector("button[title*='Close']"));
        wait.until(ExpectedConditions.elementToBeClickable(close));
        close.click();
        // driver.findElement(By.cssSelector(".mfp-close")).click();
        WebElement button = driver.findElement(By.cssSelector("#content > div > div.col-sm-8 > ul.nav.nav-tabs > li:nth-child(2)"));
        wait.until(ExpectedConditions.visibilityOf(button));
        button.click();
        driver.findElement(By.name("name")).sendKeys("Ambartsum");
        driver.findElement(By.name("text")).sendKeys(" Small text about Palm Treo Pro smartphone ");
        WebElement radiobutton = driver.findElement(By.cssSelector("input[value='5']"));
        radiobutton.click();
        wait.until(ExpectedConditions.visibilityOf(radiobutton));
        driver.findElement(By.id("button-review")).click();
        driver.findElement(By.id("button-cart")).click();
       String item = driver.findElement(By.xpath("//*[@id=\"cart-total\"]")).getText();
        String text = driver.findElement(By.xpath("//*[@id=\"cart-total\"]")).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong/i[@class='fa fa-shopping-cart']")));
        if (item == text) {
            System.out.println("product was successfully added to cart");
        } else {
            System.out.println("product was not  added to cart");
        }
            WebElement itembtn = driver.findElement(By.cssSelector("#cart"));
        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cart"))).click();
            itembtn.click();
            WebElement check = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong"));
        //new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(check)).click();
           // wait.until(ExpectedConditions.elementToBeClickable(check));
           check.click();
            new WebDriverWait(driver, 10);
            driver.findElement(By.cssSelector("#cart > ul > li:nth-child(2) > div > p > a:nth-child(2) > strong")).sendKeys("ambartsum@gmail.com");
            driver.findElement(By.cssSelector("#input-password")).sendKeys("Ab123.bA.");
            WebElement acclogin = driver.findElement(By.cssSelector("input[value='Login']"));
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(acclogin)).click();

        wait.until(ExpectedConditions.visibilityOf(acclogin));

            driver.findElement(By.name("firstname")).sendKeys("Ambartsum");
            driver.findElement(By.name("lastname")).sendKeys("Karapetyan");
            driver.findElement(By.name("company")).sendKeys("TBC");
            driver.findElement(By.name("address_1")).sendKeys("2 Ateni street");
            driver.findElement(By.name("address_2")).sendKeys("33 Ilia Chavchavadze avenue");
            driver.findElement(By.name("city")).sendKeys("Tbilisi");
            driver.findElement(By.name("postcode")).sendKeys("1200");
            driver.findElement(By.name("country_id")).click();
            WebElement georgia = driver.findElement(By.cssSelector("option[value='80']"));
            wait.until(ExpectedConditions.elementToBeClickable(georgia));
            driver.findElement(By.name("zone_id")).click();
            driver.findElement(By.cssSelector("option[value='1244']")).click();
            driver.findElement(By.cssSelector("input[value='Continue']")).click();
            driver.findElement(By.id("button-shipping-address")).click();
            driver.findElement(By.name(" comment")).sendKeys("sheping coatis 5 dolars");
            driver.findElement(By.name("//*[@id=\"button-shipping-method\"]")).click();
            driver.findElement(By.name("agree")).click();
            driver.findElement(By.name(" //*[@id=\"button-payment-method\"]")).click();
            
//Check that Sub-Total, Flat Shipping Rate and Total amount is correct
        //Get subtotal value from cart
        WebElement cartButton2 = driver.findElement(By.cssSelector("[class='btn btn-inverse btn-block btn-lg dropdown-toggle']"));
        js.executeScript("arguments[0].click();", cartButton2);
        String subTotalCart = driver.findElement(By.xpath("//*[text()='Sub-Total']/ancestor::td/following-sibling::td")).getText();
        //Initialize flat rate string with just flat shipping rate value
     //   String flatRateDelivery = flatRateDeliveryFull.substring(flatRateDeliveryFull.indexOf("$"));
        //Get subtotal, flat shipping rate and total values from confirm order section
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-confirm")));
        String subTotalConfirm = driver.findElement(By.xpath("//*[text()='Sub-Total:']/ancestor::td/following-sibling::td")).getText();
        String flatRateConfirm = driver.findElement(By.xpath("//*[text()='Flat Shipping Rate:']/ancestor::td/following-sibling::td")).getText();
        String total = driver.findElement(By.xpath("//*[text()='Total:']/ancestor::td/following-sibling::td")).getText();
        //Check if the values in Confirm Order section are correct, and confirm the order
        System.out.println("Subtotal is correct: " + subTotalConfirm.equals(subTotalCart));
        //System.out.println("Flat Shipping Rate is correct: " + flatRateConfirm.equals(flatRateDelivery));
        double floatrt = Double.parseDouble (flatRateConfirm.substring (1, flatRateConfirm.length() - 1));
        double subttl;
        double subtitl = Double.parseDouble (subTotalConfirm.substring (1, subTotalConfirm.length() - 1));
        double tl = Double.parseDouble (total.substring (1, total.length() - 1));
        System.out.println("Total is correct: " + ((floatrt + subtitl) == tl));
        driver.findElement(By.id("button-confirm")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Continue']")));
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Order History']")).click();
        List<WebElement> headers = driver.findElements(By.xpath("//thead//td"));
        int index = 0;
        int statusInd = 0;
        int dateIndex = 0;
        for(WebElement hd : headers){
            index++;
            if(hd.getText().equalsIgnoreCase("status"))
                statusInd = index;
            else if(hd.getText().equalsIgnoreCase("date added"))
                dateIndex = index;
        }
        String status = driver.findElement(By.xpath("//tbody//td["+statusInd+"]")).getText();
        System.out.println("Status is pending: " + status.equalsIgnoreCase("pending"));
        String dateAdded = driver.findElement(By.xpath("//tbody//td(" +dateIndex+ ")")).getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Date is correct: " + dateAdded.equals(date.format(String.valueOf(formatter))));

        }
    }

