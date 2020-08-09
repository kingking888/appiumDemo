import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.MySelectPage;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;


public class MySelectTest {

    static MainPage mainPage;
    static MySelectPage selectPage;

    @BeforeAll
    static void beforeAll(){
        mainPage=MainPage.start();
        selectPage=mainPage.gotoMySelect();
    }

    @Test
    void addMySelectTest(){
        boolean result=selectPage.addMySelect("pdd");
        assertThat(result, equalTo(true));
    }


    @Test
    void removeSelectTest(){
        ArrayList<String> array=new ArrayList<String>();
        array.add("拼多多");
        boolean result=selectPage.removeSelect(array);
        assertThat(result, equalTo(true));
    }
}
