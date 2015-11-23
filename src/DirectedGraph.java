import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */

/**
 * @author yusong
 *
 */
public class DirectedGraph {
	
	private int V;
	private int E;
	private int X;
	private int S;
	private HashMap<Integer, ArrayList<Edge>> adj = new HashMap<Integer, ArrayList<Edge>>();    // adj.get(v) = adjacency list for vertex v
	private int[] populatedCities;
	private int[] safeCities;

	public DirectedGraph(List<String> graph){
		// first line
		String line0 = graph.get(0);
		String[] constants = line0.split(" ");
		V = Integer.parseInt(constants[0]);
		E = Integer.parseInt(constants[1]);
		X = Integer.parseInt(constants[2]);
		S = Integer.parseInt(constants[3]);
		populatedCities = new int[X];
		safeCities = new int[S];
		// second line
		String line1 = graph.get(1);
		String[] pcs = line1.split(" ");
		for (int i=0; i<X; i++ ){
			populatedCities[i] = Integer.parseInt(pcs[i]);
			
		}
		// third line
		String line2 = graph.get(2);
		String[] scs = line2.split(" ");
		for (int i=0; i<S; i++ ){
			safeCities[i] = Integer.parseInt(scs[i]);
			
		}
		// fourth and rest of lines
        for (int i = 0; i < E; i++) {
        	String line = graph.get(i+3);
        	String[] parts = line.split(" ");
            int v = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            // give intermediate edge enough capacity
            double weight = 2*X;
            addEdge(new Edge(v, w, weight));
        }		
		
	}
    public void addEdge(Edge e) {
        int v = e.from();
        int w = e.to();
        if (adj.containsKey(v)){
        	adj.get(v).add(e);
    	} else {
    		ArrayList<Edge> adjList = new ArrayList<Edge>();
    		adj.put(v, adjList);
    		adj.get(v).add(e);
    	}
        if (adj.containsKey(w)){
            adj.get(w).add(e);
        } else {
            ArrayList<Edge> adjList = new ArrayList<Edge>();
            adj.put(w, adjList);
            adj.get(w).add(e);            
        }
    }
    
    public Iterable<Edge> adj(int v) {
        return adj.get(v);
    }

    public int V(){
    	return V;
    }
    
    public int E(){
    	return E;
    }
    
    public int X(){
    	return X;
    }
    
    public int S(){
    	return S;
    }

    public int[] getPopulatedCities(){
    	return populatedCities;
    }
    
    public int[] getSaftCities(){
    	return safeCities;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + " -> ");
            if (adj.containsKey(v)) {
	            for (Edge e : adj.get(v)) {
	                s.append(e + "  ");
	            }
	            s.append("\n");
        	}
        }
        return s.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
	        DirectedGraph G = new DirectedGraph(lines);
	        System.out.println(G);
        } catch (IOException e){
            e.printStackTrace();
        }

    }        
    
    // end class
}
