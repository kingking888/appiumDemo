package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static AndroidDriver<AndroidElement> driver;
    public static void start(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

//        GlobalConfig config=GlobalConfig.load("/data/globalConfig.yaml");
//        for(String key: config.appium.capabilities.keySet()){
//            desiredCapabilities.setCapability(key, config.appium.capabilities.get(key));
//        }
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "CLB0218403000759");
        desiredCapabilities.setCapability("appPackage", "cn.futu.trader");
        desiredCapabilities.setCapability("appActivity", "cn.futu.trader.launch.activity.LaunchActivity");
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("noReset", true);
        URL remoteUrl = null;
        try {
//            remoteUrl = new URL(config.appium.url);
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
    }

    public static AndroidDriver<AndroidElement> getCurrentDriver(){
        return driver;
    }
}
