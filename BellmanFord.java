import java.util.*;

public class BellmanFord {

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int[] bellmanFord(int n,
                             List<Edge> edges,
                             int source) {

        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < n; i++) {
            for (Edge e : edges) {

                if (dist[e.u] != Integer.MAX_VALUE &&
                    dist[e.u] + e.w < dist[e.v]) {

                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        // Check for negative cycle
        for (Edge e : edges) {
            if (dist[e.u] != Integer.MAX_VALUE &&
                dist[e.u] + e.w < dist[e.v]) {

                System.out.println("Negative cycle exists");
                break;
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        int n = 6;

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 6));   // SRC -> A1
        edges.add(new Edge(0, 3, 5));   // SRC -> A3
        edges.add(new Edge(1, 2, 4));   // A1 -> A2
        edges.add(new Edge(1, 4, 8));   // A1 -> A4
        edges.add(new Edge(3, 4, 3));   // A3 -> A4
        edges.add(new Edge(4, 2, 2));   // A4 -> A2
        edges.add(new Edge(2, 5, 5));   // A2 -> DST
        edges.add(new Edge(4, 5, 7));   // A4 -> DST
        edges.add(new Edge(2, 3, -4));  // A2 -> A3

        int[] dist = bellmanFord(n, edges, 0);

        System.out.println("Shortest Distances:");

        String[] names = {
            "SRC", "A1", "A2",
            "A3", "A4", "DST"
        };

        for (int i = 0; i < n; i++) {
            System.out.println(
                names[i] + " : " + dist[i]
            );
        }
    }
}