package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestSelenium {
    public static void main(String[] args) {
       // System.setProperty("webdriver.chrome.driver", "/Users/priyanka/IdeaProjects/Test/Driver/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Open the Fast.com website
            driver.get("https://fast.com");

            // Wait for the page to load (increase the timeout if necessary)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

            // Locate the speed number element
            WebElement speedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("speed-value")) // Replace this selector if needed
            );

            // Initialize an array to store all speed values
            List<String> speedValues = new ArrayList<>();

            // Poll the element for updates for 30 seconds
            long startTime = System.currentTimeMillis();
            long duration = 30000; // 30 seconds
            String lastValue = "";

            while ((System.currentTimeMillis() - startTime) < duration) {
                String currentValue = speedElement.getText();

                // Add the value to the list if it's different from the last value
                if (!currentValue.isEmpty() && !currentValue.equals(lastValue)) {
                    speedValues.add(currentValue);
                    lastValue = currentValue;
                }

                // Sleep for a short interval to avoid excessive polling
                Thread.sleep(500);
            }

            // Print all collected speed values
            System.out.println("Collected Speed Values: " + speedValues);

            System.out.println("Collected Speed Values: " + Collections.max(speedValues));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
