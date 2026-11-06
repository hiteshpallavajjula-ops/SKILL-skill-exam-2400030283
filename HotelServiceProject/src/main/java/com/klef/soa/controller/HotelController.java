package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.klef.soa.entity.Hotel;
import com.klef.soa.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService service;
	
	@GetMapping("/")
	public String demo() {
		return "Hotel Demo Project";
	}
	@PostMapping("/add")
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel h)
	{
		Hotel hotel = service.addhoHotel(h);
		return ResponseEntity.status(201).body(hotel);
	}
	
	@GetMapping("/displayall")
	public ResponseEntity<List<Hotel>> displayAllHotel(){
		List<Hotel> hotel = service.displayAllHotels();
		return ResponseEntity.status(200).body(hotel);
	}
	
	@GetMapping("/display")
	public ResponseEntity<?> displayHotelById(@RequestParam Long id){
		Hotel hotel =service.displayHotelById(id);
		
		if (hotel !=null) {
			return ResponseEntity.status(200).body(hotel);
		}else {
			return ResponseEntity.status(404).body("hotel ID not found");
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateHotel(@RequestBody Hotel hotel)
	{
		Hotel h = service.updateHotel(hotel);

		if (h != null)
		{
			return ResponseEntity.ok(h);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel ID Not Found");
		}
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteHotelById(@PathVariable Long id)
	{
		String message = service.deleteHotelById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/displaybyCity/{city}")
	public ResponseEntity<List<Hotel>> displayHotelByCity(@PathVariable String city)
	{
		List<Hotel> hotel = service.displayHotelByCity(city);
		return ResponseEntity.status(200).body(hotel);
	}
	
	// Doctor Count
	@GetMapping("/rating/{rating}")
	public ResponseEntity<List<Hotel>> displayHotelByRating(@PathVariable double rating)
	{
		List<Hotel> hotels = service.displayHotelByRating(rating);
		
		return ResponseEntity.status(200).body(hotels);
	}
	

}
