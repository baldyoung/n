package n.baldyoung.messageManager;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 消息调用中心的实现类
 */
public class MessageManagementCenterImpl {
    // 当前调度中心的名称
    private String name;

    // 已注册的控制单元
    private Map<String, MessageOptionUnitImpl> optionUnitMap;

    // 线程池
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 参数初始化
     */
    private void init() {
        optionUnitMap = new ConcurrentHashMap(32);
        threadPoolExecutor = new ThreadPoolExecutor(
                5,
                50,
                120,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(124),
                new ThreadPoolExecutor.DiscardPolicy());
    }
    /**
     * 执行信息发送流程
     */
    private void sendMessage(List<MessageCell> messageCellList) {
        HashMap<String, List<MessageCell>> messageCellMap = new HashMap();
        List<MessageCell> list;
        for (MessageCell cell : messageCellList) {
            list = messageCellMap.get(cell.getReceiverId());
            if (null == list) {
                list = new LinkedList();
                messageCellMap.put(cell.getReceiverId(), list);
            }
            list.add(cell);
        }
        for (Map.Entry<String, List<MessageCell>> entry : messageCellMap.entrySet()) {
            if (null == entry.getKey()) {
                continue;
            }
            MessageOptionUnitImpl messageOptionUnit = optionUnitMap.get(entry.getKey());
            if (null != messageOptionUnit) {
                messageOptionUnit.onReceiveMessage(entry.getValue());
            }
        }
    }

    /**
     * 获取一个调度中心的实例
     * @param name
     * @return
     */
    public static MessageManagementCenterImpl getInstance(String name) {
        System.out.println("a");
        MessageManagementCenterImpl messageManagementCenter = new MessageManagementCenterImpl();
        System.out.println("b");
        messageManagementCenter.init();
        System.out.println("c");
        messageManagementCenter.name = name;
        System.out.println("d");
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
        optionUnitMap.put(unitId, messageOptionUnit);
        return messageOptionUnit;
    }

    /**
     * 将指导编号的控制器从控制中心注销
     * @param unitId
     */
    public void destroyOptionUnit(String unitId) {
        optionUnitMap.remove(unitId);
    }

    /**
     * 提交消息集到调动中心
     * @param messageCellList
     * @param isAsyn
     */
    public void submitMessage(List<MessageCell> messageCellList, boolean isAsyn) {
        if (isAsyn) {
            // 异步执行发送任务
            threadPoolExecutor.submit(() -> {
                sendMessage(messageCellList);
            });
            return;
        }
        // 同步执行发送任务
        sendMessage(messageCellList);
    }

    /**
     * 获取指定控制器下所有未读的消息
     * @return
     */
    public List<MessageCell> pullUnreadMessage(String unitId) {
        return null;
    }

    /**
     * 获取指定控制器下所有的消息
     * @return
     */
    public List<MessageCell> pullMessage(String unitId, int startIndex, int size) {
        return null;
    }

}
