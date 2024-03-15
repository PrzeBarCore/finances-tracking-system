package io.github.PrzeBarCore.Common;

import io.github.PrzeBarCore.ValueObjects.TransactionType;
import io.github.PrzeBarCore.ValueObjects.Unit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/common")
@CrossOrigin(origins = "http://localhost:4200/")
class CommonController {
    @GetMapping("/units")
    ResponseEntity<List<Unit>>findUnits(){
        return ResponseEntity.ok(Arrays.stream(Unit.values()).toList());
    }

    @GetMapping("/transactionTypes")
    ResponseEntity<List<TransactionType>>findTransactionTypes(){
        return ResponseEntity.ok(Arrays.stream(TransactionType.values()).toList());
    }
}
