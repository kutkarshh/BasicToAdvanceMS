package com.my.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.my.user.service.entities.Hotel;
import com.my.user.service.entities.Rating;
import com.my.user.service.entities.User;
import com.my.user.service.exceptions.ResourceNotFoundException;
import com.my.user.service.repositories.UserRepository;
import com.my.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(User user) {

        // generate unique user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    public List<User> createMultipleUsers(List<User> users) {
        List<User> usersList = users.stream().map(user -> createUser(user)).toList();
        return usersList;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found on server. for this id: " + userId));
        // Fetch Ratings of the User from the RATING-SERVICE using Rating Service API
        // through REST Template
        // /rating/user/{userId}
        String ratingsUrl = "http://localhost:8083/rating/user/" + userId;
        Rating[] ratingOfUser = restTemplate.getForObject(ratingsUrl, Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // Fetch Hotel Details of the User from the HOTEL-SERVICE using Rating Service
            // API
            // through REST Template
            // /rating/hotels/{hotelId}
            String hotelUrl = "http://localhost:8082/hotels/" + rating.getHotelId();
            ResponseEntity<Hotel> hotelData = restTemplate.getForEntity(hotelUrl, Hotel.class);
            Hotel hotel = hotelData.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        if (ratingList != null) {
            user.setRatings(ratingList);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Fetch Ratings of the User from the RATING-SERVICE using Rating Service API
        // through REST Template
        // /rating/user/{userId}
        users.forEach(user -> {
            String ratingsUrl = "http://localhost:8083/rating/user/" + user.getUserId();
            ArrayList<Rating> ratingOfUser = restTemplate.getForObject(ratingsUrl, ArrayList.class);
            if (ratingOfUser != null) {
                user.setRatings(ratingOfUser);
            }
        });
        return users;
    }

}