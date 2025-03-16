package desafio.itau.SpringItau.controller;

import desafio.itau.SpringItau.dto.StatisticsResponse;
import desafio.itau.SpringItau.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final TransactionService transactionService;
    
    public StatisticController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics(){
        DoubleSummaryStatistics statistics = transactionService.getStatistics();
        return ResponseEntity.ok(new StatisticsResponse(statistics));
    }
}
