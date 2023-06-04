package com.example.tx.service;

import com.example.tx.entity.Client;
import com.example.tx.repository.ClientRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Client findByFirstNameRequired(String fn){
        return clientRepository.findByFirstName(fn);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Client findByFirstNameRequiresNew(String fn){
        return clientRepository.findByFirstName(fn);
    }

    @Transactional(propagation = Propagation.NEVER)
    public Client findByFirstNameNever(String fn){
        return clientRepository.findByFirstName(fn);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Client findByFirstNameNotSupported(String fn){
        return clientRepository.findByFirstName(fn);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Client findByFirstNameMandatory(String fn){
        return clientRepository.findByFirstName(fn);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Client findByFirstNameSupports(String fn){
        return clientRepository.findByFirstName(fn);
    }

    @Transactional
    public Client findById(int id){
        return clientRepository.findById(id).get();
    }

    @Transactional
    public void decreaseBalance(int id,int amount){
        if(findById(id).getBalance() <= amount){
            throw new RuntimeException("You don't have enough money!");
        }

        clientRepository.decrementBalance(id,amount);
    }

}
