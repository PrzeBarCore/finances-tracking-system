package io.github.PrzeBarCore.Account;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryAccountRepository implements AccountRepository {
    ConcurrentHashMap<Integer,Account> map= new ConcurrentHashMap<Integer,Account>();
    InMemoryAccountRepository() {
    }

    @Override
    public Account save(Account entity) {
        if(null!=entity){
            Integer id=entity.getSnapshot().getId();
            if(id==0){
                entity= Account.restore(
                        new AccountSnapshot(map.size()+1,
                                entity.getSnapshot().getName(),
                                entity.getSnapshot().getBalance(),
                                entity.getSnapshot().getCurrency()
                        ));
                map.put(map.size()+1, entity);
            } else {
                map.remove(id);
                map.put(id,entity);
            }
        }
        return entity;
    }

    @Override
    public Optional<Account> findById(Integer id) {
            return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<Account> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public boolean delete(Account entity) {
        if(map.contains(entity)){
            map.remove(entity.getSnapshot().getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Integer id) {
        return map.containsKey(id);


    }
}
