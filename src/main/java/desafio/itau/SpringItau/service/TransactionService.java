package desafio.itau.SpringItau.service;

import desafio.itau.SpringItau.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void removeTransaction(){
        transactions.clear();
    }




}
