import java.io.File;
import java.util.Scanner;

public class MapReader {

  public static Graph readGraph(String vertexfile, String edgefile) {
    Graph mapGraph = new Graph();
    // try and catch blocks to catch file read exceptions
    try {
      File vFile = new File(vertexfile);
      File eFile = new File(edgefile);
      // use scanners to read files
      Scanner vScanner = new Scanner(vFile);
      Scanner eScanner = new Scanner(eFile);
      while (vScanner.hasNext()) {
        String vLine = vScanner.nextLine();
        // use String.split() to split commas
        String[] vSplit = vLine.split(",");
        // add vertices to graph
        mapGraph.addVertex(new Vertex(vSplit[0],Integer.parseInt(vSplit[1]),Integer.parseInt(vSplit[2])));
      }
      while (eScanner.hasNext()) {
        String eLine = eScanner.nextLine();
        String[] eSplit = eLine.split(",");
        // add undirected edges to graph
        mapGraph.addUndirectedEdge(eSplit[0],eSplit[1],1.0);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }

    return mapGraph;
  }

  public static void main(String[] args) {
    // get the args passed in when the program is called using command line
    // $ java MapReader ttrvertices.txt ttredges.txt
    String vertexfile = args[0];
    String edgefile = args[1];

    // based on the file names input, create a map graph
    Graph mapGraph = readGraph(vertexfile, edgefile);

    // test computeAllEuclideanCosts()
    mapGraph.computeAllEuclideanCosts();
    mapGraph.printAdjacencyList();

    // display the map graph
    DisplayGraph display = new DisplayGraph(mapGraph);
    display.setVisible(true);
  }
}