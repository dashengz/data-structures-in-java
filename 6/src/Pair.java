/**
 * Created by Jonathan on 12/4/15.
 */
public class Pair implements Comparable<Pair>{
    public Double cost;
    public Vertex vertex;

    public Pair(Double cost, Vertex vertex) {
        this.cost = cost;
        this.vertex = vertex;
    }

    @Override
    public int compareTo(Pair o) {
        return cost.compareTo(o.cost);
    }
}
