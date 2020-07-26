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
     */
    private Integer statusFlag;
    /**
     * 消息类型标识
     */
    private Integer typeFlag;
    /**
     * 发送方编号
     */
    private Integer senderId;
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
    private Integer receiverId;
    /**
     * 接收方验收时间
     */
    private Date receptionDate;

}
