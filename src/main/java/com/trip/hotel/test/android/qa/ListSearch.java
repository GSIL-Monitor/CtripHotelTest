package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.android.po.HtlDetailPage;
import com.trip.hotel.test.android.po.HtlHomePage;
import com.trip.hotel.test.android.po.HtlListPage;
import com.trip.hotel.test.android.po.PoBase;
import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class ListSearch extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonServiceImpl appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver;

    @Test(description = "by ylf: C1309714	2成人2儿童（一成年，一未成年）搜索酒店", groups = {"Base"})
    public void Adults2Children2() {
        String searchKey = "上海";
        logger.info("--C1309714	2成人2儿童（一成年，一未成年）搜索酒店---");

        logger.info("--点击输入框 转到输入页面---");
        HtlHomePage.findElement(driver, HtlHomePage.hotel_main_search).click();

        logger.info("--在输入框输入 " + searchKey + "---");
        WebElement input = HtlHomePage.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
        input.clear();
        input.sendKeys(searchKey);

        logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
        ArrayList<WebElement> elm = HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);
        elm.get(0).click();

/*		logger.info("--设置入店日期---");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		String Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckin(driver, Month, (cal.get(Calendar.DATE)+""));
		
		logger.info("--设置离店日期---");
		cal.add(Calendar.DATE, 1);
		Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckout(driver, Month, (cal.get(Calendar.DATE)+""));*/

        logger.info("进入酒店列表");
        HtlHomePage.DoSearch(driver);

        logger.info("打开日期、人数设置");
        HtlListPage.ShowDateAdultChildPage(driver);

        logger.info("打开成人、儿童设置");
        HtlListPage.DateAdultChildPage.ShowAdultChildPage(driver);

        logger.info("设置2成人2儿童");
        HtlListPage.AdultChildPage.SetAdultChildNumber(driver, 2, 2);//2成人2儿童

        logger.info("第1个儿童1岁");
        HtlListPage.AdultChildPage.SetChildAge(driver, 1, 1);//第1个儿童1岁

        logger.info("第2个儿童13岁");
        HtlListPage.AdultChildPage.SetChildAge(driver, 2, 13);//第2个儿童13岁

        logger.info("点击确认");
        HtlListPage.AdultChildPage.Confirm(driver);

        logger.info("点击搜寻");
        HtlListPage.DateAdultChildPage.DoSearch(driver);

        logger.info("进入详情页");
        HtlListPage.ToFirstHotelDetailPage(driver);

        logger.info("验证成人数");
        Assert.assertTrue(HtlDetailPage.getAdultNumber(driver).equals("2 成人"));

        logger.info("验证儿童数");
        Assert.assertTrue(HtlDetailPage.getChildNumber(driver).equals("2 小童"));

        HtlDetailPage.backToList(driver);
    }

    @Test(description = "by ylf: C1309713	8成人3儿童（均成年）", groups = {"Base"})
    public void Adults8Children3() {
        String searchKey = "首尔";
        logger.info("--C1309713	8成人3儿童（均成年）---");

        logger.info("--点击输入框 转到输入页面---");
        HtlHomePage.findElement(driver, HtlHomePage.hotel_main_search).click();

        logger.info("--在输入框输入 " + searchKey + "---");
        WebElement input = HtlHomePage.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
        input.clear();
        input.sendKeys(searchKey);

        logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
        ArrayList<WebElement> elm = HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);
        elm.get(0).click();

        logger.info("进入酒店列表");
        HtlHomePage.DoSearch(driver);

        logger.info("打开日期、人数设置");
        HtlListPage.ShowDateAdultChildPage(driver);

        logger.info("打开成人、儿童设置");
        HtlListPage.DateAdultChildPage.ShowAdultChildPage(driver);

        logger.info("设置8成人3儿童");
        HtlListPage.AdultChildPage.SetAdultChildNumber(driver, 8, 3);

        HtlListPage.AdultChildPage.SetChildAge(driver, 1, 12);
        HtlListPage.AdultChildPage.SetChildAge(driver, 2, 13);
        HtlListPage.AdultChildPage.SetChildAge(driver, 3, 14);


        logger.info("点击确认");
        HtlListPage.AdultChildPage.Confirm(driver);

        logger.info("点击搜寻");
        HtlListPage.DateAdultChildPage.DoSearch(driver);

        logger.info("进入详情页");
        HtlListPage.ToFirstHotelDetailPage(driver);

        logger.info("验证成人数");
        Assert.assertTrue(HtlDetailPage.getAdultNumber(driver).equals("8 成人"));

        logger.info("验证儿童数");
        Assert.assertTrue(HtlDetailPage.getChildNumber(driver).equals("3 小童"));

        HtlDetailPage.backToList(driver);
    }

    @Test(description = "by sxm: C1309712 页面默认成人儿童数量搜索", groups = {"Base"})
    public void DefaultAdultChild() throws Exception {
        try {
            logger.info("---开始验证C1309712 页面默认成人儿童数量搜索---");
            String searchKey = "广州";

            HtlHomePage.findElement(driver, HtlHomePage.hotel_main_search).click();
            WebElement input = HtlHomePage.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
            input.clear();
            input.sendKeys(searchKey);

            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(HtlHomePage.SearchEnginePage.tvTitle));
            ArrayList<WebElement> elements = HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);
            logger.info("查看联想的数据");
            elements.get(0).click();
            HtlHomePage.DoSearch(driver);

            logger.info("点击日历框");
            PoBase.findElement(driver, HtlListPage.hotel_address);
            HtlListPage.ShowDateAdultChildPage(driver);
            HtlListPage.DateAdultChildPage.ShowAdultChildPage(driver);

            logger.info("获取列表页默认的成人儿童数");
            ArrayList<WebElement> numbers = HtlListPage.findElements(driver, HtlListPage.AdultChildPage.numbers);
            String adult = numbers.get(0).getText();
            String child = numbers.get(1).getText();

            HtlListPage.AdultChildPage.Confirm(driver);
            HtlListPage.DateAdultChildPage.DoSearch(driver);
            HtlListPage.ToFirstHotelDetailPage(driver);
            logger.info("详情页验证成人数");
            Assert.assertTrue(HtlDetailPage.getAdultNumber(driver).contains(adult));
            logger.info("详情页验证儿童数");
            Assert.assertTrue(HtlDetailPage.getChildNumber(driver).contains(child));
            logger.info("---验证C1309712 页面默认成人儿童数量搜索成功---");

        } catch (Exception exception) {
            logger.info("---验证C1309712 页面默认成人儿童数量搜索失败---");
            exception.printStackTrace();
        }
    }

    @Test(description = "By sxm :C1309717	2成人1儿童（1岁），搜索酒店", groups = {"Base"})
    public void Adult2Children1() throws Exception {
        try {
            logger.info("---开始验证C1309717	2成人1儿童（1岁），搜索酒店---");
            String searchKey = "深圳";

            HtlHomePage.findElement(driver, HtlHomePage.hotel_main_search).click();
            WebElement input = HtlHomePage.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
            input.clear();
            input.sendKeys(searchKey);

            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(HtlHomePage.SearchEnginePage.tvTitle));
            ArrayList<WebElement> elements = HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);
            logger.info("查看联想的数据");
            elements.get(0).click();
            HtlHomePage.DoSearch(driver);

            logger.info("点击日历框");
            PoBase.findElement(driver, HtlListPage.hotel_address);
            HtlListPage.ShowDateAdultChildPage(driver);
            HtlListPage.DateAdultChildPage.ShowAdultChildPage(driver);

            logger.info("设置2成人1儿童");
            HtlListPage.AdultChildPage.SetAdultChildNumber(driver, 2, 1);
            HtlListPage.AdultChildPage.SetChildAge(driver, 1, 1);

            logger.info("点击确认");
            HtlListPage.AdultChildPage.Confirm(driver);
            logger.info("点击搜寻");
            HtlListPage.DateAdultChildPage.DoSearch(driver);

            logger.info("进入详情页");
            HtlListPage.ToFirstHotelDetailPage(driver);

            logger.info("验证成人数");
            Assert.assertTrue(HtlDetailPage.getAdultNumber(driver).equals("2 成人"));

            logger.info("验证儿童数");
            Assert.assertTrue(HtlDetailPage.getChildNumber(driver).equals("1 小童"));
            logger.info("---验证C1309717	2成人1儿童（1岁），搜索酒店Success---");
            HtlDetailPage.backToList(driver);
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("---验证C1309717	2成人1儿童（1岁），搜索酒店失败---");
            e.printStackTrace();
        }
    }

    @Test(description = "By sxm：C1309720	列表页减少成人和儿童数量，酒店筛选", groups = {"Base"})
    public void DeleteAdultChildren() throws Exception {
        try {
            logger.info("---开始验证C1309720	列表页减少成人和儿童数量，酒店筛选---");
            String searchKey = "深圳";

            HtlHomePage.findElement(driver, HtlHomePage.hotel_main_search).click();
            WebElement input = HtlHomePage.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
            input.clear();
            input.sendKeys(searchKey);

            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(HtlHomePage.SearchEnginePage.tvTitle));
            ArrayList<WebElement> elements = HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);
            logger.info("查看联想的数据");
            elements.get(0).click();
            logger.info("打开成人儿童页面");
            HtlHomePage.ShowDateAdultChildPage(driver);

            //设置成人儿童数量
            logger.info("设置3成人1儿童");
            HtlListPage.AdultChildPage.SetAdultChildNumber(driver, 3, 1);
            HtlListPage.AdultChildPage.SetChildAge(driver, 1, 1);

            logger.info("点击确认");
            HtlListPage.AdultChildPage.Confirm(driver);
            HtlHomePage.DoSearch(driver);

            logger.info("点击日历框");
            PoBase.findElement(driver, HtlListPage.hotel_address);
            HtlListPage.ShowDateAdultChildPage(driver);
            HtlListPage.DateAdultChildPage.ShowAdultChildPage(driver);

            logger.info("设置2成人0儿童");
            HtlListPage.AdultChildPage.SetAdultChildNumber(driver, 2, 0);

            logger.info("点击确认");
            HtlListPage.AdultChildPage.Confirm(driver);
            logger.info("点击搜寻");
            HtlListPage.DateAdultChildPage.DoSearch(driver);

            logger.info("进入详情页");
            HtlListPage.ToFirstHotelDetailPage(driver);

            logger.info("验证成人数");
            Assert.assertTrue(HtlDetailPage.getAdultNumber(driver).equals("2 成人"));

            logger.info("验证儿童数");
            Assert.assertTrue(HtlDetailPage.getChildNumber(driver).equals("0 小童"));
            logger.info("---验证验证C1309720	列表页减少成人和儿童数量，酒店筛选Success---");
            HtlDetailPage.backToList(driver);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.info("---验证验证C1309720	列表页减少成人和儿童数量，酒店筛选Fail---");
        }
    }

    @AfterMethod
    public void afterMethod() {
        //		logger.info("---返回搜索首页---");
        //		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
        // 返回Trip首页
        //logger.info("---返回Trip首页---");
        //driver.findElementByClassName("android.widget.ImageButton").click();.
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        driver = initial.createAndroidReleaseDriver();
        logger.info("初始化成功");
        logger.info("进入酒店首页");
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
                .click();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
