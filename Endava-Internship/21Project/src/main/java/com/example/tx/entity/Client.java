package com.example.tx.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Client {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "balance")
    private Integer balance;

    public Client(String firstName, String lastName, Integer balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }
}
