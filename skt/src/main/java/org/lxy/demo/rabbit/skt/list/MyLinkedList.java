package org.lxy.demo.rabbit.skt.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuxinyi
 * @date 2019-04-29
 */
@Slf4j
public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = this.head;
    }

    public boolean insertHead(T t) {
        Node<T> node = new Node<>(t);
        node.setNext(this.head.getNext());
        this.head.setNext(node);
        return true;
    }

    public boolean addTail(T t) {
        Node node = new Node(t);
        if (this.head.getNext() == null) {
            this.head.setNext(node);
            this.tail = node;
            return true;
        } else {
            this.tail.setNext(node);
            this.tail = node;
            return false;
        }
    }

    public boolean del(T data) {
        if (this.head.getNext() == null) {
            return false;
        }
        Node<T> node = new Node<>(data);
        Node<T> temp = this.head;
        while (null != temp.getNext()) {
            if (temp.getNext().getData().equals(node.getData())) {
                if (null == temp.getNext().getNext()) {
                    tail = temp;
                } else {
                    temp.setNext(temp.getNext().getNext());
                }
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public Node<T> get(T data) {
        if (this.head.getNext() == null) {
            return null;
        }
        Node<T> temp = head;
        while (temp.getNext() != null) {
            if (temp.getNext().getData().equals(data)) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    public void reverse() {
        // 少于2个元素不用反转
        if (this.head.getNext() == null || null == this.head.getNext().getNext()) {
            return;
        }
        // 处理尾指针
        Node first = head.getNext();
        tail = first;

        // 从第二个元素开始反转
        Node cur = first.getNext();
        // 尾指针指向空
        first.setNext(null);
        // 防止第二个元素为NULL引发异常
        Node next = null;
        while (null != cur) {
            // 保存next
            next = cur.getNext();
            // 当前元素插入头部
            cur.setNext(head.getNext());
            head.setNext(cur);
            // cur右移
            cur = next;
        }

    }

    // 递归反转
    public void reverseRecursive() {
        // 没有元素不用反转
        if (this.head.getNext() == null) {
            return;
        }
        Node next = head.getNext().getNext();
        head.getNext().setNext(null);
        tail = head.getNext();
        doRecursiveReverse(head, next);
    }

    private void doRecursiveReverse(Node head, Node tail) {
        if (null == tail) {
            return;
        }
        Node next = tail.getNext();
        tail.setNext(head.getNext());
        head.setNext(tail);
        doRecursiveReverse(head, next);
    }


    public void print() {
        Node temp = head;
        if (null == temp.getNext()) {
            log.info("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");

        while (null != temp.getNext()) {
            sb.append(temp.getNext().getData());
            sb.append(",");
            temp = temp.getNext();
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        log.info(sb.toString());
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

    }
}
