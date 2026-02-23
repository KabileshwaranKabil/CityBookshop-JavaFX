package com.citybookshop.controller;

import com.citybookshop.model.User;
import com.citybookshop.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;
    private UserService userService;

    @FXML
    public void initialize(){
        userService = new UserService();
        errorLabel.setText("");
    }
    @FXML
    public void handleLogin() throws IOException {
        String username=usernameField.getText();
        String password=passwordField.getText();

        if(username.isEmpty() || password.isEmpty()){
            errorLabel.setText("Please Enter Username or Password");
            return;
        }
        User loggedInUser=userService.login(username,password);
        if(loggedInUser!=null){
            loadDashboard(loggedInUser);
        }else{
            errorLabel.setText("Invalid username or password");
            passwordField.clear();
        }
    }

    private void loadDashboard(User loggedInUser) throws IOException {
        // 1. Load the Dashboard.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/citybookshop/view/Dashboard.fxml"));

        // 2. Create the scene from the loaded FXML
        Parent root=loader.load();

        // 3. Get the DashboardController from the loader
        DashboardController dController=loader.getController();

        // 4. Pass the logged in user to the dashboard
        dController.setUser(loggedInUser);

        // 5. Get the current window (Stage)
        Stage stage=(Stage) loginButton.getScene().getWindow();


        // 6. Create a new scene with the dashboard layout
        Scene scene = new Scene(root);


        // 7. Set the new scene on the stage
        stage.setScene(scene);

        // 8. Show the stage
        stage.show();
    }
}
