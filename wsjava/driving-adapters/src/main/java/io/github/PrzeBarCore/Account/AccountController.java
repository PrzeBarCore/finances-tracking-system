package io.github.PrzeBarCore.Account;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/accounts")
@CrossOrigin(origins = "http://localhost:4200/")
class AccountController {
    private final AccountFacade facade;

    AccountController(final AccountFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(new ArrayList<>(facade.findAllAccounts()));
    }
    @GetMapping(path = "/{id}")
    ResponseEntity<AccountDto> getAccount(@PathVariable int id){
        return ResponseEntity.of(facade.findAccount(id));
    }
    @GetMapping(path = "/details/{id}")
    ResponseEntity<AccountDto> getAccountWithDetails(@PathVariable int id){
        return ResponseEntity.of(facade.findAccountWithTransactions(id));
    }
    @PostMapping
    ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountToCreate){
        return ResponseEntity.ok(facade.createAccount(accountToCreate));
    }
    @PutMapping
    ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountToUpdate){
        return ResponseEntity.of(facade.updateAccount(accountToUpdate));
    }


}
