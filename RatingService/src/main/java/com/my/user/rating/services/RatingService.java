package com.my.user.rating.services;

import java.util.List;

import com.my.user.rating.entities.Rating;

public interface RatingService {

    // Create Rating
    Rating createRating(Rating rating);

    // Get All Rating
    List<Rating> getAllRating();

    // Get all Rating By UserId
    List<Rating> getRatingByUserId(String userId);

    // Get all Rating By HotelId
    List<Rating> getRatingByHotelId(String hotelId);
}
