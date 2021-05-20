package cn.locusc.s.jvm.OOMerror.methodarea;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jayChan
 * 方法区 OOM错误
 * 2021/5/20
 **/
public class JavaMethodAreaOOM {

    /**
     * JDK7中运行  VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
     * Caused by: java.lang.OutOfMemoryError: PermGen space
     *  at java.lang.ClassLoader.defineClass1(Native Method)
     *  at java.lang.ClassLoader.defineClassCond(ClassLoader.java:632)
     *  at java.lang.ClassLoader.defineClass(ClassLoader.java:616)
     *  ... 8 more
     *
     * JDK8中运行 VM Args：-XX:MaxMetaspaceSize=8m
     * Caused by: java.lang.OutOfMemoryError: Metaspace
     * 	at java.lang.ClassLoader.defineClass1(Native Method)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
     * 	... 11 more
     **/
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {

    }

}
