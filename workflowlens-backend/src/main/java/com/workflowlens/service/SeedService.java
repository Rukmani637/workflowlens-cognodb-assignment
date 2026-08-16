package com.workflowlens.service;

import com.workflowlens.repository.GraphRepository;
import org.springframework.stereotype.Service;

@Service
public class SeedService {
    private  final GraphRepository graphRepository;
    public SeedService(GraphRepository graphRepository){
        this.graphRepository=graphRepository;
    }
    public void seedDatabase(){
        //  TEAMS    ////
        graphRepository.createTeam(
                "team-sales",
                "Sales",
                "Team responsible for lead generation and customer acquisition."
        );
        graphRepository.createTeam(
                "team-support",
                "Customer Support",
                "Team responsible for customer issues and service requests."
        );
        graphRepository.createTeam(
                "team-finance",
                "Finance",
                "Team responsible for invoices and payment operations."
        );
        graphRepository.createTeam(
                "team-operations",
                "Operations",
                "Team responsible for internal operational workflows."
        );

        /// /// USERS////

        graphRepository.createUser(
                "user-aarav",
                "Aarav Sharma",
                "aarav@workflowlens.demo"
        );
        graphRepository.createUser(
                "user-priya",
                "Priya Das",
                "priya@workflowlens.demo"
        );
        graphRepository.createUser(
                "user-rohan",
                "Rohan Mehta",
                "rohan@workflowlens.demo"
        );
        graphRepository.createUser(
                "user-ananya",
                "Ananya Patel",
                "ananya@workflowlens.demo"
        );

        /// WORKFOLW///

        graphRepository.createWorkflow(
                "workflow-lead-qualification",
                "Lead Qualification",
                "Automatically analyzes and qualifies incoming sales leads.",
                "ACTIVE"
        );
        graphRepository.createWorkflow(
                "workflow-sales-followup",
                "Sales Follow-up",
                "Sends personalized follow-up communication to qualified leads.",
                "ACTIVE"
        );
        graphRepository.createWorkflow(
                "workflow-ticket-resolution",
                "Customer Ticket Resolution",
                "Classifies and routes incoming customer support tickets.",
                "ACTIVE"
        );
        graphRepository.createWorkflow(
                "workflow-feedback-analysis",
                "Customer Feedback Analysis",
                "Analyzes customer feedback and identifies common issues.",
                "ACTIVE"
        );
        graphRepository.createWorkflow(
                "workflow-invoice-processing",
                "Invoice Processing",
                "Processes incoming invoices and extracts financial information.",
                "ACTIVE"
        );
        graphRepository.createWorkflow(
                "workflow-payment-reconciliation",
                "Payment Reconciliation",
                "Matches payments with invoices and identifies discrepancies.",
                "ACTIVE"
        );
        graphRepository.createWorkflow(
                "workflow-employee-onboarding",
                "Employee Onboarding",
                "Automates internal employee onboarding activities.",
                "ACTIVE"
        );
        graphRepository.createWorkflow(
                "workflow-document-approval",
                "Document Approval",
                "Routes internal documents through an approval workflow.",
                "PAUSED"
        );


        ///   AGENTS   ///
        graphRepository.createAgent(
                "agent-lead-qualification",
                "Lead Qualification Agent",
                "Analyzes incoming leads and determines qualification.",
                "ACTIVE"
        );
        graphRepository.createAgent(
                "agent-sales-followup",
                "Sales Follow-up Agent",
                "Creates and sends personalized sales follow-ups.",
                "ACTIVE"
        );
        graphRepository.createAgent(
                "agent-ticket-classification",
                "Ticket Classification Agent",
                "Classifies incoming support tickets.",
                "ACTIVE"
        );
        graphRepository.createAgent(
                "agent-feedback-analysis",
                "Feedback Analysis Agent",
                "Analyzes customer feedback.",
                "ACTIVE"
        );
        graphRepository.createAgent(
                "agent-invoice",
                "Invoice Processing Agent",
                "Extracts and validates invoice information.",
                "ACTIVE"
        );
        graphRepository.createAgent(
                "agent-payment",
                "Payment Reconciliation Agent",
                "Matches payments with financial records.",
                "ACTIVE"
        );
        graphRepository.createAgent(
                "agent-onboarding",
                "Employee Onboarding Agent",
                "Coordinates employee onboarding activities.",
                "ACTIVE"
        );
        graphRepository.createAgent(
                "agent-document",
                "Document Approval Agent",
                "Routes documents to appropriate approvers.",
                "ACTIVE"
        );


        ///   SKILLS   ///
        graphRepository.createSkill(
                "skill-lead-analysis",
                "Lead Analysis",
                "SALES",
                "Analyzes lead information and determines qualification."
        );
        graphRepository.createSkill(
                "skill-crm-update",
                "CRM Update",
                "SALES",
                "Creates and updates CRM records."
        );
        graphRepository.createSkill(
                "skill-email-communication",
                "Email Communication",
                "COMMUNICATION",
                "Creates and sends email messages."
        );
        graphRepository.createSkill(
                "skill-ticket-classification",
                "Ticket Classification",
                "SUPPORT",
                "Classifies customer support requests."
        );
        graphRepository.createSkill(
                "skill-feedback-analysis",
                "Feedback Analysis",
                "SUPPORT",
                "Analyzes customer feedback."
        );
        graphRepository.createSkill(
                "skill-document-extraction",
                "Document Extraction",
                "FINANCE",
                "Extracts structured information from documents."
        );
        graphRepository.createSkill(
                "skill-payment-matching",
                "Payment Matching",
                "FINANCE",
                "Matches payments against invoices."
        );
        graphRepository.createSkill(
                "skill-slack-notification",
                "Slack Notification",
                "COMMUNICATION",
                "Sends operational notifications to Slack."
        );

        ///    CONNECTS   ///
        graphRepository.createConnector(
                "connector-salesforce",
                "Salesforce",
                "CRM",
                "AVAILABLE"
        );
        graphRepository.createConnector(
                "connector-gmail",
                "Gmail",
                "EMAIL",
                "AVAILABLE"
        );
        graphRepository.createConnector(
                "connector-zendesk",
                "Zendesk",
                "SUPPORT",
                "AVAILABLE"
        );
        graphRepository.createConnector(
                "connector-google-drive",
                "Google Drive",
                "STORAGE",
                "AVAILABLE"
        );
        graphRepository.createConnector(
                "connector-stripe",
                "Stripe",
                "PAYMENTS",
                "AVAILABLE"
        );
        graphRepository.createConnector(
                "connector-slack",
                "Slack",
                "COMMUNICATION",
                "AVAILABLE"
        );


        ///    SYSTEMS   ///
        graphRepository.createSystem(
                "system-salesforce",
                "Salesforce CRM",
                "CRM"
        );
        graphRepository.createSystem(
                "system-gmail",
                "Gmail",
                "EMAIL"
        );
        graphRepository.createSystem(
                "system-zendesk",
                "Zendesk",
                "SUPPORT"
        );
        graphRepository.createSystem(
                "system-google-drive",
                "Google Drive",
                "STORAGE"
        );
        graphRepository.createSystem(
                "system-stripe",
                "Stripe",
                "PAYMENTS"
        );
        graphRepository.createSystem(
                "system-slack",
                "Slack",
                "COMMUNICATION"
        );


        ///    TASKS   ///
        graphRepository.createTask(
                "task-analyze-lead",
                "Analyze Incoming Lead",
                "Analyze the information submitted by a new lead.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-update-crm",
                "Update CRM",
                "Create or update the lead in Salesforce.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-send-followup",
                "Send Follow-up",
                "Send personalized follow-up email.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-classify-ticket",
                "Classify Ticket",
                "Determine the category of an incoming support ticket.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-analyze-feedback",
                "Analyze Feedback",
                "Analyze customer feedback.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-extract-invoice",
                "Extract Invoice Data",
                "Extract financial information from an invoice.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-match-payment",
                "Match Payment",
                "Match payment with the corresponding invoice.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-onboard-employee",
                "Onboard Employee",
                "Complete employee onboarding activities.",
                "PENDING"
        );
        graphRepository.createTask(
                "task-approve-document",
                "Approve Document",
                "Route a document for approval.",
                "PENDING"
        );


        /// /// RELATIONSHIPS   //////

        // User  -->  Team
        System.out.println("RELATIONSHIP 1- START");
        graphRepository.connectUserToTeam(
                "user-aarav",
                "team-sales"
        );
        System.out.println("Relationship 1 -done");
        System.out.println("RELATIONSHIP 2- START");
        graphRepository.connectUserToTeam(
                "user-priya",
                "team-support"
        );
        System.out.println("Relationship 1 -done");
        graphRepository.connectUserToTeam(
                "user-rohan",
                "team-finance"
        );
        graphRepository.connectUserToTeam(
                "user-ananya",
                "team-operations"
        );

        // Team --->  Workflow
        graphRepository.connectTeamToWorkflow(
                "team-sales",
                "workflow-lead-qualification"
        );
        graphRepository.connectTeamToWorkflow(
                "team-sales",
                "workflow-sales-followup"
        );
        graphRepository.connectTeamToWorkflow(
                "team-support",
                "workflow-ticket-resolution"
        );
        graphRepository.connectTeamToWorkflow(
                "team-support",
                "workflow-feedback-analysis"
        );
        graphRepository.connectTeamToWorkflow(
                "team-finance",
                "workflow-invoice-processing"
        );
        graphRepository.connectTeamToWorkflow(
                "team-finance",
                "workflow-payment-reconciliation"
        );
        graphRepository.connectTeamToWorkflow(
                "team-operations",
                "workflow-employee-onboarding"
        );
        graphRepository.connectTeamToWorkflow(
                "team-operations",
                "workflow-document-approval"
        );

        //  Workflow  --->  Agent
        graphRepository.connectWorkflowToAgent(
                "workflow-lead-qualification",
                "agent-lead-qualification"
        );
        graphRepository.connectWorkflowToAgent(
                "workflow-sales-followup",
                "agent-sales-followup"
        );
        graphRepository.connectWorkflowToAgent(
                "workflow-ticket-resolution",
                "agent-ticket-classification"
        );
        graphRepository.connectWorkflowToAgent(
                "workflow-feedback-analysis",
                "agent-feedback-analysis"
        );
        graphRepository.connectWorkflowToAgent(
                "workflow-invoice-processing",
                "agent-invoice"
        );
        graphRepository.connectWorkflowToAgent(
                "workflow-payment-reconciliation",
                "agent-payment"
        );
        graphRepository.connectWorkflowToAgent(
                "workflow-employee-onboarding",
                "agent-onboarding"
        );
        graphRepository.connectWorkflowToAgent(
                "workflow-document-approval",
                "agent-document"
        );

        //  Agent   --->   Skills
        graphRepository.connectAgentToSkill(
                "agent-lead-qualification",
                "skill-lead-analysis"
        );
        graphRepository.connectAgentToSkill(
                "agent-lead-qualification",
                "skill-crm-update"
        );
        graphRepository.connectAgentToSkill(
                "agent-sales-followup",
                "skill-email-communication"
        );
        graphRepository.connectAgentToSkill(
                "agent-sales-followup",
                "skill-crm-update"
        );
        graphRepository.connectAgentToSkill(
                "agent-ticket-classification",
                "skill-ticket-classification"
        );
        graphRepository.connectAgentToSkill(
                "agent-feedback-analysis",
                "skill-feedback-analysis"
        );
        graphRepository.connectAgentToSkill(
                "agent-invoice",
                "skill-document-extraction"
        );
        graphRepository.connectAgentToSkill(
                "agent-payment",
                "skill-payment-matching"
        );
        graphRepository.connectAgentToSkill(
                "agent-payment",
                "skill-document-extraction"
        );
        graphRepository.connectAgentToSkill(
                "agent-onboarding",
                "skill-email-communication"
        );
        graphRepository.connectAgentToSkill(
                "agent-onboarding",
                "skill-slack-notification"
        );
        graphRepository.connectAgentToSkill(
                "agent-document",
                "skill-document-extraction"
        );
        graphRepository.connectAgentToSkill(
                "agent-document",
                "skill-slack-notification"
        );

        //   Skills  --->  Connector
        graphRepository.connectSkillToConnector(
                "skill-crm-update",
                "connector-salesforce"
        );
        graphRepository.connectSkillToConnector(
                "skill-lead-analysis",
                "connector-salesforce"
        );
        graphRepository.connectSkillToConnector(
                "skill-email-communication",
                "connector-gmail"
        );
        graphRepository.connectSkillToConnector(
                "skill-ticket-classification",
                "connector-zendesk"
        );
        graphRepository.connectSkillToConnector(
                "skill-feedback-analysis",
                "connector-zendesk"
        );
        graphRepository.connectSkillToConnector(
                "skill-document-extraction",
                "connector-google-drive"
        );
        graphRepository.connectSkillToConnector(
                "skill-payment-matching",
                "connector-stripe"
        );
        graphRepository.connectSkillToConnector(
                "skill-slack-notification",
                "connector-slack"
        );

        //   Connectors  --->  Systems
        graphRepository.connectConnectorToSystem(
                "connector-salesforce",
                "system-salesforce"
        );
        graphRepository.connectConnectorToSystem(
                "connector-gmail",
                "system-gmail"
        );
        graphRepository.connectConnectorToSystem(
                "connector-zendesk",
                "system-zendesk"
        );
        graphRepository.connectConnectorToSystem(
                "connector-google-drive",
                "system-google-drive"
        );
        graphRepository.connectConnectorToSystem(
                "connector-stripe",
                "system-stripe"
        );
        graphRepository.connectConnectorToSystem(
                "connector-slack",
                "system-slack"
        );

        //  Workflow   --->  Tasks
        graphRepository.connectWorkflowToTask(
                "workflow-lead-qualification",
                "task-analyze-lead"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-lead-qualification",
                "task-update-crm"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-sales-followup",
                "task-send-followup"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-ticket-resolution",
                "task-classify-ticket"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-feedback-analysis",
                "task-analyze-feedback"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-invoice-processing",
                "task-extract-invoice"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-payment-reconciliation",
                "task-match-payment"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-employee-onboarding",
                "task-onboard-employee"
        );
        graphRepository.connectWorkflowToTask(
                "workflow-document-approval",
                "task-approve-document"
        );

    }
}
