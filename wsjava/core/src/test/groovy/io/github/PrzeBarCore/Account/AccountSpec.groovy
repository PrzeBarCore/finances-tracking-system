package io.github.PrzeBarCore.Account


import io.github.PrzeBarCore.ValueObjects.MonetaryAmount
import io.github.PrzeBarCore.ValueObjects.NameString
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency
import spock.lang.Specification

class AccountSpec extends Specification{
    AccountFacade accountFacade= new AccountFacade(new InMemoryAccountRepository())

    AccountDto wallet=createAccountDto(0,NameString.of("Wallet"), MonetaryAmount.of(300), SimpleCurrency.PLN)
    AccountDto bankAccount=createAccountDto(0,NameString.of("PKO BP"), MonetaryAmount.of(300), SimpleCurrency.PLN)

    def "should get an account"(){
        when: "new account is added"
        accountFacade.createAccount(wallet)

        then: "system has this account"
        accountFacade.findAccount(wallet.getId()).get() == wallet
    }

    def "should list accounts "(){
        given: "two accounts exist in the system"
        accountFacade.createAccount(wallet)
        accountFacade.createAccount(bankAccount)

        when: "we ask for all accounts"
        List<AccountDto> resultList=accountFacade.findAllAccounts()
        AccountDto wallet2=createAccountDto(1,NameString.of("Wallet"), MonetaryAmount.of(300), SimpleCurrency.PLN)
        AccountDto bankAccount2=createAccountDto(2,NameString.of("PKO BP"), MonetaryAmount.of(300), SimpleCurrency.PLN)


        then: "result list contains both accounts"
        resultList.contains(wallet2)
        resultList.contains(bankAccount2)
    }

    def "should remove account from the system"(){
        given: "account exists in the system"
        accountFacade.createAccount(wallet)

        when: "we ask for removing account"
        accountFacade.removeAccount(wallet.getId())

        then: "account cannot be found in repository"
        Optional<AccountDto> result= accountFacade.findAccount(wallet.getId())
        result.isEmpty()
    }

    def "should update data of account"(){
        given: "account exists in the system"
        accountFacade.createAccount(wallet);

        when: "we update update account data and save in repository"
        AccountDto changedWallet=createAccountDto(1,NameString.of("Changed name"), MonetaryAmount.of(400), SimpleCurrency.PLN)
        Optional<AccountDto> result = accountFacade.updateAccount(changedWallet)

        then: "account is found with changed data"
        result.present
        result.get().name == changedWallet.getName()
        result.get().balance == changedWallet.getBalance()
    }

    def "should return empty Optional for incorrect dto to update"(){
        given: "account exists in the system"
        accountFacade.createAccount(wallet);

        when: "we update update account data and save in repository"
        AccountDto changedWallet=createAccountDto(2,NameString.of("Changed name"), MonetaryAmount.of(400), SimpleCurrency.PLN)
        Optional<AccountDto> result= accountFacade.updateAccount(changedWallet)

        then: "account is found with changed data"
        !result.present
    }

    private AccountDto createAccountDto(int id, NameString accountName, MonetaryAmount startingBalance, SimpleCurrency currency) {
        return AccountDto.builder().withId(id).withAccountName(accountName).withBalance(startingBalance).withCurrency(currency).build();
    }
}
