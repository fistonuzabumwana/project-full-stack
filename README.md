Fruit Mart - E-commerce Application
---------------------------------------------
Project Overview
-----------------
Fruit Mart is an e-commerce platform designed to sell a variety of fruits and related products. It targets online shoppers looking for fresh produce, juices, and smoothies, with an admin interface for managing categories and products. The application is built with a focus on user experience and robust error handling.
Features

Global Exception Handling: Custom error pages for 404, 400, and 500 errors with detailed logging.
Category Management: Add, edit, and delete categories with image uploads and status toggling.
Product Management: Add products with titles, descriptions, prices, stock, categories, and images, with required field validation.
Welcome Page: Dynamic carousel with professional gradient overlay for text visibility, showcasing new arrivals, sales, and categories.
Responsive Design: Utilizes Bootstrap for a mobile-friendly interface with smooth scrolling and animations.

Technologies Used
----------------

Backend: Spring Boot
Frontend: Thymeleaf, Bootstrap 5.3.0, Animate.css 4.1.1
Database: PostgreSQL
Other: Maven, SLF4J for logging, Font Awesome

Project Structure
src/main/
├── java/
│   └── com.ecom/
│       ├── config/              # Configuration classes (e.g., GlobalExceptionHandler)
│       ├── controller/          # Controllers (e.g., AdminController, HomeController)
│       ├── model/               # Entity classes (e.g., Category, Product)
│       ├── repository/          # JPA repositories (e.g., CategoryRepository)
│       ├── service/             # Service layer (e.g., CategoryService)
│       └── util/                # Utility classes
├── resources/
│   ├── static/                  # Static assets (CSS, images)
│   └── templates/               # Thymeleaf templates (e.g., index.html, category.html)
├── application.properties       # Configuration file

Setup Instructions
Prerequisites

Java 17 or higher
Maven 3.6+
PostgreSQL 15+
Git

Steps

Clone the Repository
git clone https://github.com/yourusername/fruit-mart.git
cd fruit-mart


Configure PostgreSQL

Create a database named fruit_mart.
Update application.properties with your PostgreSQL credentials:spring.datasource.url=jdbc:postgresql://localhost:5432/fruit_mart
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update




Install Dependencies
mvn clean install


Run the Application
mvn spring-boot:run

Access the application at http://localhost:8080.


Usage Guide

Home Page: Visit / to see the carousel with product highlights.
Admin Category Management: Navigate to /admin/category to add, edit, or delete categories (requires admin access).
Admin Product Management: Go to /admin/addProduct to add new products with required fields (title, description, category, price, stock, image).

Enhancements and Contributions

Added gradient overlay to the carousel for better text visibility.
Implemented required field validation for category and product forms.
Contributions are welcome! Please submit pull requests or create issues on GitHub.

Known Issues and Future Work

Resolved Whitelabel Error Page issue related to static resource handling.
Future work: Add user authentication, shopping cart functionality, and order management.

License
[Specify license or note "No license specified"]
