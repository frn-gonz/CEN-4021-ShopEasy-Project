package com.example.shopeasy.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
}
