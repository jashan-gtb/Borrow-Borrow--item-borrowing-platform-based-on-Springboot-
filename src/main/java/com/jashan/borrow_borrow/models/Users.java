package com.jashan.borrow_borrow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jashan.borrow_borrow.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true,nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Items> items = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("sender")
    private List<BorrowRequest> sendRequests = new ArrayList<>();

    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("receiver")
    private List<BorrowRequest> receiveRequests= new ArrayList<>();

    public void addSendRequest(BorrowRequest request){
        this.sendRequests.add(request);
        request.setSender(this);
    }
    public void addReceiveRequest(BorrowRequest request){
        this.receiveRequests.add(request);
        request.setReceiver(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + getRole().name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

}
