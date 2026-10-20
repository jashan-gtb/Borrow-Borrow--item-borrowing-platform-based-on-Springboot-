package com.jashan.borrow_borrow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jashan.borrow_borrow.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private RequestStatus sendRequestStatus;
    private RequestStatus receiveRequestStatus;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Items> items;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<BorrowRequest> sendRequests = new ArrayList<>();

    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<BorrowRequest> receiveRequests= new ArrayList<>();

    public void addSendRequest(BorrowRequest request){
        this.sendRequests.add(request);
        request.setSender(this);
    }
    public void addReceiveRequest(BorrowRequest request){
        this.receiveRequests.add(request);
        request.setReceiver(this);
    }
}
