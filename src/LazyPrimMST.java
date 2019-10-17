import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class LazyPrimMST {
    public boolean[] visited;
    public Queue<Edge> mst;
    public PriorityQueue<Edge> pq;
    public double weight = 0.0D;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<>();
        mst = new LinkedList<>();
        visited = new boolean[G.V];
        visit(G, 0);
        while (!pq.isEmpty() && mst.size() < G.V - 1) {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (visited[v] && visited[w])
                continue;
            mst.add(e);
            weight += e.weight;
            if (!visited[v])
                visit(G, v);
            if (!visited[w])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        visited[v] = true;
        for (Edge e : G.adj(v)) {
            if (!visited[e.other(v)])
                pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
//8
//16
//4 5 0.35
//4 7 0.37
//5 7 0.28
//0 7 0.16
//1 5 0.32
//0 4 0.38
//2 3 0.17
//1 7 0.19
//1 2 0.36
//1 3 0.29
//2 7 0.34
//6 2 0.40
//3 6 0.52
//6 0 0.58
//6 4 0.93
//0 2 0.26
        Scanner sc= new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for (int i = 0; i < E; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            double weight = sc.nextDouble();
            Edge e = new Edge(v, w, weight);
            G.addEdge(e);
        }
        LazyPrimMST mst = new LazyPrimMST(G);
        System.out.println(mst.weight);
        System.out.println(mst.edges());

    }
}
