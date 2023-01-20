package io.github.PrzeBarCore.Account

import io.github.PrzeBarCore.Account.Dto.AccountDto
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency

class AccountSpec {
    AccountFacade accountFacade= new AccountFacade(new InMemoryAccountRepository())

    AccountDto wallet=createAccountDto("Wallet", MonetaryAmount.of(300), SimpleCurrency.PLN)
    AccountDto bankAccount=createAccountDto("PKO BP", MonetaryAmount.of(300), SimpleCurrency.PLN)

    def "should get an account"(){
        when: "new account is added"
        accountFacade.add(wallet)

        then: "system has this account"
        accountFacade.find(wallet.getId()) == wallet
    }

    def "should list accounts "(){
        given: "two accounts exist in the system"
        accountFacade.add(wallet);
        accountFacade.add(bankAccount);

        when: "we ask for all accounts"
        List<AccountDto> resultList=accountFacade.findAll();

        then: "result list contains both accounts"
        resultList.contains(wallet);
        resultList.contains(bankAccount);
    }

    def "should remove account from the system"(){
        given: "account exists in the system"
        accountFacade.add(wallet);

        when: "we ask for removing account"
        accountFacade.remove(wallet.getId());

        then: "account cannot be found in repository"
        Optional<AccountDto> result= accountFacade.find(wallet.getId());
        result.isEmpty();
    }


    private AccountDto createAccountDto(String accountName, MonetaryAmount startingBalance, SimpleCurrency currency) {
        return AccountDto.builder().withAccountName(accountName).withBalance(startingBalance).withCurrency(currency).build();
    }
}
