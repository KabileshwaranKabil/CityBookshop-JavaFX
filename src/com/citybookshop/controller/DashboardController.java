package com.citybookshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML private Label welcomeLabel;

    public void setUserName(String name) {
        welcomeLabel.setText("Welcome " + name);
    }
}