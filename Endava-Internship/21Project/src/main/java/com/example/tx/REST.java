package com.example.tx;

import com.example.tx.entity.Client;
import com.example.tx.repository.ClientRepository;
import com.example.tx.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class REST {
    private final ClientService clientService;

    @Transactional
    @GetMapping("/required")
    public Client required(){
        return clientService.findByFirstNameRequired("Mititiuc");
    }

    @Transactional
    @GetMapping("/requires_new")
    public Client requiesNew(){
        return clientService.findByFirstNameRequiresNew("Mititiuc");
    }

    @Transactional
    @GetMapping("/mandatory")
    public Client mandatory(){
        return clientService.findByFirstNameMandatory("Mititiuc");
    }

    @Transactional
    @GetMapping("/never")
    public Client never(){
        return clientService.findByFirstNameNever("Mititiuc");
    }

    @Transactional
    @GetMapping("/supports")
    public Client supports(){
        return clientService.findByFirstNameSupports("Mititiuc");
    }

    @Transactional
    @GetMapping("/not_supported")
    public Client notSupported(){
        return clientService.findByFirstNameNotSupported("Mititiuc");
    }
    @GetMapping("/balance")
    public Client getClient() throws InterruptedException {
        Thread thread1 = new Thread(
                () -> {
                    clientService.decreaseBalance(1,800);
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    clientService.decreaseBalance(1,400);
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        return clientService.findById(1);
    }
}
