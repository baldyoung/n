

import n.baldyoung.FileDataOption.FileDataSaveModule;
import n.baldyoung.SendEmail.SendEmailModule;
import n.baldyoung.UniqueCode.UniqueCodeModule;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.*;


/**
 * 测试模块
 * 依赖于：junit.jar(测试版本4.12）、hamcrest-core.jar(测试版本1.3）
 */
public class Test {



    @org.junit.Test
    public void run1(){

        for(int i=0;i<100;i++){
            long a = System.nanoTime();
            long b = System.nanoTime();
            if(a == b) out.println("warning:"+a+"   "+b);
        }
    }
    @org.junit.Test
    public void run2(){
        UniqueCodeModule ucm = UniqueCodeModule.getInstance("id", "x");
        for(int i=0; i<20; i++){
            out.println(ucm.getUniqueCode());
        }
        out.println(UniqueCodeModule.getInstance("id", ""));
    }

    @org.junit.Test
    public void run3() throws Exception{
        List<FileDataSaveModule> fdsmList;
        List<InputStream> inputList = new LinkedList<>();
        for(int i=1;i<=5; i++){
            String filePathName = "C:\\Users\\Administrator\\Desktop"+"\\timg ("+i+").jpg";
            inputList.add(new FileInputStream(filePathName));

        }
        fdsmList = FileDataSaveModule.getListByInputStreams(inputList, "C:\\Users\\Administrator\\Desktop/testarea/", ".jpg");
        out.println("FileDataSaveModule test action is start ...");
        for(FileDataSaveModule cell : fdsmList){
            out.println("save file("+cell.getPathName()+"):"+cell.save());
        }
        out.println("test is end");
    }

    @org.junit.Test
    public void run4() throws Exception {
        String[] addresses = new String[]{"baldyoung@163.com", "791178881@qq.com"};
        SendEmailModule obj = SendEmailModule.getInstance("baldyoung@126.com", "china123");

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
}
