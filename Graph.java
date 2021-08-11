import java.util.*;

/**
 * This graph class represents a directed, weighted graph.
 */
public class Graph {
    private final Map<String, Set<Edge>> edges;

    /**
     * Initialize a new, empty graph, with no vertices or edges.
     */
    public Graph()
    {
        edges = new HashMap<String, Set<Edge>>();
    }

    /**
     * Add a new vertex to this graph.  An exception will be thrown if this is a duplicate vertex.
     */
    public void addVertex(String v)
    {
        if (edges.containsKey(v))
            throw new IllegalArgumentException("Cannot add duplicate vertex to graph: " + v);
        else
            edges.put(v, new HashSet<Edge>());
    }

    /**
     * Add a new edge to this graph.  An exception will be thrown if there either vertex doesn't
     * exist, or if there is already an edge between these two vertices.
     */
    public void addEdge(String v1, String v2, int weight)
    {
        Edge e = new Edge(v1, v2, weight);

        if (!edges.containsKey(v1))
            throw new IllegalArgumentException("Cannot add edge " + e + " ; vertex v1 doesn't exist: " + v1);
        else if (!edges.containsKey(v2))
            throw new IllegalArgumentException("Cannot add edge " + e + " ; vertex v2 doesn't exist: " + v2);
        else {
            for (Edge e1 : edges.get(v1))
            {
                if (e1.getVertex1().equals(v1) && e1.getVertex2().equals(v2))
                    throw new IllegalArgumentException("Cannot add duplicate edge " + e + " to graph");
            }
        }

        edges.get(v1).add(e);
    }

    /**
     * Get all the edges that begin at vertex v (edges that lead away from v).
     */
    public Set<Edge> getEdgesFrom(String v)
    {
        return Collections.unmodifiableSet(edges.get(v));
    }

    /**
     * Get all the vertices that are connected to vertex v by an edge leading away from v.
     */
    public Set<String> getAdjacentVerticesFrom(String v)
    {
        HashSet<String> verts = new HashSet<String>();
        for (Edge e : getEdgesFrom(v))
            verts.add(e.getVertex2());
        return Collections.unmodifiableSet(verts);
    }

    /**
     * Get all the vertices in the graph.
     */
    public Set<String> getVertices()
    {
        return Collections.unmodifiableSet(edges.keySet());
    }

    /**
     * Get the weight of an edge in the graph.  An exception will be thrown if the edge doesn't exist.
     */
    public int getWeight(String v1, String v2)
    {
        for (Edge e : edges.get(v1))
        {
            if (e.getVertex1().equals(v1) && e.getVertex2().equals(v2))
                return e.getWeight();
        }
        throw new IllegalArgumentException("Cannot get weight for non-existent edge: " + v1 + ", " + v2);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + getVertices() + ", " +
                "edges=" + edges +
                '}';
    }


}
