package com.citybookshop.controller;

import com.citybookshop.model.Book;
import com.citybookshop.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class BookController {

    @FXML private TableView<Book> bookTable;
    @FXML private TextField searchField;

    private BookService bookService = new BookService();

    @FXML
    private void initialize() {
        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = bookService.getAllBooks();
        ObservableList<Book> bookList = FXCollections.observableArrayList(books);
        bookTable.setItems(bookList);
    }

    @FXML
    private void handleSearch() {

        String keyword = searchField.getText();

        List<Book> books = bookService.searchByTitle(keyword);
        bookTable.setItems(FXCollections.observableArrayList(books));
    }
}