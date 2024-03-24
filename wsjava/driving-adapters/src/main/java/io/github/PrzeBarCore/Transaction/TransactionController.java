package io.github.PrzeBarCore.Transaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/transactions")
@CrossOrigin(origins = {"http://localhost:4200/"})
class TransactionController {
    private final TransactionFacade transactionFacade;
    TransactionController(TransactionFacade transactionFacade){
        this.transactionFacade = transactionFacade;
    }
    @DeleteMapping(path = "/{id}")
    boolean deleteTransaction(@PathVariable("id") Integer id){
        return transactionFacade.deleteTransaction(id);
    }
}
