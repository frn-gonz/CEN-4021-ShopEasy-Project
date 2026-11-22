package com.example.shopeasy.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountsRepository extends JpaRepository<Account, Integer> {

    // Returns a List, not an Optional: the database doesn't always enforce unique
    // usernames (the seed accounts have none), and Optional throws if two rows match.
    List<Account> findByUsername(String username);
}
