package n.baldyoung.UniqueCode;


import java.util.HashMap;
import java.util.Map;

/**
 * 一个可以生成唯一码的工厂模块，它用于保证，在该架构下每次生成的标识码都时唯一的，即使同时存在这多个工厂模块。
 */
public class UniqueCodeModule {
    //标记队列，记录
    private static Map<String, String> signQueue = new HashMap<>();
    private long initialValue;
    private String header, tail;


}
