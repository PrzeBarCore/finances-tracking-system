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
            if(map.containsKey(id)){
                map.remove(id);
            }
            map.put(id,entity);
        }
        return entity;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        if(map.containsKey(id))
            return Optional.of(map.get(id));
        else
            return Optional.of(null);
    }

    @Override
    public List<Account> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public boolean delete(Account entity) {
        if(map.contains(entity)){
            map.remove(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Integer id) {
        return map.containsKey(id);


    }
}
