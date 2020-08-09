package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    By profile=By.id("user_profile_icon");

    public static MainPage start(){
        Driver.start();
        return new MainPage();
    }

    public ProfilePage gotoProfile(){
        Driver.getCurrentDriver().findElement(profile).click();
        return new ProfilePage();

    }

    public SearchPage gotoSearch(){
        find(By.id("home_search")).click();
        return new SearchPage();
    }

    public MySelectPage gotoMySelect(){
        WebElement ele=find(By.xpath("//*[@text='自选']"));
        int num=0;
        while (num<10) {
            WebElement ele2=find(By.xpath("//*[@text='自选']"));
            if (ele!=null&&ele2!=null) {
                if (ele.getLocation().toString().equals(ele2.getLocation().toString()))break;
            }
            else {
//                System.out.println(ele.getLocation());
//                System.out.println(ele2.getLocation());
                num++;
                ele=ele2;
            }
        }
        ele.click();
        return new MySelectPage();
    }
}
