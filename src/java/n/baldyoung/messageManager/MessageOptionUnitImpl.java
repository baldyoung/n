package n.baldyoung.messageManager;

import java.util.Date;
import java.util.List;

public class MessageOptionUnitImpl {
    private MessageManagementCenterImpl messageManagementCenter;
    private String unitId;

    public MessageManagementCenterImpl getMessageManagementCenter() {
        return messageManagementCenter;
    }

    public void setMessageManagementCenter(MessageManagementCenterImpl messageManagementCenter) {
        this.messageManagementCenter = messageManagementCenter;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

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
        messageManagementCenter.pushMessage(messageCellList, false);
    }

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
        messageManagementCenter.pushMessage(messageCellList, true);
    }

    public List<MessageCell> pullMessage(int startIndex, int size) {
        return null;
    }

    public List<MessageCell> pullUnreadMessage() {
        return null;
    }

    public boolean changeMessageStatus(List<MessageCell> messageCellList) {
        return false;
    }

    public void onReceiveMessage(List<MessageCell> messageCellList) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.isDaemon());
        for(MessageCell cell : messageCellList) {
            System.out.println(cell.getContent());
        }
    }
}
