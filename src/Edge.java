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
	private final double flow;
	private final double capacity;

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
    
    public double flow(){
    	return flow;
    }
    public double capacity() {
    	return capacity;
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