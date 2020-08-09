package pages;

import driver.Driver;
import org.openqa.selenium.By;
import sun.rmi.runtime.Log;

public class ProfilePage {

    By login=By.xpath("//*[@text='点击登录']");
    public void gotoWeixinLogin(){

    }

    public void gotoPictureLogin(){

    }

    public LoginPage gotoPasswordLogin(){
        Driver.getCurrentDriver().findElement(login).click();
        return new LoginPage();
    }
}
