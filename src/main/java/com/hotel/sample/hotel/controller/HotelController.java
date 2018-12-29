package com.hotel.sample.hotel.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HotelController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HotelService hotelService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send("hotels", msg);
	}

	@GetMapping("/hotels")
	public List<Hotel> getHotels() throws InterruptedException, ExecutionException {
		logger.debug("In getHotels");
		Object hotelL = hotelService.getHotels();
		List<Hotel> hotelList = null;
		if (hotelL instanceof List) {
			hotelList = (List<Hotel>) hotelL;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonString = objectMapper.writeValueAsString(hotelList);
			sendMessage(jsonString);
		} catch (JsonProcessingException e) {
		}
		logger.info("list of hotels are : " + hotelList.toString());
		return hotelList;
	}

}
