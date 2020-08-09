package pages;

import org.openqa.selenium.By;

import java.util.ArrayList;

public class ManagePage extends BasePage {

    public boolean removeSelect(ArrayList<String> removelist){
        for(String rn:removelist){
            String xpath="//*[@text='"+rn+"']/../../*[@resource-id='com.xueqiu.android:id/check']";
            find(By.xpath(xpath)).click();
        }
        find(By.xpath("//*[@text='取消关注']")).click();
        find(By.xpath("//*[@text='确定']")).click();
        return true;
    }

    public void finishMange(){
        find(By.xpath("//*[@text='完成']")).click();
    }

}
