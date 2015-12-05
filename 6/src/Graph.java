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
        // assumes that no actual euclidean cost in the map is the default value 1.0
        // since this is an undirected graph, without the if statement, the edge would all be calculated twice.
        if (e.cost == 1.0)
          e.cost = computeEuclideanCost(e.sourceVertex.posX,e.sourceVertex.posY,e.targetVertex.posX,e.targetVertex.posY);
      }
    }
  }

  /** BFS */
  public void doBfs(String s) {
    LinkedList<Vertex> bfsQ = new LinkedList<>();
    Vertex vertexS = getVertex(s);
    vertexS.cost = 0;
    vertexS.visited = true;
    for (Vertex v : vertices.values()) {
      if (!v.name.equalsIgnoreCase(vertexS.name))
        v.cost = Double.MAX_VALUE;
    }
    bfsQ.add(vertexS);

    while (bfsQ.size() > 0) {
      Vertex u = bfsQ.pollFirst();
      for (Edge e : u.getEdges()) {
        Vertex v = e.targetVertex;
        if (!v.visited) {
          if (v.cost == Double.MAX_VALUE) {
            v.backpointer = u;
            v.cost = u.cost + 1;
            v.visited = true;
            bfsQ.add(v);
          }
        }
      }
    }
  }
  
  public Graph getUnweightedShortestPath(String s, String t) {
    // call doBfs() and start with s
    doBfs(s);

    Graph spStoT = new Graph();
    for (Vertex v : vertices.values()) {
      spStoT.addVertex(v);
      spStoT.getVertex(v.name).getEdges().clear();
    }

    Vertex vertexS = getVertex(s);
    Vertex vertexT = getVertex(t);

    Vertex u = vertexT.backpointer;
    spStoT.addUndirectedEdge(t, u.name, 1.0);
    while (!u.name.equalsIgnoreCase(vertexS.name)) {
      spStoT.addUndirectedEdge(u.name, u.backpointer.name, 1.0);
      u = u.backpointer;
    }

    return spStoT;
  }

  /** Dijkstra's
   * An example city pair that has different unweighted shortest path and weighted shortest path:
   * SaintLouis and Charleston:
   * BFS's result: SaintLouis -> Nashville -> Raleigh -> Charleston
   * Dijkstra's result: SaintLouis -> Nashville -> Atlanta -> Charleston
   */
  public void doDijkstra(String s) {
    PriorityQueue<Pair> dijkstraPQ = new PriorityQueue<>();
    Vertex vertexS = getVertex(s);
    vertexS.cost = 0;
    for (Vertex v : vertices.values()) {
      if (!v.name.equalsIgnoreCase(vertexS.name))
        v.cost = Double.MAX_VALUE;
        v.visited = false;
    }
    dijkstraPQ.add(new Pair(vertexS.cost, vertexS));

    while (dijkstraPQ.size() > 0) {
      Pair u = dijkstraPQ.poll();
      if (!u.vertex.visited) {
        u.vertex.visited = true;
        for (Edge e : u.vertex.getEdges()) {
          Vertex v = e.targetVertex;
          if (!v.visited) {
            if (u.cost + e.cost < v.cost) {
              v.backpointer = u.vertex;
              v.cost = u.vertex.cost + e.cost;
              dijkstraPQ.add(new Pair(v.cost, v));
            }
          }
        }
      }
    }
  }

  public Graph getWeightedShortestPath(String s, String t) {
    // call doDijkstra() and start with s
    doDijkstra(s);

    Graph spStoT = new Graph();
    for (Vertex v : vertices.values()) {
      spStoT.addVertex(v);
      spStoT.getVertex(v.name).getEdges().clear();
    }

    Vertex vertexS = getVertex(s);
    Vertex vertexT = getVertex(t);

    Vertex u = vertexT.backpointer;
    spStoT.addUndirectedEdge(t, u.name, 1.0);
    while (!u.name.equalsIgnoreCase(vertexS.name)) {
      spStoT.addUndirectedEdge(u.name, u.backpointer.name, 1.0);
      u = u.backpointer;
    }

    return spStoT;
  }

  /** Prim's */
  public void doPrim(String s) {
    PriorityQueue<Pair> primPQ = new PriorityQueue<>();
    Vertex vertexS = getVertex(s);
    vertexS.cost = 0;
    vertexS.visited = true;
    for (Vertex v : vertices.values()) {
      if (!v.name.equalsIgnoreCase(vertexS.name))
        v.cost = Double.MAX_VALUE;
        v.visited = false;
    }
    primPQ.add(new Pair(vertexS.cost, vertexS));

    while (primPQ.size() > 0) {
      Pair u = primPQ.poll();
      if (!u.vertex.visited) {
        u.vertex.visited = true;
        for (Edge e : u.vertex.getEdges()) {
          Vertex v = e.targetVertex;
          if (!v.visited) {
            if (e.cost < v.cost) {
              v.backpointer = u.vertex;
              v.cost = e.cost;
              primPQ.add(new Pair(v.cost, v));
            }
          }
        }
      }
    }
  }

  public Graph getMinimumSpanningTree(String s) {
    // call doPrim() and start with s
    doPrim(s);

    Graph mst = new Graph();
    for (Vertex v : vertices.values()) {
      mst.addVertex(v);
      v.visited = false;
      mst.getVertex(v.name).getEdges().clear();
    }

    for (Vertex v : vertices.values()) {
      while (v.backpointer != null && !v.visited) {
        Vertex u = v.backpointer;
        v.visited = true;
        mst.addUndirectedEdge(v.name, u.name, 1.0);
      }
    }

    return mst;
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
