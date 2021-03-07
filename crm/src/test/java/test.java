import com.xc.crm.utils.MD5Util;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

    @Test
    public void test_time(){
        System.out.println("测试失效时间");

        String expire_time="2021-01-19 21:57:50";

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_time=simpleDateFormat.format(date);
        System.out.println(format_time);

        String pwd="123";
        pwd= MD5Util.getMD5(pwd);
        System.out.println(pwd);




    }


}
