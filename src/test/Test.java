

import n.baldyoung.UniqueCode.UniqueCodeModule;

import static java.lang.System.*;

public class Test {



    @org.junit.Test
    public void run1(){

        for(int i=0;i<100;i++){
            long a = System.nanoTime();
            long b = System.nanoTime();
            if(a == b) out.println("warning:"+a+"   "+b);
        }
    }
    @org.junit.Test
    public void run2(){
        UniqueCodeModule ucm = UniqueCodeModule.getInstance("id", "x");
        for(int i=0; i<20; i++){
            out.println(ucm.getUniqueCode());
        }
        out.println(UniqueCodeModule.getInstance("id", ""));
    }
}
