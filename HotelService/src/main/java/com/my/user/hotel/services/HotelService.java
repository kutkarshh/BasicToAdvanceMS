package com.my.user.hotel.services;

import java.util.List;

import com.my.user.hotel.entities.Hotel;

public interface HotelService {

    // Create Hotel
    Hotel createHotel(Hotel hotel);

    // Get Hotel
    Hotel getHotel(String hotelId);

    // Update Hotel
    Hotel updateHotel(Hotel hotel);

    // Delete Hotel
    void deleteHotel(String hotelId);

    // Get All Hotel
    List<Hotel> getAllHotel();

    // Get Hotel By Name
    Hotel getHotelByName(String hotelName);

    // Get Hotel By Location
    Hotel getHotelByLocation(String location);

    // Get Hotel By ID
    Hotel getHotelById(String hotelId);

}
