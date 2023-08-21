package com.backend.backend.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.backend.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findAccountByName(String name);

}
