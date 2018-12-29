package com.hotel.sample.hotel.controller;

import java.util.Comparator;

public class Hotel implements Comparator<Hotel> {

	private int hotelId;
	private String hotelName;
	
	private Hotel() {
		// TODO Auto-generated constructor stub
	}

	Hotel(int id, String name) {
		hotelId = id;
		hotelName = name;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + "]";
	}

	@Override
	public int hashCode() {
		return this.getHotelName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Hotel) {
			if(!((Hotel) obj).hotelName.equals(this.hotelName)) {
				return false;
			}
			if(!(((Hotel) obj).hotelId == this.hotelId)) {
				return false;
			}
			//return ((Hotel) obj).hashCode()== (this.hashCode());
			return true;
		}
		return false;
	}

	@Override
	public int compare(Hotel o1, Hotel o2) {
		return o1.hotelName.compareTo(o2.hotelName);
	}

}
