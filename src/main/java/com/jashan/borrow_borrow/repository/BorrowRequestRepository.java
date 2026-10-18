package com.jashan.borrow_borrow.repository;

import com.jashan.borrow_borrow.models.BorrowRequestSend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRequestRepository extends JpaRepository<BorrowRequestSend,Long> {
}
