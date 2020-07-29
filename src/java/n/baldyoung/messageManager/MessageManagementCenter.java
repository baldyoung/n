package n.baldyoung.messageManager;

import java.util.List;

/**
 * Message Management Center - interface
 * 消息控制中心 - 接口
 */
public interface MessageManagementCenter {

    /**
     * 推送消息
     * @param messageCellList
     * @param isAsyn 是否异步执行
     */
    void pushMessage(List<MessageCell> messageCellList, boolean isAsyn);

    /**
     * 同步未读消息
     */
    List<MessageCell> pullUnreadMessage();

    /**
     * 同步所有消息
     * @return
     */
    List<MessageCell> pullMessage();




}
