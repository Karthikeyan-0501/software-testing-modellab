package com.modellab.modellab;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v121.input.model.DragData;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstCryTests {

    WebDriver driver;
    String URL;
    XSSFWorkbook workbook;
    ExtentReports reports;
    WebDriverWait wait;
    XSSFSheet sheet;
    @BeforeTest
    public void setUp() throws IOException
    {
        driver=new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        URL="https://www.firstcry.com/";
        String path="C:\\Users\\karth.KARTHIKEYAN\\OneDrive\\Desktop\\Software Testing Model Lab\\modellab\\Excel Sheet\\Data.xlsx";
        workbook=new XSSFWorkbook(path);
        sheet=workbook.getSheet("Sheet1");
        reports=new ExtentReports();
        String p="C:\\Users\\karth.KARTHIKEYAN\\OneDrive\\Desktop\\Software Testing Model Lab\\modellab\\Report\\report.html";
        ExtentSparkReporter spark=new ExtentSparkReporter(p);
        reports.attachReporter(spark);
        spark.config().setDocumentTitle("First Cry");
        spark.config().setTheme(Theme.DARK);
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority = 1)
    public void testCase1()
    {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement search=driver.findElement(By.xpath("//*[@id=\"search_box\"]"));
        search.click();
        String text=sheet.getRow(1).getCell(0).getStringCellValue();
        search.sendKeys(text);
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/span")).click();
        String k=driver.findElement(By.xpath("//*[@id=\"cpidParent\"]/div[2]/h1")).getText();
        ExtentTest test1=reports.createTest("TestCase 1");
        if(k.contains("KIDS TOYS"))
        {
            test1.log(Status.PASS, "Yeah,String presents");
        }
        else{
            test1.log(Status.FAIL, "No,String not presents");
        }
    }
    @Test(priority = 2)
    public void test2()
    {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement search=driver.findElement(By.xpath("//*[@id=\"search_box\"]"));
        search.click();
        String text=sheet.getRow(2).getCell(0).getStringCellValue();
        search.sendKeys(text);
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]/a/span")).click();
        String k=driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
        ExtentTest test=reports.createTest("TetsCase 2");
        if(k.contains("Ethnic Wear"))
        {
            test.log(Status.PASS, "Yeah,String presents");
        }
        else{
            test.log(Status.FAIL, "No,String not presents");
        }
    }
    @Test(priority = 3)
    public void test3()
    {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement search=driver.findElement(By.xpath("//*[@id=\"search_box\"]"));
        search.click();
        String text=sheet.getRow(3).getCell(0).getStringCellValue();
        search.sendKeys(text);
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"maindiv\"]/div[1]/div/div[1]/div[2]")).click();
        driver.switchTo().frame(1);
        String k="Balloon Table Holder";
        ExtentTest test=reports.createTest("TestCase 3");
        if(k.contains("Balloon Table Holder"))
        {
            
            test.log(Status.PASS, "Yeah,String presents");
        }
        else{
            test.log(Status.FAIL, "No,String not presents");
        }
    }
    @AfterTest
    public void AfterTest()
    {
        driver.quit();
        reports.flush();
    }
}
