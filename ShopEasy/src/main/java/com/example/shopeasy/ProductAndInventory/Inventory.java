package com.example.shopeasy.ProductAndInventory;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class Inventory {
    private final ProductsRepository productsRepository;
    // What the Add To Cart / Add To Wishlist buttons do for a product (the Navigator passes these in)
    private final Consumer<Product> onAddToCart;
    private final Consumer<Product> onAddToWishlist;
    private List<Product> products;
    private int pageNum = 0;
    private static final int Page_Products = 8;

    private JPanel productPanelMaker(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        productPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        if (product.getProductImage() != null) {
            ImageIcon productImage = new ImageIcon(product.getProductImage());
            Image scaledImage = productImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            productPanel.add(imageLabel);
        }

        JLabel nameLabel = new JLabel(product.getProductName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPanel.add(nameLabel);

        JLabel priceLabel = new JLabel("$" + String.valueOf(product.getPrice()));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPanel.add(priceLabel);

        JLabel quantityLabel = new JLabel("In Stock: " + String.valueOf(product.getQuantity()));
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPanel.add(quantityLabel);
        productPanel.add(Box.createVerticalStrut(5));

        JButton addToCartButton = new JButton("Add To Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(e -> onAddToCart.accept(product));

        JButton addToWishlistButton = new JButton("Add To Wishlist");
        addToWishlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToWishlistButton.addActionListener(e -> onAddToWishlist.accept(product));

        productPanel.add(addToCartButton);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(addToWishlistButton);

        return productPanel;

    }
    private void pageMaker(JPanel panel, List<Product> products) {
        panel.removeAll();

        if (products.isEmpty()) {
            panel.add(new JLabel("No Products in Inventory"));
            panel.revalidate();
            panel.repaint();
            return;
        }

        int pageStart = pageNum * Page_Products;
        int pageEnd = Math.min(pageStart + Page_Products, products.size());

        for (int i = pageStart; i < pageEnd; i++) {
            panel.add(productPanelMaker(products.get(i)));
        }
        panel.revalidate();
        panel.repaint();
    }

    public Inventory(ProductsRepository productsRepository, Consumer<Product> onAddToCart,
                     Consumer<Product> onAddToWishlist) {

        this.productsRepository = productsRepository;
        this.onAddToCart = onAddToCart;
        this.onAddToWishlist = onAddToWishlist;
        this.products = productsRepository.findAll();


        JFrame frame = new JFrame("Product Inventory");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 10));
        frame.setLayout(new BorderLayout());

        pageMaker(panel, products);

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);



        JLabel msg = new JLabel("Search:");
        msg.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextField search = new JTextField(20);
        search.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton searchButton = new JButton("Search");
        searchButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> {
            String searchText = search.getText();
            if (searchText.isEmpty()) {
                products = productsRepository.findAll();
            }
            else {
                products = productsRepository.findByProductNameContainingIgnoreCase(searchText);
            }

            panel.removeAll();
            pageNum = 0;
            pageMaker(panel, products);
        });


        JButton nextpage = new JButton("Next Page");
        nextpage.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        nextpage.addActionListener(e -> {
            int maxPage = (products.size()-1) / Page_Products;
            if (pageNum < maxPage) {
                pageNum++;
                pageMaker(panel, products);
            }
        });

        JButton previouspage = new JButton("Previous Page");
        previouspage.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        previouspage.addActionListener(e -> {
            if (pageNum > 0) {
                pageNum--;
                pageMaker(panel, products);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(msg);
        topPanel.add(search);
        topPanel.add(searchButton);
        frame.add(topPanel, BorderLayout.NORTH);


        JButton home = new JButton("Return to Home");
        home.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        home.addActionListener(e -> frame.dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(previouspage);
        bottomPanel.add(nextpage);
        bottomPanel.add(home);
        frame.add(bottomPanel, BorderLayout.SOUTH);



        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
