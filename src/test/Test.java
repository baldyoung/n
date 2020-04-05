

import n.baldyoung.DateTimeOption.DateStringOption;
import n.baldyoung.Encryption.EncryptionModule;
import n.baldyoung.FileDataOption.FileDataSaveModule;
import n.baldyoung.SendEmail.SendEmailModule;
import n.baldyoung.UniqueCode.UniqueCodeModule;

import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.*;
import static n.baldyoung.DateTimeOption.DateStringOption.*;

/**
 * 测试模块
 * 依赖于：junit.jar(测试版本4.12）、hamcrest-core.jar(测试版本1.3）
 */
public class Test {


    @org.junit.Test
    public void run1() {

        for (int i = 0; i < 100; i++) {
            long a = System.nanoTime();
            long b = System.nanoTime();
            if (a == b) out.println("warning:" + a + "   " + b);
        }
    }

    @org.junit.Test
    public void run2() {
        UniqueCodeModule ucm = UniqueCodeModule.getInstance("id", "x");
        for (int i = 0; i < 20; i++) {
            out.println(ucm.getUniqueCode());
        }
        out.println(UniqueCodeModule.getInstance("id", ""));
    }

    @org.junit.Test
    public void run3() throws Exception {
        List<FileDataSaveModule> fdsmList;
        List<InputStream> inputList = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            String filePathName = "C:\\Users\\Administrator\\Desktop" + "\\timg (" + i + ").jpg";
            inputList.add(new FileInputStream(filePathName));

        }
        fdsmList = FileDataSaveModule.getListByInputStreams(inputList, "C:\\Users\\Administrator\\Desktop/testarea/", ".jpg");
        out.println("FileDataSaveModule test action is start ...");
        for (FileDataSaveModule cell : fdsmList) {
            out.println("save file(" + cell.getPathName() + "):" + cell.save());
        }
        out.println("test is end");
    }

    @org.junit.Test
    public void run4() throws Exception {
        String[] addresses = new String[]{"*****@163.com", "*******@qq.com"};
        SendEmailModule obj = SendEmailModule.getInstance("emailAccount@126.com", "password");

        out.println("带附件邮件发送测试");
        MimeMessage msg = obj.createMessage("Test Email", "<center><h1>test msg</h1></center><div style='width:100%: text-align:center; background:gray; color:green; '>http://baldyoung.com</div>", new String[]{"C:\\Users\\Administrator\\Desktop\\test\\temp1.jpg", "C:\\Users\\Administrator\\Desktop\\test\\temp1.jpg"});
        obj.insertAddresseeToMsg(msg, addresses);
        obj.sendMessage(msg);

        out.println("纯文本邮件发送测试");
        msg = obj.createMessage("纯文本邮件测试", "纯文本内容");
        obj.insertAddresseeToMsg(msg, addresses);
        obj.sendMessage(msg);

        out.println("邮件默认发送测试");
        msg = obj.createMessage("默认邮件", "默认邮件用户是发送者本身");
        obj.sendMessage(msg);
    }

    @org.junit.Test
    public void run5() {
        EncryptionModule obj = EncryptionModule.getInstance("我的玛雅");
        EncryptionModule obj2 = EncryptionModule.getInstance("我的玛雅2");
        String testStr = "中午吃什么呢？楼下的猫旁边的便利店不错！";
        byte[] targetData = testStr.getBytes();
        out.println("\nsourceData:");
        for (byte temp : targetData) {
            out.print(temp + " ");
        }
        targetData = obj.encrypt(testStr.getBytes());
        out.println("\ntargetData:");
        for (byte temp : targetData) {
            out.print(temp + " ");
        }
        // --------------------------------------------------------------- 解密尝试
        // ---------------------------------------- 错误密码
        byte[] targetData2 = obj2.decode(targetData);
        out.println("\ndecodeData（错误的密码）:");
        for (byte temp : targetData2) {
            out.print(temp + " ");
        }
        out.println("\nsourceStr:" + new String(targetData2));
        // ----------------------------------------- 正确密码
        byte[] targetData3 = obj.decode(targetData);
        out.println("\ndecodeData（正确的密码）:");
        for (byte temp : targetData3) {
            out.print(temp + " ");
        }
        out.println("\nsourceStr:" + new String(targetData3));

    }

    @org.junit.Test
    public void run6() throws  Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateStr = simpleDateFormat.format(new Date());
        out.println(dateStr);
        Date date = simpleDateFormat.parse("2019-12-26 00:00:00");
        //date = DateStringOption.plusDay(date, -1);
        out.println(simpleDateFormat.format(date));
        //out.println(DateStringOption.dayOfMonth(date));
    }

    @org.junit.Test
    public void run7() throws Exception {
        Calendar calendar = Calendar.getInstance();
        out.println(calendar.get(Calendar.DAY_OF_MONTH)+","+calendar.get(Calendar.MONDAY)+", "+calendar.get(Calendar.YEAR));
        out.println(calendar.get(Calendar.HOUR)+", "+calendar.get(Calendar.MINUTE)+", "+calendar.get(Calendar.SECOND));

        Date date = parseDate("2019-12-32 25:00:00");
        out.println(defaultFormat(date));

        for(int i=0; false && i<10000; i++) {
            new Thread(() -> {
                for(int j=0; j<30; j++) {
                    //out.println("->");
                    try {
                        getYearOfDate(new Date());
                        getMonthOfDate(new Date());
                        getDayOfDate(new Date());
                    }catch (Exception e) {
                        out.println(e);
                    }
                }
            }).start();
        }


    }


}
