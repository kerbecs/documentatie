package com.example.tx;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class CustomTransactionManager extends JpaTransactionManager {
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        System.out.println("-------------------------------------------------------");
        System.out.println("           A new Transaction was created               ");
        System.out.println("-------------------------------------------------------");
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        System.out.println("-------------------------------------------------------");
        System.out.println("                 Transaction Committed                 ");
        System.out.println("-------------------------------------------------------");
        super.doCommit(status);
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) {
        System.out.println("-------------------------------------------------------");
        System.out.println("                  RollBack Transaction                 ");
        System.out.println("-------------------------------------------------------");
        super.doRollback(status);
    }
}
