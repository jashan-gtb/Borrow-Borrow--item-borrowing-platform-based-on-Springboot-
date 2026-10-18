package com.jashan.borrow_borrow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jashan.borrow_borrow.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private RequestStatus sendRequestStatus;
    private RequestStatus receiveRequestStatus;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Items> items;

    @OneToMany(mappedBy = "user")
    private List<BorrowRequestSend> requests;
}
