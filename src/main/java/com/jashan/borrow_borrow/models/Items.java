package com.jashan.borrow_borrow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private boolean active=true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("items")
    private Users user;

    @OneToMany(mappedBy = "items")
    @JsonIgnoreProperties("items")
    private List<BorrowRequest> requests;

}
