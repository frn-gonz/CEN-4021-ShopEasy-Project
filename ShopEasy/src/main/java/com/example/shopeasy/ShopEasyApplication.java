package com.example.shopeasy;

import com.example.shopeasy.UserInterface.Navigator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.SwingUtilities;

@SpringBootApplication
public class ShopEasyApplication {

    public static void main(String[] args) {
        // Spring Boot runs headless by default, which blocks Swing windows
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ShopEasyApplication.class)
                .headless(false)
                .run(args);

        // Swing components must be created on the Swing event thread
        Navigator navigator = context.getBean(Navigator.class);
        SwingUtilities.invokeLater(navigator::openLogin);
    }

}
