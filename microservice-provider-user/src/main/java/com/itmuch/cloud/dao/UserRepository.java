package com.itmuch.cloud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itmuch.cloud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
