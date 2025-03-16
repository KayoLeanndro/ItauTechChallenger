package desafio.itau.SpringItau.service;

import desafio.itau.SpringItau.dto.TransactionRequest;
import desafio.itau.SpringItau.model.Transaction;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(TransactionRequest transactionRequest){
        Transaction transaction = new Transaction(transactionRequest.getValor(), transactionRequest.getDataHora());
        transactions.add(transaction);
    }

    public void clearTransactions(){
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics(){

        //Chama apenas os objetos que foram criados a 60 segundos atrás.
        //Ex:
        //{
        //    "valor": 200,
        //    "dataHora": "2025-03-16T14:25:00-03:00"
        //}
        OffsetDateTime now = OffsetDateTime.now();
        return transactions.stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

    }


}
