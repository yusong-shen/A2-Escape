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
        DirectedGraph residualG = G.makeResidualGraph(lines);
		maxflow = 0.0;
		// check whether there is a augmenting path from s to t
		// it equals to whether there is a direct path from s to t
		// which can be solved by BFS
		while (hasAugmentingPath(G, s, t)){
			//TODO : modify the flow network
			// first, compute bottleneck capacity
			// what is bottleneck? -
			double bottleneck = Double.POSITIVE_INFINITY;
			for (int v=t; v!=s; v=edgeTo[v].other(v))
				bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
			// then, augment flow
			for (int v=t; v!=s; v=edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottleneck);
			
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
