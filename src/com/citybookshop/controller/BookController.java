package com.citybookshop.controller;

import com.citybookshop.model.Book;
import com.citybookshop.model.User;
import com.citybookshop.service.BookService;
import com.citybookshop.service.CategoryService;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookController {
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, String> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, Double> priceCol;
    @FXML private TableColumn<Book, Integer> quantityCol;
    @FXML private TableColumn<Book, String> categoryCol;
    @FXML private ComboBox<String> categoryCombo;
    @FXML private TextField titleField;
    @FXML private TextField minPriceField;
    @FXML private TextField maxPriceField;
    @FXML private Button searchButton;
    @FXML private Button clearButton;
    @FXML private Button backButton;
    @FXML private Button logout;
    @FXML private Label roleBtn;
    @FXML private Button viewBooks;
    @FXML private Button searchBooks;
    @FXML private Button viewStocks;
    @FXML private Label resultLabel;
    @FXML private Label welcomeLabel;

    private BookService bookService;
    private CategoryService categoryService;
    private ObservableList<Book> books;
    private User loggedInUser;

    public void setUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        if (loggedInUser != null) {
            welcomeLabel.setText("Welcome, " + loggedInUser.getUsername());
            roleBtn.setText(loggedInUser.getRole());
        }
    }

    @FXML
    public void initialize() {
        bookService = new BookService();
        categoryService = new CategoryService();
        setUpTable();
        loadBooks();
        populateCategories();
    }

    private ObservableList<Book> loadFromFile(String filePath) {
        List<Book> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    list.add(new Book(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            Integer.parseInt(parts[4].trim()),
                            parts[5].trim()
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FXCollections.observableArrayList(list);
    }

    private void setUpTable() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getBookId()));
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        priceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());
        quantityCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()).asObject());
        categoryCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategoryName()));

    }

    private void loadBooks() {
        books = loadFromFile("src/data/books.txt");
        bookTable.setItems(books);
        resultLabel.setText("Total books: " + books.size());

    }

    private void populateCategories() {
        List<String> categories = categoryService.getCategoryName();
        categories.add(0, "All");
        categoryCombo.setItems(FXCollections.observableArrayList(categories));
        categoryCombo.setValue("All");
    }

    @FXML
    private void handleSearch() {
        String title = titleField.getText().trim();
        String category = categoryCombo.getValue();
        double minPrice = 0;
        double maxPrice = Double.MAX_VALUE;

        try {
            if (!minPriceField.getText().isEmpty()) {
                minPrice = Double.parseDouble(minPriceField.getText());
            }
            if (!maxPriceField.getText().isEmpty()) {
                maxPrice = Double.parseDouble(maxPriceField.getText());
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid price range");
            return;
        }

        double finalMinPrice = minPrice;
        double finalMaxPrice = maxPrice;

        List<Book> filtered = books.stream()
                .filter(b -> title.isEmpty() || b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(b -> "All".equals(category) || b.getCategoryName().equals(category))
                .filter(b -> b.getPrice() >= finalMinPrice && b.getPrice() <= finalMaxPrice)
                .collect(Collectors.toList());

        bookTable.setItems(FXCollections.observableArrayList(filtered));
        resultLabel.setText("Found " + filtered.size() + " books");
    }

    @FXML
    private void handleClear() {
        titleField.clear();
        minPriceField.clear();
        maxPriceField.clear();
        categoryCombo.setValue("All");
        loadBooks();
    }

    @FXML
    private void handleBack() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/citybookshop/view/Dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root);

            FadeTransition fade = new FadeTransition(Duration.millis(300), root);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.play();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load Dashboard. Please try again.");
        }
    }

    private void showError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Navigation Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    private void handleLogout() throws IOException {
        loadView("/com/citybookshop/view/Login.fxml");
    }

    @FXML
    private void handleViewBooks() throws IOException {
        loadBooks();
    }

    @FXML
    private void handleSearchBooks() throws IOException {
        loadBooks();
    }

    @FXML
    private void handleViewStocks() throws IOException {
        loadView("/com/citybookshop/view/Stock.fxml");
    }

    private void loadView(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
