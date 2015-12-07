public class TestPrim {

  public static void main(String[] args) {
    Graph g = MapReader.readGraph(args[0],args[1]);
    g.computeAllEuclideanCosts();
    Graph primResult = g.getMinimumSpanningTree(args[2]);
    DisplayGraph display = new DisplayGraph(primResult);
    display.setVisible(true);
  }

}
