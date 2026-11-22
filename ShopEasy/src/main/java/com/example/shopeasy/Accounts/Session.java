package com.example.shopeasy.Accounts;

import org.springframework.stereotype.Component;

/**
 * Remembers which account is logged in to the desktop app.
 * Spring creates one Session for the whole app, and the Navigator
 * passes it to the screens that need to know who is logged in.
 */
@Component
public class Session {

    private Account currentAccount;

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }

    // Returns null when nobody is logged in
    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void logout() {
        currentAccount = null;
    }
}
