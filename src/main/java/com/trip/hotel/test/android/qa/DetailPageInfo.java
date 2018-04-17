package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.android.po.HtlDetailPage;
import com.trip.hotel.test.android.po.HtlHomePage;
import com.trip.hotel.test.android.po.HtlListPage;
import com.trip.hotel.test.android.po.PoBase;
import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DetailPageInfo extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver ;
    int timeOutInSeconds = 60;
    

  @BeforeClass
	public void beforeClass() throws MalformedURLException {
		//driver = initial.createAndroidReleaseDriver();
	}
  
  @Test(description = "By yulf: C1309731	点评页各元素展示", groups = { "Base" })
  public void VerifyDescription() throws Exception{
		logger.info("搜上海");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.clear();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.sendKeys("上海");
		ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
		logger.info(destinationlist.size());
		destinationlist.get(0).click();

		logger.info("进入上海列表页");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		/*
	  logger.info("进入酒店首页");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
		
		logger.info("搜上海");
		PoBase.findElement(driver, HtlHomePage.hotel_main_search).click();
		PoBase.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword).clear();
		PoBase.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword).sendKeys("上海");
		PoBase.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle).get(0).click();
		
		logger.info("进入酒店列表");
		PoBase.findElement(driver, HtlHomePage.search_button).click();*/

		logger.info("进入酒店详情页");
		HtlListPage.ToFirstHotelDetailPage(driver);
		
		logger.info("进入酒店点评页");
		HtlDetailPage.toReviews(driver);
		
		try {
  		logger.info("---开始验证C1309731	点评页各元素展示---");
  		
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.hotel_detail_comment_cleanliness_progress));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.hotel_detail_comment_facility_progress));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.hotel_detail_comment_grade));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.hotel_detail_comment_header_excellent));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.hotel_detail_comment_location_progress));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.hotel_detail_comment_service_progress));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.left_filter_tv));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.right_filter_tv));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.tag));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.translate));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.tv_content));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.tv_date));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.tv_room_and_date));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.tv_user_desc));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.tv_user_nickname));
  		Assert.assertTrue(HtlDetailPage.ReviewPage.IsExist(driver, HtlDetailPage.ReviewPage.tv_user_score));
  		
  		logger.info("---验证C1309746	酒店描述的展示Pass---");
		
  		
		} catch (Exception e) {
			logger.info("---验证C1309746	酒店描述的展示Fail---");
		}
		HtlDetailPage.ReviewPage.Back(driver);
  }
  
  @Test(description = "By sxm: C1309729	收藏功能", groups = {"Base"})
  public void Share() throws Exception{
	  try {
		String keyword = "北京";
		logger.info("开始搜索");
	    HtlHomePage.DoSearch(driver, keyword);
	    
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
  }

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		driver = initial.createAndroidReleaseDriver();
		logger.info("初始化成功");
		logger.info("进入酒店首页");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();

	}
  @AfterMethod
	public void afterTest() {
  	//logger.info("---返回列表页---");
	     //new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("ivBack"))).click();
	     //logger.info("---返回搜索首页---");
	     //new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
	     //返回Trip首页	     
	     //logger.info("---返回Trip首页---");
	     //driver.findElementByClassName("android.widget.ImageButton").click();
	     driver.quit();
	}	
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}