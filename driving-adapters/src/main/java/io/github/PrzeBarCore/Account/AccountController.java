package io.github.PrzeBarCore.Account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
class AccountController {
    private final AccountFacade accountFacade;

    AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @PostMapping
    ResponseEntity<AccountSnapshot>(@RequestBody AccountSnapshot toCreate){
    AccountSnapshot result= accountFacade.save(toCreate);
    return ResponseEntity.created(URI.create("/" +result.getId())).body(result);
    }
}
