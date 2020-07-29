package n.baldyoung.messageManager;

import java.util.Date;

/**
 * Message Cell
 * 消息单元
 * 消息传递的载体
 */
public class MessageCell {
    /**
     * 消息状态标识
     * 0 : 待发送
     * 1 : 已发送
     * 2 : 发送失败
     * 3 : 发送成功，待读状态
     * 4 : 已读状态
     */
    private Integer statusFlag;
    /**
     * 消息类型标识
     */
    private Integer typeFlag;
    /**
     * 发送方编号
     */
    private String senderId;
    /**
     * 发送时间
     */
    private Date sendDate;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;

    /**
     * 接收方编号
     */
    private String receiverId;
    /**
     * 接收方验收时间
     */
    private Date receptionDate;

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Integer getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }
}
