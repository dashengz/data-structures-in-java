public class TestBfs {
  public static void main(String[] args) {
    Graph testBfsGraph = MapReader.readGraph(args[0],args[1]);

    // display the map graph
    DisplayGraph display = new DisplayGraph(testBfsGraph.getUnweightedShortestPath(args[2],args[3]));
    display.setVisible(true);
  }
}
