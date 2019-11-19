package n.baldyoung.SendEmail;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送模块，提供邮件发送服务。
 * 支持发送带文本、附件的邮件（可以通过网络路径实现图片添加需求）
 * 依赖：javax.mail.jar
 * @author baldyoung
 */


public class SendEmailModule {

    // 发件人地址
    private String senderAddress;
    // 发件人账户名
    private String senderAccount;
    // 发件人账户密码
    private String senderPassword;
    // 用于邮件发送的session对象
    public Session session;

    /**
     * 私有的构造函数
     * @param sAddress
     * @param sAccount
     * @param sPassword
     */
    private SendEmailModule (String sAddress, String sAccount, String sPassword) {
        this.senderAddress = sAddress;
        this.senderAccount = sAccount;
        this.senderPassword = sPassword;
    }

    /**
     * 获取邮件发送模块的实例
     * @param sAccount
     * @param sPassword
     * @return
     */
    public static SendEmailModule getInstance(String sAccount, String sPassword) throws Exception {
        SendEmailModule obj = new SendEmailModule(sAccount, sAccount, sPassword);
        obj.session = Session.getInstance(obj.getDefaultPropertis());
        if (null == obj.session) throw new Exception("创建session失败");
        return obj;
    }

    /**
     * 获取连接邮件服务器的默认参数设置,需要提前配置好正确的发件人地址。
     * @return
     */
    public Properties getDefaultPropertis() {
        Properties props = new Properties();
        // 设置用户认证方式
        props.setProperty("mail.smtp.auth", "true");
        // 设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置smtp邮件服务器地址
        String serviceAddress = null;
        if (null != this.senderAddress) {
           serviceAddress = this.senderAddress.substring(this.senderAddress.lastIndexOf("@") + 1, this.senderAddress.lastIndexOf("."));
        }
        StringBuilder builder = new StringBuilder();
        builder.append("smtp.");
        builder.append(serviceAddress);
        builder.append(".com");
        props.setProperty("mail.smtp.host", builder.toString());
        return props;
    }
    /**
     * 将给定邮件发送给指定的接收者集合
     * @param msg
     * @param target
     * @throws MessagingException
     */
    private void sendMessageTo(Message msg, Address[] target) throws MessagingException {
        Transport transport = this.session.getTransport();
        transport.connect(this.senderAccount, this.senderPassword);
        transport.sendMessage(msg, target);
        transport.close();
    }

    /**
     * 创建带有附件的邮件实例，附件的指定通过文件路径来实现。同时邮件也带有文本内容。
     *
     * @param title
     * @param content
     * @param filePathNames
     * @param sendDate
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public MimeMessage createMessage(String title, String content, String[] filePathNames, Date sendDate) throws MessagingException, UnsupportedEncodingException {
        // 创建邮件实例
        MimeMessage msg = new MimeMessage(this.session);
        // 设置发件人地址
        msg.setFrom(new InternetAddress(this.senderAddress));
        // 抄送一份给自己，必须这样，否则如126邮件服务器可能拒绝群发操作
        msg.setRecipient(MimeMessage.RecipientType.CC,new InternetAddress(this.senderAddress));
        // 设置邮件主题
        msg.setSubject(title);
        // 创建一个混合节点，将下面的节点放入里头
        MimeMultipart mm = new MimeMultipart();
        // 打包文本内容
        // 创建一个节点，用于存储文本内容
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(content, "text/html;charset=UTF-8");
        mm.addBodyPart(textPart);
        // 打包附件内容
        for(String filePathName : filePathNames) {
            // 创建一个节点，用于存储附件数据
            MimeBodyPart enclosurePart = new MimeBodyPart();
            // 读取本地文件
            DataHandler dataHandler = new DataHandler(new FileDataSource(filePathName));
            // 将附件数据添加到节点
            enclosurePart.setDataHandler(dataHandler);
            // 设置附件的文件名（需要编码）
            enclosurePart.setFileName(MimeUtility.encodeText(dataHandler.getName()));
            mm.addBodyPart(enclosurePart);
        }
        // 设置为混合关系
        mm.setSubType("mixed");
        msg.setContent(mm);
        // 设置发送时间
        msg.setSentDate(sendDate);
        return msg;
    }
    public MimeMessage createMessage(String title, String content, String[] filePathNames) throws MessagingException, UnsupportedEncodingException{
        return createMessage(title, content, filePathNames, new Date());
    }
    /**
     * 创建纯文本邮件实例，设置标题并填充要发送的文本内容。
     * 其实内容也可以是html标签，一般的邮件客户端都支持渲染html标签。这也意味着，这个邮件未必就是纯文本的，可以通过添加网络图片路径来实现添加图片的效果。
     * @param title
     * @param content
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public MimeMessage createMessage(String title, String content) throws MessagingException, UnsupportedEncodingException {
        return createMessage(title, content, new String[]{});
    }


    /**
     * 为给定的邮件实例添加接收者
     * @param msg
     * @param userAddresses
     * @throws MessagingException
     */
    public void insertAddresseeToMsg(Message msg, String[] userAddresses) throws MessagingException {
        Address[] addresses = new Address[userAddresses.length];
        for(int i = 0; i < userAddresses.length; i++) {
            addresses[i] = new InternetAddress(userAddresses[i]);
        }
        msg.addRecipients(MimeMessage.RecipientType.TO, addresses);
    }

    /**
     * 将给定的邮件实例发送出去
     * @param msg
     * @throws MessagingException
     */
    public void sendMessage(Message msg) throws MessagingException {
        sendMessageTo(msg, msg.getAllRecipients());
    }


}

