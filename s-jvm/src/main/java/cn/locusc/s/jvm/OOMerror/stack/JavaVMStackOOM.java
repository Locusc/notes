package cn.locusc.s.jvm.OOMerror.stack;

/**
 * @author jayChan
 * 栈内存 OOM错误
 * -Xss2M(减少栈内存, 避免系统假死)
 * 谨慎测试 32位win系统可能导致假死
 * 2021/5/20
 **/
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }

}
