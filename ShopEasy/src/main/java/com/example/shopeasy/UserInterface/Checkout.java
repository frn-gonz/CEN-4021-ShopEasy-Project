package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Accounts.Session;
import com.example.shopeasy.DiscountsAndPromo.DiscountCode;
import com.example.shopeasy.Services.CartService;
import com.example.shopeasy.Services.CheckoutService;
import com.example.shopeasy.Services.OrderReceipt;
import com.example.shopeasy.Services.OrderTotals;
import com.example.shopeasy.Services.ShopEasyException;

import javax.swing.*;
import java.math.BigDecimal;

public class Checkout {

    private final CheckoutService checkoutService;
    private final BigDecimal subtotal;
    private final JFrame frame;
    private final JLabel subtotalinfo;
    private final JLabel discountinfo;
    private final JLabel taxinfo;
    private final JLabel total;

    public Checkout(Navigator navigator, CartService cartService, CheckoutService checkoutService,
                    Session session) {
        this.checkoutService = checkoutService;
        Integer accountId = session.getCurrentAccount().getAccountId();
        this.subtotal = cartService.calculateSubtotal(cartService.getItems(accountId));

        frame = new JFrame("Checkout");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Checkout");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Subtotal - discount + sales tax = total, filled in by showTotals()
        subtotalinfo = new JLabel();
        subtotalinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        discountinfo = new JLabel();
        discountinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        taxinfo = new JLabel();
        taxinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        total = new JLabel();
        total.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel paymentinfo = new JLabel("Card Number:");
        paymentinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JTextField paymentField = new JTextField();
        paymentField.setMaximumSize(new java.awt.Dimension(200, 60));
        paymentField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel shippinginfo = new JLabel("Shipping Address:");
        shippinginfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JTextField shippingField = new JTextField();
        shippingField.setMaximumSize(new java.awt.Dimension(200, 60));
        shippingField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel code = new JLabel("Discount Code (optional):");
        code.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JTextField codeField = new JTextField();
        codeField.setMaximumSize(new java.awt.Dimension(200, 60));
        codeField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton applycode = new JButton("Apply Code");
        applycode.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        applycode.addActionListener(e -> applyDiscount(codeField.getText()));
        codeField.addActionListener(e -> applyDiscount(codeField.getText())); // Enter also applies it

        JButton confirmorder = new JButton("Confirm Order");
        confirmorder.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        confirmorder.addActionListener(e -> {
            confirmorder.setEnabled(false); // no double orders from a double click
            try {
                // Checks the card, address, code, and stock, then places the order in one
                // transaction. On any error nothing in the database changes.
                OrderReceipt receipt = checkoutService.placeOrder(accountId, paymentField.getText(),
                        shippingField.getText(), codeField.getText());
                frame.dispose();
                navigator.openOrderConfirmation(receipt);
            } catch (ShopEasyException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Checkout", JOptionPane.ERROR_MESSAGE);
                confirmorder.setEnabled(true);
            }
        });

        JButton back = new JButton("Return to Shopping Cart");
        back.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        back.addActionListener(e -> {
            frame.dispose();
            navigator.openShoppingCart();
        });

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        panel.add(subtotalinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(discountinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(taxinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(total);
        panel.add(Box.createVerticalStrut(30));
        panel.add(paymentinfo);
        panel.add(paymentField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(shippinginfo);
        panel.add(shippingField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(code);
        panel.add(codeField);
        panel.add(Box.createVerticalStrut(5));
        panel.add(applycode);
        panel.add(Box.createVerticalStrut(15));
        panel.add(confirmorder);
        panel.add(Box.createVerticalStrut(15));
        panel.add(back);
        panel.add(Box.createVerticalGlue());

        showTotals(null);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Updates the totals for the code typed in; a blank code means no discount.
    // An unknown or expired code shows Error 205 and the totals go back to no discount.
    private void applyDiscount(String codeText) {
        try {
            showTotals(checkoutService.findValidDiscount(codeText));
        } catch (ShopEasyException ex) {
            showTotals(null);
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Discount Code", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showTotals(DiscountCode discountCode) {
        OrderTotals totals = checkoutService.calculateTotals(subtotal, discountCode);
        subtotalinfo.setText("Subtotal: " + MoneyFormat.format(totals.subtotal()));
        discountinfo.setText(discountCode == null
                ? "Discount: " + MoneyFormat.format(totals.discount())
                : "Discount (" + discountCode.getCodeName() + ", "
                        + MoneyFormat.percent(discountCode.getDiscount()) + " off): -"
                        + MoneyFormat.format(totals.discount()));
        taxinfo.setText("Sales Tax (" + MoneyFormat.percent(CheckoutService.SALES_TAX_RATE) + "): "
                + MoneyFormat.format(totals.tax()));
        total.setText("Total: " + MoneyFormat.format(totals.total()));
    }
}
