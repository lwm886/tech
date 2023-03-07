package com.tech.synchronizer;

/**
 * @author lw
 * @since 2023-02-16
 */
public class LockOnObject {
    public static Object object=new Object();
    private Integer stock=10;
    public void decrStock(){
        synchronized (object){
            --stock;
            if(stock<=0){
                System.out.println("库存售罄");
                return;
            }
        }
    }
}
