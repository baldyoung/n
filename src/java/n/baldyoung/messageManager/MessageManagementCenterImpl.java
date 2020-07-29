package n.baldyoung.messageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息调用中心的实现类
 */
public class MessageManagementCenterImpl {
    /**
     * 当前调度中心的名称
     */
    private String name;

    // private MessageDao messageDao;

    private Map<String, MessageOptionUnitImpl> optionUnitMap;

    // 线程池

    public void pushMessage(List<MessageCell> messageCellList, boolean isAsyn) {

    }

    public List<MessageCell> pullUnreadMessage() {
        return null;
    }

    public List<MessageCell> pullMessage() {
        return null;
    }

    /**
     * 获取一个调度中心的实例
     * @param name
     * @return
     */
    public static MessageManagementCenterImpl getInstance(String name) {
        MessageManagementCenterImpl messageManagementCenter = new MessageManagementCenterImpl();
        messageManagementCenter.name = name;
        messageManagementCenter.optionUnitMap = new HashMap();
        return messageManagementCenter;
    }

    /**
     * 在当前的调度中心中注册一个控制器
     * @param unitId
     * @return
     * @throws Exception
     */
    public MessageOptionUnitImpl registerOptionUnit(String unitId) throws Exception {
        if (null != optionUnitMap.get(unitId)) {
            throw new Exception("编号已存在");
        }
        MessageOptionUnitImpl messageOptionUnit = new MessageOptionUnitImpl();
        messageOptionUnit.setUnitId(unitId);
        messageOptionUnit.setMessageManagementCenter(this);
        return messageOptionUnit;
    }
}
