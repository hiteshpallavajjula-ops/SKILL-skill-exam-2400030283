
package com.klef.soa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import com.klef.soa.entity.Hotel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class HotelRepositoryTest {

    @Autowired
    private HotelRepository repository;

    private Hotel hotel1;
    private Hotel hotel2;

    @BeforeEach
    void setUp()
    {
        repository.deleteAll();

        hotel1 = new Hotel();
        hotel1.setHotelName("Grand Palace");
        hotel1.setCity("Hyderabad");
        hotel1.setRating(4.5);
        hotel1.setPricePerNight(3500.0);
        hotel1.setRoomsAvailabe("10");

        hotel1 = repository.save(hotel1);

        hotel2 = new Hotel();
        hotel2.setHotelName("Sea View");
        hotel2.setCity("Vizag");
        hotel2.setRating(4.0);
        hotel2.setPricePerNight(2800.0);
        hotel2.setRoomsAvailabe("5");

        hotel2 = repository.save(hotel2);

        repository.flush();
    }

    // Test 1: Find All Hotels
    @Test
    void testFindAllHotels()
    {
        List<Hotel> hotels = repository.findAll();

        assertNotNull(hotels);
        assertEquals(2, hotels.size());

        System.out.println("Total Hotels: " + hotels.size());
    }

    // Test 2: Find Hotel By ID
    @Test
    void testFindHotelById()
    {
        Optional<Hotel> hotel =
                repository.findById(hotel1.getId());

        assertTrue(hotel.isPresent());
        assertEquals(hotel1.getId(), hotel.get().getId());

        Optional<Hotel> missing =
                repository.findById(999999L);

        assertFalse(missing.isPresent());
    }

    // Test 3: Find Hotel By Derived Query
    @Test
    void testFindHotelByCity()
    {
        List<Hotel> hotels =
                repository.findByCity("Hyderabad");

        assertNotNull(hotels);
        assertFalse(hotels.isEmpty());

        assertEquals("Grand Palace", hotels.get(0).getHotelName());
        assertEquals("Hyderabad", hotels.get(0).getCity());

        List<Hotel> ratedHotels =
                repository.findByRating(4.5);

        assertEquals(1, ratedHotels.size());
    }

    // Test 4: Total Hotels Count
    @Test
    void testTotalHotelsCount()
    {
        long count = repository.count();

        assertEquals(2L, count);
    }

    // Test 5: Verify Hotel Details
    @Test
    void testHotelDetails()
    {
        Optional<Hotel> hotel =
                repository.findById(hotel1.getId());

        assertTrue(hotel.isPresent());

        Hotel foundHotel = hotel.get();

        assertNotNull(foundHotel.getHotelName());
        assertNotNull(foundHotel.getCity());
        assertNotNull(foundHotel.getRating());
        assertNotNull(foundHotel.getPricePerNight());
        assertNotNull(foundHotel.getRoomsAvailabe());
    }

}
