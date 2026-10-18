package com.jashan.borrow_borrow.models;

import com.jashan.borrow_borrow.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BorrowRequestSend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;
    private LocalDateTime request_time;
    private RequestStatus requestStatus;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne
    private Items items;
}
