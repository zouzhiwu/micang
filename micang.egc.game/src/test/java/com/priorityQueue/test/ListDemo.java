package com.priorityQueue.test;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
	public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("dafei", 20));
        studentList.add(new Student("will", 17));
        studentList.add(new Student("setf", 30));
        System.out.println(studentList.contains(new Student("setf", 30)));
    }
}
