import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 
 */

/**
 * Ford Fulkerson Algorithm
 * @author yusong
 *
 */
public class MaxFlow {
	/**
	 * value of max flow
	 */
	private double maxflow;
	/**
	 * last edge on s->v path
	 */
	private HashMap<Integer, Edge> edgeTo;
	private HashMap<Integer, Boolean> marked ;

	public MaxFlow(DirectedGraph G, int s, int t){
		// change original graph to residual graph
        DirectedGraph residualG = G.makeResidualGraph();
		maxflow = 0.0;
		// check whether there is a augmenting path from s to t
		// it equals to whether there is a direct path from s to t
		// (forward edge not full, backward edge not empty.)
		// which can be solved by BFS
		while (hasAugmentingPath(residualG, s, t)){
			// first, compute bottleneck capacity
			double bottleneck = Double.POSITIVE_INFINITY;
			// trace the vertex backward from target
			for (int v=t; v!=s; v=edgeTo.get(v).other(v))
				//the bottleneck should be the minimal residual capacity along the path
				bottleneck = Math.min(bottleneck, edgeTo.get(v).residualCapacityTo(v));
			// then, augment flow
			// add the residual flow(bottleneck) to all the vertex along the path
			for (int v=t; v!=s; v=edgeTo.get(v).other(v))
				edgeTo.get(v).addResidualFlowTo(v, bottleneck);
			
			// update the flow value
			maxflow += bottleneck;
		}
	}
	
	public double maxflow(){
		return maxflow;
	}
	
	/**
	 * BFS search the residual flow graph to find whether
	 * there is a path from s to t
	 * @param G
	 * @param s
	 * @param t
	 * @return
	 */
	private boolean hasAugmentingPath(DirectedGraph G, int s, int t) {
		edgeTo = new HashMap<Integer, Edge>();
		marked = new HashMap<Integer, Boolean>();
		// initialize all vertex to nor marked
		Set<Integer> vSet = G.adj().keySet();
		for (int v : vSet )
			marked.put(v, false);
		
		// use linked list implementation for queue interface
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		marked.put(s, true) ;
		while(!queue.isEmpty()){
			int v = queue.remove();
			for (Edge e : G.adj(v)){
				int w = e.other(v);
				// check if there is a path from s to w in the
				// residual graph
				if (e.residualCapacityTo(w)>0 && !marked.get(w)){
					edgeTo.put(w, e); // save last edge on path to w
					marked.put(w, true) ; // mark w as visited
					queue.add(w); // add w to the queue
				}
			}
		}
		
		return marked.get(t);
	}


	

}
