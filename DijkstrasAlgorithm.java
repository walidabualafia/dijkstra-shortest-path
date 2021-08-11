import java.io.InputStream;
import java.util.*;

public class DijkstrasAlgorithm {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What file do you want to read? ");
        String filename = scan.nextLine();
        processFile(filename);
    }

    public static void dijkstra(Graph g, String start, String finish)
    {
        // Initialize HashMaps dist, prev, and Priority Queue pq.
        HashMap<String, Integer> dist = new HashMap<String, Integer>();
        HashMap<String, String> prev = new HashMap<String, String>();
        PriQueue<String, Integer> pq = new PriQueue<String, Integer>(true);

        // Traverse all the vertices.
        for (String vertex : g.getVertices())
        {
            // Set all values in dist to 999999.
            dist.put(vertex, 999999);
            // Set all values in prev to undefined.
            prev.put(vertex, "undefined");
        }

        // Set the intial value for the start vertex in dist to 0.
        // Initial value for the start vertex in prev remains undefined
        //     because it does not have a previous vertex.
        dist.replace(start, 0);
        // Add the start index to the priority queue.
        pq.add(start, 0);

        // While the priority queue has not been completely emptied.
        while (!pq.isEmpty())
        {
            // Visit vertex u and remove it from the priority queue.
            String u = pq.remove();
            System.out.println("\nVisiting vertex " + u);

            // If u is the finish vertex, break out of the loop.
            if (u.equals(finish))
                break;

            // Look at all the neighboring vertices for u
            for (String vertex : g.getAdjacentVerticesFrom(u))
            {
                // Calculate the value of the alternate path.
                int alt = dist.get(u) + g.getWeight(u, vertex);

                // If the alternate path is smaller than the existing
                //     value of the vertex distance in dist...
                if (alt < dist.get(vertex))
                {
                    System.out.println("    Updating dist[" + vertex +
                            "] from " + dist.get(vertex) + " to " + alt);
                    // Change the value of the vertex in dist to alt path.
                    dist.replace(vertex, alt);
                    // Change the value of the previous vertex in prev to u.
                    prev.replace(vertex, u);

                    // If the vertex is already in the priority queue.
                    if (pq.contains(vertex))
                        // Change the vertex priority to the alt path.
                        pq.changePriority(vertex, alt);
                    // If the vertex is not in the priority queue.
                    else
                        // Add the vertex to the priority queue.
                        pq.add(vertex, alt);
                }
            }
        }

        // Create a singly linked list to hold the shortest path.
        LinkedList<String> path = new LinkedList<>();
        // Initialize a variable to keep track of the previous vertex.
        String prevS = prev.get(finish);
        // Traverse HashTable prev starting from finish until you reach start.
        //     The prev value for start will always be "undefined".
        for (String p = prev.get(finish); !p.equals("undefined"); p = prev.get(prevS))
        {
            // Use addFirst to add the prev vertices to the linked list in order.
            path.addFirst(prevS);
            prevS = prev.get(prevS);    // Reassign.
        }


        /*
        Alternate solution to the for loop.

           String prev1 = prev.get(finish);

           while (!prev1.equals("undefined"))
           {
               path.addFirst(prev1);
               prev1 = prev.get(prev1);
           }

           // Printing
           System.out.print("\nShortest path is: ");
           for (String vertex : path)
                System.out.print(vertex + " ");
           System.out.println(finish);
        */


        // Print the shortest path by traversing LL path and printing all the elements
        //     (LL path will not include start and finish).
        System.out.print("\nShortest path is: " + start + " ");
        for (String vertex : path)
            System.out.print(vertex + " ");
        System.out.println(finish);

        // Print the distance from start to finish
        System.out.println("Distance is: " + dist.get(finish));

        // Print the dist map by traversing its keySet().
        System.out.println("\nFinal dist map:");
        for (String key : dist.keySet())
            System.out.println(key + ": " + dist.get(key));

        // Print the prev map by traversing its keySet().
        System.out.println("\nFinal prev map:");
        for (String key : prev.keySet())
            System.out.println(key + ": " + prev.get(key));
    }

    /**
     * Read the file specified to add proper items to the word frequencies.
     */
    private static void processFile(String filename)
    {
        InputStream is = DijkstrasAlgorithm.class.getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Bad filename: " + filename);
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        // Make a blank graph.
        Graph g = new Graph();

        // Store the type of the graph (directed / undirected).
        String type = scan.nextLine();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");

            // If vertex, add a vertex.
            if (words[0].equals("vertex"))
                g.addVertex(words[1]);

            // If edge, add edge of appropriate type.
            else if (words[0].equals("edge"))
            {
                // If the graph type is directed, add one edge
                if (type.equals("directed"))
                    g.addEdge(words[1], words[2], Integer.parseInt(words[3]));
                // If the graph type is undirected, add two edges
                else
                {
                    g.addEdge(words[1], words[2], Integer.parseInt(words[3]));
                    g.addEdge(words[2], words[1], Integer.parseInt(words[3]));
                }
            }

            // If Dijkstra (last line), print out the vertices, edges,
            //     and call the dijkstra() method.
            else
            {
                // Print out the vertices of the graph.
                System.out.println("Vertices: " + g.getVertices());
                // Print out the edges of the graph.
                System.out.println("Edges: ");
                // Create a set that holds the value of all the vertices.
                Set<String> vertices = g.getVertices();

                // Traverse the vertices set.
                for (String vertex : vertices)
                {
                    // Create a set of edges for each vertex.
                    Set<Edge> edges = g.getEdgesFrom(vertex);
                    // Traverse the edges set and print out each
                    //     edge's vertices and weight.
                    for (Edge edge : edges)
                        System.out.println(edge.getVertex1() + " -> " +
                                edge.getVertex2() + ": " + edge.getWeight());
                }

                // Call Dijkstra's algorithm method.
                dijkstra(g, words[1], words[2]);
            }
        }
        scan.close();
    }
}
