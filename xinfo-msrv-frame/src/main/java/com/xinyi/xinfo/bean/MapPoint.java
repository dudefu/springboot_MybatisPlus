package com.xinyi.xinfo.bean;

public class MapPoint {

	public String x;
	public String y;
	public String centPoints;

	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getCentPoints() {
		return centPoints;
	}
	public void setCentPoints(String centPoints) {
		this.centPoints = centPoints;
	}
	
	@Override
	public String toString() {
		return "MapPoint [x=" + x + ", y=" + y + ", centPoints=" + centPoints + "]";
	}

}
