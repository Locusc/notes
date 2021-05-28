package cn.locusc.s.jvm.OOMerror.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jayChan
 * 堆溢出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 2021/5/20
 **/
public class HeapOOM {

    static class OOMObject {

    }

    /**
     * JDK 1.8运行
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:265)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * 	at java.util.ArrayList.add(ArrayList.java:462)
     * 	at cn.locusc.s.jvm.OOMerror.heap.HeapOOM.main(HeapOOM.java:21)
     *
     * 第一步首先应确认内存中导致OOM的对象是否是必要的
     * 也就是需要判断是了内存泄漏（Memory Leak）还是内存溢出（Memory Overflow）
     *
     * 内存泄漏: 通过工具查看泄漏对象到GC Roots的引用链，找到泄漏对象是通过怎
     * 样的引用路径、与哪些GC Roots相关联，才导致垃圾收集器无法回收它们，根据泄漏对象的类型信息
     * 以及它到GC Roots引用链的信息，一般可以比较准确地定位到这些对象创建的位置，进而找出产生内
     * 存泄漏的代码的具体位置
     *
     * 内存溢出: 就应当检查Java虚拟机的堆参数（-Xmx与-Xms）设置，与机器的内存对比，看看是否还有向上调整的空间。再从代码上检查
     * 是否存在某些对象生命周期过长、持有状态时间过长、存储结构设计不合理等情况，尽量减少程序运
     * 行期的内存消耗。
     **/
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }

}
