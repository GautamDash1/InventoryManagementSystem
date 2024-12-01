package com.example.IMS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IMS.entities.User;
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}