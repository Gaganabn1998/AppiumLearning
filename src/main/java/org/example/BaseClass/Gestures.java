package org.example.BaseClass;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class Gestures {
    private AndroidDriver driver;

    public Gestures(AndroidDriver driver) {
        this.driver = driver;
    }

    // Tap on an element
    public void tap(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
    }

    // Longpress on an element
    public void longPress(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        String elementText = element.getText();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);
        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), element.getLocation().getX(), element.getLocation().getY()));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, Duration.ofSeconds(2)));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(longPress));
        System.out.println("Long Pressed on element: " + elementText);
    }

    //Scroll to an element
    public void scrollToElement(String accessibilityId) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().description(\"" + accessibilityId + "\"))"));
        System.out.println("Scrolled to element with accessibility ID: " + accessibilityId);
    }

    //Swipe from one element to another
    public void swipe(WebElement source) {
        try {
            ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("element", ((RemoteWebElement) source).getId(),  // Fix: Use "element" instead of "elementID"
                    "direction", "left", "percent", 0.3));
        } catch (Exception e) {
            System.err.println("Swipe action failed: " + e.getMessage());
        }
    }


    //Drag and Drop from one element to another
    public void dragAndDrop(WebElement source) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId", ((RemoteWebElement) source).getId(), "endX", 619, "endY", 560));
        System.out.println("Dragged element to target");
    }
}
