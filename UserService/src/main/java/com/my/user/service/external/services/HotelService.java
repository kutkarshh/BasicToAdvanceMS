package com.my.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.my.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    // Get Hotel By ID using Feign Client calling Hotel Service API
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotelById(@PathVariable("hotelId") String hotelId);

}
