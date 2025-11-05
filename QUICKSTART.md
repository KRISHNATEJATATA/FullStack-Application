# Quick Start Guide

## 🚀 Fastest Way to Run

### Using Docker Compose (Recommended)

```bash
# Start everything
docker-compose up -d

# Wait for services to start (30-60 seconds)
# Then access:
# - Frontend: http://localhost:3000
# - Backend: http://localhost:8080/api
```

### Manual Setup

#### 1. Start Databases
```bash
docker-compose -f docker-compose.dev.yml up -d
```

#### 2. Backend
```bash
cd backend
mvn spring-boot:run
```

#### 3. Frontend
```bash
cd frontend
npm install
npm run dev
```

## 🔑 Login Credentials

- **Admin**: `admin` / `password`
- **User**: `user` / `password`

## 📝 First Steps

1. Open http://localhost:3000
2. Login with admin credentials
3. Navigate to Products to create some data
4. Check Admin panel to see all users

## 🛠️ Troubleshooting

- **Port conflicts**: Change ports in docker-compose.yml
- **Database connection**: Wait 30 seconds after starting containers
- **Frontend not loading**: Check `VITE_API_URL` in `.env` file

