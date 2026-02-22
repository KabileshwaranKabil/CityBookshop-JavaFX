package com.citybookshop.controller;

import com.citybookshop.model.User;
import com.citybookshop.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AccountController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleBox;
    @FXML private Label messageLabel;

    private UserService userService = new UserService();

    @FXML
    private void initialize() {
        roleBox.getItems().addAll("Cashier", "Manager");
    }

    @FXML
    private void handleCreateAccount() {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleBox.getValue();

        userService.createAccount(username, password, role);

        messageLabel.setText("Account Created Successfully");
    }
}