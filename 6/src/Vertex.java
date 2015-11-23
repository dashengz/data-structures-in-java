import java.util.LinkedList;
import java.util.List;

public class Vertex {
  public String name;
  private List<Edge> adjacent;
  public int posX = 0;
  public int posY = 0;

  // Variables used by BFS, Dijkstra's, and Prim's
  public boolean visited;
  public double cost;   
  public Vertex backpointer; // use this instance variable for the 
                             // prev pointers used by BFS and Dijkstras, 
                             // and for the parent pointer used by Prim's

  /**
   * Construct a new vertex containing an adjacency list.
   * 
   * @param vertexName
   *          a unique identifier for this vertex.
   * @param x
   *          the x coordinate for this vertex
   * @param y
   *          the y coordinate for this vertex
   */
  public Vertex(String vertexName, int x, int y) {
    name = vertexName;
    adjacent = new LinkedList<Edge>();
    posX = x;
    posY = y;
  }

  /**
   * Construct a new vertex containing an adjacency list.
   * 
   * @param vertexName
   *          a unique identifier for this vertex.
   */
  public Vertex(String vertexName) {
    this(vertexName, 0, 0);
  }

  /**
   * Retrieve the list of edges connected to this vertex.
   * 
   * @return a list of edges connected to this vertex.
   */
  public List<Edge> getEdges() {
    return adjacent;
  }

  /**
   * Connect an edge to this vertex.
   * 
   * @param e
   *          The new edge to connect to this vertex.
   */
  public void addEdge(Edge e) {
    adjacent.add(e);
  }

  public String toString() {
    return name;
  }

}
