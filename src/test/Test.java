

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
}
