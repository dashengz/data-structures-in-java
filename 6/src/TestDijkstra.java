public class TestDijkstra {
  public static void main(String[] args) {
    Graph testDijkstraGraph = MapReader.readGraph(args[0],args[1]);

    DisplayGraph display = new DisplayGraph(testDijkstraGraph.getWeightedShortestPath(args[2],args[3]));
    display.setVisible(true);
  }
}
