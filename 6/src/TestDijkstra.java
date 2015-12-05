public class TestDijkstra {
  public static void main(String[] args) {
    Graph map = MapReader.readGraph(args[0],args[1]);
    map.computeAllEuclideanCosts();

    // display weighted shortest path
    DisplayGraph display = new DisplayGraph(map.getWeightedShortestPath(args[2],args[3]));
    display.setVisible(true);
  }
}