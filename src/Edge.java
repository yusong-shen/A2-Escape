/*
 The Edge class represent an edge in an weighted graph
*/
import java.util.*;

/**
 * 
 * @author yusong
 *
 */
public class Edge {
	private final int v;
	private final int w;
	private double flow;
	private double capacity;

    public Edge(int v, int w, double flow, double capacity) {
        this.v = v;
        this.w = w;
        this.flow = flow;
        this.capacity = capacity;
    }

    public int from() {
        return v;
    }

    public int to() {
    	return w;
    }
    
    public int other(int vertex){
    	if (vertex == v) 
    		return w;
    	else if (vertex == w) 
    		return v;
    	else throw new IllegalArgumentException();
    }
    
    public double flow(){
    	return flow;
    }
    public double capacity() {
    	return capacity;
    }
    
    /**
     * Given a vertex, for its forward edge, the residual capacity
     * is its capacity - flow
     * @param vertex
     * @return
     */
    public double residualCapacityTo(int vertex){
    	// backward edge
    	if (vertex == v) return flow;
    	// forward edge
    	else if (vertex == w) return capacity-flow;
    	else throw new IllegalArgumentException();
    }
    
    public void addResidualFlowTo(int vertex, double delta){
    	// backward edge
    	if (vertex == v) flow -= delta;
    	// forward edge
    	else if (vertex == w) flow += delta;
    	else throw new IllegalArgumentException();

    }
    
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f/%5.2f", flow, capacity);
    }    

    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67, 6);
        Edge e1 = new Edge(123, 345, 45.67, 70);
        System.out.println(e);
        ArrayList<Edge> list = new ArrayList<Edge>();
        list.add(e);
        list.add(e1);
        for (Edge item : list){
        	System.out.println(item);
        }
    }    
}