package com.my.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
