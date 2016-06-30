import java.util.*;

public class Kruskal
{
    private final static int INF = -1;

    private ArrayList<HashSet<Integer>> nodes;
    private TreeSet<Edge> allEdges;
    private ArrayList<Edge> allNewEdges;
    private ArrayList<ArrayList<Integer>> mst;

    public Kruskal(int size_graph, int[][] graph)
    {
        nodes = new ArrayList<>();
        allEdges = new TreeSet(new Edge());
        allNewEdges = new ArrayList<>();
        mst = new ArrayList<>();

        for (int i = 0; i < size_graph; i++)
        {
            nodes.add(null);
            mst.add(new ArrayList<>());
        }

        for (int i = 0; i < graph.length; i++)
        {
            for (int j = 0; j < graph[i].length; j++)
            {
                if (graph[i][j] != INF) {
                    allEdges.add(new Edge(i, j, graph[i][j]));

                    if (nodes.get(i) == null) {
                        nodes.set(i, new HashSet<>());
                        nodes.get(i).add(i);
                    }

                    if (nodes.get(j) == null) {
                        nodes.set(j, new HashSet<>());
                        nodes.get(j).add(j);
                    }
                }
            }
        }
    }

    public void Kruskal() {
        int size = allEdges.size();
        for (int i=0; i<size; i++) {
            Edge curEdge = allEdges.first();
            if (allEdges.remove(curEdge)) {
                if (nodesAreInDifferentSets(curEdge.v1, curEdge.v2)) {
                    HashSet src, dst;
                    int dstHashSetIndex;
                    if (nodes.get(curEdge.v1).size() > nodes.get(curEdge.v2).size()) {
                        src = nodes.get(curEdge.v2);
                        dst = nodes.get(dstHashSetIndex = curEdge.v1);
                    } else {
                        src = nodes.get(curEdge.v1);
                        dst = nodes.get(dstHashSetIndex = curEdge.v2);
                    }

                    Object srcArray[] = src.toArray();
                    int transferSize = srcArray.length;
                    for (int j = 0; j < transferSize; j++) {
                        if (src.remove(srcArray[j])) {
                            dst.add(srcArray[j]);
                            nodes.set((Integer) srcArray[j], nodes.get(dstHashSetIndex));
                        } else {
                            System.out.println("Something wrong: set union");
                            System.exit(1);
                        }
                    }
                    allNewEdges.add(curEdge);
                }
            } else {
                System.out.println("TreeSet should have contained this element!!");
                System.exit(1);
            }
        }

        while (!allNewEdges.isEmpty()) {
            Edge e = allNewEdges.get(0);
            mst.get(e.v1).add(e.v2);
            mst.get(e.v2).add(e.v1);
            allNewEdges.remove(e);
        }
    }

    public ArrayList<ArrayList<Integer>> getMST()
    {
        return mst;
    }

    private boolean nodesAreInDifferentSets(int a, int b)
    {
        return(!nodes.get(a).equals(nodes.get(b)));
    }

    public void printTree()
    {
        System.out.println(mst);
    }

    class Edge implements Comparator {
        public int v1, v2, weight;

        public Edge() {}

        public Edge(int v1, int v2, int weight)
        {
            this.v1= v1;
            this.v2 = v2;
            this.weight = weight;
        }

        public int compare(Object o1, Object o2)
        {
            int weight1 = ((Edge) o1).weight;
            int weight2 = ((Edge) o2).weight;
            int v1_1 = ((Edge) o1).v1;
            int v1_2 = ((Edge) o2).v1;
            int v2_1 = ((Edge) o1).v2;
            int v2_2 = ((Edge) o2).v2;

            if (weight1 < weight2)
                return -1;
            else if (weight1 == weight2 && v1_1 == v1_2 && v2_1 == v2_2)
                return 0;
            else if (weight1 == weight2)
                return -1;
            else if (weight1 > weight2)
                return 1;
            else
                return 0;
        }

        public boolean equals(Object obj)
        {
            Edge e = (Edge) obj;
            return (weight == e.weight && v1 == e.v1 && v2 == e.v2);
        }
    }
}