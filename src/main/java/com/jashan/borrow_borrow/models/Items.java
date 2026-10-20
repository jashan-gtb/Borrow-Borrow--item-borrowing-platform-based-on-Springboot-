package com.jashan.borrow_borrow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;
    private String item_name;
    private String item_description;
    private String item_image;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties("items")
    private Users user;

}
