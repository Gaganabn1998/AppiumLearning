package org.example.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.BaseClass.Gestures;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class AppiumGesturesTest {

    public AndroidDriver driver;

    @Test
    public void GesturesTest() throws MalformedURLException, URISyntaxException, InterruptedException {


        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("My_Medium_Phone");
        options.setPlatformName("Android");
        options.setApp("D:\\SDKTools\\ApiDemos-debugTest.apk");
        System.out.println("App launched");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Gestures gestures = new Gestures(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        //*******************************Long Press Gesture test*******************************************
        WebElement viewAccordion = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Views")));
        viewAccordion.click();
        System.out.println("Views accordion clicked");
        Thread.sleep(2000);
        WebElement expandableListsAccordion = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Expandable Lists")));
        expandableListsAccordion.click();
        System.out.println("Expandable Lists accordion clicked");
        WebElement CustomAdaptorOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("1. Custom Adapter")));
        CustomAdaptorOption.click();
        System.out.println("Custom Adaptor Option clicked");
        WebElement catNames = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Cat Names']")));
        gestures.longPress(By.xpath("//android.widget.TextView[@text='Cat Names']"));
        Thread.sleep(2000);
        WebElement sampleActionOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.LinearLayout[@resource-id='android:id/content']")));
        sampleActionOption.click();
        System.out.println("Sample action option clicked");


        //*******************************Scroll Gesture test*******************************************
        driver.navigate().back();
        driver.navigate().back();
        gestures.scrollToElement("WebView3");
        System.out.println("Web View 3 found");
        gestures.scrollToElement("Animation");

        WebElement animationOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Animation")));
        System.out.println("Animation found");
        animationOption.click();

        //*******************************Swipe Gesture test*******************************************
        driver.navigate().back();
        WebElement galleryOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gallery")));
        System.out.println("Gallery found");
        galleryOption.click();

        WebElement photosOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("1. Photos")));
        System.out.println("Photos found");
        photosOption.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.ImageView)[1]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.ImageView")));
        int swipeCount = 0;
        while (swipeCount < 5) {
            List<WebElement> photos = driver.findElements(By.xpath("//android.widget.ImageView"));

            if (photos.size() >= 5) {
                photos.get(4).click();
                System.out.println("Swiped to the 5th photo and clicked on it.");
                break;
            } else {
                System.out.println("Only " + photos.size() + " photos found. Swiping...");
                WebElement lastPhoto = photos.get(photos.size() - 1);
                gestures.swipe(lastPhoto);
                swipeCount++;
                Thread.sleep(1000);
            }
        }
        if (swipeCount == 5) {
            System.err.println("5th photo not found after swiping 5 times.");
        }

        System.out.println("Swiped to the 5th photo and clicked on it.");


        driver.navigate().back();


//********************************Drag and Drop Gesture test********************************
        WebElement dragAndDropOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Drag and Drop")));
        dragAndDropOption.click();
        System.out.println("Drag and Drop option clicked");
        WebElement dot1 = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("io.appium.android.apis:id/drag_dot_1")));
        gestures.dragAndDrop(dot1);

        System.out.println("Drag and Drop completed");


//*******************************Tap Gesture test*******************************************
        driver.navigate().back();
        driver.navigate().back();
        WebElement AnimationAccordion = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Animation")));
        AnimationAccordion.click();
        System.out.println("Animation accordion clicked");
        WebElement viewFlipOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("View Flip")));
        viewFlipOption.click();
        System.out.println("View Flip option clicked");
        WebElement flipButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Flip")));
        gestures.tap(flipButton);

    }


}
