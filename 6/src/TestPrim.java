public class TestPrim {

  public static void main(String[] args) {
    Graph map = MapReader.readGraph(args[0],args[1]);

    // display minimum spanning tree
    DisplayGraph display = new DisplayGraph(map.getMinimumSpanningTree(args[2]));
    display.setVisible(true);
  }

}
