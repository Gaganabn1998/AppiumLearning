package AppiumPractice.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import AppiumPractice.BaseClass.Gestures;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AlertHandellingTest {

    public AndroidDriver driver;

    @Test
    public void AlertHandleTest() throws MalformedURLException, URISyntaxException, InterruptedException {


        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("My_Medium_Phone");
        options.setPlatformName("Android");
        options.setApp("D:\\SDKTools\\ApiDemos-debugTest.apk");
        System.out.println("App launched");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Gestures gestures = new Gestures(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        Thread.sleep(5000);
        WebElement AppAccordion = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("App")));
        AppAccordion.click();
        System.out.println("App accordion clicked");
        Thread.sleep(5000);

        WebElement AlertDialogs = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Alert Dialogs")));
        AlertDialogs.click();
        System.out.println("Alert Dialogs clicked");
        Thread.sleep(5000);

        WebElement AlertDialogOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("OK Cancel dialog with a message")));
        AlertDialogOption.click();
        System.out.println("Alert Dialog Option clicked");
        Thread.sleep(5000);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);
        alert.accept();
    }

    @Test
    public void SingleChoiceAlertListTest() throws MalformedURLException, URISyntaxException, InterruptedException {


        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("My_Medium_Phone");
        options.setPlatformName("Android");
        options.setApp("D:\\SDKTools\\ApiDemos-debugTest.apk");
        System.out.println("App launched");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Gestures gestures = new Gestures(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        WebElement AppAccordion = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("App")));
        AppAccordion.click();
        System.out.println("App accordion clicked");
        Thread.sleep(5000);

        WebElement AlertDialogs = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Alert Dialogs")));
        AlertDialogs.click();
        System.out.println("Alert Dialogs clicked");
        Thread.sleep(5000);

        WebElement AlertDialogOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Single choice list")));
        AlertDialogOption.click();
        System.out.println("Single choice list Alert Dialog Option clicked");
        Thread.sleep(5000);

        Alert alert = driver.switchTo().alert();
        WebElement streetViewOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Street view']")));
        streetViewOption.click();
        System.out.println("Clicked on 'Street view' option.");
        alert.accept();
    }

    @Test
    public void HandleEntryDialogTest() throws MalformedURLException, URISyntaxException, InterruptedException {


        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("My_Medium_Phone");
        options.setPlatformName("Android");
        options.setApp("D:\\SDKTools\\ApiDemos-debugTest.apk");
        System.out.println("App launched");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Gestures gestures = new Gestures(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        WebElement AppAccordion = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("App")));
        AppAccordion.click();
        System.out.println("App accordion clicked");
        Thread.sleep(5000);

        WebElement AlertDialogs = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Alert Dialogs")));
        AlertDialogs.click();
        System.out.println("Alert Dialogs clicked");
        Thread.sleep(5000);

        WebElement AlertDialogOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Text Entry dialog")));
        AlertDialogOption.click();
        System.out.println("Text Entry dialog Alert Dialog Option clicked");
        Thread.sleep(5000);

        Alert alert = driver.switchTo().alert();
        WebElement userName = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("io.appium.android.apis:id/username_edit")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("io.appium.android.apis:id/password_edit")));
        userName.sendKeys("Gagana");
        password.sendKeys("Gagana123");
        WebElement alertAcceptButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("android:id/button1")));
        alertAcceptButton.click();
        System.out.println("saved the entry dialog alert");
    }


    @Test
    public void HandleRepeatAlarmAlertTest() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("My_Medium_Phone");
        options.setPlatformName("Android");
        options.setApp("D:\\SDKTools\\ApiDemos-debugTest.apk");
        System.out.println("App launched");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Open App -> Alert Dialogs -> Repeat Alarm
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("App"))).click();
        System.out.println("App accordion clicked");

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Alert Dialogs"))).click();
        System.out.println("Alert Dialogs clicked");

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Repeat alarm"))).click();
        System.out.println("Repeat Alarm dialog Alert Dialog Option clicked");

        // **Check and Uncheck Checkboxes**
        handleRepeatAlarmDialog();
    }

    public void handleRepeatAlarmDialog() {
        List<String> daysToCheck = Arrays.asList("Every Monday", "Every Tuesday", "Every Wednesday", "Every Thursday", "Every Friday");
        List<String> daysToUncheck = Arrays.asList("Every Saturday", "Every Sunday");

        for (String day : daysToCheck) {
            toggleCheckbox(day, true);
        }

        for (String day : daysToUncheck) {
            toggleCheckbox(day, false);
        }
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();
        System.out.println("Saved the entry dialog alert");
    }

    public void toggleCheckbox(String day, boolean shouldBeChecked) {

        WebElement checkbox = driver.findElement(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='" + day + "']"));
        String isChecked = checkbox.getAttribute("checked");

        if (shouldBeChecked && isChecked.equalsIgnoreCase("false")) {
            checkbox.click();
            System.out.println(day + " was unchecked, now checked.");
        } else if (!shouldBeChecked && isChecked.equalsIgnoreCase("true")) {
            checkbox.click();
            System.out.println(day + " was checked, now unchecked.");
        } else {
            System.out.println(day + " is already in the desired state.");
        }
    }

}
