# Football-Ticket

## Overview
The **Football Ticket System** is a backend application built with **Spring Boot** to manage ticket sales for football matches. It provides APIs for handling user authentication, ticket reservations, and payment processing. The system integrates **Keycloak** for secure authentication and **Stripe** for online payments, ensuring a seamless ticket purchasing experience.

## Features
- User authentication and authorization using **Keycloak**
- Secure **JWT-based** session management
- Ticket booking and seat selection functionality
- **Stripe** integration for secure online payments
- Role-based access control (Admin, User, etc.)
- **MariaDB** as the relational database for storing ticketing data
- **Docker** support for containerized deployment
- API documentation via **Swagger**

## Tech Stack
- **Backend:** Java, Spring Boot
- **Authentication:** Keycloak, JWT Tokens
- **Database:** MariaDB
- **Payment Integration:** Stripe
- **Containerization:** Docker
- **API Documentation & Testing:** Swagger, Postman

## Installation & Setup
### Prerequisites
Ensure you have the following installed:
- Java 23
- Docker & Docker Compose
- Keycloak (or use a hosted instance)
- MariaDB
- Stripe API keys

### Steps to Run
1. **Clone the repository:**
   ```sh
   git clone https://github.com/Football-Ticket/football-ticket-system.git
   cd football-ticket-system
   ```
2. **Configure Environment Variables:**
   Create an `.env` file and set up your database, Keycloak, and Stripe credentials.
   ```env
   DATABASE_URL=jdbc:mariadb://localhost:3306/football_tickets
   DATABASE_USER=root
   DATABASE_PASSWORD=yourpassword
   
   KEYCLOAK_URL=http://localhost:8080
   KEYCLOAK_REALM=your-realm
   KEYCLOAK_CLIENT_ID=myclient
   KEYCLOAK_CLIENT_SECRET=your-secret
   
   STRIPE_SECRET_KEY=your-stripe-secret-key
   STRIPE_PUBLIC_KEY=your-stripe-public-key
   ```
3. **Run MariaDB and Keycloak using Docker:**
   ```sh
   docker-compose up -d
   ```
4. **Build and run the application:**
   ```sh
   ./mvnw spring-boot:run
   ```

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/auth/login` | User login |
| POST | `/auth/register` | User registration |
| GET | `/tickets` | Get available tickets |
| POST | `/tickets/book` | Book a ticket |
| POST | `/payments/checkout` | Process payment with Stripe |

For detailed API documentation, visit `http://localhost:8080/swagger-ui.html`.

## License
This project is open-source and available under the **MIT License**.

## Contributing
Contributions are welcome! Feel free to fork the repo and submit pull requests.

## Contact
For any inquiries, reach out via [your email] or open an issue in the repository.

