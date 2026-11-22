package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Accounts.Session;
import com.example.shopeasy.Services.CartLine;
import com.example.shopeasy.Services.CartService;
import com.example.shopeasy.Services.ShopEasyException;

import javax.swing.*;
import java.awt.Dimension;
import java.util.List;

public class ShoppingCart {

    // Tall enough for about five items; a longer cart scrolls inside this area
    private static final int MAX_ITEMS_HEIGHT = 320;

    private final CartService cartService;
    private final Integer accountId;
    private final JFrame frame;
    private final JLabel total;
    private final JPanel itemsPanel;
    private final JScrollPane itemsScroll;

    public ShoppingCart(Navigator navigator, CartService cartService, Session session) {
        this.cartService = cartService;
        this.accountId = session.getCurrentAccount().getAccountId();

        frame = new JFrame("Shopping Cart");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Shopping Cart");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        total = new JLabel(); // filled in by showItems()
        total.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Holds an "Item | Qty | Price" line and a Remove button for each item in the cart
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsScroll = new JScrollPane(itemsPanel);
        itemsScroll.setBorder(BorderFactory.createEmptyBorder());
        itemsScroll.getVerticalScrollBar().setUnitIncrement(16);
        itemsScroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton proceed = new JButton("Proceed to Checkout");
        proceed.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        proceed.addActionListener(e -> {
            if (cartService.getItems(accountId).isEmpty()) {
                JOptionPane.showMessageDialog(frame, ShopEasyException.EMPTY_CART,
                        "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                return;
            }
            frame.dispose(); // Checkout's "Return to Shopping Cart" opens a fresh cart window
            navigator.openCheckout();
        });

        JButton home = new JButton("Return to Home");
        home.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        home.addActionListener(e -> frame.dispose()); // Home is still open behind this window

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(total);
        panel.add(Box.createVerticalStrut(30));
        panel.add(itemsScroll);
        panel.add(Box.createVerticalStrut(25));
        panel.add(proceed);
        panel.add(Box.createVerticalStrut(10));
        panel.add(home);
        panel.add(Box.createVerticalGlue());

        showItems();

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Reloads the cart from the database, then rebuilds the item list and the subtotal
    private void showItems() {
        List<CartLine> items = cartService.getItems(accountId);
        itemsPanel.removeAll();

        if (items.isEmpty()) {
            JLabel empty = new JLabel(ShopEasyException.EMPTY_CART);
            empty.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            itemsPanel.add(empty);
        }

        for (CartLine item : items) {
            JLabel iteminfo = new JLabel("Item: " + item.productName() + " | Qty: " + item.quantity()
                    + " | Price: " + MoneyFormat.format(item.price()) + " each");
            iteminfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

            JButton remove = new JButton("Remove from Cart");
            remove.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            remove.addActionListener(e -> {
                cartService.removeItem(accountId, item.cartItemId());
                showItems();
            });

            itemsPanel.add(iteminfo);
            itemsPanel.add(Box.createVerticalStrut(5));
            itemsPanel.add(remove);
            itemsPanel.add(Box.createVerticalStrut(15));
        }

        total.setText("Subtotal: " + MoneyFormat.format(cartService.calculateSubtotal(items)));

        // The item area grows with the cart up to MAX_ITEMS_HEIGHT, then scrolls
        int height = Math.min(itemsPanel.getPreferredSize().height, MAX_ITEMS_HEIGHT);
        Dimension size = new Dimension(700, height);
        itemsScroll.setPreferredSize(size);
        itemsScroll.setMaximumSize(size);

        frame.revalidate();
        frame.repaint();
    }
}
