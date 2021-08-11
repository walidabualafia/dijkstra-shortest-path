# Dijkstra's Algorithm

This project is designed to calculate the shortest path between two vertices in a Graph data structure. The user will provide a text file of commands that follows certain guidelines to be able to build a graph of their choice, containing the vertices and edges they would like. 

### Guidelines for the TextFile Input:
 - The first line should always either be the word `"directed"` or `"undirected"`. This line will be used to specify the type of graph. The project is designed to fake undirected graphs as directed graphs: representing each undirected edge as two directed edges, one in each direction.
 - `"vertex name"` adds a vertex called name to the graph.
 - `"edge name1 name2 weight"` adds an edge between name1 and name2 with the weight. Note that weight has to be an integer.
 - The very last line should always be `"dijkstra name1 name2"`. When the program sees this command, it prints out a list of all the vertices and edges in the graph. The program then calls a priority queue implementation of Dijkstra's algorithm to find the shortest path between `"name1"` and `"name2"`.

_Sample input text files are provided: `graph1.txt graph2.txt graph3.txt graph4.txt`__

### General Outline of Classes:
- _DijkstrasAlgorithm.java_
  > This class contains 3 functions. `main()` uses a Scanner to prompt user input for which text file to read. `dijkstra()` is the implementation of dijkstra's algorithm. `processFile()` processes the commands in the text file and builds the graph.
- _Edge.java_
  > This class is an object `Edge` that is used to build the graph.
- _Graph.java_
  > This class in an object `Graph` which is the data structure containing all the functions that will be used to build and traverse the graph.
- _PriQueue.java_
 
