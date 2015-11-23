/**
 * 
 */

/**
 * Ford Fulkerson Algorithm
 * @author yusong
 *
 */
public class MaxFlow {
	/*
	 * value of max flow
	 */
	private double maxflow;

	public MaxFlow(DirectedGraph G, int s, int t){
		// change original graph to residual graph
		DirectedGraph resiualG = makeResidualGraph(G);
		maxflow = 0.0;
		// check whether there is a augmenting path from s to t
		// it equals to whether there is a direct path from s to t
		// which can be solved by BFS
		while (hasAugmentingPath(G, s, t)){
			//TODO : modify the flow network
			// first, compute bottleneck capacity
			// then, augment flow
		}
	}
	
	private boolean hasAugmentingPath(DirectedGraph g, int s, int t) {
		// TODO Auto-generated method stub
		return false;
	}

	public DirectedGraph makeResidualGraph(DirectedGraph G){
		//TODO 
		return G;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
