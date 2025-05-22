# 📦 Scalable Tracking Number Generator API

This is a high-performance, scalable RESTful API built with **Spring Boot** and **Redis** to generate unique tracking numbers for parcels. It supports high concurrency, is stateless, and can scale horizontally.

---

## 🧾 Features

- REST API: `GET /api/next-tracking-number`
- Unique 16-character alphanumeric tracking number
- Redis-based atomic uniqueness check
- High concurrency support
- Zipkin tracing for observability
- Logging and exception handling
- Unit tests using JUnit + Mockito

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot 3.1.5
- Redis (Local/Docker/Cloud)
- Micrometer Tracing + Zipkin
- SLF4J Logging
- Maven 3.9

---

## 🔗 API Specification

### Endpoint
```
GET /api/next-tracking-number
```

### Query Parameters

| Name                  | Type    | Example                                      |
|-----------------------|---------|----------------------------------------------|
| origin_country_id     | String  | MY                                           |
| destination_country_id| String  | ID                                           |
| weight                | Decimal | 1.234                                        |
| created_at            | RFC3339 | 2025-05-22T10:30:00+05:30                    |
| customer_id           | UUID    | de619854-b59b-425e-9db4-943979e1bd49         |
| customer_name         | String  | RedBox Logistics                             |
| customer_slug         | String  | redbox-logistics                             |

### Sample Response

```json
{
  "tracking_number": "ABC123XYZ456LMNO",
  "created_at": "2025-05-22T05:07:08.249934"
}
```

---

## 🚀 Running Locally

### Prerequisites

- Java 17+
- Maven 3.9+
- Redis

### Setup

```bash
#Import Zip file in Intellij

# Start Redis
brew install redis
brew services start redis

# Start Zipkin (optional)
java -jar zipkin-server-2.24.3-exec.jar

# Run the application
mvn spring-boot:run
```

---

## 🧪 Testing

```bash
mvn -Dtest=TrackingNumberServiceTest test
mvn test
```

- Redis mocked for unit testing
- Test for tracking number format and uniqueness

---

## 📂 Project Structure

```
src/main/java/com/example/demo/
├── controller
├── service
├── dto
├── config
├── exception
```

---

## 👤 Author

**Mohit Agarwal**  
Senior Backend Developer  
[LinkedIn Profile](https://www.linkedin.com/in/mohit-agarwal-9712672a1/)

---

## 📄 License

MIT License
