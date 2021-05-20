package cn.locusc.s.jvm.OOMerror.stack;

/**
 * @author jayChan
 * 栈内存 SOF错误
 * VM Args：-Xss128k
 * 2021/5/20
 *
 * 1.如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。
 * 2.如果虚拟机的栈内存允许动态扩展，当扩展栈容量无法申请到足够的内存时，将抛出
 * OutOfMemoryError异常。
 **/
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }


}
