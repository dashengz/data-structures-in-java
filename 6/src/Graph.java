import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph {

  // Keep a fast index to nodes in the map
  protected Map<String, Vertex> vertices;

  /**
   * Construct an empty Graph.
   */
  public Graph() {
    vertices = new HashMap<String, Vertex>();
  }

  public void addVertex(String name) {
    Vertex v = new Vertex(name);
    addVertex(v);
  }

  public void addVertex(Vertex v) {
    if (vertices.containsKey(v.name))
      throw new IllegalArgumentException(
          "Cannot create new vertex with existing name.");
    vertices.put(v.name, v);
  }

  public Collection<Vertex> getVertices() {
    return vertices.values();
  }

  public Vertex getVertex(String s) {
    return vertices.get(s);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param nameU
   *          the source vertex.
   * @param nameV
   *          the target vertex.
   * @param cost
   *          the cost of the edge.
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertices.containsKey(nameU))
      addVertex(nameU);
    if (!vertices.containsKey(nameV))
      addVertex(nameV);
    Vertex sourceVertex = vertices.get(nameU);
    Vertex targetVertex = vertices.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param nameU
   *          unique name of the first vertex.
   * @param nameV
   *          unique name of the second vertex.
   */
  public void addEdge(String nameU, String nameV) {
    addEdge(nameU, nameV, 1.0);
  }


  /****************************
   * Your code follow here.   *
   ****************************/ 

  public void addUndirectedEdge(String s, String t, double cost) {
    if (!vertices.containsKey(s))
      addVertex(s);
    if (!vertices.containsKey(t))
      addVertex(t);
    Vertex sourceVertex = vertices.get(s);
    Vertex targetVertex = vertices.get(t);
    Edge sToT = new Edge(sourceVertex, targetVertex, cost);
    Edge tToS = new Edge(targetVertex, sourceVertex, cost);
    sourceVertex.addEdge(sToT);
    targetVertex.addEdge(tToS);
  }

  public double computeEuclideanCost(double ux, double uy, double vx, double vy) {
    return Math.sqrt((ux - vx) * (ux - vx) + (uy - vy) * (uy - vy));
  }

  public void computeAllEuclideanCosts() {
    for (Vertex v : vertices.values()) {
      for (Edge e : v.getEdges()) {
        e.cost = computeEuclideanCost(e.sourceVertex.posX,e.sourceVertex.posY,e.targetVertex.posX,e.targetVertex.posY);
      }
    }
  }

  /** BFS */
  public void doBfs(String s) {
    return; // TODO
  }
  
  public Graph getUnweightedShortestPath(String s, String t) {
    return null; // TODO
  }

  /** Dijkstra's */
  public void doDijkstra(String s) {
    return; // TODO
  }

  public Graph getWeightedShortestPath(String s, String t) {
    return null; // TODO
  }

  /** Prim's */
  public void doPrim(String s) {
    return; // TODO
  }

  public Graph getMinimumSpanningTree(String s) {
    return null; // TODO
  }

  /*************************/

  public void printAdjacencyList() {
    for (String u : vertices.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertices.get(u).getEdges()) {
        sb.append(e.targetVertex.name);
        sb.append("(");
        sb.append(e.cost);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    g.addVertex(new Vertex("v0", 0, 0));
    g.addVertex(new Vertex("v1", 0, 1));
    g.addVertex(new Vertex("v2", 1, 0));
    g.addVertex(new Vertex("v3", 1, 1));

    g.addEdge("v0", "v1");
    g.addEdge("v1", "v2");
    g.addEdge("v2", "v3");
    g.addEdge("v3", "v0");
    g.addEdge("v0", "v2");
    g.addEdge("v1", "v3");
    g.printAdjacencyList();

    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);

  }

}
