# toll-fastag-platform
Toll Fastag Microservices Platform
________________________________________________________________________________________________________________________________________________________________

📘 I. Introduction
________________________________________
🔹 Purpose
The purpose of this System Design Document (SDD) is to define the architecture, components, and implementation approach for a scalable, event-driven Toll FastTag microservices platform built using Spring Boot. It serves as a blueprint for development, deployment, and maintenance. </br>
🔹 Audience
Development Team (Backend, DevOps)
Architects (Solution / Technical)
Project Managers
Business Stakeholders </br>
🔹 Problem Statement
The system aims to automate toll collection using RFID (FASTag), eliminating manual payment delays, reducing congestion, and ensuring seamless vehicle movement across toll plazas. </br>
🔹 Strategic Objectives
High scalability (handle peak traffic loads)
Low latency (real-time toll processing)
High availability (24/7 system uptime)
Fault tolerance (resilient microservices)
Extensibility (support future channels like WhatsApp notifications </br>
🔹 Design Constraints
Must support real-time processing (< 200ms response)
Budget constraints on infrastructure
Integration with external bank/payment systems
Network reliability issues at toll plazas
Compliance with financial transaction regulations
________________________________________________________________________________________________________________________

🏗️ II. Architecture and System Overview
________________________________________
🔹 High-Level Architecture
Event-driven microservices architecture
Communication via Apache Kafka
Deployed on Kubernetes</br>

🔹 Component View (Microservices)
<table>
  <tr>
    <th>Service</th>
    <th>Responsibility</th>
  </tr>
  <tr>
    <td>Ingestion Service</td>
    <td>Receives RFID scan events</td>
  </tr>
  <tr>
    <td>Processing Service</td>
    <td>Validates tag & calculates toll amount</td>
  </tr>
  <tr>
    <td>Payment Service</td>
    <td>Amount deduction & transactions</td>
  </tr>
  <tr>
    <td>Notification Service</td>
    <td>SMS/Email/WhatsApp alerts</td>
  </tr>
  <tr>
    <td>Gate Service</td>
    <td>Opens/closes toll barrier</td>
  </tr>
</table></br>

🔹 Key Design Patterns
API Gateway Pattern → centralized routing
Database per Service → loose coupling
Circuit Breaker (Resilience4j) → fault tolerance
Saga Pattern → distributed transaction handling
Proxy Pattern → notification channel abstraction
Event-Driven Architecture → async processing</br>

🔹 Technology Stack
Application Framework
Spring Boot
Spring Cloud (Feign, Gateway)
Containerization
Docker
Docker Compose (local setup)
Service Communication
REST APIs (Spring Web)
Async messaging via Apache Kafka
Service Discovery
Eureka / Consul
Observability
Metrics: Prometheus
Visualization: Grafana
Logging: ELK Stack
Tracing: OpenTelemetry
Data Storage
PostgreSQL (primary DB)
Redis (caching layer)
Spring Data JPA
________________________________________________________________________________________________________________________

⚙️ III. System Design Details
________________________________________
🔹 Service-Specific Design

1. Ingestion Service
API Reference
POST /tag/scan
Database
Minimal (event-driven, no heavy storage)
Business Logic
Validate request
Publish event to Kafka topic: tag-scan-topic</br>

2. Processing Service
Database Schema - Toll
Table 1 - Tags
Business Logic
Validate tag
Fetch vehicle tag details (Redis + DB)
Calculate toll amount based on vehicle type
Trigger Payment</br>

4. Payment Service
Database Schema - Toll</br>
Table 1 - Tags
Table 2 - Transaction
Table 3 - Idempotency</br>

Business Logic
Handle insufficient balance
Deduct balance
Handle Idempotency
Maintain transaction history</br>

4. Notification Service
Business Logic
Uses Proxy Pattern
Routes notifications to:
SMS
Email
WhatsApp (future)</br>

5. Gate Service
Business Logic
Integrates with toll hardware
Opens/closes barrier based on payment status</br>

🔹 Deployment Strategy</br>
Containerized using Docker
Orchestrated via Kubernetes
Hosted on AWS EKS (or similar)
Horizontal Pod Autoscaling (HPA)
Rolling updates for zero downtime</br>

🔹 Security</br>
OAuth2 / JWT-based authentication
Identity provider: Keycloak
API Gateway handles auth validation
Role-based access control (RBAC)
________________________________________________________________________________________________________________________

🚀 IV. Development and Operations
________________________________________
🔹 Development Environment</br>
Prerequisites
Java 17+
Maven / Gradle
Docker
Postgresql/mysql
Redis
Kafka
Build Command
mvn clean install </br>

🔹 Testing Strategy</br>
Unit Testing → JUnit 5
Integration Testing → Spring Boot Test
Contract Testing → WireMock
Load Testing → JMeter</br>

🔹 CI/CD Pipeline</br>
Using Jenkins
Code commit → GitHub
Build → Maven
Test → Automated tests
Docker image build
Push to registry
Deploy to Kubernetes</br>

🔹 Monitoring and Logging</br>
Logs → ELK Stack
Metrics → Prometheus
Dashboards → Grafana
Distributed tracing → OpenTelemetry
________________________________________________________________________________________________________________________

📌 V. Conclusion
________________________________________
🔹 Key Milestones & Deliverables</br>
<table>
  <tr>
    <th>Phase</th>
    <th>Deliverable</th>
  </tr>
  <tr>
    <td>Phase 1</td>
    <td>Core microservices setup</td>
  </tr>
  <tr>
    <td>Phase 1</td>
    <td>Kafka integration</td>
  </tr>
  <tr>
    <td>Phase 3</td>
    <td>Payment & notification</td>
  </tr>
  <tr>
    <td>Phase 4</td>
    <td>Kubernetes deployment</td>
  </tr>
  <tr>
    <td>Phase 5</td>
    <td>Observability setup</td>
  </tr>
</table>
</br>

🔹 Future Work / Enhancements</br>
<ol>
  <li>AI-based traffic prediction</li>
  <li>Dynamic toll pricing</li>
  <li>Multi-country currency support</li>
  <li>Advanced fraud detection</li>
  <li>Mobile app integration</li>
</ol>









