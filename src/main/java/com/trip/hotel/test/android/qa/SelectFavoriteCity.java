package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class SelectFavoriteCity extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver;

    @BeforeClass
    public void beforeclass() throws MalformedURLException {
        driver = initial.createAndroidReleaseDriver();
    }

    @Test(description = "hotelBySxm", groups = {"Base"})
    public void selectFavoriteCity() throws Exception {

        logger.info("---启动APP---");

        logger.info("初始化成功，准备登陆");
        appCommonService.loginForApp(driver, "wwwwww", "good08");
        logger.info("---进入酒店首页---");
        driver.findElement(By.id("myctrip_hotel_icon")).click();

        logger.info("--C1309666喜爱酒店城市筛选---");
        driver.findElement(By.id("tv_hotel_favorite")).click();
        //logger.info(" --点击喜爱酒店--");
        //driver.findElement(By.id("favoriteRL")).click();
        logger.info(" --点击所有喜爱酒店，出来下拉城市--");
        driver.findElement(By.id("tv_title")).click();

        logger.info(" --获取所有城市--");
        List<WebElement> citys = driver.findElements(By.id("tv_title"));
        String cityname1 = citys.get(7).getText();
        citys.get(7).click();

        String cityname2 = driver.findElement(By.id("tv_title")).getText();

        logger.info("----断言C1309666喜爱酒店城市筛选----");
        assertTrue(cityname2.equals(cityname1));
    }

    @DataProvider(name = "testdata")
    public Iterator<Object[]> data1test() throws IOException {
        return ExcelProviderByEnv(this, "testData");
    }

    @AfterClass
    public void afterClass() {
        logger.info("I here afterclass");
        driver.quit();
    }

}
