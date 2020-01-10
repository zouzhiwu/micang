package com.priorityQueue.test;

public class Student {
	private String name;  //名字
    private int score;    //分数
    
    public Student(String name, int score) {
    	this.name = name;
    	this.score = score;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean equals(Student s) {
		return s.name.equals(name) && s.score == score;
	}
}
