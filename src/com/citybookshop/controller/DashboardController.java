package com.citybookshop.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.citybookshop.model.User;

public class DashboardController {
    @FXML private Label welcomeLabel;

    public void setUserName(String name) {
        welcomeLabel.setText("Welcome " + name);
    }
}

public class DashboardController {
    public void setUser(User loggedInUser) {

    }
}

