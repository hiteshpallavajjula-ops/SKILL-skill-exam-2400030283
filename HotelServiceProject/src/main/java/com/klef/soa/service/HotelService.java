package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Hotel;

public interface HotelService {
	
	Hotel addhoHotel(Hotel h);
	Hotel updateHotel(Hotel h);
	List<Hotel> displayAllHotels();
	Hotel displayHotelById(Long id);
	String deleteHotelById(Long id);
	List<Hotel> displayHotelByCity(String city);
	List<Hotel> displayHotelByRating(Double rating);
	

}
