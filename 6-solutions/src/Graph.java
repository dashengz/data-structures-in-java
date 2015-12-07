import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.*;

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
   * @param u
   *          the source vertex.
   * @param w
   *          the target vertex.
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
   * @param u
   *          unique name of the first vertex.
   * @param w
   *          unique name of the second vertex.
   */
  public void addEdge(String nameU, String nameV) {
    addEdge(nameU, nameV, 1.0);
  }


  /****************************
   * Your code follow here.   *
   ****************************/ 

  public void addUndirectedEdge(String s, String t, double cost) {
      addEdge(s,t,cost);
      addEdge(t,s,cost);
  }

  public double computeEuclideanCost(double ux, double uy, double vx, double vy) {
    return Math.sqrt(Math.pow(ux-vx, 2) + Math.pow(uy-vy, 2));
  }

  public void computeAllEuclideanCosts() {
    for (Vertex u : getVertices())
      for (Edge uv : u.getEdges()) {
        Vertex v = uv.targetVertex;
        uv.cost = computeEuclideanCost(u.posX, u.posY, v.posX, v.posY);
      }
  }

  /** BFS */
  public void doBfs(String s) {
    LinkedList<Vertex> queue = new LinkedList<>();
    for (Vertex u : getVertices()) {
      u.visited = false;
      u.cost = Double.POSITIVE_INFINITY;
      u.backpointer = null;
    }

    Vertex v;
    Vertex u = vertices.get(s);
    u.visited = true;
    queue.offer(u);

    while (!queue.isEmpty()) {
      u = queue.poll();
      for (Edge uv : u.getEdges()) {
        v = uv.targetVertex;
        if (!v.visited) {
          v.backpointer = u;
          v.cost = u.cost + 1.0;
          v.visited = true;
          queue.offer(v);
        }
      }
    }
  }
  
  public Graph getUnweightedShortestPath(String s, String t) {
    doBfs(s);
    // Initialize new graph with vertices but no edges
    Graph result = new Graph();
    for (Vertex v : getVertices()) result.addVertex(new Vertex(v.name, v.posX, v.posY));
    Vertex v = vertices.get(t);
    // Follow backpointers and insert new edges
    while (v.backpointer != null) {
      result.addEdge(v.backpointer.name, v.name);
      v = v.backpointer;
    }
    return result;
  }

  private class AgendaItem implements Comparable<AgendaItem> {
    double cost;
    Vertex vertex;
    public AgendaItem(double cost, Vertex vertex) {
      this.cost = cost;
      this.vertex = vertex;
    }
    public int compareTo(AgendaItem o) {
      if (o.cost > this.cost)
        return -1;
      if (o.cost < this.cost)
        return 1;
      return 0;
    }
  }


  /** Dijkstra's */
  public void doDijkstra(String s) {
    PriorityQueue<AgendaItem> queue = new PriorityQueue<>();
    for (Vertex u : getVertices()) {
      u.visited = false;
      u.cost = Double.POSITIVE_INFINITY;
      u.backpointer = null;
    }


    Vertex v;
    Vertex u = vertices.get(s);
    u.visited = true;
    u.cost = 0.0;
    queue.offer(new AgendaItem(0.0, u));

    AgendaItem next;
    while (!queue.isEmpty()) {
      next = queue.poll();
      u = next.vertex;
      u.visited = true;
      for (Edge uv : u.getEdges()) {
        v = uv.targetVertex;
        if (!v.visited && (u.cost + uv.cost < v.cost)) {
          v.backpointer = u;
          v.cost = uv.cost + u.cost;
          queue.offer(new AgendaItem(v.cost, v));
        }
      }
    }
  }

  public Graph getWeightedShortestPath(String s, String t) {
    doDijkstra(s);
    // Initialize new graph with vertices but no edges
    Graph result = new Graph();
    for (Vertex v : getVertices()) result.addVertex(new Vertex(v.name, v.posX, v.posY));
    Vertex v = vertices.get(t);

    // Follow backpointers and insert new edges
    while (v.backpointer != null) {
      result.addEdge(v.backpointer.name, v.name);
      v = v.backpointer;
    }
    return result;
  }

  /** Prim's */
  public void doPrim(String s) {

    PriorityQueue<AgendaItem> queue = new PriorityQueue<>();
    for (Vertex u : getVertices()) {
      u.visited = false;
      u.cost = Double.POSITIVE_INFINITY;
      u.backpointer = null;
    }


    Vertex v;
    Vertex u = vertices.get(s);
    u.visited = true;
    u.cost = 0.0;
    queue.offer(new AgendaItem(0.0, u));

    AgendaItem next;
    while (!queue.isEmpty()) {
      next = queue.poll();
      System.out.println(next.cost);
      u = next.vertex;
      u.visited = true;
      for (Edge uv : u.getEdges()) {
        v = uv.targetVertex;
        if (!v.visited && (uv.cost < v.cost)) {
          v.backpointer = u;
          v.cost = uv.cost;
          queue.offer(new AgendaItem(v.cost, v));
        }
      }
    }
  }

  public Graph getMinimumSpanningTree(String s) {
    doPrim(s);
    // Initialize new graph with vertices but no edges
    Graph result = new Graph();
    for (Vertex v : getVertices()) result.addVertex(new Vertex(v.name, v.posX, v.posY));
    for (Vertex v: getVertices()) {
      if (v.backpointer!=null)
        result.addEdge(v.name, v.backpointer.name);
    }

    return result;
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
