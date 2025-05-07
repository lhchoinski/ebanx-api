package com.ebanx.api.db;

import com.ebanx.api.entities.Account;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class DbAccount {
    private final Map<Long, Account> db = new HashMap<>();

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public void save(Account Account) {
        db.put(Account.getId(), Account);
    }

    public List<Account> findAll() {
        return new ArrayList<>(db.values());
    }

    public void deleteAll() {
        db.clear();
    }
}
