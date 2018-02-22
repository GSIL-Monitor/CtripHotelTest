package test.apptest.hotel;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;
import service.impl.HotelHomePageInitialImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.testng.annotations.DataProvider;
import common.frame.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
public class MyHotelOrder extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
		logger.info("初始化成功，准备登陆");
		appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆
	}
	
	@Test(description = "By sxm : C1309650 酒店订单/历史订单与订单详情页的跳转联动（快速预订用户和ctrip用户）", groups={"Base"})
	public void myHotelOrderTrip() throws Exception{
		logger.info("---进入酒店首页---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		logger.info("---进入我的订单页面---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_my_order"))).click();
		Thread.sleep(5000);
		WebElement List = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.className("android.support.v7.widget.RecyclerView")));
		//WebElement List = driver.findElement(By.className("android.support.v7.widget.RecyclerView"));
		WebElement hotel = List.findElements(By.className("android.widget.LinearLayout")).get(0);
		String state = hotel.findElement(By.id("tv_order_state")).getText();
		String price = hotel.findElement(By.id("view_hotel_price")).getText();
		String name = hotel.findElement(By.id("tv_hotel_name")).getText();
		String room = hotel.findElement(By.id("tv_hotel_info")).getText();
		String night = hotel.findElement(By.id("tv_check_info")).getText();
		logger.info("---进入订单详情页---");
		hotel.click();
		try {
			logger.info("---开始验证酒店订单与订单详情页的跳转联动（ctrip账户）---");
			String hotelname = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_hotel_name"))).getText();
			String hotelprice = driver.findElement(By.id("tv_price_pay")).getText();
			String hotelstate = driver.findElement(By.id("tv_order_status")).getText();
			String roomname = driver.findElement(By.id("tv_room_name")).getText();
			String roomnight = driver.findElement(By.id("tv_date_night_room")).getText();
			logger.info("---断言订单状态: "+state+"---"+hotelstate);
			Assert.assertEquals(state,hotelstate);
			logger.info("---断言订单价格: "+price+"---"+hotelprice);
			Assert.assertEquals(price,hotelprice);
			logger.info("---断言酒店名称: "+name+"---"+hotelname);
			Assert.assertEquals(name,hotelname);
			logger.info("---断言房型名称: "+room+"---"+roomname);
			Assert.assertEquals(room,roomname);
			//logger.info("---断言间夜数目: "+night+"---"+roomnight);
			//Assert.assertTrue(night.contains(roomnight));
			logger.info("---验证酒店订单与订单详情页的跳转联动（ctrip账户）成功---");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("---验证酒店订单与订单详情页的跳转联动（ctrip账户）失败---");
		}
		
		logger.info("---返回到酒店订单列表页---");
		driver.findElement(By.className("android.widget.ImageButton")).click();
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("toolbar"))).
		findElement(By.className("android.support.v7.widget.LinearLayoutCompat")).click();
		logger.info("---进入订单历史记录列表---");
		WebElement hisList = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.className("android.support.v7.widget.RecyclerView")));
		//WebElement List = driver.findElement(By.className("android.support.v7.widget.RecyclerView"));
		WebElement hishotel = hisList.findElements(By.className("android.widget.LinearLayout")).get(0);
		String hisstate = hishotel.findElement(By.id("tv_order_state")).getText();
		String hisprice = hishotel.findElement(By.id("view_hotel_price")).getText();
		String hisname = hishotel.findElement(By.id("tv_hotel_name")).getText();
		String hisroom = hishotel.findElement(By.id("tv_hotel_info")).getText();
		String hisnight = hishotel.findElement(By.id("tv_check_info")).getText();
		logger.info("---进入订单详情页---");
		hishotel.click();
		try {
			logger.info("---开始验证酒店历史订单详情页的跳转联动（ctrip账户）---");
			String hotelname = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_hotel_name"))).getText();
			String hotelprice = driver.findElement(By.id("tv_price_pay")).getText();
			String hotelstate = driver.findElement(By.id("tv_order_status")).getText();
			String roomname = driver.findElement(By.id("tv_room_name")).getText();
			String roomnight = driver.findElement(By.id("tv_date_night_room")).getText();
			logger.info("---断言订单状态: "+hisstate+"---"+hotelstate);
			Assert.assertEquals(hisstate,hotelstate);
			logger.info("---断言订单价格: "+hisprice+"---"+hotelprice);
			Assert.assertEquals(hisprice,hotelprice);
			logger.info("---断言酒店名称: "+hisname+"---"+hotelname);
			Assert.assertEquals(hishotel,hotelname);
			logger.info("---断言房型名称: "+hisroom+"---"+roomname);
			Assert.assertEquals(hisroom,roomname);
			logger.info("---验证酒店历史订单与订单详情页的跳转联动（ctrip账户）成功---");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("---验证酒店历史订单与订单详情页的跳转联动（ctrip账户）失败---");
		}
	}
	
	@AfterMethod
	public void afterTest() {
//	     logger.info("---返回搜索首页---");
//	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
//	     //返回Trip首页	     
//	     logger.info("---返回Trip首页---");
//	     driver.findElementByClassName("android.widget.ImageButton").click();
	}	
	
 	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}