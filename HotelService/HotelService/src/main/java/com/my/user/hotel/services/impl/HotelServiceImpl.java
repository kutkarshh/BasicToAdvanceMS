package com.my.user.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.user.hotel.entities.Hotel;
import com.my.user.hotel.exceptions.ResourceNotFoundException;
import com.my.user.hotel.repositories.HotelRepository;
import com.my.user.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Hotel with given id not found on server: " + hotelId));
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    // Custom Methods for Hotel Service
    @Override
    public Hotel getHotelByName(String hotelName) {
        return hotelRepository.getHotelByName(hotelName);
    }

    @Override
    public Hotel getHotelByLocation(String location) {
        return hotelRepository.getHotelByLocation(location);
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.getHotelById(hotelId);
    }

}
