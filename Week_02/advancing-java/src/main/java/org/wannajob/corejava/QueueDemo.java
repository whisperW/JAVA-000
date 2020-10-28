package org.wannajob.corejava;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueDemo {

    public static void main(String[] args) {
        Queue queue = new LinkedBlockingDeque();
        queue.add("abc");
        Object o = queue.poll();
//        Object remove = queue.remove();
        System.out.println(queue.size());


        while (true) {
            String [] strArray = new  String[10000];
        }
    }
}
