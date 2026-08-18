# Movie Ticket Booking System

A JavaFX desktop application developed for **COMP 380 – Software Engineering** at **California State University, Northridge (CSUN)**. The application provides a movie ticket booking workflow that allows users to browse movies, view showtimes, select seats, complete a simulated payment, and manage their bookings.

---

## Technologies Used

* Java 25
* JavaFX
* Maven
* MongoDB Atlas
* JUnit 5
* Git & GitHub
* Jira
* VS Code

---

## Features

### Customer Features

* User registration and login
* Browse available movies
* Search movies by title or description
* View movie details
* View available movie dates and showtimes
* Select available seats
* View checkout summary
* Payment input validation
* Simulated payment processing
* View upcoming bookings
* Cancel upcoming bookings
* Account management

### Administrative Features

* User account management
* Administrative movie management *(planned)*
* Administrative reporting features *(planned)*

---

## Architecture

The application follows an **MVC-style architecture**.

* **Model** – Represents domain objects such as users, movies, showtimes, seats, tickets, and bookings.
* **View** – JavaFX FXML files define the application's user interface.
* **Controller** – Handles user interactions and communication between the application's views and models.

The application also uses centralized utility and management classes for functionality shared across multiple controllers.

* **SceneManager** – Handles JavaFX scene navigation and data passing between controllers.
* **SessionManager** – Maintains information about the currently logged-in user.
* **DatabaseService** – Manages the application's MongoDB connection.
* **BookingManager** – Maintains bookings created during the current application session.

---

## Project Structure

```text
src/main
├── java/org/ScrumLords
│   ├── controller
│   ├── model
│   ├── BookingManager.java
│   ├── DatabaseService.java
│   ├── SceneManager.java
│   └── SessionManager.java
│
└── resources/org/ScrumLords
    ├── images
    └── view
```

---

## Application Flow

```text
Login / Registration
        ↓
    Main Page
        ↓
  Movie Gallery
        ↓
  Movie Details
        ↓
Movie Showtimes
        ↓
Ticket Selection
        ↓
     Payment
        ↓
  My Bookings
```

Users can also access **My Bookings** directly from the Main Page to view or cancel bookings created during the current application session.

---

## Payment Validation

Payment processing is simulated for demonstration purposes.

Before a payment is accepted, the application performs basic input validation on payment information, including:

* Cardholder name
* Card number
* Expiration date
* CVV
* Billing ZIP code

Invalid payment information prevents the booking workflow from continuing and displays an error message to the user.

No real financial transaction or external payment service is used.

---

## Booking Management

After a successful simulated payment, the booking is added to the user's upcoming bookings and displayed on the **My Bookings** page.

Bookings created during the current application session are maintained by `BookingManager`. This allows booking information to remain available while navigating between different application scenes.

Users can select and cancel an upcoming booking. Cancelled bookings are removed from the current session's booking list.

> **Note:** Booking persistence is currently session-based. Booking information is reset when the application is closed.

---

## Running the Project

Clone the repository:

```bash
git clone https://github.com/COMP-380-10548/MovieTicketSystem.git
```

Navigate to the project directory:

```bash
cd MovieTicketSystem
```

Create the environment configuration file from the provided example:

```bash
cp .env.example .env
```

Configure the required MongoDB connection information in the `.env` file.

Run the application using the Maven wrapper:

### Windows

```powershell
.\mvnw.cmd clean javafx:run
```

### macOS / Linux

```bash
./mvnw clean javafx:run
```

---

## Project Workflow

Development follows a feature-branch workflow.

Each team member develops features in individual branches created from the `dev` branch. Completed work is submitted through GitHub Pull Requests before being merged into `dev`.

The `dev` branch serves as the primary integration branch for completed project features.

---

## Known Limitations

* Payment processing is simulated and does not perform real financial transactions.
* Booking persistence is currently limited to the active application session.
* Some application data uses seed/demo data rather than complete database-backed persistence.
* Administrative movie management and reporting functionality are not fully implemented.
* Database integration is not complete across every application feature.

---

## Future Improvements

* Complete MongoDB persistence across application features
* Persistent booking history across application sessions
* Ticket generation
* Concessions and shopping cart functionality
* Administrative dashboard
* Sales and booking reporting
* Real payment processing integration
* Additional automated testing

---

## Team Members

* Andrew Garcia
* Cameron Wichman
* Erik Avalyan
* Ian Coumbe

---

## License

Developed as part of **COMP 380 – Software Engineering** at **California State University, Northridge (CSUN)** for educational purposes.