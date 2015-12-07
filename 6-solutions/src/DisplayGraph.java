import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class DisplayGraph extends JFrame {

  private static final long serialVersionUID = 5437720403705933606L;

  public DisplayGraph(Graph graph) {

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLocationRelativeTo(null);
    setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    GraphPanel graphPanel = new GraphPanel(graph);

    this.add(graphPanel);
    Dimension dimension = new Dimension(800, 600);
    this.setPreferredSize(dimension);
    this.pack();
  }

  private class ComputeHandler implements ActionListener {
    public ComputeHandler() {
    }

    public void actionPerformed(ActionEvent e) {
    }
  }
}
