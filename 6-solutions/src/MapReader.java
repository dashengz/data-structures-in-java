import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapReader {

  public static Graph readGraph(String vertexfile, String edgefile) {

    Graph graph = new Graph();
    try {
      String line;
      String[] parts;
      // Read in the vertices
      FileReader vertexFileReader = new FileReader(vertexfile);
      BufferedReader vertexFileBr = new BufferedReader(vertexFileReader);
      while((line = vertexFileBr.readLine()) != null) {
        parts = line.split(",");
        if (parts.length != 3)
          throw new IOException("Invalid line in vertex file "+ line);
        String cityname = parts[0];
        int x = Integer.valueOf(parts[1]);
        int y = Integer.valueOf(parts[2]);
        Vertex vertex = new Vertex(cityname, x, y);
        graph.addVertex(vertex);
      }
      vertexFileBr.close();
      // Now read in the edges
      FileReader edgeFileReader = new FileReader(edgefile);
      BufferedReader edgeFileBr = new BufferedReader(edgeFileReader);
      while ((line = edgeFileBr.readLine()) != null) {
        parts = line.split(",");
        if (parts.length != 2)
          throw new IOException("Invalid line in edge file "+ line);
        graph.addUndirectedEdge(parts[0],parts[1],1.0);
      }
    } catch (IOException e) {
      System.err.println("Could not read the graph: " +e);
      return null;
    }
    return graph;
  }

  public static void main(String[] args) {
    Graph g = readGraph(args[0],args[1]);
    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
  }


}
