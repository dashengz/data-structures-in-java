public class TestBfs {

  public static void main(String[] args) {
    Graph g = MapReader.readGraph(args[0],args[1]);
    Graph bfsResult = g.getUnweightedShortestPath(args[2],args[3]);
    DisplayGraph display = new DisplayGraph(bfsResult);
    display.setVisible(true);
  }

}
