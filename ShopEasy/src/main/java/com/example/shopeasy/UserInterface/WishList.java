package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Accounts.Session;
import com.example.shopeasy.Services.ShopEasyException;
import com.example.shopeasy.Services.WishlistLine;
import com.example.shopeasy.Services.WishlistService;

import javax.swing.*;
import java.awt.Dimension;
import java.util.List;

public class WishList {

    // Tall enough for about four items; a longer wish list scrolls inside this area
    private static final int MAX_ITEMS_HEIGHT = 320;

    private final WishlistService wishlistService;
    private final Integer accountId;
    private final JFrame frame;
    private final JPanel itemsPanel;
    private final JScrollPane itemsScroll;

    public WishList(Navigator navigator, WishlistService wishlistService, Session session) {
        this.wishlistService = wishlistService;
        this.accountId = session.getCurrentAccount().getAccountId();

        frame = new JFrame("Wish List");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Wish List");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Holds an "Item | Price" line with Add to Cart and Remove buttons for each item
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsScroll = new JScrollPane(itemsPanel);
        itemsScroll.setBorder(BorderFactory.createEmptyBorder());
        itemsScroll.getVerticalScrollBar().setUnitIncrement(16);
        itemsScroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton home = new JButton("Return to Home");
        home.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        home.addActionListener(e -> frame.dispose()); // Home is still open behind this window

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(itemsScroll);
        panel.add(Box.createVerticalStrut(25));
        panel.add(home);
        panel.add(Box.createVerticalGlue());

        showItems();

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Reloads the wish list from the database and rebuilds the item list
    private void showItems() {
        List<WishlistLine> items = wishlistService.getItems(accountId);
        itemsPanel.removeAll();

        if (items.isEmpty()) {
            JLabel empty = new JLabel("Your wish list is empty");
            empty.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            itemsPanel.add(empty);
        }

        for (WishlistLine item : items) {
            JLabel iteminfo = new JLabel("Item: " + item.productName()
                    + " | Price: " + MoneyFormat.format(item.price()));
            iteminfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

            JButton cart = new JButton("Add to Cart");
            cart.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            cart.addActionListener(e -> {
                try {
                    // Adds one to the cart and takes it off the wish list (Error 321 if out of stock)
                    wishlistService.moveToCart(accountId, item.wishlistItemId());
                    JOptionPane.showMessageDialog(frame, item.productName() + " was moved to your cart.");
                } catch (ShopEasyException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Wish List", JOptionPane.ERROR_MESSAGE);
                }
                showItems();
            });

            JButton remove = new JButton("Remove from Wish List");
            remove.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            remove.addActionListener(e -> {
                wishlistService.removeItem(accountId, item.wishlistItemId());
                showItems();
            });

            itemsPanel.add(iteminfo);
            itemsPanel.add(Box.createVerticalStrut(5));
            itemsPanel.add(cart);
            itemsPanel.add(Box.createVerticalStrut(5));
            itemsPanel.add(remove);
            itemsPanel.add(Box.createVerticalStrut(15));
        }

        // The item area grows with the list up to MAX_ITEMS_HEIGHT, then scrolls
        int height = Math.min(itemsPanel.getPreferredSize().height, MAX_ITEMS_HEIGHT);
        Dimension size = new Dimension(700, height);
        itemsScroll.setPreferredSize(size);
        itemsScroll.setMaximumSize(size);

        frame.revalidate();
        frame.repaint();
    }
}
