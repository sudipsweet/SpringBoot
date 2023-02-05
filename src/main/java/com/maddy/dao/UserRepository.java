package com.maddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maddy.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
