package cn.locusc.s.jvm.OOMerror;

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

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

}
