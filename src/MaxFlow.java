import java.util.Collections;
import java.util.List;
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
	private Edge[] edgeTo;

	public MaxFlow(DirectedGraph G, int s, int t, List<String> lines){
		// change original graph to residual graph
        DirectedGraph residualG = G.makeResidualGraph();
		maxflow = 0.0;
		// check whether there is a augmenting path from s to t
		// it equals to whether there is a direct path from s to t
		// (forward edge not full, backward edge not empty.)
		// which can be solved by BFS
		while (hasAugmentingPath(G, s, t)){
			// first, compute bottleneck capacity
			double bottleneck = Double.POSITIVE_INFINITY;
			// trace the vertex backward from target
			for (int v=t; v!=s; v=edgeTo[v].other(v))
				//the bottleneck should be the minimal residual capacity along the path
				bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
			// then, augment flow
			// add the residual flow(bottleneck) to all the vertex along the path
			for (int v=t; v!=s; v=edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottleneck);
			
			// update the flow value
			maxflow += bottleneck;
		}
	}
	
	private boolean hasAugmentingPath(DirectedGraph g, int s, int t) {
		// TODO Auto-generated method stub
		return false;
	}


	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
