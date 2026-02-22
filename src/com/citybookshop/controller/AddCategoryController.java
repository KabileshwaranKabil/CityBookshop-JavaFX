package com.citybookshop.controller;

import java.lang.classfile.Label;

import com.citybookshop.model.Category;
import com.citybookshop.service.CategoryService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddCategoryController {

    @FXML private TextField categoryNameField;
    @FXML private Label messageLabel;
    @FXML private TextField discriptionField;

    private CategoryService categoryService = new CategoryService();

    @FXML
    private void handleAddCategory() {

        String name = categoryNameField.getText();
        String discription = discriptionField.getText();

        Category category = new Category(name, discription);

        //categoryService.addCategory(category);
        categoryService.addCategory(name, discription);

        messageLabel.setText("Category Added Successfully");
    }
}