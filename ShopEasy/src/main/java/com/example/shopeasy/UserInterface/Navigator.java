package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Accounts.AccountsRepository;
import com.example.shopeasy.Login.SignUp;
import com.example.shopeasy.ProductAndInventory.Inventory;
import com.example.shopeasy.ProductAndInventory.ProductsRepository;
import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import java.awt.Window;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Opens every screen in the app and hands it the Spring beans it needs,
 * so screens never have to create repositories or services themselves.
 * All methods must be called on the Swing thread.
 */
@Component
public class Navigator {

    private final AccountsRepository accountsRepository;
    private final ProductsRepository productsRepository;

    public Navigator(AccountsRepository accountsRepository, ProductsRepository productsRepository) {
        this.accountsRepository = accountsRepository;
        this.productsRepository = productsRepository;
    }

    public void openLogin() {
        new Login();
    }

    public void openSignUp() {
        openClosingOnlyItself(() -> new SignUp(accountsRepository));
    }

    public void openHome() {
        new Home();
    }

    public void openInventory() {
        openClosingOnlyItself(() -> new Inventory(productsRepository));
    }

    public void openShoppingCart() {
        new ShoppingCart();
    }

    public void openCheckout() {
        new Checkout();
    }

    public void openWishList() {
        new WishList();
    }

    public void openAccount() {
        new Account();
    }

    public void openNotifications() {
        new Notifications();
    }

    // SignUp and Inventory set EXIT_ON_CLOSE, which would quit the whole app when closed.
    // Those files belong to teammates, so after they open we switch the new window to
    // DISPOSE_ON_CLOSE (closes just that window) and center it like the other screens.
    private void openClosingOnlyItself(Runnable openScreen) {
        Set<Window> alreadyOpen = new HashSet<>(Arrays.asList(Window.getWindows()));
        openScreen.run();
        for (Window window : Window.getWindows()) {
            if (!alreadyOpen.contains(window) && window instanceof JFrame frame) {
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
            }
        }
    }
}
