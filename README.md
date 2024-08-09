# Foodies

Foodies is a desktop food ordering application built using JavaFX and Gradle. This application allows users to browse menus, place orders, manage their profiles, and search for specific menu items. It uses PostgreSQL as the database and follows the MVC (Model-View-Controller) architectural pattern.

## Features

- **User Authentication**: Login and register functionality for users.
- **Browse Menus**: View latest and popular menus.
- **Search Menu**: Search for specific menus.
- **Order Menu**: Place orders for menu items.
- **Order History**: View past orders.
- **User Profile**: Show user information.
- **Address Management**: Add and delete user address.

## Technologies Used

- **JavaFX**: For building the user interface.
- **Gradle**: For project build and dependency management.
- **PostgreSQL**: For the database.
- **MVC Pattern**: For structuring the application.

## Setup and Installation

### Prerequisites

- Java 21 or higher
- Gradle
- PostgreSQL

### Steps

1.  **Clone the repository**:

    ```bash
    git clone https://github.com/kuswandev/foodies-desktop.git
    cd foodies-desktop
    ```

2.  **Set up the PostgreSQL database**:

    Create a database named `foodies_db`.

3.  **Create the tables**:

    Execute the following SQL script to create the required tables:

    ```sql
    CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        email TEXT UNIQUE,
        name TEXT,
        password TEXT
    );

    CREATE TABLE addresses (
        id SERIAL PRIMARY KEY,
        user_id INT,
        address TEXT,
        FOREIGN KEY (user_id) REFERENCES users(id)
    );

    CREATE TABLE menus (
        id SERIAL PRIMARY KEY,
        category TEXT,
        description TEXT,
        image TEXT,
        name TEXT,
        price INTEGER,
        rating DOUBLE PRECISION
    );

    CREATE TABLE orders (
        id SERIAL PRIMARY KEY,
        created_at TIMESTAMP DEFAULT current_timestamp,
        menu_id INT,
        user_id INT,
        amount INT,
        menu_quantity INT,
        payment_method TEXT,
        status TEXT,
        FOREIGN KEY (menu_id) REFERENCES menus(id),
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
    ```

4.  **Insert sample data**:

    Execute the following SQL script to insert sample data into the `menus` table:

    ```sql
    INSERT INTO menus (category, description, image, name, price, rating)
    VALUES
        ('Beverages', 'Freshly brewed coffee made from Arabica beans...', 'coffee.png', 'Coffee', 15000, 4.5),
        ('Beverages', 'Green tea with a hint of honey...', 'green-tea.png', 'Green Tea', 20000, 4.7),
        ('Appetizers', 'Crispy french fries with ketchup and mayo...', 'fries.png', 'French Fries', 25000, 4.3),
        ('Appetizers', 'Garlic bread topped with melted cheese...', 'garlic-bread.png', 'Garlic Bread', 30000, 4.6),
        ('Main Course', 'Grilled chicken with BBQ sauce, vegetables...', 'grilled-chicken.png', 'Grilled Chicken', 60000, 4.8),
        ('Main Course', 'Spaghetti carbonara with creamy sauce...', 'spaghetti.png', 'Spaghetti Carbonara', 55000, 4.4),
        ('Desserts', 'Chocolate lava cake with molten center...', 'chocolate-lava.png', 'Chocolate Lava Cake', 45000, 4.9),
        ('Desserts', 'Vanilla ice cream made from vanilla beans...', 'vanilla-ice-cream.png', 'Vanilla Ice Cream', 20000, 4.2),
        ('Beverages', 'Smoothie with mixed berries...', 'smoothie.png', 'Berry Smoothie', 35000, 4.5),
        ('Beverages', 'Iced lemon tea with mint...', 'lemon-tea.png', 'Iced Lemon Tea', 18000, 4.1);
    ```

5.  **Add PostgreSQL dependency**:

    Add the PostgreSQL dependency to your `build.gradle` file:

    ```gradle
    dependencies {
        ...
        implementation('org.postgresql:postgresql:42.7.3')
    }
    ```

6.  **Update database configuration**:

    Open `src/main/java/com/me/foodies/utils/Database.java` and update the database connection properties:

    ```java
    String url = "jdbc:postgresql://localhost:5432/foodies_db";
    String user = "your_database_user";
    String password = "your_database_password";
    ```

7.  **Build and run the project**:

    ```sh
    ./gradlew build
    ./gradlew run
    ```

## Usage

- **Register**: Create a new user account.
- **Login**: Authenticate with your user account.
- **Browse Menus**: View latest and popular menus.
- **Search Menu**: Search for specific menu items.
- **Order Menu**: Select items and place orders.
- **Order History**: View past orders.
- **User Profile**: Show user information.
- **Address Management**: Add and delete addresses.

## Previews

You can find screenshots of the application in the `previews` directory.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License.
