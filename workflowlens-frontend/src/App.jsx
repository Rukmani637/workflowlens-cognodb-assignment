import { useEffect, useState } from "react";
import "./App.css";

const API_BASE_URL = "http://localhost:8080/api";

function App() {
  const [workflows, setWorkflows] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searching, setSearching] = useState(false);
  const [error, setError] = useState("");
  const [selectedWorkflow, setSelectedWorkflow] = useState(null);
  const [dependencies, setDependencies] = useState(null);
  const [dependencyLoading, setDependencyLoading] = useState(false);
  const [dependencyError, setDependencyError] = useState("");
  const [showImpactAnalysis, setShowImpactAnalysis] = useState(false);
  const [activeSection, setActiveSection] = useState("dashboard");
  const [showTeams, setShowTeams] = useState(false);
  const [showSystems, setShowSystems] = useState(false);

  // Load Sales workflows
  useEffect(() => {
    fetch(`${API_BASE_URL}/teams/team-sales/workflows`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to load workflows");
        }
        return response.json();
      })
      .then((data) => {
        setWorkflows(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setError("Unable to load workflows.");
        setLoading(false);
      });
  }, []);

  // Global search
  useEffect(() => {
    if (!searchQuery.trim()) {
      setSearchResults([]);
      return;
    }
    const timer = setTimeout(() => {
      setSearching(true);

      fetch(
        `${API_BASE_URL}/search?q=${encodeURIComponent(searchQuery)}`
      )
        .then((response) => {
          if (!response.ok) {
            throw new Error("Search failed");
          }
          return response.json();
        })
        .then((data) => {
          setSearchResults(data);
          setSearching(false);
        })
        .catch((err) => {
          console.error(err);
          setSearchResults([]);
          setSearching(false);
        });
    }, 300);

    return () => clearTimeout(timer);
  }, [searchQuery]);

  const viewWorkflowDependencies = async (workflow) => {
    setSelectedWorkflow(workflow);
    setDependencies(null);
    setDependencyError("");
    setDependencyLoading(true);

    try {
      const response = await fetch(
        `${API_BASE_URL}/workflows/${workflow.id}/dependencies`
      );
      if (!response.ok) {
        throw new Error("Failed to load workflow dependencies");
      }
      const data = await response.json();
      console.log("Workflow dependencies:", data);
      setDependencies(data);
    } catch (err) {
      console.error(err);
      setDependencyError("Unable to load workflow dependencies.");
    } finally {
      setDependencyLoading(false);
    }
  };

  const getTypeLabel = (type) => {
    switch (type) {
      case "TEAM":
        return "Team";
      case "WORKFLOW":
        return "Workflow";
      case "AGENT":
        return "Agent";
      case "CONNECTOR":
        return "Connector";
      case "SYSTEM":
        return "System";
      case "SKILL":
        return "Skill";
      case "TASK":
        return "Task";
      default:
        return type;
    }
  };

  return (
    <div className="app">

      {/* SIDEBAR */}
      <aside className="sidebar">
        <div className="logo">
          ◈ WorkflowLens
        </div>
        <nav>
          <button className={`nav-item ${
              activeSection === "dashboard" ? "active" : ""
            }`}
            onClick={() => setActiveSection("dashboard")}>
            Dashboard
          </button>

          <button className={`nav-item ${
              activeSection === "workflows" ? "active" : ""
            }`}
            onClick={() => setActiveSection("workflows")}>
            Workflows
          </button>

          <button className={`nav-item ${
              activeSection === "teams" ? "active" : ""}`}
              onClick={() => {
                  setActiveSection("teams");
                  setShowTeams(true);
              }}>Teams
          </button>

           <button className={`nav-item ${
               activeSection === "systems" ? "active" : ""}`}
                onClick={async () => {
                 setActiveSection("systems");
                 if (!dependencies && workflows.length > 0) {
                 await viewWorkflowDependencies(workflows[0]);
                 }
                setShowSystems(true);}}>
             Systems
           </button>

          <button className={`nav-item ${
              activeSection === "impact" ? "active" : ""
            }`}
            onClick={async () => {
              setActiveSection("impact");
              if (!dependencies && workflows.length > 0) {
                await viewWorkflowDependencies(workflows[0]);
              }
              setShowImpactAnalysis(true);
            }}>
            Impact Analysis
          </button>
        </nav>
      </aside>

      {/* MAIN CONTENT */}
      <main className="main-content">
        <header className="topbar">
          <div>
            <h1>Workflow Intelligence</h1>
            <p>Understand your workflow dependencies and system impact.</p>
          </div>

          {/* SEARCH */}
          <div className="search-container">
            <div className="search-box">
              <span>🔍</span>
              <input type="text" value={searchQuery} onChange={(event) =>setSearchQuery(event.target.value)}
                placeholder="Search workflows, systems, teams..."/>
              {searching && (
                <span className="search-loading">...</span>
              )}
            </div>

            {/* SEARCH RESULTS */}
            {searchQuery.trim() && (
              <div className="search-results">
                {!searching && searchResults.length === 0 && (
                  <div className="no-results">
                    No matching results found.
                  </div>
                )}

                {searchResults.map((result) => (
                  <div className="search-result" key={`${result.type}-${result.id}`}>
                    <div className="result-icon">
                      {result.type === "TEAM" && "👥"}
                      {result.type === "WORKFLOW" && "⚙️"}
                      {result.type === "AGENT" && "🤖"}
                      {result.type === "CONNECTOR" && "🔌"}
                      {result.type === "SYSTEM" && "🖥️"}
                      {result.type === "SKILL" && "🧠"}
                      {result.type === "TASK" && "✓"}
                    </div>

                    <div className="result-info"><strong>{result.name}</strong>
                      <span>{getTypeLabel(result.type)}</span>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </div>
        </header>

        {/* STATS */}
        <section className="stats-grid">
          <div className="stat-card">
            <span>Teams</span><strong>4</strong>
          </div>

          <div className="stat-card">
            <span>Workflows</span><strong>{workflows.length}</strong>
          </div>

          <div className="stat-card">
            <span>Systems</span><strong>6</strong>
          </div>

          <div className="stat-card">
            <span>Agents</span><strong>8</strong>
          </div>
        </section>

        {/* WORKFLOWS */}
        {activeSection === "dashboard" || activeSection === "workflows" ? (
        <section className="content-section">
          <div className="section-header">
            <div>
              <h2>Sales Workflows</h2>
              <p>Workflows owned by the Sales team.</p>
            </div>
            <button className="view-all">View all →</button>
          </div>

          <div className="workflow-list">
            {loading && (
              <div className="workflow-card">
                <p>Loading workflows...</p>
              </div>
            )}
            {error && (
              <div className="workflow-card">
                <p>{error}</p>
              </div>
            )}
            {!loading &&
              !error &&
              workflows.map((workflow) => (
                <div className="workflow-card" key={workflow.id} >
                  <div>
                    <h3>{workflow.name}</h3>
                    <p>{workflow.description ||"Workflow available in WorkflowLens."}</p>
                  </div>
                  <button onClick={() => viewWorkflowDependencies(workflow)}>View →</button>                </div>
              ))}
          </div>
        </section>) : null}

        {/* TEAMS */}
        {activeSection === "teams" && (
          <section className="content-section">
            <div className="section-header">
              <div>
                <h2>Teams</h2>
                <p>Teams available in WorkflowLens.</p>
              </div>
            </div>

            <div className="workflow-list">
              <div className="workflow-card">
                <div>
                  <h3>Sales</h3>
                  <p>Team responsible for sales workflows.</p>
                </div>
                <span>{workflows.length} workflows</span>
              </div>

              <div className="workflow-card">
                <div>
                  <h3>Marketing</h3>
                  <p>Team responsible for marketing workflows.</p>
                </div>
              </div>

              <div className="workflow-card">
                <div>
                  <h3>Operations</h3>
                  <p>Team responsible for operational workflows.</p>
                </div>
              </div>

              <div className="workflow-card">
                <div>
                  <h3>Customer Success</h3>
                  <p>Team responsible for customer success workflows.</p>
                </div>
              </div>
            </div>
          </section>
        )}

    {/* SYSTEMS */}
    {activeSection === "systems" && (
      <section className="content-section">
        <div className="section-header">
          <div>
            <h2>Systems</h2>
            <p>Systems connected to your workflow ecosystem.</p>
          </div>
        </div>

        {dependencyLoading && (
          <div className="workflow-card">
            <p>Loading systems...</p>
          </div>
        )}

        {dependencies && !dependencyLoading && (
          <div className="workflow-list">

            {dependencies.systems.map((system) => (
              <div
                className="workflow-card"
                key={system.id}>
                <div>
                  <h3>{system.name}</h3>
                  <p>Connected system used by the workflow ecosystem.</p>
                </div>

                <span>System</span>
              </div>
            ))}
          </div>
        )}
      </section>
    )}

        {/* DEPENDENCY PREVIEW */}
        <section className="content-section">
          <div className="section-header">
            <div>
              <h2>Dependency Intelligence</h2>
              <p>Explore relationships across your workflow ecosystem.</p>
            </div>
          </div>

          <div className="dependency-preview">
            <div className="dependency-node">Workflow
              <strong>Lead Qualification</strong>
            </div>

            <span>→</span>
            <div className="dependency-node">Agent
              <strong>Lead Qualification Agent</strong>
            </div>

            <span>→</span>
            <div className="dependency-node">Skill
              <strong>CRM Update</strong>
            </div>

            <span>→</span>
            <div className="dependency-node">System
              <strong>Salesforce CRM</strong>
            </div>
          </div>
        </section>

        {/* DEPENDENCY EXPLORER */}
        {selectedWorkflow && (
          <section className="content-section">
            <div className="section-header">
              <div>
                <h2>Dependency Explorer</h2>
                <p>Exploring dependencies for{" "}
                  <strong>{selectedWorkflow.name}</strong>
                </p>
              </div>
                <div>
                    <button className="view-all" onClick={() => setShowImpactAnalysis(true)}>
                        Impact Analysis
                    </button>
                    <button className="view-all" onClick={() => {
                        setSelectedWorkflow(null);
                        setDependencies(null);
                        setShowImpactAnalysis(false);
                    }}>Close
                    </button>
                </div>
            </div>

            {dependencyLoading && (
              <div className="workflow-card">
                <p>Loading dependency information...</p>
              </div>
            )}

            {dependencyError && (
              <div className="workflow-card">
                <p>{dependencyError}</p>
              </div>
            )}

            {dependencies && !dependencyLoading && (
              <div className="dependency-explorer">

                {/* WORKFLOW */}
                <div className="explorer-title">
                  <span>WORKFLOW</span>
                  <h3>{dependencies.workflow.name}</h3>
                </div>

                {/* RELATIONSHIPS */}
                {dependencies.relationships.map(
                  (relationship, index) => {

                    const fromNode =relationship.from;
                    const toNode = relationship.to;
                    const findNodeName = (id) => {
                      if (dependencies.workflow.id === id) {
                        return dependencies.workflow.name;
                      }

                      const agent =dependencies.agents.find(
                          (item) => item.id === id);
                      if (agent) return agent.name;

                      const skill =dependencies.skills.find(
                          (item) => item.id === id);
                      if (skill) return skill.name;

                      const connector =dependencies.connectors.find(
                          (item) => item.id === id);
                      if (connector) return connector.name;

                      const system =dependencies.systems.find(
                          (item) => item.id === id);
                      if (system) return system.name;
                      return id;
                    };

                    return (
                      <div className="dependency-relationship"key={index}>
                        <div className="dependency-node">
                          <span>FROM</span>
                          <strong>{findNodeName(fromNode)}</strong>
                        </div>

                        <div className="relationship-arrow">
                          <span>{relationship.type}</span>
                          <strong>↓</strong>
                        </div>

                        <div className="dependency-node">
                          <span>TO</span>
                          <strong>{findNodeName(toNode)}</strong>
                        </div>
                      </div>
                    );
                  }
                )}
              </div>
            )}
          </section>
        )}

    {/* IMPACT ANALYSIS */}
    {showImpactAnalysis && dependencies && (
      <section className="content-section">
        <div className="section-header">
          <div>
            <h2>Impact Analysis</h2>
            <p>Understanding the potential impact of changes to{" "}
              <strong>{dependencies.workflow.name}</strong>
            </p>
          </div>

          <button className="view-all" onClick={() => setShowImpactAnalysis(false)}>
            Back to Dependencies
          </button>
        </div>

        <div className="stats-grid">
          <div className="stat-card">
            <span>Workflows</span>
            <strong>1</strong>
          </div>

          <div className="stat-card">
            <span>Agents</span>
            <strong>{dependencies.agents.length}</strong>
          </div>

          <div className="stat-card">
            <span>Skills</span>
            <strong>{dependencies.skills.length}</strong>
          </div>

          <div className="stat-card">
            <span>Systems</span>
            <strong>{dependencies.systems.length}</strong>
          </div>
        </div>

        <div className="content-section">
          <h3>Potentially Affected Components</h3>
          <div className="workflow-list">

            {dependencies.agents.map((agent) => (
              <div className="workflow-card" key={agent.id}>
                <div>
                  <h3>{agent.name}</h3>
                  <p>Agent involved in this workflow.</p>
                </div>
              </div>
            ))}

            {dependencies.skills.map((skill) => (
              <div className="workflow-card" key={skill.id}>
                <div>
                  <h3>{skill.name}</h3>
                  <p>Skill used by the workflow.</p>
                </div>
              </div>
            ))}

            {dependencies.connectors.map((connector) => (
              <div className="workflow-card" key={connector.id}>
                <div>
                  <h3>{connector.name}</h3>
                  <p>Connector used by the workflow.</p>
                </div>
              </div>
            ))}

            {dependencies.systems.map((system) => (
              <div className="workflow-card" key={system.id}>
                <div>
                  <h3>{system.name}</h3>
                  <p>System connected to the workflow.</p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>
    )}
      </main>
    </div>
  );
}

export default App;