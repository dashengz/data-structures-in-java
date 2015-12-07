public class Edge implements Comparable<Edge> {
  public Vertex sourceVertex;
  public Vertex targetVertex;
  public Double cost;

  public Edge(Vertex source, Vertex target, Double theCost) {
    sourceVertex = source;
    targetVertex = target;
    cost = theCost;
  }

  public Edge(Vertex source, Vertex target) {
    this(source, target, 1.0);
  }

  public int compareTo(Edge other) {
    return cost.compareTo(other.cost);
  }
}
