package io.github.PrzeBarCore.Receipt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/receipts")
@CrossOrigin(origins = {"http://localhost:4200/"})
class ReceiptController {
    private final ReceiptFacade facade;
    private static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    ReceiptController(ReceiptFacade facade) {
        this.facade = facade;
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ReceiptDto> findReceipt(@PathVariable int id){
        Optional<ReceiptDto> receipt =facade.findReceipt(id);
        return receipt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }
    @GetMapping(path = "/transaction/{transactionId}")
    ResponseEntity<ReceiptDto> findReceiptWithTransactionId(@PathVariable int transactionId){
        Optional<ReceiptDto> receipt =facade.findReceiptWithTransaction(transactionId);
        return receipt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PostMapping
    ResponseEntity createReceipt(@RequestBody ReceiptDto receiptToCreate){
        logger.info(receiptToCreate.toString());
        if(facade.createReceipt(receiptToCreate))
            return ResponseEntity.ok().build();
        return  ResponseEntity.internalServerError().build();
    }
}
