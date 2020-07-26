package n.baldyoung.messageManager;

import java.util.List;

/**
 * Message Option Unit - interface
 * 消息操作单元
 */
public interface MessageOptionUnit {
    /**
     * 同步地将消息推送到消息控制中心
     */
    void pushMessage(List<MessageCell> messageCellList);

    /**
     * 异步地将消息推送到消息控制中心
     */
    void pushMessageAsynchronously(List<MessageCell> messageCellList);

    /**
     * 同步所有消息
     */
    List<MessageCell> pullMessage(int startIndex, int size);

    /**
     * 同步未读消息
     */
    List<MessageCell> pullUnreadMessage(int startIndex, int size);

    /**
     * 修改指定消息的状态
     * @return
     */
    boolean changeMessageStatus(List<MessageCell> messageCellList);
}
