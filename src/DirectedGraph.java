import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 
 */

/**
 * @author yusong
 *
 */
public class DirectedGraph {
	
	private int V = 0;
	private int E = 0;
	private int X;
	private int S;
	private HashMap<Integer, ArrayList<Edge>> adj = new HashMap<Integer, ArrayList<Edge>>();    // adj.get(v) = adjacency list for vertex v
	private int[] populatedCities;
	private int[] safeCities;

	public DirectedGraph(List<String> graph){
		// first line
		String line0 = graph.get(0);
		String[] constants = line0.split(" ");
		int V = Integer.parseInt(constants[0]);
		int E = Integer.parseInt(constants[1]);
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
            // initial flow
            double flow = 0;
            // give intermediate edge enough capacity
            double capacity = 2*X;
            addEdge(new Edge(v, w, flow, capacity));
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
    		V ++;
    	}
        // counted the node
        if (adj.containsKey(w) == false){
    		ArrayList<Edge> adjList = new ArrayList<Edge>();
        	adj.put(w, adjList);
        	V++;
        }
        E ++;
    }
    
    public void deleteEdge(Edge e){
    	int v = e.from();
//    	int w = e.to();
    	adj.get(v).remove(e);
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
    
	public void makeResidualGraph(){
		//TODO 
		// if a original flow edge is v -7/9(f/c) -> w
		// we will change it into v - 0/2 -> w
		// 						  v <-0/7 -- w
		// directly modify from original graph 
		// then it needs one more deleteEdge method
        Set<Integer> vSet = adj.keySet();
        int minv = Collections.min(vSet); 
        // loop through all the edges
        // shallow copy a adj to avoid concurrent modification
        HashMap<Integer, ArrayList<Edge>> adjCopy = new HashMap<Integer, ArrayList<Edge>>();
        adjCopy.putAll(adj);
        for (int v = minv; v < V() + minv; v++) {
            if (adjCopy.containsKey(v)) {
	            for (Edge e : adjCopy.get(v)) {
	            	// remove original edge
	            	deleteEdge(e);
	            	int w = e.to();
	            	// add forward residual edges
	            	addEdge(new Edge(v, w, 0, e.capacity()-e.flow()));
	            	// add backward residual edges
	            	addEdge(new Edge(w, v, 0, e.flow()));
	            	
	            	
	            }
        	}
        }
 	
		
	}
    
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        Set<Integer> vSet = adj.keySet();
        int minv = Collections.min(vSet); 
        for (int v = minv; v < V + minv; v++) {
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
	        G.makeResidualGraph();
	        System.out.println(G);
	        
        } catch (IOException e){
            e.printStackTrace();
        }

    }        
    
    // end class
}
