package com.my.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.my.user.service.entities.Hotel;
import com.my.user.service.entities.Rating;
import com.my.user.service.entities.User;
import com.my.user.service.exceptions.ResourceNotFoundException;
import com.my.user.service.repositories.UserRepository;
import com.my.user.service.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(User user) {
        // Generate unique user ID
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> createMultipleUsers(List<User> users) {
        return users.stream().map(this::createUser).collect(Collectors.toList());
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found for id: " + userId));
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found for id: " + userId));

        try {
            // Fetch ratings from RATING-SERVICE
            String ratingsUrl = "http://RATING-SERVICE/rating/user/" + userId;
            Rating[] ratingOfUser = restTemplate.getForObject(ratingsUrl, Rating[].class);

            if (ratingOfUser != null) {
                List<Rating> ratingList = Arrays.stream(ratingOfUser)
                        .map(this::fetchHotelDetailsForRating) // Extracting hotel details
                        .collect(Collectors.toList());
                user.setRatings(ratingList);
            }
        } catch (RestClientException e) {
            log.error("Error while fetching ratings for userId: {}", userId, e);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            try {
                // Fetch ratings from RATING-SERVICE
                String ratingsUrl = "http://RATING-SERVICE/rating/user/" + user.getUserId();
                Rating[] ratingOfUser = restTemplate.getForObject(ratingsUrl, Rating[].class);

                if (ratingOfUser != null) {
                    List<Rating> ratingList = Arrays.stream(ratingOfUser)
                            .map(this::fetchHotelDetailsForRating)
                            .collect(Collectors.toList());
                    user.setRatings(ratingList);
                }
            } catch (RestClientException e) {
                log.error("Error while fetching ratings for userId: {}", user.getUserId(), e);
            }
        });

        return users;
    }

    /**
     * Fetches hotel details for a given rating and attaches it to the rating.
     */
    private Rating fetchHotelDetailsForRating(Rating rating) {
        try {
            String hotelUrl = "http://HOTEL-SERVICE/hotels/" + rating.getHotelId();
            ResponseEntity<Hotel> hotelData = restTemplate.getForEntity(hotelUrl, Hotel.class);
            Hotel hotel = hotelData.getBody();
            rating.setHotel(hotel);
        } catch (RestClientException e) {
            log.error("Error while fetching hotel details for hotelId: {}", rating.getHotelId(), e);
        }
        return rating;
    }
}
