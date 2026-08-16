package com.workflowlens.repository;

import com.workflowlens.dto.SearchResultResponse;
import com.workflowlens.dto.SystemImpactResponse;
import com.workflowlens.dto.WorkflowDependencyResponse;
import com.workflowlens.dto.WorkflowResponse;
import org.neo4j.driver.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GraphRepository {
    private final Driver driver;

    public GraphRepository(Driver driver){
        this.driver=driver;
    }

    public  void createUser(String id, String name, String email){
        String cypher= """
                 MERGE (user:User {id: $id})
                SET user.name = $name,
                    user.email = $email
                """;
        try(Session session=driver.session()){
            session.run(
                    cypher,
                    Values.parameters(
                            "id",id,
                            "name", name,
                            "email", email
                    )
            );
        }
    }

    public void createTeam(String id, String name, String description){
        String cypher= """
                MERGE (team:Team{id:$id})
                SET team.name=$name,
                    team.description=$description""";

        try(Session session=driver.session()){
            session.run(
                    cypher,org.neo4j.driver.Values.parameters(
                            "id",id,
                            "name",name,
                            "description", description
                    )
            );
        }
    }

    public void createWorkflow(
            String id,
            String name,
            String description,
            String status) {

        String cypher = """
                 MERGE (workflow:Workflow {id: $id})
                              SET workflow.name = $name,
                                  workflow.description = $description,
                                  workflow.status = $status
                """;
        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "id", id,
                            "name", name,
                            "description", description,
                            "status", status
                    )
            );
        }
    }
    public void createAgent(
            String id,
            String name,
            String description,
            String status) {

        String cypher = """
                 MERGE (agent:Agent {id: $id})
                    SET agent.name = $name,
                        agent.description = $description,
                        agent.status = $status
                """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "id", id,
                            "name", name,
                            "description", description,
                            "status", status
                    )
            );
        }
    }

    public void createSkill(
            String id,
            String name,
            String category,
            String description) {

        String cypher = """
                MERGE (skill:Skill {id: $id})
                    SET skill.name = $name,
                        skill.category = $category,
                        skill.description = $description
                """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "id", id,
                            "name", name,
                            "category", category,
                            "description", description
                    )
            );
        }
    }

    public void createConnector(
            String id,
            String name,
            String type,
            String status) {

        String cypher = """
                 MERGE (connector:Connector {id: $id})
                    SET connector.name = $name,
                        connector.type = $type,
                        connector.status = $status
                """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "id", id,
                            "name", name,
                            "type", type,
                            "status", status
                    )
            );
        }
    }

    public void createSystem(
            String id,
            String name,
            String type) {

        String cypher = """
                 MERGE (system:System {id: $id})
                    SET system.name = $name,
                        system.type = $type
                """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "id", id,
                            "name", name,
                            "type", type
                    )
            );
        }
    }

    public void createTask(
            String id,
            String name,
            String description,
            String status) {

        String cypher = """
                MERGE (task:Task {id: $id})
                    SET task.name = $name,
                        task.description = $description,
                        task.status = $status
                """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "id", id,
                            "name", name,
                            "description", description,
                            "status", status
                    )
            );
        }
    }


    /// ///Relationship Methods//////

// User-->Team

    public void connectUserToTeam(String userId, String teamId) {

        String cypher = """
            MATCH (user:User {id: $userId})
            MATCH (team:Team {id: $teamId})
            MERGE (user)-[:MEMBER_OF]->(team)
            """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "userId", userId,
                            "teamId", teamId
                    )
            );
        }
    }
    // Team-->Workflow
    public void connectTeamToWorkflow(String teamId, String workflowId) {
        String cypher = """
            MATCH (team:Team {id: $teamId})
            MATCH (workflow:Workflow {id: $workflowId})
            MERGE (team)-[:OWNS]->(workflow)
            """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "teamId", teamId,
                            "workflowId", workflowId
                    )
            );
        }
    }

    // Workflow-->Agent

    public void connectWorkflowToAgent(String workflowId, String agentId) {
        String cypher = """
            MATCH (workflow:Workflow {id: $workflowId})
            MATCH (agent:Agent {id: $agentId})
            MERGE (workflow)-[:CONTAINS]->(agent)
            """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "workflowId", workflowId,
                            "agentId", agentId
                    )
            );
        }
    }

    // Agent-->Skill

    public void connectAgentToSkill(String agentId, String skillId) {

        String cypher = """
            MATCH (agent:Agent {id: $agentId})
            MATCH (skill:Skill {id: $skillId})
            MERGE (agent)-[:USES]->(skill)
            """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "agentId", agentId,
                            "skillId", skillId
                    )
            );
        }
    }

    // Skill-->Connector

    public void connectSkillToConnector(String skillId, String connectorId) {

        String cypher = """
            MATCH (skill:Skill {id: $skillId})
            MATCH (connector:Connector {id: $connectorId})
            MERGE (skill)-[:REQUIRES]->(connector)
            """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "skillId", skillId,
                            "connectorId", connectorId
                    )
            );
        }
    }

    // Connector-->System

    public void connectConnectorToSystem(String connectorId, String systemId) {

        String cypher = """
            MATCH (connector:Connector {id: $connectorId})
            MATCH (system:System {id: $systemId})
            MERGE (connector)-[:CONNECTS_TO]->(system)
            """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "connectorId", connectorId,
                            "systemId", systemId
                    )
            );
        }
    }

    // Workflow-->Task

    public void connectWorkflowToTask(String workflowId, String taskId) {

        String cypher = """
            MATCH (workflow:Workflow {id: $workflowId})
            MATCH (task:Task {id: $taskId})
            MERGE (workflow)-[:HAS_TASK]->(task)
            """;

        try (Session session = driver.session()) {
            session.run(
                    cypher,
                    Values.parameters(
                            "workflowId", workflowId,
                            "taskId", taskId
                    )
            );
        }
    }

    public List<WorkflowResponse> findWorkflowsByTeam(String teamId) {
        String cypher = """
            MATCH (team:Team {id: $teamId})
                  -[:OWNS]->
                  (workflow:Workflow)

            RETURN workflow.id AS id,
                   workflow.name AS name,
                   workflow.description AS description,
                   workflow.status AS status

            ORDER BY workflow.name
            """;
        try (Session session = driver.session()) {
            return session.run(
                            cypher,
                            Values.parameters("teamId", teamId)
                    )
                    .list(record -> new WorkflowResponse(
                            record.get("id").asString(),
                            record.get("name").asString(),
                            record.get("description").asString(),
                            record.get("status").asString()
                    ));
        }
    }
    public WorkflowDependencyResponse findWorkflowDependencies(String workflowId) {

        String cypher = """
        MATCH (workflow:Workflow {id: $workflowId})

        OPTIONAL MATCH (workflow)-[r1:CONTAINS]->(agent:Agent)
        OPTIONAL MATCH (agent)-[r2:USES]->(skill:Skill)
        OPTIONAL MATCH (skill)-[r3:REQUIRES]->(connector:Connector)
        OPTIONAL MATCH (connector)-[r4:CONNECTS_TO]->(system:System)

        RETURN workflow.id AS workflowId,
               workflow.name AS workflowName,

               collect(DISTINCT {
                   id: agent.id,
                   name: agent.name
               }) AS agents,

               collect(DISTINCT {
                   id: skill.id,
                   name: skill.name
               }) AS skills,

               collect(DISTINCT {
                   id: connector.id,
                   name: connector.name
               }) AS connectors,

               collect(DISTINCT {
                   id: system.id,
                   name: system.name
               }) AS systems,

               collect(DISTINCT {
                   from: startNode(r1).id,
                   to: endNode(r1).id,
                   type: type(r1)
               }) +
               collect(DISTINCT {
                   from: startNode(r2).id,
                   to: endNode(r2).id,
                   type: type(r2)
               }) +
               collect(DISTINCT {
                   from: startNode(r3).id,
                   to: endNode(r3).id,
                   type: type(r3)
               }) +
               collect(DISTINCT {
                   from: startNode(r4).id,
                   to: endNode(r4).id,
                   type: type(r4)
               }) AS relationships
        """;

        try (Session session = driver.session()) {

            var record = session.run(
                    cypher,
                    Values.parameters("workflowId", workflowId)
            ).single();
            var workflow = new WorkflowDependencyResponse.WorkflowInfo(
                    record.get("workflowId").asString(),
                    record.get("workflowName").asString()
            );

            var agents = record.get("agents").asList(value ->
                    new WorkflowDependencyResponse.AgentInfo(
                            value.get("id").asString(),
                            value.get("name").asString()
                    )
            );

            var skills = record.get("skills").asList(value ->
                    new WorkflowDependencyResponse.SkillInfo(
                            value.get("id").asString(),
                            value.get("name").asString()
                    )
            );

            var connectors = record.get("connectors").asList(value ->
                    new WorkflowDependencyResponse.ConnectorInfo(
                            value.get("id").asString(),
                            value.get("name").asString()
                    )
            );

            var systems = record.get("systems").asList(value ->
                    new WorkflowDependencyResponse.SystemInfo(
                            value.get("id").asString(),
                            value.get("name").asString()
                    )
            );
            var relationships = record.get("relationships").asList(value ->
                    new WorkflowDependencyResponse.RelationshipInfo(
                            value.get("from").asString(),
                            value.get("to").asString(),
                            value.get("type").asString()
                    )
            );
            return new WorkflowDependencyResponse(
                    workflow,
                    agents,
                    skills,
                    connectors,
                    systems,
                    relationships
            );
        }
    }
    public SystemImpactResponse findSystemImpact(String systemId) {

        String cypher = """
            MATCH (system:System {id: $systemId})

            OPTIONAL MATCH
                (workflow:Workflow)
                -[:CONTAINS]->(:Agent)
                -[:USES]->(:Skill)
                -[:REQUIRES]->(:Connector)
                -[:CONNECTS_TO]->(system)

            OPTIONAL MATCH
                (team:Team)-[:OWNS]->(workflow)

            RETURN system.id AS systemId,
                   system.name AS systemName,

                   collect(DISTINCT {
                       id: workflow.id,
                       name: workflow.name
                   }) AS workflows,

                   collect(DISTINCT {
                       id: team.id,
                       name: team.name
                   }) AS teams
            """;

        try (Session session = driver.session()) {

            var record = session.run(
                    cypher,
                    Values.parameters("systemId", systemId)
            ).single();
            var system =
                    new SystemImpactResponse.SystemInfo(
                            record.get("systemId").asString(),
                            record.get("systemName").asString()
                    );

            var workflows = record.get("workflows").asList(value ->
                    new SystemImpactResponse.WorkflowInfo(
                            value.get("id").asString(),
                            value.get("name").asString()
                    )
            );

            var teams = record.get("teams").asList(value ->
                    new SystemImpactResponse.TeamInfo(
                            value.get("id").asString(),
                            value.get("name").asString()
                    )
            );

            return new SystemImpactResponse(system, workflows, teams);
        }
    }
    public List<SearchResultResponse> search(String query) {

        String cypher = """
            MATCH (node)
            WHERE
                toLower(coalesce(node.name, '')) CONTAINS toLower($query)
                OR
                toLower(coalesce(node.id, '')) CONTAINS toLower($query)

            RETURN
                CASE
                    WHEN node:Team THEN 'TEAM'
                    WHEN node:Workflow THEN 'WORKFLOW'
                    WHEN node:Agent THEN 'AGENT'
                    WHEN node:Skill THEN 'SKILL'
                    WHEN node:Connector THEN 'CONNECTOR'
                    WHEN node:System THEN 'SYSTEM'
                    WHEN node:Task THEN 'TASK'
                    WHEN node:User THEN 'USER'
                    ELSE 'UNKNOWN'
                END AS type,

                node.id AS id,
                node.name AS name

            ORDER BY name
            LIMIT 20
            """;

        try (Session session = driver.session()) {

            return session.run(
                            cypher,
                            Values.parameters("query", query)
                    )
                    .list(record -> new SearchResultResponse(
                            record.get("type").asString(),
                            record.get("id").asString(),
                            record.get("name").asString()
                    ));
        }
    }

}
