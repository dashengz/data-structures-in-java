public class TestDijkstra {

  public static void main(String[] args) {
    Graph g = MapReader.readGraph(args[0],args[1]);
    g.computeAllEuclideanCosts();
    Graph dijkstraResult = g.getWeightedShortestPath(args[2],args[3]);
    DisplayGraph display = new DisplayGraph(dijkstraResult);
    display.setVisible(true);
  }

}
