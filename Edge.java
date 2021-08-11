/**
 * The Edge class represents an Edge used in the Graph class.
 */
public class Edge
{
    private final String vertex1, vertex2;
    private final int weight;

    /**
     * Create a new edge from v1 to v2, with the given weight.
     */
    public Edge(String v1, String v2, int weight)
    {
        this.vertex1 = v1;
        this.vertex2 = v2;
        this.weight = weight;
    }

    public String toString()
    {
        return "(" + vertex1 + ", " + vertex2 + ", " + weight + ")";
    }

    /**
     * Return the weight of this edge.
     */
    public int getWeight() { return weight; }

    /**
     * Return the first vertex in this edge.
     */
    public String getVertex1() { return vertex1; }

    /**
     * Return the second vertex in this edge.
     */
    public String getVertex2() { return vertex2; }
}