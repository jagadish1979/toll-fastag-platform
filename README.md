# toll-fastag-platform
<b>Toll Fastag Microservices Platform</b>
<hr />
📘 <b>I. Introduction</b>
<hr />
🔹 Purpose<br/>
The purpose of this System Design Document (SDD) is to define the architecture, components, and implementation approach for a scalable, event-driven Toll FastTag microservices platform built using Spring Boot. It serves as a blueprint for development, deployment, and maintenance. </br>
🔹 Audience<br/>
Development Team (Backend, DevOps)
Architects (Solution / Technical)
Project Managers
Business Stakeholders </br>
🔹 Problem Statement</br>
The system aims to automate toll collection using RFID (FASTag), eliminating manual payment delays, reducing congestion, and ensuring seamless vehicle movement across toll plazas. </br>
🔹 Strategic Objectives<br/>
High scalability (handle peak traffic loads)
Low latency (real-time toll processing)
High availability (24/7 system uptime)
Fault tolerance (resilient microservices)
Extensibility (support future channels like WhatsApp notifications </br>
🔹 Design Constraints<br/>
Must support real-time processing (< 200ms response)
Budget constraints on infrastructure
Integration with external bank/payment systems
Network reliability issues at toll plazas
Compliance with financial transaction regulations
<hr />
🏗️ <b>II. Architecture and System Overview</b>
<hr />
🔹 High-Level Architecture <br/>
<table>
  <tr>
    <td>Event-driven microservices architecture</td>
  </tr>
  <tr>
    <td>Communication via Apache Kafka</td>
  </tr>
    <tr>
    <td>Deployed on Kubernetes</td>
  </tr>
</table>
<br/>

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

🔹 Key Design Patterns<br/>
<table>
  <tr>
  <th>Design Pattern</th>
  <th>Purpose</th>
  </tr>
  <tr>
  <td>API Gateway Pattern</td>
  <td>centralized routing</td>
  </tr>
  <tr>
  <td>Database per Service</td>
  <td>loose coupling</td>
  </tr>
  <tr>
  <td>Circuit Breaker (Resilience4j)</td>
  <td>fault tolerance</td>
  </tr>
  <tr>
  <td>Saga Pattern</td>
  <td>distributed transaction handling</td>
  </tr>
  <tr>
  <td>Proxy Pattern</td>
  <td>Notification channel abstraction</td>
  </tr>
  <tr>
  <td>Event-Driven Architecture</td>
  <td>Async processing</td>
  </tr>
</table>
</br>

🔹 Technology Stack <br/>
<table>
 <tr>
  <td>Application Framework</td>
  <td>
    <table>
      <tr>
        <td>Spring Boot</td>
      </tr>
      <tr>
        <td>Spring Cloud (Feign, Gateway)</td>
      </tr>      
    </table>
     </td>
  </tr>
  <tr>
  <td>Containerization</td>
  <td>
    <table>
      <tr>
        <td>Docker</td>
      </tr>
      <tr>
        <td>Docker Compose (local setup)</td>
      </tr>      
    </table>
    </td>
  </tr>
  <tr>
    <td>Service Communication</td>
    <td>
       <table>
      <tr>
        <td>REST APIs (Spring Web)</td>
      </tr>
      <tr>
        <td>Async messaging via Apache Kafka</td>
      </tr>      
    </table>
      </td>
  </tr>
  <tr>
    <td>Service Discovery</td>
    <td>Eureka / Consul</td>
  </tr>
  <tr>
    <td>Observability</td>
    <td>
       <table>
         <tr>
           <td>Metrics</td>
           <td>Prometheus</td>
         </tr>
         <tr>
           <td>Visualization</td>
           <td>Grafana</td>
         </tr>
         <tr>
           <td>Logging</td>
           <td>ELK Stack</td>
         </tr>
         <tr>
           <td>Tracing</td>
           <td>OpenTelemetry</td>
         </tr>         
       </table>
    </td>
  </tr>
  <tr>
    <td>Data Storage</td>
    <td>
      <table>
         <tr>
           <td>PostgreSQL (primary DB)</td>
         </tr>
          <tr>
           <td>Redis (caching layer)</td>
         </tr>
         <tr>
           <td>Spring Data JPA</td>
         </tr>
      </table>
      </td>
  </tr>
</table><br/>
<hr />
⚙️ <b>III. System Design Details</b>
<hr />
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
Table 1 - Tags</br>
Table 2 - Transaction</br>
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
<hr />
🚀 <b>IV. Development and Operations</b>
<hr />
🔹 Development Environment</br>
Prerequisites </br>
<ol>
  <li>Java 17+</li>
  <li>Maven / Gradle</li>
  <li>Docker</li>
  <li>Postgresql/mysql</li>
  <li>Redis</li>
  <li>Kafka</li>
</ol><br/>

Build Command
<ol>
  <li>mvn clean install</li>
</ol>
 </br>

🔹 Testing Strategy</br>
<ol>
  <li>Unit Testing → JUnit 5</li>
  <li>Integration Testing → Spring Boot Test</li>
  <li>Contract Testing → WireMock</li>
  <li>Load Testing → JMeter or Loadrunner </li>
</ol>
</br>

🔹 CI/CD Pipeline</br>
<ol>
  <li>Using Jenkins</li>
  <li>Code commit → GitHub</li>
  <li>Build → Maven</li>
  <li>Test → Automated tests</li>
  <li>Docker image build</li>
  <li>Push to registry</li>
  <li>Deploy to Kubernetes</li>
</ol>
</br>

🔹 Monitoring and Logging</br>
<ol>
  <li>Logs → ELK Stack</li>
  <li>Metrics → Prometheus</li>
  <li>Dashboards → Grafana</li>
  <li>Distributed tracing → OpenTelemetry</li>
</ol>
<hr />
📌 <b>V. Conclusion</b>
<hr />
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









