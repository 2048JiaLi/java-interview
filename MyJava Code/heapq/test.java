package com.utils.heapq;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/9/17 13:08
 */
public class test {
    public static void main(String[] args) {
        List<MyT> my = new ArrayList<>();

        heapq.heappush(my, new MyT("11",15));
        heapq.heappush(my, new MyT("22",7));
        heapq.heappush(my, new MyT("33",9));
        heapq.heappush(my, new MyT("44",2));

        System.out.println(my.toString());
    }
}

class MyT implements Comparable<MyT> {
    String name;
    int num;

    public MyT(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public int compareTo(MyT o) {
        if (num < o.num){
            return -1;
        } else if (num == o.num){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "MyT{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
