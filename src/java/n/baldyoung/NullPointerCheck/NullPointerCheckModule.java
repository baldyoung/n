package n.baldyoung.NullPointerCheck;

public class NullPointerCheckModule {

    /**
     * 检查给定的对象集中是否有引用为null的对象
     * @param args
     * @return true 给定的对象集中含有null对象 false 给定的对象集中不含有null对象
     */
    public static boolean isAnyEmpty(Object... args){
        boolean result = false;
        for(Object temp : args){
            if(null == temp){
                result = true;
                break;
            }
        }
        return result;
    }


}
