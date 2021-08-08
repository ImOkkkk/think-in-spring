package org.liuwy.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean垃圾回收(GC)示例
 * 
 * @author ImOkkkk
 * @date 2021/8/8 21:11
 * @since 1.0
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        applicationContext.close();
        Thread.sleep(5000L);
        // 强制触发GC
        System.gc();
        Thread.sleep(5000L);
    }
}
