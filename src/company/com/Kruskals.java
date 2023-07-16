package company.com;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge>
{
    int dist;
    String name1;
    String name2;

    Edge(String n1 , String n2, int distance)
    {
        this.name1 = n1;
        this.name2 = n2;
        this.dist = distance;
    }

    public int compareTo(Edge x) { return Integer.compare(this.dist, x.dist); }
}


public class Kruskals
{
    public void kruskal() throws Exception
    {
        String string1;
        int totalDist = 0;
        int fEdge = 0 ;
        BufferedReader fileR = null;
        fileR = new BufferedReader(new FileReader("assn9_data.csv"));
        ArrayList<Edge> edgeL = new ArrayList<>();
        ArrayList<String> vertL = new ArrayList<>();

        while ((string1 = fileR.readLine()) != null)
        {
            String[] data = string1.split(",\\s*");
            edgeL.add(new Edge(data[0], data[1], Integer.valueOf(data[2])));
            vertL.add(data[0]);

            int z = 3;
            if(z < data.length)
            {
                for (int n = 3 ; n < data.length ; n++)
                {
                    try
                    {
                        edgeL.add(new Edge(data[0], data[n], Integer.valueOf(data[n + 1])));
                    }
                    finally
                    {
                        n++;
                    }
                }
            }
        }

        DisjSets ds = new DisjSets(vertL.size());
        PriorityQueue<Edge> y = new PriorityQueue<>(edgeL);
        int vertS = vertL.size()-1;

        while (vertS > fEdge)
        {
            Edge m;
            m = y.poll();
            assert m != null;

            if (ds.find(vertL.indexOf(m.name1)) != ds.find(vertL.indexOf(m.name2)))
            {
                totalDist = totalDist + m.dist;
                ds.union(ds.find(vertL.indexOf(m.name1)), ds.find(vertL.indexOf(m.name2)));
                System.out.println(m.name1 + " to " + m.name2 + ": Distance = " + m.dist);
                fEdge++;
            }
        }

        System.out.println("Total Distance = " + totalDist);
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Min Tree:");
        Kruskals kruskal = new Kruskals();
        kruskal.kruskal();
    }
}