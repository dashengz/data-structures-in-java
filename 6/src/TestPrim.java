public class TestPrim {

  public static void main(String[] args) {
    Graph map = MapReader.readGraph(args[0],args[1]);
    map.computeAllEuclideanCosts();

    // not part of Homework 6
    // test maximum spanning tree
    // map.reverseAllCosts();

    // display minimum spanning tree
    DisplayGraph display = new DisplayGraph(map.getMinimumSpanningTree(args[2]));
    display.setVisible(true);
  }
}