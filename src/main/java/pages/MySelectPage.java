package pages;

import driver.Driver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class MySelectPage extends BasePage {


    public boolean addMySelect(String name){
        find(By.id("com.xueqiu.android:id/action_create_cube")).click();
        SearchPage search=new SearchPage();
        search.search(name).addSelected();
        find(By.xpath("//*[@text='取消']")).click();
        return true;
    }

    public ArrayList<String> getAll(){
        ArrayList<String> array=new ArrayList<String>();
        for(WebElement e: Driver.getCurrentDriver().findElements(By.id("com.xueqiu.android:id/portfolio_stockName"))){
            array.add(e.getText());
        }
        return array;
    }

    public boolean removeSelect(ArrayList<String> removenames){
        find(By.id("com.xueqiu.android:id/edit_group")).click();
        ManagePage managePage=new ManagePage();
        managePage.removeSelect(removenames);
        managePage.finishMange();
        return true;
    }
}
