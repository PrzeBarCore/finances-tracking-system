package io.github.PrzeBarCore.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/transactions")
@CrossOrigin(origins = {"http://localhost:4200/"})
class TransactionController {
    private final TransactionFacade transactionFacade;
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    TransactionController(TransactionFacade transactionFacade){
        this.transactionFacade = transactionFacade;
    }
    @DeleteMapping(path = "/{id}")
    boolean deleteTransaction(@PathVariable("id") Integer id){
        logger.info("Received deleteTransaction request");
        return transactionFacade.deleteTransaction(id);
    }

    @GetMapping(path = "/receipt/{id}")
    ResponseEntity<ReceiptTypeTransaction> findReceipt(@PathVariable int id){
        logger.info("Received findReceipt request");
        Optional<ReceiptTypeTransaction> receipt =transactionFacade.findReceiptTransaction(id);
        return receipt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PostMapping(path = "/receipt")
    ResponseEntity createReceipt(@RequestBody ReceiptTypeTransaction receiptTransaction){
        logger.info("Received createReceipt request");
        if(transactionFacade.addReceiptTransaction(receiptTransaction))
            return ResponseEntity.ok().build();
        return  ResponseEntity.internalServerError().build();
    }
}
