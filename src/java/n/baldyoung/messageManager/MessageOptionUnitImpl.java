package n.baldyoung.messageManager;

import java.util.Date;
import java.util.List;

/**
 * 消息控制单元
 * 需要通过向消息控制中心注册后获得
 */
public class MessageOptionUnitImpl {
    // 所属的消息控制中心
    private MessageManagementCenterImpl messageManagementCenter;
    // 控制器编号
    private String unitId;

    public String getUnitId() {
        return unitId;
    }

    /**
     * 当调度中心传来消息时被调用
     * @param messageCellList
     */
    public void onReceiveMessage(List<MessageCell> messageCellList) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.isDaemon());
        for(MessageCell cell : messageCellList) {
            System.out.println(cell.getContent());
        }
    }

    /**
     * 将编辑好的消息推送到消息控制中心，该操作将会同步执行
      */
    public void pushMessage(List<MessageCell> messageCellList) {
        if (null == messageCellList || 0 == messageCellList.size()) {
            return;
        }
        Date currentDate = new Date();
        for (MessageCell cell : messageCellList) {
            cell.setSenderId(unitId);
            cell.setSendDate(currentDate);
            cell.setStatusFlag(0);
        }
        messageManagementCenter.submitMessage(messageCellList, false);
    }

    /**
     * 将编辑好的消息推送到消息控制中心，该操作将会异步执行
     * @param messageCellList
     */
    public void pushMessageAsynchronously(List<MessageCell> messageCellList) {
        if (null == messageCellList || 0 == messageCellList.size()) {
            return;
        }
        Date currentDate = new Date();
        for (MessageCell cell : messageCellList) {
            cell.setSenderId(unitId);
            cell.setSendDate(currentDate);
            cell.setStatusFlag(0);
        }
        messageManagementCenter.submitMessage(messageCellList, true);
    }

    /**
     * 获取所有消息，通过分页的方式
     * @param startIndex
     * @param size
     * @return
     */
    public List<MessageCell> pullMessage(int startIndex, int size) {
        return null;
    }

    /**
     * 获取当前控制器下所有的未读消息
     * @return
     */
    public List<MessageCell> pullUnreadMessage() {
        return null;
    }

    /**
     * 变更指定消息的状态
     * @param messageCellList
     * @return
     */
    public boolean changeMessageStatus(List<MessageCell> messageCellList) {
        return false;
    }


}
