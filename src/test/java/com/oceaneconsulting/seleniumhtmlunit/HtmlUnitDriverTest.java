package com.oceaneconsulting.seleniumhtmlunit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static junit.framework.Assert.*;

/**
 * Created by ubuntu on 3/27/14.
 */
@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class HtmlUnitDriverTest {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUnitDriverTest.class);
    public static final String URL = "http://www.lemonde.fr";
    public static final String URL2 = "http://www.oceaneconsulting.com";

    private WebDriver htmlUnitDriver;
    private WebDriver firefoxDriver;

    @Before
    public void setUp() {
        DesiredCapabilities cap = new DesiredCapabilities(BrowserType.FIREFOX, null, null);
        cap.setJavascriptEnabled(true);
        htmlUnitDriver = new HtmlUnitDriver(cap);
        firefoxDriver = new FirefoxDriver();
    }


    @Test
    public void createWebDriver() throws IOException {

        final long beginHtmlUnit = System.currentTimeMillis();

        File htmlUnit  = new File("/home/ubuntu/htmlUnit_generatedHTML_oceaneconsulting.html");
        writeToFile(htmlUnitDriver, URL2,htmlUnit);
        logger.info("HtmlUnit execution took {}", (System.currentTimeMillis() - beginHtmlUnit));

        logger.info("**************************************");

        final long beginFirefox = System.currentTimeMillis();
        File firefox = new File("/home/ubuntu/firefox_generatedHTML_oceaneconsulting.html");
        writeToFile(firefoxDriver, URL2,firefox);

        logger.info("Firefox execution took {}", (System.currentTimeMillis() - beginFirefox));
    }

    private void writeToFile(WebDriver driver, String  url,File target) throws IOException {
        FileWriter fileWriter = new FileWriter(target);
        driver.get(url);
        fileWriter.write(driver.getPageSource());
        fileWriter.close();
    }

    private void comparePageSources() {
        logger.info("pages generated By Both drivers are equals? response: {}",
                htmlUnitDriver.getPageSource().compareToIgnoreCase(firefoxDriver.getPageSource()));
        assertEquals(htmlUnitDriver.getPageSource(), firefoxDriver.getPageSource());
    }

    @After
    public void tearDown() {
        htmlUnitDriver.quit();
        firefoxDriver.quit();

    }
}
