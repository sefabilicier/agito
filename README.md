
# 1. Introduction

**Project Description**

This project aims to develop an ERP (Enterprise Resource Planning) web application to apply the knowledge gained during academic life to real-world industry scenarios.

### Target Audience

This project is specifically designed for:

- **Insurance Companies**
- **Software Companies Consulting for Insurance Firms**
- **Individuals and Organizations Seeking Insurance**

### Use Case Scenario: Customer Insurance Application

The application allows individuals or companies to become insurance policyholders by entering the required information about the insurance they wish to obtain.

### Steps:

1. **User Registration/Login**
2. **Insurance Selection → customer(person || company)**
3. **Information Entry**
    - Personal or company information (name, contact details, etc.)
    - Specific requirements for the insurance policy (coverage amount, type of coverage, etc.)
    - Any additional information relevant to the insurance (e.g., existing policies, special conditions).
4. **Review and Submit**
5. **Confirmation:** The application is processed, and the user receives a confirmation notification via message or email, along with next steps (e.g., underwriting process, additional documentation if needed).
6. **Policy Issuance:** Upon approval, the insurance policy is issued, and the user can access it through their account.


# 2. Technological Infrastructure

This ERP web application utilizes a comprehensive set of technologies and methodologies designed to ensure robustness, scalability, and maintainability. The key components are outlined below:

### 1. Development Methodologies

- **Clean Code Practices:**
    - Adopts best practices for writing readable and maintainable code, such as meaningful naming conventions, modular functions, and comprehensive comments.
- **Clean Architecture Methods:**
    - Focuses on the separation of concerns, promoting independence from frameworks and UI, making the application easier to test and maintain.

### 2. Frameworks and Libraries

- **Spring Framework:**
    - **Spring Boot:** Simplifies the development process with convention over configuration, enabling rapid application development.
    - **Spring Security:** Provides robust security features for authentication and authorization.
    - **Spring Data JPA:** Facilitates data access and manipulation, using repositories for cleaner data handling.
    - **Lombok:** Reduces boilerplate code for model classes by generating getters, setters, and other methods at compile time.
    - **ModelMapper:** Streamlines object mapping between different layers of the application.

### 3. Database

- **Oracle Database:**
    - Utilizes PL/SQL for complex queries and stored procedures, ensuring efficient data operations.

### 4. Testing Frameworks

- **JUnit Testing:**
    - **Jupiter Engine:** Utilized for writing and executing tests.
    - **JUnit Params:** Facilitates parameterized tests for more comprehensive coverage.
    - **Mockito:** Mocks dependencies to isolate tests, allowing for more focused testing.
    - **AssertJ Core:** Provides fluent assertions for clearer test cases.
    - **Google Code Catch Exceptions:** Helps in testing exception handling.
    - **JavaFaker:** Generates fake data for testing purposes.

### 5. Logging

- **Log4j, SLF4J, Logback:**
    - These libraries are integrated for effective logging strategies, enabling better monitoring and troubleshooting.
- **Spring Boot Logging:**
    - Utilizes built-in logging configurations for streamlined logging management.

### 6. Caching

- **Spring Boot Starter Cache:**
    - Implements caching mechanisms to improve performance and reduce database load.

### 7. Utilities

- **Google Guava:**
    - Provides a set of core libraries that enhance Java collections, caching, and more.

### 8. User Interface

- **Thymeleaf UI:**
    - A modern server-side Java template engine that simplifies rendering web pages.

### 9. Communication

- **Spring Boot Starter Mail:**
    - Facilitates email communication within the application.
- **Twilio:**
    - Integrates SMS and other communication services for user notifications.

### 10. Monitoring and Actuation

- **Spring Boot Starter Actuator:**
    - Provides production-ready features such as monitoring and management endpoints.
- **Prometheus and Grafana:**
    - Used for monitoring application metrics and visualizing data in real time.

### 11. Cloud Services

- **AWS (S3, Lambda):**
    - Utilizes AWS S3 for file storage and AWS Lambda for serverless computing functions, enhancing scalability and cost-efficiency.

# 3. Project Architecture

The architecture of the ERP web application is designed to enhance modularity, maintainability, and scalability. By following an N-tier architecture, we can separate concerns into distinct layers, allowing for clear responsibilities and easier testing. Each layer serves a specific function, from handling user interactions to managing data access, and is built with best practices in mind. Below is a detailed breakdown of each layer and its purpose:

### 1. Architecture

- **N-Tier Architecture:**
    - The application is structured into distinct layers:
        - **Common:**
            - Project commons such as exceptions, IyzicoPayment, and result types (for the API such as DataResult, SuccessDataResult, ErrorDataResult, Result, SuccessResult, ErrorResult).
            - Utilities for clean code are separated to enhance maintainability and reuse.
        - **Configuration:**
            - Contains configurations for various components, including:
                - AdminServerApplication (Spring Boot Actuator)
                - AsyncConfiguration
                - CachingConfiguration
                - ExceptionConfiguration
                - LoggingConfiguration
                - MailConfiguration
                - MapperConfiguration
                - S3Configuration
                - SecurityConfiguration
                - ThymeleafConfiguration
                - WebConfiguration
        - **DTO (Data Transfer Object):**
            - Defines objects for transferring data, including:
                - Mappers
                - Requests
                - Responses
        - **Helper:**
            - Contains the Messages class for API responses, facilitating internationalization and consistency in messaging.
        - **Logging:**
            - Manages logging rules to standardize logging practices across the application.
        - **Models:**
            - Includes concrete classes (entities) and enums that represent the data structure.
        - **Repository:**
            - Provides data access layer abstractions and concrete implementations for interacting with the database.
        - **Security:**
            - Defines security rules for both admin and customer roles, ensuring proper access control.
        - **Service:**
            - Contains abstract and concrete service classes, including NotificationCenter and business rules, for handling application logic.
        - **WebAPI:**
            - Manages web interactions through:
                - Concrete classes
                - SecurityTemplate
                - ThymeleafControllers
                - UI pages (home, login, logout)

# 4. Setup and Configuration

This section outlines the steps required to develop and run the application. Below are detailed instructions for installing and configuring it.

### 1. Development Environment Setup

- **Required Software:**
    - **JDK (Java Development Kit):** Download and install the latest version from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.java.net/).
    - **Apache Maven:** Used for managing project dependencies. [Download Maven](https://maven.apache.org/download.cgi).
    - **An IDE (Integrated Development Environment):** You can use IntelliJ IDEA, Eclipse, or Spring Tool Suite.

### 2. Cloning the Project

- Clone the project from Git:
    
    ```bash
    git clone https://github.com/sefabilicier/agito.git
    cd <project-directory-you-installed>
    ```

### 3. Installing Maven Dependencies

- Run the following command in the project directory to load the required Maven dependencies:
    
    ```bash
    mvn clean install
    ```

### 4. Database Setup

- **Oracle Database:**
    - Install the Oracle database and configure it as needed.
    - Create a database for the application and execute PL/SQL scripts to create necessary tables.

### 5. Application Configuration

- Configure necessary settings in the **application.properties** or **application.yml** file:
    - Database connection settings (URL, username, password).
    - Spring Security settings.
    - Caching configurations.

### 6. Using Docker (Optional)

- If you want to run the application in Docker containers, here is **Dockerfile** and **docker-compose.yml** file. You can start the application with the following command:
    
    ```bash
    docker-compose up --build
    ```

The application will be accessible at `http://localhost:8080`.

### 7. Starting the Application

- To start the application with Maven, use:
    
    ```bash
    mvn spring-boot:run
    ```

### 8. Testing and Verification

- Once your application is running, check that it is working correctly by navigating to `http://localhost:8080` in your web browser.

For API URLs:

- `http://localhost:8080/api/customer/get-all`
- `http://localhost:8080/api/customer/add`
- `http://localhost:8080/api/customer/update/1`
- `http://localhost:8080/api/customer/delete-by-id/2`

For UI URLs:

- `http://localhost:8080/ui/customer/get-all`
- `http://localhost:8080/ui/customer/add`
- `http://localhost:8080/ui/customer/update/1`
- `http://localhost:8080/ui/customer/delete-by-id/2`

### 9. Stopping the Application

To stop the application and remove containers, networks, and images created by `docker-compose up`, use:

```bash
docker-compose down
```

### 10. Accessing Prometheus and Grafana

1. **Access Prometheus**:
    - In the address bar, type: `http://localhost:9090`
    - Press **Enter** to go to the Prometheus interface.
2. **Access Grafana**:
    - In a new tab, type: `http://localhost:3000`
    - Press **Enter** to go to the Grafana interface.
    - **Login Credentials**:
        - Username: `admin`
        - Password: (use the password you set in the `docker-compose.yml` file)
3. **View Dashboards in Grafana**:
    - After logging in, you should see the existing dashboards.
    - Click on any dashboard to view metrics visualizations.

## Interacting with the Dashboards

- **Explore Metrics**:
    - You can click on different panels in the dashboards to see various metrics visualized.
- **Use the Explore Feature** (optional):
    - If you want to query specific metrics, click on the **Explore** icon (compass) in the left sidebar to run ad-hoc queries.

## Monitoring Application Performance

- Keep an eye on the dashboards to monitor the performance of the application in real time.

# 5. Contact - Question

<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--!Font Awesome Free 6.6.0 by @fontawesome - [https://fontawesome.com](https://fontawesome.com/) License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M100.3 448H7.4V148.9h92.9zM53.8 108.1C24.1 108.1 0 83.5 0 53.8a53.8 53.8 0 0 1 107.6 0c0 29.7-24.1 54.3-53.8 54.3zM447.9 448h-92.7V302.4c0-34.7-.7-79.2-48.3-79.2-48.3 0-55.7 37.7-55.7 76.7V448h-92.8V148.9h89.1v40.8h1.3c12.4-23.5 42.7-48.3 87.9-48.3 94 0 111.3 61.9 111.3 142.3V448z"/></svg>

[https://tr.linkedin.com/in/sefabilicier](https://tr.linkedin.com/in/sefabilicier)
