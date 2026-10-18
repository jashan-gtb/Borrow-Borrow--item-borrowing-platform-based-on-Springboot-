package com.jashan.borrow_borrow.repository;

import com.jashan.borrow_borrow.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
