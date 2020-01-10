package com.common.entity;

public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Point point) {
		if (point == null) {
			return false;
		} else {
			return this.x == point.x && this.y == point.y;
		}
	}
	
	public void reset(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
        return String.format("[x=%s y=%s]", this.x, this.y);
    }
}
