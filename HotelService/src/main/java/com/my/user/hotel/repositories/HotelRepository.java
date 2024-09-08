package com.my.user.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.user.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    // Custom Methods for Hotel Repository

    // Get Hotel By ID
    public Hotel getHotelById(String hotelId);

    // Get Hotel By Location
    public Hotel getHotelByLocation(String location);

    // Get Hotel By Name
    public Hotel getHotelByName(String hotelName);

}
