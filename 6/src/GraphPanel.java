import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -428358575826475449L;
  // graph layout parameters
  static final int VERTEX_RADIUS = 10;
  static final int SPACE = 3;

  static final int MARGIN_X = 50;
  static final int MARGIN_Y = 50;

  // scale factors
  float xFactor, yFactor;

  Graph graph;

  public GraphPanel(Graph graph) {
    this.graph = graph;
  }

  public void paintComponent(Graphics g) {
    // scale the graph
    int minX = 0;
    int maxX = 1;
    int minY = 0;
    int maxY = 1;
    for (Vertex v : graph.getVertices()) {
      if (v.posX < minX)
        minX = v.posX;
      if (v.posX > maxX)
        maxX = v.posX;
      if (v.posY < minY)
        minY = v.posY;
      if (v.posY > maxY)
        maxY = v.posY;
    }
    xFactor = (this.getBounds().width - 2 * MARGIN_X) / (float) (maxX - minX);
    yFactor = (this.getBounds().height - 2 * MARGIN_Y) / (float) (maxY - minY);
    super.paintComponent(g); // paint the panel
    paintGraph(g); // paint the graph
  }

  public void paintGraph(Graphics g) {
    for (Vertex v : graph.getVertices()) {
      for (Edge e : v.getEdges()) {
        paintEdge(g, e.sourceVertex, e.targetVertex);
      }
    }
    for (Vertex v : graph.getVertices())
      paintVertex(g, v);
  }

  public void paintVertex(Graphics g, Vertex v) {
    int x = Math.round(xFactor * (float) v.posX + (float) MARGIN_X);
    int y = Math.round(yFactor * (float) v.posY + (float) MARGIN_Y);
    g.setColor(Color.BLACK);
    g.drawOval(x - VERTEX_RADIUS, y - VERTEX_RADIUS, 2 * VERTEX_RADIUS,
        2 * VERTEX_RADIUS);
    g.setColor(Color.WHITE);
    g.fillOval(x - VERTEX_RADIUS, y - VERTEX_RADIUS, 2 * VERTEX_RADIUS,
        2 * VERTEX_RADIUS);
    g.setColor(Color.BLACK);
    g.drawString(v.name, x - v.name.length() * 8 / 2, y + VERTEX_RADIUS / 2);
  }

  public void paintEdge(Graphics g, Vertex u, Vertex v) {
    int x1 = Math.round(xFactor * (float) u.posX + (float) MARGIN_X);
    int y1 = Math.round(yFactor * (float) u.posY + (float) MARGIN_Y);
    int x2 = Math.round(xFactor * (float) v.posX + (float) MARGIN_X);
    int y2 = Math.round(yFactor * (float) v.posY + (float) MARGIN_Y);
    g.drawLine(x1, y1, x2, y2);
  }

}
