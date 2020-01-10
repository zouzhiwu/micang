package com.priorityQueue.test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo1 {
	public static void main(String[] args) {
        //通过改造器指定排序规则
        PriorityQueue<Student> q = new PriorityQueue<Student>(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                //按照分数低到高，分数相等按名字
                if(o1.getScore() == o2.getScore()){
                    return o1.getName().compareTo(o2.getName());
                } else {
                	return o1.getScore() - o2.getScore();
                }
            }
        });
        //入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        while (!q.isEmpty()) {
			Student student = q.poll();
			System.out.println(String.format("name=%s score=%d", student.getName(), student.getScore()));
		}
    }
}
