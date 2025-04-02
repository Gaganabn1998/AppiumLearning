//package org.example.BaseClass;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
//import io.appium.java_client.ios.IOSDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class Base {
//
//    protected static AppiumDriver driver;
//
//    public void setup(String platform) throws MalformedURLException {
//        if (platform.equalsIgnoreCase("android")) {
//            String deviceUdid = getConnectedDevice();
//
//            if (deviceUdid == null) {
//                System.out.println("❌ No Android device or emulator detected. Please check your setup.");
//                return;
//            }
//
//            UiAutomator2Options options = new UiAutomator2Options();
//            options.setPlatformName("Android");
//            options.setDeviceName(deviceUdid);
//            options.setUdid(deviceUdid);
//            options.setAppPackage("com.coloros.calculator");
//            options.setAppActivity("com.android.calculator2.Calculator");
//            options.setAutoGrantPermissions(true);
//            options.setAutomationName("UiAutomator2");
//            options.setNoReset(true);
//            options.setFullReset(false);
//
//            driver = new AndroidDriver(new URL("http://localhost:4723"), options);
//            System.out.println("✅ Android session started on device: " + deviceUdid);
//
//        } else if (platform.equalsIgnoreCase("ios")) {
//            DesiredCapabilities caps = new DesiredCapabilities();
//            caps.setCapability("platformName", "iOS");
//            caps.setCapability("deviceName", "iPhone Simulator");
//            caps.setCapability("app", System.getProperty("user.dir") + "/apps/UICatalog.app");
//            driver = new IOSDriver(new URL("http://localhost:4723"), caps);
//            System.out.println("✅ iOS session started.");
//        } else {
//            System.out.println("❌ Unsupported platform: " + platform);
//        }
//    }
//
//    /**
//     * Automatically detects the first connected Android device or emulator.
//     */
////    public String getConnectedDevice() {
////        try {
////            Process process = Runtime.getRuntime().exec("adb devices");
////            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////            String line;
////            while ((line = reader.readLine()) != null) {
////                if (line.endsWith("device")) {
////                    return line.split("\t")[0]; // Return the first available device
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return null; // No device found
////    }
////    public String getConnectedDevice() {
////        try {
////            Process process = Runtime.getRuntime().exec("adb devices");
////            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////            String line;
////
////            // Debug output to check ADB response
////            System.out.println("🔍 Checking connected devices...");
////
////            // Skip the header line
////            reader.readLine();
////
////            while ((line = reader.readLine()) != null) {
////                System.out.println("ADB Output: " + line); // Print each line for inspection
////
////                if (line.endsWith("device") && !line.contains("unauthorized")) {
////                    return line.split("\t")[0]; // Return the first available device
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return null; // No device found
////    }
//
//    public String getConnectedDevice() {
//        try {
//            while (true) {
//                Process process = Runtime.getRuntime().exec("adb devices");
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                String line;
//
//                System.out.println("🔍 Checking connected devices...");
//
//                // Skip the header line
//                reader.readLine();
//
//                while ((line = reader.readLine()) != null) {
//                    System.out.println("ADB Output: " + line);
//
//                    if (line.endsWith("device") && !line.contains("unauthorized")) {
//                        System.out.println("✅ Device found: " + line.split("\t")[0]);
//                        return line.split("\t")[0]; // Return connected device
//                    }
//                }
//
//                // No device found - try to start emulator if not already running
//                System.out.println("🚀 No connected devices found. Checking available AVDs...");
//
//                Process avdProcess = Runtime.getRuntime().exec("emulator -list-avds");
//                BufferedReader avdReader = new BufferedReader(new InputStreamReader(avdProcess.getInputStream()));
//                String avdName;
//
//                while ((avdName = avdReader.readLine()) != null) {
//                    System.out.println("🔎 Found AVD: " + avdName);
//
//                    // Start only if the AVD name is "myvirtualdevice1"
//                    if ("myvirtualdevice1".equalsIgnoreCase(avdName.trim())) {
//                        System.out.println("🟢 Starting emulator: " + avdName);
//                        Runtime.getRuntime().exec("emulator -avd " + avdName);
//
//                        // Wait until emulator appears in "adb devices"
//                        if (waitForEmulator()) {
//                            return getConnectedDevice(); // Recheck after boot
//                        }
//                    }
//                }
//
//                System.out.println("❌ No matching AVD found. Ensure 'myvirtualdevice1' exists.");
//                return null; // No matching AVD
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null; // No device found
//    }
//
//    // Helper method: Wait for the emulator to appear in adb devices
//    private boolean waitForEmulator() throws InterruptedException, IOException {
//        int retries = 40; // Wait up to 4 minutes (40 x 6 seconds)
//
//        for (int i = 0; i < retries; i++) {
//            Process process = Runtime.getRuntime().exec("adb devices");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            // Skip the header
//            reader.readLine();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (line.endsWith("device")) {
//                    System.out.println("✅ Emulator is online: " + line);
//                    return true;
//                }
//            }
//
//            System.out.println("⏳ Waiting for emulator to boot... (" + (i + 1) + "/" + retries + ")");
//            Thread.sleep(6000); // Wait for 6 seconds
//        }
//
//        System.out.println("❌ Emulator failed to boot within timeout.");
//        return false;
//    }
//
//
//
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//            System.out.println("✅ Session closed successfully.");
//        }
//    }
//}
package org.example.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Base {
    protected static AppiumDriver driver;

    public void setup(String platform) throws MalformedURLException {
        if (platform.equalsIgnoreCase("android")) {
            String deviceUdid = getConnectedDevice();

            if (deviceUdid == null) {
                System.out.println("❌ No Android device or emulator detected. Please check your setup.");
                return;
            }

            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName(deviceUdid);
            options.setUdid(deviceUdid);
            options.setAppPackage("com.android.chrome");
            options.setAppActivity("com.google.android.apps.chrome.Main");
            options.setAutomationName("UiAutomator2");
            options.setAutoGrantPermissions(true);
            options.setNoReset(true);

            driver = new AndroidDriver(new URL("http://localhost:4723"), options);
            System.out.println("✅ Chrome opened on device: " + deviceUdid);

        } else {
            System.out.println("❌ Unsupported platform: " + platform);
        }
    }

    public String getConnectedDevice() {
        try {
            while (true) {
                Process process = Runtime.getRuntime().exec("adb devices");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;

                System.out.println("🔍 Checking connected devices...");

                reader.readLine(); // Skip the header

                while ((line = reader.readLine()) != null) {
                    System.out.println("ADB Output: " + line);

                    if (line.endsWith("device") && !line.contains("unauthorized")) {
                        System.out.println("✅ Device found: " + line.split("\t")[0]);
                        return line.split("\t")[0];
                    }
                }

                System.out.println("🚀 No connected devices found. Checking available AVDs...");

                Process avdProcess = Runtime.getRuntime().exec("emulator -list-avds");
                BufferedReader avdReader = new BufferedReader(new InputStreamReader(avdProcess.getInputStream()));
                String avdName;

                while ((avdName = avdReader.readLine()) != null) {
                    System.out.println("🔎 Found AVD: " + avdName);

                    if ("myvirtualdevice1".equalsIgnoreCase(avdName.trim())) {
                        System.out.println("🟢 Starting emulator: " + avdName);
                        Runtime.getRuntime().exec("emulator -avd " + avdName + " -no-snapshot-load -gpu swiftshader_indirect");

                        if (waitForEmulator()) {
                            return getConnectedDevice();
                        }
                    }
                }

                System.out.println("❌ No matching AVD found. Ensure 'myvirtualdevice1' exists.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean waitForEmulator() throws InterruptedException, IOException {
        int retries = 40; // Wait up to 4 minutes

        for (int i = 0; i < retries; i++) {
            Process process = Runtime.getRuntime().exec("adb shell getprop sys.boot_completed");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();

            if ("1".equals(output)) {
                System.out.println("✅ Emulator fully booted.");
                return true;
            }

            System.out.println("⏳ Waiting for full boot... (" + (i + 1) + "/40)");
            Thread.sleep(6000);
        }

        System.out.println("❌ Emulator failed to boot within timeout.");
        return false;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Session closed successfully.");
        }
    }
}
