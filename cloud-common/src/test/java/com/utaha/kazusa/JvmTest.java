package com.utaha.kazusa;

import com.utaha.kazusa.pojo.Master;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.test.context.SpringBootTest;

import static com.utaha.kazusa.common.util.PrintObjectHeader.printObjectHeader;

@SpringBootTest
public class JvmTest {

    @Test
    public void test() {
        Master master = new Master();
        System.out.println("====加锁前====");
        System.out.println(ClassLayout.parseInstance(master).toPrintable());
        System.out.println("====加锁后====");
        synchronized (master) {
            System.out.println(ClassLayout.parseInstance(master).toPrintable());
        }
    }
}
