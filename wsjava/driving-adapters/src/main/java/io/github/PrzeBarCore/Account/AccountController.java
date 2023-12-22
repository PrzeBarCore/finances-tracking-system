package io.github.PrzeBarCore.Account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/accounts")
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
    ResponseEntity<AccountDto> find(@PathVariable int id){
        return ResponseEntity.of(facade.findAccount(id));
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
