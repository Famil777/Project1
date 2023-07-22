package com.example.demo.entity;


import java.util.List;

import com.example.demo.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence" , sequenceName = "user_sequence" , allocationSize = 1)
    @GeneratedValue(generator = "user_sequence" , strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String name;
    private String surname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "user")
    private List<Ticket> ticket;



}
