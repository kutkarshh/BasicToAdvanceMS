package com.my.user.rating.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.my.user.rating.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

    // Custom Finder Methods for Rating Repository

    List<Rating> getRatingByHotelId(String hotelId);

    List<Rating> getRatingByUserId(String userId);

}
