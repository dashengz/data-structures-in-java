public class TestDijkstra {
  public static void main(String[] args) {
    Graph testDijkstraGraph = MapReader.readGraph(args[0],args[1]);

    // display the map graph
    DisplayGraph display = new DisplayGraph(testDijkstraGraph.getUnweightedShortestPath(args[2],args[3]));
    display.setVisible(true);
  }
}
