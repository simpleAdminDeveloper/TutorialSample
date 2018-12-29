package com.hotel.sample.hotel.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.stereotype.Component;

@Component
public class HotelService {

	Set<Hotel> hotelset;
	List<Hotel> hotelList;
	Set<Integer> integerset;
	volatile int hotelId = 1;
	HotelService() {
		hotelList = new ArrayList<>();
		hotelList.add(new Hotel(1, "Taj"));
		 hotelList.add(new Hotel(2, "Vedanta"));
		 hotelList.add(new Hotel(3, "Samrat"));
		 hotelList.add(new Hotel(4, "Ashoka"));
		 hotelList.add(new Hotel(5, "Oberoy"));
//		hotelList.add(new Hotel(1, "Taj"));
//		integerset = new HashSet<>();
//		integerset.add(new Integer(1));
//		integerset.add(new Integer(2));
//		integerset.add(new Integer(2));
//		integerset.add(new Integer(3));
//		integerset.add(new Integer(1));
//		integerset.add(new Integer(4));

	}

	private static ExecutorService pool = Executors.newFixedThreadPool(10);
	
	
	public List<Hotel> getHotels() throws InterruptedException, ExecutionException {
		
		class HotelOnFly implements Callable<Hotel>{
			@Override
			public Hotel call() throws Exception {
				return new Hotel(hotelId, "hotel"+ (hotelId++));
			}
			
		}
		List<Hotel> hotelList = new ArrayList<>();
		List<HotelOnFly> hotelOnFlyList = new ArrayList<>();
		for(int i=0;i<5;i++) {
			hotelOnFlyList.add(new HotelOnFly());
		}
		List<Future<Hotel>> hotelListFuture = pool.invokeAll(hotelOnFlyList);
		//hotelListFuture.forEach(hotelFut -> hotelList.addAll(hotelFut));
		for(Future<Hotel> futureHotel : hotelListFuture) {
			hotelList.add(futureHotel.get());
		}
		//pool.shutdown();
		return hotelList;
	}

//	public static void main(String[] args) {
//		HotelService hotelService = new HotelService();
//		System.out.println(hotelService.hotelList);
//		// System.out.println(hotelService.integerset);
//	}
}
