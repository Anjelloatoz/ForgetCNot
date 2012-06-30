package com.svgonbatik.forgetcnot;

import java.util.ArrayList;

public class ParkSite {
	private String name;
	private String postcode;
	private String address;
	private int latitudeE6;
	private int longitudeE6;
	private String telephone;
	private String email;
	private int spaces = 0;
	private String icon_url;
	private String opening_hours;
	int[] hours;
	double[] charges;
	
	ParkSite(String name, String postcode, String address, int latitude, int longitude, String telephone, String email, int spaces, String icon_url, String opening_hours, ArrayList<TimeSlot> time_slots){
		this.name = name;
		this.postcode = postcode;
		this.address = address;
		this.latitudeE6 = latitude;
		this.longitudeE6 = longitude;
		this.telephone = telephone;
		this.email = email;
		this.spaces = spaces;
		this.icon_url = icon_url;
		this.opening_hours = opening_hours;
		hours = new int[time_slots.size()];
		charges = new double[time_slots.size()];
		
		for(int i = 0; i < time_slots.size(); i++){
			hours[i] = time_slots.get(i).getHours();
			charges[i] = time_slots.get(i).getCharge();
		}
		System.out.println("Created a ParkSite with "+this.getSlotLength()+" slots.");
	}
	
	public String getName(){
		return this.name;
	}
	public String getPostCode(){
		return this.postcode;
	}
	public String getAddress(){
		return this.address;
	}
	public int getLatitude(){
		return this.latitudeE6;
	}
	public int getLongitude(){
		return this.longitudeE6;
	}
	public String getTelephone(){
		return this.telephone;
	}
	public String getEmail(){
		return this.email;
	}
	public int getSpaces(){
		return this.spaces;
	}
	public String getIconUrl(){
		return this.icon_url;
	}
	public String getOpeningHours(){
		return this.opening_hours;
	}
	public int getSlotLength(){
		return this.hours.length;
	}
}
