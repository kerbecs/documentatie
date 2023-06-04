package com.example.tx.repository;

import com.example.tx.entity.Client;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Client> findById(Integer integer);

    @Modifying
    @Query("UPDATE Client SET balance = balance - :value WHERE id=:id")
    @Transactional
    void decrementBalance(@Param("id") Integer id,@Param("value") Integer value);

    @Query("FROM Client c WHERE c.firstName=:fn")
    Client findByFirstName(@Param("fn") String fn);
}
