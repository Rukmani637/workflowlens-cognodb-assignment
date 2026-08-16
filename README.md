# WorkflowLens — Workflow Intelligence with CognoDB

WorkflowLens is a workflow intelligence application built for the Wexa AI CognoDB assignment.

It models workflows, agents, skills, connectors, systems, and teams as a connected graph. The application allows users to search across the workflow ecosystem, explore dependencies, and understand the potential impact of changes to connected components.

---

## 1. Use Case

Modern organizations often have workflows that depend on multiple agents, skills, external connectors, and business systems.

For example:

```text
Lead Qualification
        |
     CONTAINS
        ↓
Lead Qualification Agent
        |
       USES
        ↓
Lead Analysis
        |
    REQUIRES
        ↓
Salesforce Connector
        |
   CONNECTS_TO
        ↓
Salesforce CRM

If Salesforce CRM becomes unavailable or a workflow component changes, it can be difficult to understand which workflows and components are affected.
WorkflowLens addresses this problem by representing these dependencies as a graph.
The application provides:

Workflow discovery
Global search
Dependency exploration
Relationship visualization
Impact analysis
Team and system views

2. Why a Graph Database?
WorkflowLens deals primarily with relationships between entities.
The important information is not only:
"What is this workflow?"
but also:
"What does this workflow depend on?"
and:
"What could be affected if this system changes?"
A graph database is a natural fit because relationships are first-class data.
The application models relationships such as:
Team ──OWNS──> Workflow

Workflow ──CONTAINS──> Agent

Agent ──USES──> Skill

Skill ──REQUIRES──> Connector

Connector ──CONNECTS_TO──> System

With a graph model, these relationships can be traversed directly to discover dependencies and potential impact.
This is particularly useful for:
Dependency analysis
Impact analysis
Connected-system discovery
Relationship-based search
Future multi-hop dependency analysis

A relational database could represent these relationships using multiple tables and joins, but the graph model keeps the domain relationships explicit and makes relationship traversal a central operation.

3. Data Model
The core graph contains the following entities:
Nodes
-Team
-Workflow
-Agent
-Skill
-Connector
-System
-Task

Relationships
-OWNS
-CONTAINS
-USES
-REQUIRES
-CONNECTS_TO
-HAS_TASK

Example
Team
  |
  | OWNS
  ↓
Workflow
  |
  | CONTAINS
  ↓
Agent
  |
  | USES
  ↓
Skill
  |
  | REQUIRES
  ↓
Connector
  |
  | CONNECTS_TO
  ↓
System

4. Data Model Diagram
The main dependency model used by WorkflowLens can be represented as:
                    ┌─────────────┐
                    │    Team     │
                    └──────┬──────┘
                           │
                          OWNS
                           │
                           ▼
                    ┌─────────────┐
                    │  Workflow   │
                    └──────┬──────┘
                           │
                        CONTAINS
                           │
                           ▼
                    ┌─────────────┐
                    │    Agent    │
                    └──────┬──────┘
                           │
                          USES
                           │
                           ▼
                    ┌─────────────┐
                    │    Skill    │
                    └──────┬──────┘
                           │
                        REQUIRES
                           │
                           ▼
                    ┌─────────────┐
                    │  Connector  │
                    └──────┬──────┘
                           │
                      CONNECTS_TO
                           │
                           ▼
                    ┌─────────────┐
                    │   System    │
                    └─────────────┘

5. Architecture
WorkflowLens uses a simple layered architecture.
┌───────────────────────────────┐
│          React UI             │
│                               │
│ Dashboard / Search / Explorer │
│ Impact Analysis / Teams       │
│ Systems                       │
└───────────────┬───────────────┘
                │ REST API
                ▼
┌───────────────────────────────┐
│       Spring Boot API         │
│                               │
│ Controllers                   │
│ Services                      │
│ Repository / Graph Queries    │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│           CognoDB             │
│        Graph Database         │
│                               │
│ Nodes + Relationships         │
└───────────────────────────────┘

6. Technology Stack
Backend
-Java
-Spring Boot
-Spring Web
-Spring Data / Neo4j integration
-Maven
-Cypher
Frontend
-React
-Vite
-JavaScript
-CSS
Database
-CognoDB
-Graph data model
-Cypher queries
API Testing
-Postman

7. Project Structure
workflowlens/
│
├── workflowlens-backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/workflowlens/
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── dto/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── mvnw
│
├── workflowlens-frontend/
│   ├── src/
│   │   ├── App.jsx
│   │   ├── App.css
│   │   └── main.jsx
│   ├── package.json
│   └── vite.config.js
│
├── .gitignore
└── README.md

8. CognoDB Setup
WorkflowLens requires a running CognoDB instance.
Configure the backend using environment variables.
The application expects:
COGNODB_URI
COGNODB_USERNAME
COGNODB_PASSWORD

The backend configuration uses environment variables instead of hard-coding credentials.
Example:
cognodb.uri=${COGNODB_URI}
cognodb.username=${COGNODB_USERNAME:cognodb}
cognodb.password=${COGNODB_PASSWORD}

Environment configuration
Set the required variables before starting the backend:
COGNODB_URI=<your CognoDB URI>
COGNODB_USERNAME=<your CognoDB username>
COGNODB_PASSWORD=<your CognoDB password>

9. Backend Setup
Navigate to the backend directory:
cd workflowlens-backend
Run the application using Maven:
Windows
mvnw.cmd spring-boot:run
Linux / macOS
./mvnw spring-boot:run
The backend runs on:
http://localhost:8080

10. Frontend Setup
Navigate to the frontend directory:
cd workflowlens-frontend
Install dependencies:
npm install
Start the development server:
npm run dev
The Vite development server will provide the frontend URL in the terminal, typically:
http://localhost:5173

11. Seed / Data Loading
WorkflowLens includes backend seed functionality for loading the graph data required by the application.
The seed implementation is located in:
workflowlens-backend/src/main/java/com/workflowlens/service/SeedService.java
and its corresponding controller is:
workflowlens-backend/src/main/java/com/workflowlens/controller/SeedController.java
The seed process creates the graph entities and relationships used by the application.
The seeded graph includes examples such as:
Sales team
Lead Qualification workflow
Lead Qualification Agent
Lead Analysis skill
CRM Update skill
Salesforce connector
Salesforce CRM system

12. Main Graph Queries

The application uses Cypher to retrieve connected graph information.
One of the key queries is the workflow dependency query.
It starts from a workflow and retrieves its connected entities and relationships.
The dependency response includes:
Workflow
Agents
Skills
Connectors
Systems
Relationships
The relationship information is returned in a structure such as:
{
  "from": "workflow-lead-qualification",
  "to": "agent-lead-qualification",
  "type": "CONTAINS"
}
This allows the frontend to construct the dependency explorer dynamically from graph relationships.

13. API Endpoints
Get team workflows
GET /api/teams/{teamId}/workflows
Example:
GET /api/teams/team-sales/workflows
Global search
GET /api/search?q={query}
Example:
GET /api/search?q=Salesforce
Workflow dependencies
GET /api/workflows/{workflowId}/dependencies
Example:
GET /api/workflows/workflow-lead-qualification/dependencies
This endpoint returns the workflow's connected agents, skills, connectors, systems, and relationships.

14. Dependency Explorer
The Dependency Explorer provides a visual representation of the relationships within a workflow.
Example:
Lead Qualification
        |
     CONTAINS
        ↓
Lead Qualification Agent
        |
       USES
        ↓
Lead Analysis / CRM Update
        |
     REQUIRES
        ↓
Salesforce
        |
   CONNECTS_TO
        ↓
Salesforce CRM
This makes it easier to understand how a workflow is connected to other components.

15. Impact Analysis
Impact Analysis uses the dependency information to identify components that may be affected by changes.
For example, a change to Salesforce CRM can potentially affect:

Salesforce CRM
      ↑
Salesforce Connector
      ↑
Lead Analysis
      ↑
Lead Qualification Agent
      ↑
Lead Qualification Workflow

This provides a starting point for understanding downstream impact.

16. Global Search
WorkflowLens provides a global search interface across the workflow ecosystem.
Search results can include:

-Teams
-Workflows
-Agents
-Connectors
-Systems
-Skills
-Tasks

Example:
Search: Lead Qualification
The interface displays matching entities and their types.

17. UI Screenshots
Screenshots of the completed application added here.
Screenshots

Dashboard
[WorkflowLens Dashboard](docs/screenshots/dashboard.png)

Global Search
[Global Search](docs/screenshots/search.png)

Dependency Explorer
[WorkflowLens Dependency Explorer](docs/screenshots/dependencies-1.png)
[WorkflowLens Dependency Explorer](docs/screenshots/dependencies-2.png)
[WorkflowLens Dependency Explorer](docs/screenshots/dependencies-3.png)

Systems
[WorkflowLens Systems](docs/screenshots/systems.png)

18. Hosted Demo
Demo URL: To be added after deployment.
The hosted demo will provide access to the running WorkflowLens application.

19. Screen Recording
Screen recording: To be added.
The recording demonstrates:

Dashboard
Global Search
Workflow discovery
Dependency Explorer
Impact Analysis
Teams
Systems

 
20. Future Improvements
Potential future improvements include:

-Multi-hop impact analysis
-More advanced graph visualizations
-Authentication and authorization
-Workflow creation and editing
-Real-time dependency monitoring
-Change-impact scoring
-Additional graph traversal queries
-Production deployment and monitoring

22. Author
Rukmani Oram
