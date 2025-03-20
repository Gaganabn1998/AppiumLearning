package org.example.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumFirstTest {
    static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException {
        try {
            openCalculatorApp();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void openCalculatorApp(){

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("realme 7");
        options.setUdid("E6ORR4YLWKG6GI59");
        options.setAppPackage("com.coloros.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");
        options.setAutoGrantPermissions(true);

        options.setAutomationName("UiAutomator2");
        options.setNoReset(true);
        options.setFullReset(false);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            ProcessBuilder pb = new ProcessBuilder("C:\\Windows\\System32\\cmd.exe", "/c",
                    "C:\\commandlinetools-win-11076708_latest\\platform-tools\\adb.exe",
                    "shell", "am", "start", "-n", "com.android.calculator2/.Calculator");
            Process pc = pb.start();
            pc.waitFor();
            System.out.println("Done");
            Thread.sleep(3000);
            System.out.println("chrome launched!");
        } catch (Exception e) {
            System.out.println("Failed to launch the app. Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



