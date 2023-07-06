package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.Account.Dto.AccountDto;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/accounts")
class AccountController {
    private AccountFacade facade;

    AccountController(AccountFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(new ArrayList<>(facade.findAll()));
    }

    @PostMapping
    ResponseEntity<AccountDto> createAccount(@RequestParam(name = "name") String name, @RequestParam(name = "balance") BigDecimal balance,
                                             @RequestParam(name = "currency") String currency){
                return ResponseEntity.of(facade.add(AccountDto.builder()
                        .withAccountName(NameString.of(name))
                        .withBalance(MonetaryAmount.of(balance))
                        .withCurrency(SimpleCurrency.valueOf(currency))
                        .build()));
    }


}
