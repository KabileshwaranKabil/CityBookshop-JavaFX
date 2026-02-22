# 📚 City Bookshop — OOP Group Assessment

> A Java-based Bookshop Management System developed as a group assignment for the Object-Oriented Programming module. The system automates bookshop transactions using core OOP principles with a JavaFX graphical interface and file-based data storage.

---

## Group Members

| Name             | Email                         |
| ---------------- | ----------------------------- |
| M. Kabileshwaran | kabileshwaran1896@gmail.com   |
| Indunil          |  indunilexm2022@gmail.com     |
| Praveen          | praveenvimukthi2003@gmail.com |
| Aakash           | —                             |
| M. Dinush Khan   | dinushkhan1214@gmail.com      |

---

## 🎯 System Overview

**City Bookshop** is a desktop application that supports two user roles:

**Cashier**

- View all books
- Search books by name, category, and price
- View stock levels

**Manager** _(all Cashier features plus)_

- Add new books and categories
- Create new user accounts (Cashier or Manager)

---

## Technologies Used

| Technology    | Purpose                           |
| ------------- | --------------------------------- |
| Java JDK 17+  | Core programming language         |
| JavaFX        | Graphical User Interface          |
| Java File I/O | Data storage and retrieval        |
| Scene Builder | FXML screen design                |
| Git & GitHub  | Version control and collaboration |

---

## 🏗️ Project Structure

```
CityBookshop/
│
├── src/
│   └── com/citybookshop/
│       ├── Main.java                         ← App entry point
│       │
│       ├── model/
│       │   ├── User.java
│       │   ├── Cashier.java
│       │   ├── Manager.java
│       │   ├── Book.java
│       │   └── Category.java
│       │
│       ├── service/
│       │   ├── FileHandler.java
│       │   ├── UserService.java
│       │   ├── BookService.java
│       │   └── CategoryService.java
│       │
│       ├── controller/
│       │   ├── LoginController.java
│       │   ├── DashboardController.java
│       │   ├── BookController.java
│       │   ├── StockController.java
│       │   ├── AddBookController.java
│       │   ├── AddCategoryController.java
│       │   └── AccountController.java
│       │
│       └── view/
│           ├── Login.fxml
│           ├── Dashboard.fxml
│           ├── Books.fxml
│           ├── Stock.fxml
│           ├── AddBook.fxml
│           ├── AddCategory.fxml
│           └── CreateAccount.fxml
│
├── data/
│   ├── books.txt
│   ├── categories.txt
│   └── users.txt
│
└── docs/
    └── Assignment_Specification.pdf
```

---

## OOP Concepts Applied

| Concept            | Where It Is Applied                                          |
| ------------------ | ------------------------------------------------------------ |
| **Class & Object** | `Book`, `User`, `Category` classes instantiated as objects   |
| **Encapsulation**  | All fields are `private` with getters and setters            |
| **Inheritance**    | `Cashier` and `Manager` both extend abstract `User`          |
| **Abstraction**    | `User` is abstract with abstract method `getDetails()`       |
| **Polymorphism**   | `getDetails()` behaves differently in `Manager` vs `Cashier` |

---

## Application Screens

| Screen         | Access                      |
| -------------- | --------------------------- |
| Login          | All users                   |
| Dashboard      | All users (role-based menu) |
| View Books     | All users                   |
| Search Books   | All users                   |
| View Stock     | All users                   |
| Add New Book   | Manager only                |
| Add Category   | Manager only                |
| Create Account | Manager only                |

---

## 📁 Data Storage Format

All data is stored in plain text files inside the `/data` folder.

**users.txt**

```
USR001|admin|admin123|Manager
USR002|john|pass123|Cashier
```

**books.txt**

```
BK001|Clean Code|Robert C. Martin|Programming|5500|20
BK002|The Great Gatsby|F. Scott Fitzgerald|Fiction|1850|50
```

**categories.txt**

```
CAT001|Fiction|Books that contain fictional stories
CAT002|Programming|Books related to software development
```

---

## How to Run

**Prerequisites:**

- Java JDK 17 or higher
- JavaFX SDK
- IntelliJ IDEA or Eclipse with JavaFX support

**Steps:**

1. Clone the repository

```bash
git clone https://github.com/KabileshwaranKabil/CityBookshop-JavaFX.git
```

2. Open the project in IntelliJ IDEA or Eclipse

3. Add JavaFX SDK to the project library

4. Add VM options in run configuration

```
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
```

5. Run `Main.java`

6. Login with the default admin account

```
Username: admin
Password: admin123
```

---

## 📎 Assignment Specification

The official assignment document is available in the `/docs` folder.

_Developed by Team 05 · OOP Group Assignment · 2026_
