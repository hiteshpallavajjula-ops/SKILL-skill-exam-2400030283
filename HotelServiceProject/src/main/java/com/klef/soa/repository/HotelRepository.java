package com.klef.soa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	List<Hotel> findByCity(String city);
	
	List<Hotel> findByRating(Double rating);
	
	

}
