package com.common.entity;

public class Location {
	public float x;
	public float y;
	
	public Location() {
		
	}
	
	public Location(int x, int y) {
		this.x = (short)x;
		this.y = (short)y;
	}
	
	public Location(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Location(Location location) {
		this.x = location.x;
		this.y = location.y;
	}
	
	public boolean equals(Location loc) {
		if (loc == null) {
			return false;
		} else {
			return this.x == loc.x && this.y == loc.y;
		}
	}
	
	@Override
	public String toString() {
        return String.format("[x=%s y=%s]", this.x, this.y);
    }
}
