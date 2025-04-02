package AppiumPractice.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class NewAppTest {
public  AndroidDriver driver;
    @Test
    public void AccessibilityAccordianTest() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("My_Medium_Phone");
        options.setPlatformName("Android");
        options.setApp("D:\\SDKTools\\ApiDemos-debugTest.apk");
        System.out.println("App launched");
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement accessibilityAccordion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Access'ibility\"]")));
        accessibilityAccordion.click();
        System.out.println("Clicked on Accessibility accordion");
Thread.sleep(1000);
        WebElement nodeProvider = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility Node Provider\"]")));
        wait.until(ExpectedConditions.visibilityOf(nodeProvider));
        nodeProvider.click();
        System.out.println("Clicked on Accessibility Node Provider");

        WebElement coloredSquare = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.LinearLayout/android.view.View")));
        coloredSquare.click();
        System.out.println("On clicking on coloured square, Unable to see any action");

        driver.navigate().back();
        System.out.println("Navigated back to Accessibility API DEMOS page");

        WebElement nodeQuerying = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility Node Querying\"]")));
        nodeQuerying.click();
        System.out.println("Clicked on Accessibility Node Querying");


        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                AppiumBy.xpath("//android.widget.CheckBox[@resource-id=\"io.appium.android.apis:id/tasklist_finished\"]"))
        );

        int noOfCheckboxes = driver.findElements(AppiumBy.xpath(
                "//android.widget.CheckBox[@resource-id=\"io.appium.android.apis:id/tasklist_finished\"]"
        )).size();
        if(noOfCheckboxes==7) {
    System.out.println("No of checkboxes are 7");
}else{
    System.out.println("No of checkboxes are not 7");
        }


        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("io.appium.android.apis:id/tasklist_finished")));


        List<WebElement> taskLayouts = driver.findElements(AppiumBy.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout"));


        int checkedCount = 0;
        int uncheckedCount = 0;


        for (WebElement layout : taskLayouts) {
            try {

                WebElement label = layout.findElement(By.xpath(".//android.widget.TextView"));


                WebElement checkbox = layout.findElement(By.xpath(".//android.widget.CheckBox"));


                String isChecked = checkbox.getAttribute("checked");


                if (isChecked != null && isChecked.equalsIgnoreCase("true")) {
                    System.out.println(" Checked Task: " + label.getText());
                    checkedCount++;
                } else {
                    System.out.println(" Unchecked Task: " + label.getText());
                    uncheckedCount++;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Element not found in layout: " + e.getMessage());
            }
        }


        System.out.println("Initial Checked Tasks: " + checkedCount);
        System.out.println("Initial Unchecked Tasks: " + uncheckedCount);


        for (WebElement layout : taskLayouts) {
            try {

                WebElement label = layout.findElement(By.xpath(".//android.widget.TextView"));


                WebElement checkbox = layout.findElement(By.xpath(".//android.widget.CheckBox"));


                String isChecked = checkbox.getAttribute("checked");


                if (isChecked == null || isChecked.equalsIgnoreCase("false")) {
                    checkbox.click();
                    System.out.println(" Checked : " + label.getText());
                    checkedCount++;
                    uncheckedCount--;
                }

                else if (isChecked.equalsIgnoreCase("true")) {
                    checkbox.click();
                    System.out.println(" Unchecked : " + label.getText());
                    uncheckedCount++;
                    checkedCount--;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Element not found in layout: " + e.getMessage());
            }
        }

        System.out.println("Final Checked Tasks: " + checkedCount);
        System.out.println("Final Unchecked Tasks: " + uncheckedCount);


Thread.sleep(3000);
        driver.navigate().back();
        System.out.println("Navigated back to Accessibility API DEMOS page");


        WebElement customView = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Custom View\"]")));
        customView.click();
        System.out.println("Clicked on CustomView");


        WebElement pokeButtons = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("(//android.view.View[@text=\"Off\"])[1]")));
        pokeButtons.click();
        System.out.println("Unable to see any action on clicking on poke buttons");

        driver.navigate().back();
        System.out.println("Navigated back to Accessibility API DEMOS page");

    }



}
