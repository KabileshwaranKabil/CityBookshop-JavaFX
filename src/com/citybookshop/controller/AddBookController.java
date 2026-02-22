package com.citybookshop.controller;

import java.lang.classfile.Label;

import com.citybookshop.model.Book;
import com.citybookshop.service.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private TextField categoryField;
    @FXML private Label messageLabel;

    private BookService bookService = new BookService();

    @FXML
    private void handleAddBook() {

        String title = titleField.getText();
        String author = authorField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityFieldField.getText());
        String category = categoryField.getText();

        Book book = new Book(title, author, price, quantity, category);

        //bookService.addBook(book);
        bookService.addBook(title, author, price, quantity, category);

        messageLabel.setText("Book Added Successfully");
    }
}