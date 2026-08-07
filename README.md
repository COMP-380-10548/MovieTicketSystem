# Movie Ticket Booking System

A JavaFX desktop application developed for **COMP 380 – Software Engineering** at **California State University, Northridge (CSUN)**. The application allows users to browse movies, view showtimes, select seats, and simulate the movie ticket purchasing process.

---

## Technologies Used

- Java 25
- JavaFX
- Maven
- MongoDB Atlas
- Git & GitHub
- Jira
- VS Code

---

## Features

### Customer Features

- User registration and login
- Browse available movies
- Search movies by title or description
- View movie details
- View available showtimes
- Select available seats
- View checkout summary
- Simulated payment screen
- Account management

### Administrative Features

- User account management
- Administrative movie management *(planned)*
- Administrative reporting features *(planned)*

---

## Architecture

The application follows the **Model-View-Controller (MVC)** architectural pattern.

- **Model** – Represents domain objects and application data.
- **View** – JavaFX FXML user interface files.
- **Controller** – Handles user interaction, business logic, and scene navigation.

---

## Project Structure

```text
src
├── controller
├── model
├── resources
│   ├── images
│   └── view
└── SceneManager.java
```

---

## Application Flow

```text
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
```

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

Run the application:

```bash
mvn clean javafx:run
```

> **Note:** Payment processing is currently simulated for demonstration purposes.

---

## Project Workflow

Each team member develops features in individual feature branches created from the `dev` branch.

Completed work is submitted through Pull Requests and reviewed before being merged into the `dev` branch.

At the end of each sprint, the `dev` branch is merged into `main` after team approval.

---

## Team Members

- Andrew Garcia
- Cameron Wichman
- Erik Avalyan
- Ian Coumbe

---

## Future Improvements

- MongoDB data persistence
- Booking history
- Shopping cart
- Ticket generation
- Concessions
- Administrative dashboard
- Sales reporting
- Payment processing integration

---

## License

Developed as part of **COMP 380 – Software Engineering** at **California State University, Northridge (CSUN)** for educational purposes.