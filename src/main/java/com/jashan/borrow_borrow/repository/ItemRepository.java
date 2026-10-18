package com.jashan.borrow_borrow.repository;

import com.jashan.borrow_borrow.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Items,Long> {
}
