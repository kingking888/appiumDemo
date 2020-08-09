import org.apache.tools.ant.taskdefs.Sleep;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.MainPage;
import sun.applet.Main;
import driver.Driver;

import java.io.File;
import java.io.FileOutputStream;

public class BaseTest {

    static MainPage mainPage;

    @Test
    public void baseInfoTest(){
        mainPage= MainPage.start();
        mainPage.BaseInfoAPI();
    }

    @Test
    public void futupagesource() throws InterruptedException {
        mainPage=MainPage.start();
        Driver.getCurrentDriver().findElement(By.xpath("//*[@text='资讯']")).click();
        Thread.sleep(1000);
        Driver.getCurrentDriver().findElement(By.xpath("//*[@text='要闻']")).click();
        Thread.sleep(10000);
        saveAsTxt(Driver.getCurrentDriver().getPageSource(),"pagesource","2");
    }

    void saveAsTxt(String homepage_str, String base_path, String id) {
        File dir = new File(base_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String file_path = base_path + id + ".homepage";
        try {
            FileOutputStream fos = new FileOutputStream(new File(file_path));
            fos.write(homepage_str.getBytes());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(file_path);
    }
}
