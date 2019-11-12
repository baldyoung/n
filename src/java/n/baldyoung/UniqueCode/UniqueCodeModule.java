package n.baldyoung.UniqueCode;


import java.util.HashMap;
import java.util.Map;

/**
 * 一个可以生成唯一标识的工厂模块，它用于保证，在该架构下每次生成的标识码都是唯一的，即使同时存在多个工厂模块。
 */
public class UniqueCodeModule {
    //标记队列，记录所有已经注册的工厂模块
    private static Map<String, String> SignQueue = new HashMap<>();
    private long initialValue;
    private String header, tail;
    /**
     * 向标记队列尝试新增一个工厂模块，其为线程安全的调用
     * @param tHeader
     * @param tTail
     * @return false：已存在；true：注册成功
     */
    private static boolean addModule(String tHeader, String tTail){
        if(null == tHeader || null == tTail) return false;
        synchronized(SignQueue) {
            if (null != SignQueue.get(tHeader)) return false;
            SignQueue.put(tHeader, tTail);
        }
        return true;
    }
    /**
     * 尝试删除一个指定的工厂模块，其为线程安全的调用
     * @param tHeader
     */
    private static void delModule(String tHeader){
        if(null != tHeader)
            synchronized (SignQueue) {
                SignQueue.remove(tHeader);
            }
    }

    /**
     * 生产工厂模块的方法，其为线程安全的调用
     * @param tHeader
     * @param tTail
     * @return
     */
    public static UniqueCodeModule getInstance(String tHeader, String tTail){
        if(addModule(tHeader, tTail))
            return new UniqueCodeModule(tHeader, tTail);
        return null;
    }


    /**
     * 工厂模块的私有构造函数
     * @param tHeader
     * @param tTail
     */
    private UniqueCodeModule(String tHeader, String tTail){
        this.header = tHeader;
        this.tail = tTail;
        this.initialValue = System.nanoTime();
    }

    /**
     * 获取下一个递进值，其为线程安全的调用
     * @return
     */
    private long nextValue(){
        long theNext;
        synchronized(this.header){
            theNext = this.initialValue++;
        }
        return theNext;
    }

    /**
     * 获取当前工厂模块所生产的一个唯一的标识，其为线程安全调用
     * @return
     */
    public String getUniqueCode(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.header);
        builder.append(nextValue());
        builder.append(this.tail);
        return builder.toString();
    }


}
