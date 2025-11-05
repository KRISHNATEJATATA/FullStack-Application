# Full-Stack Web Application

A comprehensive full-stack web application built with **Spring Boot** (backend), **React** (frontend), **MySQL** (authentication database), and **MongoDB** (application data database). Features JWT-based authentication with role-based access control (USER and ADMIN roles).

## 🏗️ Architecture

- **Frontend**: React 18 with Vite, React Router, TailwindCSS
- **Backend**: Spring Boot 3.2.0 with Java 17
- **Databases**:
  - MySQL 8.0 → User authentication and authorization
  - MongoDB 7.0 → Application data (Products)
- **Authentication**: JWT (JSON Web Token)
- **Authorization**: Role-based (USER, ADMIN)

## 📋 Prerequisites

- Java 17 or higher
- Node.js 18+ and npm
- Maven 3.6+
- MySQL 8.0+ (or use Docker)
- MongoDB 7.0+ (or use Docker)
- Docker and Docker Compose (optional)

## 🚀 Quick Start

### Option 1: Using Docker Compose (Recommended)

1. **Clone and navigate to the project**:
   ```bash
   cd project
   ```

2. **Start all services**:
   ```bash
   docker-compose up -d
   ```

   This will start:
   - MySQL on port 3306
   - MongoDB on port 27017
   - Spring Boot backend on port 8080
   - React frontend on port 3000

3. **Access the application**:
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080/api

### Option 2: Manual Setup

#### Backend Setup

1. **Start MySQL and MongoDB**:
   ```bash
   # Using Docker for databases only
   docker-compose -f docker-compose.dev.yml up -d
   
   # Or start MySQL and MongoDB manually
   ```

2. **Configure database connections**:
   - Update `backend/src/main/resources/application.properties` if needed
   - Default MySQL: `localhost:3306`, user: `root`, password: `rootpassword`
   - Default MongoDB: `mongodb://localhost:27017/appdb`

3. **Build and run Spring Boot**:
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```

   Backend will run on http://localhost:8080

#### Frontend Setup

1. **Install dependencies**:
   ```bash
   cd frontend
   npm install
   ```

2. **Configure API URL**:
   - Create `frontend/.env` file (if not exists):
     ```
     VITE_API_URL=http://localhost:8080/api
     ```

3. **Start development server**:
   ```bash
   npm run dev
   ```

   Frontend will run on http://localhost:3000

## 🔑 Default Users

The application automatically creates two default users on first startup:

- **Admin User**:
  - Username: `admin`
  - Email: `admin@example.com`
  - Password: `password`
  - Role: ADMIN

- **Regular User**:
  - Username: `user`
  - Email: `user@example.com`
  - Password: `password`
  - Role: USER

## 📁 Project Structure

```
project/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/fullstackapp/
│   │   │   │   ├── config/          # Security, CORS, Data initialization
│   │   │   │   ├── controller/      # REST controllers
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── entity/          # MySQL JPA entities (User, Role)
│   │   │   │   ├── document/        # MongoDB documents (Product)
│   │   │   │   ├── repository/      # JPA and MongoDB repositories
│   │   │   │   ├── security/        # Security configuration
│   │   │   │   ├── service/         # Business logic
│   │   │   │   └── util/            # JWT utilities
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── components/      # Reusable components
│   │   ├── context/         # Auth context
│   │   ├── pages/           # Page components
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── Dockerfile
│   ├── package.json
│   └── vite.config.js
├── docker-compose.yml       # Production Docker setup
├── docker-compose.dev.yml   # Development Docker setup (databases only)
└── README.md
```

## 🔌 API Endpoints

### Authentication Endpoints

- `POST /api/auth/register` - Register a new user
  ```json
  {
    "username": "newuser",
    "email": "user@example.com",
    "password": "password123"
  }
  ```

- `POST /api/auth/login` - Login and get JWT token
  ```json
  {
    "username": "admin",
    "password": "password"
  }
  ```

### User Endpoints

- `GET /api/users/me` - Get current user info (requires authentication)
- `GET /api/users/all` - Get all users (ADMIN only)

### Product Endpoints (MongoDB)

- `GET /api/products` - Get all products (requires authentication)
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/category/{category}` - Get products by category
- `POST /api/products` - Create new product
  ```json
  {
    "name": "Product Name",
    "description": "Description",
    "price": 99.99,
    "quantity": 10,
    "category": "Electronics"
  }
  ```
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

## 🛡️ Security Features

- **JWT Authentication**: Tokens stored in localStorage
- **Role-Based Access Control**: USER and ADMIN roles
- **Password Encryption**: BCrypt hashing
- **CORS Configuration**: Configured for frontend origin
- **Protected Routes**: Frontend route protection based on authentication and roles

## 🎨 Frontend Pages

- `/login` - User login page
- `/register` - User registration page
- `/dashboard` - User dashboard (protected)
- `/products` - Product management CRUD (protected)
- `/admin` - Admin panel to view all users (ADMIN only)

## 🧪 Testing

### Backend Testing

```bash
cd backend
mvn test
```

### API Testing with Postman

1. Import the following endpoints:
   - Register: `POST http://localhost:8080/api/auth/register`
   - Login: `POST http://localhost:8080/api/auth/login`
   - Get Products: `GET http://localhost:8080/api/products` (with Bearer token)

2. **Testing with JWT**:
   - Login first to get the token
   - Add token to Authorization header: `Bearer <your-token>`

## 🔧 Configuration

### Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
# JWT Secret (change in production!)
jwt.secret=your-secret-key-here

# JWT Expiration (in milliseconds)
jwt.expiration=86400000

# CORS Origins
cors.allowed-origins=http://localhost:3000
```

### Frontend Configuration

Edit `frontend/.env`:

```
VITE_API_URL=http://localhost:8080/api
```

## 🐳 Docker Commands

```bash
# Start all services
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f

# Rebuild containers
docker-compose up -d --build

# Start only databases (for local development)
docker-compose -f docker-compose.dev.yml up -d
```

## 📝 Development Notes

- **Hot Reload**: Frontend supports hot reload in development mode
- **Backend Restart**: Restart Spring Boot after changes (or use Spring Boot DevTools)
- **Database Persistence**: Data persists in Docker volumes
- **JWT Expiration**: Default is 24 hours (86400000 ms)

## 🚨 Troubleshooting

### Backend won't start
- Check MySQL and MongoDB are running
- Verify database credentials in `application.properties`
- Check port 8080 is not in use

### Frontend can't connect to backend
- Verify `VITE_API_URL` in `.env` file
- Check CORS configuration in backend
- Ensure backend is running on port 8080

### Database connection errors
- Verify MySQL/MongoDB containers are healthy: `docker-compose ps`
- Check database credentials
- Ensure ports 3306 and 27017 are available

### JWT token issues
- Clear localStorage and login again
- Check JWT secret matches in backend
- Verify token expiration time

## 📦 Production Deployment

1. **Build frontend**:
   ```bash
   cd frontend
   npm run build
   ```

2. **Update Docker Compose**:
   - Use production environment variables
   - Change JWT secret
   - Configure proper database passwords
   - Set up SSL/TLS

3. **Deploy**:
   ```bash
   docker-compose up -d
   ```

## 📄 License

This project is open source and available for educational purposes.

## 👥 Contributing

Feel free to submit issues and enhancement requests!

---

**Built with ❤️ using Spring Boot, React, MySQL, and MongoDB**

