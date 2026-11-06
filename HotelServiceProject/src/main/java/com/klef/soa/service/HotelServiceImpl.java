package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Hotel;
import com.klef.soa.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository repo;
	
	@Override
	public Hotel addhoHotel(Hotel h) {
		return repo.save(h);
	}

	@Override
	public Hotel updateHotel(Hotel h) {
		Optional<Hotel> optional = repo.findById(h.getId());
		if(optional.isPresent()) {
			Hotel hotel = optional.get();
			hotel.setHotelName(h.getHotelName());
			hotel.setCity(h.getCity());
			hotel.setRating(h.getRating());
			hotel.setPricePerNight(h.getPricePerNight());
			hotel.setRoomsAvailabe(h.getRoomsAvailabe());
			
			return repo.save(hotel);
		}
		else {
			return null;
		}
		
	}

	@Override
	public List<Hotel> displayAllHotels() {
		return repo.findAll();
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hotel displayHotelById(Long id) {
		return repo.findById(id).orElse(null);
		// TODO Auto-generated method stub
	
	}

	@Override
	public String deleteHotelById(Long id) {
		boolean flag= repo.existsById(id);
		if(flag) {
			repo.deleteById(id);
			return "Hotel deleted successfully";
		}
		else {
			return "Hotel ID Not Found";
		}
		
		
		
	}

	@Override
	public List<Hotel> displayHotelByCity(String city) {
		
		return repo.findByCity(city);
	}

	@Override
	public List<Hotel> displayHotelByRating(Double rating) {
		// TODO Auto-generated method stub
		return repo.findByRating(rating);
	}

}
