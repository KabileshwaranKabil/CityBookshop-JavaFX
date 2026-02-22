package com.citybookshop.controller;

import com.citybookshop.model.Book;
import com.citybookshop.service.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class StockController {

    @FXML private TextField searchField;
    @FXML private TextArea resultArea;

    private BookService bookService = new BookService();

    @FXML
    private void handleCheckStock() {

        String title = searchField.getText();

        List<Book> books = bookService.searchByTitle(title);

        if (books.isEmpty()) {
            resultArea.setText("No book found.");
        } else {
            Book book = books.get(0);
            resultArea.setText("Stock: " + book.getQuantity()());
        }
    }
}