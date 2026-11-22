package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Accounts.Account;
import com.example.shopeasy.Accounts.AccountPage;
import com.example.shopeasy.Accounts.AccountsRepository;
import com.example.shopeasy.Accounts.Session;
import com.example.shopeasy.Login.Login;
import com.example.shopeasy.ProductAndInventory.Inventory;
import com.example.shopeasy.ProductAndInventory.ProductsRepository;
import com.example.shopeasy.Services.CartService;
import com.example.shopeasy.Services.CheckoutService;
import com.example.shopeasy.Services.OrderReceipt;
import com.example.shopeasy.Services.WishlistService;
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

    // SignUp and ForgotPassword open the Login screen themselves with new Login(accountsRepository),
    // so Login can't be handed the Navigator in its constructor and uses Navigator.get() instead.
    // Spring creates exactly one Navigator when the app starts.
    private static Navigator instance;

    private final AccountsRepository accountsRepository;
    private final ProductsRepository productsRepository;
    private final Session session;
    private final CartService cartService;
    private final CheckoutService checkoutService;
    private final WishlistService wishlistService;

    public Navigator(AccountsRepository accountsRepository, ProductsRepository productsRepository,
                     Session session, CartService cartService, CheckoutService checkoutService,
                     WishlistService wishlistService) {
        this.accountsRepository = accountsRepository;
        this.productsRepository = productsRepository;
        this.session = session;
        this.cartService = cartService;
        this.checkoutService = checkoutService;
        this.wishlistService = wishlistService;
        instance = this;
    }

    public static Navigator get() {
        return instance;
    }

    // Login and Home are the two main windows. Opening one closes everything else:
    // logging in closes Login, logging out closes Home and every open screen.
    // (Login opens Sign Up and Forgot Password itself, one window at a time.)
    public void openLogin() {
        closeAllWindows();
        new Login(accountsRepository);
    }

    // Called by the Login screen once the username and password check out
    public void logIn(Account account) {
        session.setCurrentAccount(account);
        openHome();
    }

    public void openHome() {
        closeAllWindows();
        new Home(this, session);
    }

    public void openInventory() {
        openClosingOnlyItself(() -> new Inventory(productsRepository));
    }

    public void openShoppingCart() {
        new ShoppingCart(this, cartService, session);
    }

    public void openCheckout() {
        new Checkout(this, cartService, checkoutService, session);
    }

    public void openOrderConfirmation(OrderReceipt receipt) {
        new OrderConfirmation(receipt);
    }

    public void openWishList() {
        new WishList(this, wishlistService, session);
    }

    public void openAccount() {
        new AccountPage(accountsRepository, session.getCurrentAccount());
    }

    // dispose() closes a window without triggering EXIT_ON_CLOSE
    private void closeAllWindows() {
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
    }

    // Inventory sets EXIT_ON_CLOSE, which would quit the whole app when closed.
    // That file belongs to a teammate, so after it opens we switch the new window to
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
