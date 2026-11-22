package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Services.CartLine;
import com.example.shopeasy.Services.CheckoutService;
import com.example.shopeasy.Services.OrderReceipt;
import com.example.shopeasy.Services.OrderTotals;

import javax.swing.*;
import java.awt.Dimension;

// Shown after an order is placed. Home is still open behind this window.
public class OrderConfirmation {

    // A long order scrolls inside this area instead of pushing the totals off screen
    private static final int MAX_ITEMS_HEIGHT = 200;

    public OrderConfirmation(OrderReceipt receipt) {
        JFrame frame = new JFrame("Order Confirmation");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Order Confirmed!");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel thanks = new JLabel("Thank you for shopping with ShopEasy. Here is your receipt:");
        thanks.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        for (CartLine item : receipt.items()) {
            JLabel iteminfo = new JLabel("Item: " + item.productName() + " | Qty: " + item.quantity()
                    + " | Price: " + MoneyFormat.format(item.price()) + " each");
            iteminfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            itemsPanel.add(iteminfo);
            itemsPanel.add(Box.createVerticalStrut(5));
        }
        JScrollPane itemsScroll = new JScrollPane(itemsPanel);
        itemsScroll.setBorder(BorderFactory.createEmptyBorder());
        itemsScroll.getVerticalScrollBar().setUnitIncrement(16);
        itemsScroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        Dimension size = new Dimension(700, Math.min(itemsPanel.getPreferredSize().height, MAX_ITEMS_HEIGHT));
        itemsScroll.setPreferredSize(size);
        itemsScroll.setMaximumSize(size);

        OrderTotals totals = receipt.totals();
        JLabel subtotalinfo = new JLabel("Subtotal: " + MoneyFormat.format(totals.subtotal()));
        subtotalinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel discountinfo = new JLabel(receipt.discountCode() == null
                ? "Discount: " + MoneyFormat.format(totals.discount())
                : "Discount (" + receipt.discountCode() + "): -" + MoneyFormat.format(totals.discount()));
        discountinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel taxinfo = new JLabel("Sales Tax (" + MoneyFormat.percent(CheckoutService.SALES_TAX_RATE) + "): "
                + MoneyFormat.format(totals.tax()));
        taxinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel total = new JLabel("Total: " + MoneyFormat.format(totals.total()));
        total.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel shipinfo = new JLabel("Shipping to: " + receipt.shippingAddress());
        shipinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel payinfo = new JLabel("Paid with card ending in " + receipt.cardLastFour());
        payinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton home = new JButton("Return to Home");
        home.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        home.addActionListener(e -> frame.dispose());

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(thanks);
        panel.add(Box.createVerticalStrut(20));
        panel.add(itemsScroll);
        panel.add(Box.createVerticalStrut(20));
        panel.add(subtotalinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(discountinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(taxinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(total);
        panel.add(Box.createVerticalStrut(20));
        panel.add(shipinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(payinfo);
        panel.add(Box.createVerticalStrut(25));
        panel.add(home);
        panel.add(Box.createVerticalGlue());

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
