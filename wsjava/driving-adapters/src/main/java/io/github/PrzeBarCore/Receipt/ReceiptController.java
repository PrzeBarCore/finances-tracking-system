package io.github.PrzeBarCore.Receipt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/receipts")
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

    @PostMapping
    ResponseEntity<ReceiptDto> createReceipt(@RequestBody ReceiptDto receiptToCreate){
        logger.info(receiptToCreate.toString());
        return ResponseEntity.ok(facade.createReceipt(receiptToCreate));
    }
}
