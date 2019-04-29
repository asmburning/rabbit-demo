package org.lxy.demo.rabbit.skt.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author liuxinyi
 * @date 2019-04-29
 */
@Slf4j
public class TestList {

    @Test
    public void test2() {

        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.print();
        list.addTail(3);
        list.addTail(4);
        list.addTail(5);

        list.print();
        list.reverseRecursive();
        log.info("after reverseRecursive");
        list.print();

        list.addTail(2);
        list.del(4);
        log.info("after addTail 2 , del 4 reverse");
        list.reverse();
        list.print();

        log.info("after insertHead");
        list.insertHead(1);
        list.print();

        log.info("after addTail");
        list.addTail(8);
        list.print();

    }
}
