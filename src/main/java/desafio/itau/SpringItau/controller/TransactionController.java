package desafio.itau.SpringItau.controller;

import desafio.itau.SpringItau.dto.TransactionRequest;
import desafio.itau.SpringItau.model.Transaction;
import desafio.itau.SpringItau.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    //Irá receber um objeto transacao
    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest request){

        if(request.getDataHora().isAfter(OffsetDateTime.now())){
            return ResponseEntity.unprocessableEntity().build();
        }

        if(request.getDataHora() == null ||  request.getValor() <= 0 || request.getValor() == null){
            return ResponseEntity.badRequest().build();
        }

        transactionService.addTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransactions(){
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }

}
