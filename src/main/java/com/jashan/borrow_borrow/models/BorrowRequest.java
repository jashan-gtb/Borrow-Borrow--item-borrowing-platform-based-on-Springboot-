package com.jashan.borrow_borrow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jashan.borrow_borrow.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BorrowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;
    private LocalDateTime request_time;
    private RequestStatus requestStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties("requests")
    private Users sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    @JsonIgnoreProperties("requests")
    private Users receiver;

    @OneToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties("requests")
    private Items items;
}
