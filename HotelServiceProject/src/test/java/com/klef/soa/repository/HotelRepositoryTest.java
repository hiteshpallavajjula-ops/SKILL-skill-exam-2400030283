package com.klef.soa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.klef.soa.entity.Hotel;

@DataJpaTest
class HotelRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel1;
    private Hotel hotel2;

    @BeforeEach
    void setUp() {
        hotelRepository.deleteAll();

        hotel1 = new Hotel();
        hotel1.setHotelName("Grand Palace");
        hotel1.setCity("Hyderabad");
        hotel1.setRating(4.5);
        hotel1.setPricePerNight(3500.0);
        hotel1.setRoomsAvailabe("10");
        hotel1 = hotelRepository.save(hotel1);

        hotel2 = new Hotel();
        hotel2.setHotelName("Sea View");
        hotel2.setCity("Vizag");
        hotel2.setRating(4.0);
        hotel2.setPricePerNight(2800.0);
        hotel2.setRoomsAvailabe("5");
        hotel2 = hotelRepository.save(hotel2);

        hotelRepository.flush();
    }

    // Test 1: Find all records
    @Test
    void testFindAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        assertNotNull(hotels);
        assertEquals(2, hotels.size());
    }

    // Test 2: Find by existing and non-existing ID
    @Test
    void testFindById() {
        Optional<Hotel> found = hotelRepository.findById(hotel1.getId());
        assertTrue(found.isPresent());
        assertEquals(hotel1.getId(), found.get().getId());

        Optional<Hotel> missing = hotelRepository.findById(999999L);
        assertFalse(missing.isPresent());
    }

    // Test 3: Derived query method
    @Test
    void testFindByCity() {
        List<Hotel> hotels = hotelRepository.findByCity("Hyderabad");
        assertEquals(1, hotels.size());
        assertEquals("Grand Palace", hotels.get(0).getHotelName());
        assertEquals("Hyderabad", hotels.get(0).getCity());

        List<Hotel> ratedHotels = hotelRepository.findByRating(4.5);
        assertEquals(1, ratedHotels.size());
        assertEquals("Grand Palace", ratedHotels.get(0).getHotelName());
    }

    // Test 4: Count records
    @Test
    void testCount() {
        assertEquals(2L, hotelRepository.count());
    }

    // Test 5: Verify required entity properties
    @Test
    void testEntityProperties() {
        Hotel hotel = hotelRepository.findById(hotel1.getId()).orElseThrow();
        assertNotNull(hotel.getHotelName());
        assertNotNull(hotel.getCity());
        assertNotNull(hotel.getRoomsAvailabe());
        assertEquals(4.5, hotel.getRating());
        assertEquals(3500.0, hotel.getPricePerNight());
    }
}
