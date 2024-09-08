package com.my.user.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.user.hotel.entities.Hotel;
import com.my.user.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Create Hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.createHotel(hotel));
    }

    // Get Hotel
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }

    // Update Hotel
    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.updateHotel(hotel));
    }

    // Delete Hotel
    @RequestMapping(value = "/{hotelId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().body("Hotel deleted successfully!!");
    }

    // Get All Hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel() {
        return ResponseEntity.ok(hotelService.getAllHotel());
    }

    // Get Hotel By Name
    @GetMapping("/name/{hotelName}")
    public ResponseEntity<Hotel> getHotelByName(@PathVariable String hotelName) {
        return ResponseEntity.ok(hotelService.getHotelByName(hotelName));
    }

    // Get Hotel By Location
    @GetMapping("/location/{location}")
    public ResponseEntity<Hotel> getHotelByLocation(@PathVariable String location) {
        return ResponseEntity.ok(hotelService.getHotelByLocation(location));
    }

    // // Get Hotel By ID
    // @GetMapping("/id/{hotelId}")
    // public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
    // return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    // }

}
