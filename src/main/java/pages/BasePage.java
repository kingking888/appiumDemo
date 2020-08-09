package pages;


import driver.Driver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BasePage {

    static WebElement find(By locator){
        try{
            return Driver.getCurrentDriver().findElement(locator);
        }catch (Exception e){
            System.out.println("no fund");
            e.printStackTrace();
            try {
                FileUtils.copyFile(Driver.getCurrentDriver().getScreenshotAs(OutputType.FILE).getCanonicalFile(),
                        new File("test.png"));
                Driver.getCurrentDriver().findElement(text("下次再说")).click();
                return Driver.getCurrentDriver().findElement(locator);
            }catch (Exception e1){return  null;}
        }
    }

    static By locate(String locator){
        if(locator.matches("/.*")){
            return By.xpath(locator);
        }else{
            return By.id(locator);
        }
    }

    static By text(String content){
        return By.xpath("//*[@text='"+ content + "']");
    }

    public void testToast() throws InterruptedException {
        Driver.getCurrentDriver().findElementByXPath("//*[@text='Views']").click();
        Driver.getCurrentDriver().findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" +
                        "new UiSelector().text(\"Popup Menu\").instance(0));").click();
        Driver.getCurrentDriver().findElementByXPath("//*[contains(@text, 'Make')]").click();
        Driver.getCurrentDriver().findElementByXPath("//*[@text='Search']").click();
        //System.out.println(driver.findElementByClassName("android.widget.Toast").getText());
        System.out.println(Driver.getCurrentDriver().findElementByXPath("//*[@class='android.widget.Toast']").getText());
//        System.out.println(Driver.getCurrentDriver().getPageSource());

    }

    public void BaseInfoAPI(){
        AndroidDriver<AndroidElement> driver=Driver.getCurrentDriver();
        String path=null;
        try {
            File dir=new File(".");
            path=dir.getCanonicalPath()+"\\src\\main\\resources\\data";
            System.out.println(path);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        boolean isInstall= driver.isAppInstalled("com.xueqiu.android");//判断应用是否安装
        if(isInstall){
            boolean isRemove= driver.removeApp("com.xueqiu.android");//卸载app,Android 是包名，IOS是bundleID
            if(isRemove){
                driver.installApp(path+"\\com.xueqiu.android_11.14.1_199.apk");//安装app
            }
        }
        else {
            driver.installApp(path+"\\com.xueqiu.android_11.14.1_199.apk");//安装app
        }
        driver.closeApp();//关闭应用
        driver.launchApp();//启动应用
        driver.resetApp();//重启应用

        String pageActivity= driver.currentActivity();//获取当前界面的activity
        System.out.println(pageActivity);
        String pageSource=driver.getPageSource();//获取当前界面的树状结构图
        System.out.println(pageSource);

        try {
            FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),new File(path+"\\screentest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.startActivity(new Activity("com.android.messaging",
                ".ui.conversationlist.ConversationListActivity"));//启动其他应用
        //int status=(AndroidDriver)driver.getNetworkConnection().value;
//        new NetworkConnection(false,true,false);
//        driver.getConnection().isWiFiEnabled();
//        driver.setConnection(new ConnectionState());

        File file=new File(path+"\\test.txt");
//        String context=null;
//        try {
//            context=FileUtils.readFileToString(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] data = Base64.encodeBase64(context.getBytes());
        try {
            driver.pushFile("sdcard/test11.txt",file);

            byte[] resultdate=driver.pullFile("sdcard/test11.txt");
            FileOutputStream filewrite=new FileOutputStream(path+"\\testresult.txt",true);
            filewrite.write(resultdate);
            filewrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte GetFindType(String type){
        if (type=="id" || type=="ids"){
            return 0;
        }
        else if (type=="class" || type=="classs"){
            return 1;
        }
        else if (type=="xpath" || type=="xpaths"){
            return 2;
        }
        else if (type=="name" || type=="names"){
            return 3;
        }
        else if(type=="accessibility" || type=="accessibilitys"){
            return 4;
        }
        else if(type=="uiautomator" || type=="uiautomators"){
            return 5;
        }
        return -1;
    }

    public By GetByText(String content,byte type){
        switch (type){
            case 0:
                return By.id(content);
            case 1:
                return By.className(content);
            case 2:
                return By.xpath(content);
            case 3:
               return By.name(content);
        }
        return null;
    }

    public AndroidElement BaseFindElementAPI(String content, byte type){
        AndroidDriver<AndroidElement> driver=Driver.getCurrentDriver();
        AndroidElement ele=null;
        switch (type){
            case 0:
                ele=driver.findElementById(content);
                break;
            case 1:
                ele=driver.findElementByClassName(content);
                break;
            case 2:
                ele=driver.findElementByXPath(content);
                break;
            case 3:
                ele=driver.findElementByName(content);
                break;
            case 4:
                ele=driver.findElementByAccessibilityId(content);//content-desc定位
                break;
            case 5:
                ele=driver.findElementByAndroidUIAutomator(content);
                break;
        }
        return ele;
    }

    public List<AndroidElement> BaseFindElementsAPI(String content,byte type){
        AndroidDriver<AndroidElement> driver=Driver.getCurrentDriver();
        List<AndroidElement> eles=null;
        switch (type){
            case 0:
                eles=driver.findElementsById(content);
                break;
            case 1:
                eles=driver.findElementsByClassName(content);
                break;
            case 2:
                eles=driver.findElementsByXPath(content);
                break;
            case 3:
                eles=driver.findElementsByName(content);
                break;
            case 4:
                eles=driver.findElementsByAccessibilityId(content);
                break;
            case 5:
                eles=driver.findElementsByAndroidUIAutomator(content);
                break;
        }
        return eles;
    }

    public AndroidElement BaseFindElementAPI(By by){
        return Driver.getCurrentDriver().findElement(by);
    }

    public List<AndroidElement> BaseFindElementsAPI(By by){
        return Driver.getCurrentDriver().findElements(by);
    }

    public void BaseOperateAPI(){
        AndroidDriver<AndroidElement> driver=Driver.getCurrentDriver();
//        driver.shake();
//        driver.click();

        //5.0.0之后已废除
//        driver.tap([(300,500)],10);
        // 将屏屏幕从(startX,startY)滑动到(endX,endY)间隔during(ms)//5.0.0之后已废除
        //driver.swipe(startX,startY, endX,endY, during(ms));
        //按住A(startX,startY)点快速移动到B(endX,endY)点
        //driver.flick(startX,startY,endX,endY)
        //在元素上执行缩小操作，percent和steps可不写，当不写时表示默认
        //driver.pinch(element,percent,steps);
        //在元素上执行放大操作,percent和steps可不写，当不写时表示默认
        //driver.zoom(element,percent,steps);
        //driver.longPress();//长按
         //driver.keyevent("4");//返回键操作
        driver.close();//关闭当前窗口
        driver.quit();//退出脚本运行并关闭每个相关的窗口连接
        driver.manage().window().getSize().getHeight();//获取窗口的高
        driver.manage().window().getSize().getWidth();//获取窗口的宽
        try {
            Thread.sleep(6000);//休眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.openNotifications();//打开通知栏界面
        driver.lockDevice();//锁屏
        driver.unlockDevice();//解锁
        driver.isDeviceLocked();//是否锁屏

//        driver.execute();

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);//隐式等待，全局等待60s

        WebDriverWait wait = new WebDriverWait(driver, 10,2000);
        WebElement element =
                wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));

//        WebElement element2 =
//                wait.until(ExpectedConditions<WebElement>(){
//                    @Override
//                    public WebElement apply(AndroidDriver d){
//                        return driver.findElement(By.xpath("//*[@text='ttt']"));
//            }
//        });

        new TouchAction(driver).press(PointOption.point(0,308)).release().perform();
        new TouchAction(driver).longPress(PointOption.point(0,308)).perform().release();
        new TouchAction(driver).tap(PointOption.point(0,308)).perform().release();


    }

    public void zoom(WebElement element) {
        AndroidDriver<AndroidElement> driver=Driver.getCurrentDriver();
        int centerX = element.getLocation().getX()+element.getSize().getWidth()/2;
        int centerY = element.getLocation().getY()+element.getSize().getHeight()/2;
        TouchAction left = new TouchAction(driver)
                .press(PointOption.point(centerX-50,centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(element.getSize().getWidth()/4,element.getSize().getHeight()/4))
                .release();
        TouchAction right = new TouchAction(driver)
                .press(PointOption.point(centerX+50, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(element.getSize().getWidth()/4,element.getSize().getHeight()/4))
                .release();
        new MultiTouchAction(driver).add(left).add(right).perform();
    }

    public void swipeUp(){
        AndroidDriver<AndroidElement> driver=Driver.getCurrentDriver();
        int width=driver.manage().window().getSize().getWidth();
        int higth=driver.manage().window().getSize().getHeight();
        new TouchAction(driver).press(PointOption.point(width/2,higth*4/5)).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).
                moveTo(PointOption.point(width/2,higth*4)).release().perform();
    }

    public void BaseOperateAPI(AndroidElement ele){
        ele.click();
        ele.sendKeys("sendkeysnnnn");
        ele.isDisplayed();
        ele.isEnabled();
        ele.isSelected();
    }

    static String UiScrollable(String uiSelector){
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
                +uiSelector+".instance(0));";
    }

    public AndroidElement scrollTo(String text){
        String uiScrollables=UiScrollable("new UiSelector().descriptionContains(\""
                +text+ "\")") +
                UiScrollable("new UiSelector().textContains(\""+text+"\")");
        return (AndroidElement)Driver.getCurrentDriver().
                findElement(MobileBy.AndroidUIAutomator(uiScrollables));
    }

    public AndroidElement scrollToExact(String text){
        String uiScrollables=UiScrollable("new UiSelector().description(\""
                +text+ "\")") +
                UiScrollable("new UiSelector().text(\""+text+"\")");
        return (AndroidElement)Driver.getCurrentDriver().
                findElement(MobileBy.AndroidUIAutomator(uiScrollables));
    }
}
