package com.svgonbatik.forgetcnot;

public class TimeSlot {
	private int hours;
	private double charge;
	
	TimeSlot(int hours, double charge){
		this.hours = hours;
		this.charge = charge;
	}
	
	public int getHours(){
		return this.hours;
	}
	
	public double getCharge(){
		return this.charge;
	}
}
