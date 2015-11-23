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

	public DirectedGraph(List<String> graph){
		String line = graph.get(0);
		String[] constants = line.split(" ");
		V = Integer.parseInt(constants[0]);
		E = Integer.parseInt(constants[1]);
		X = Integer.parseInt(constants[2]);
		S = Integer.parseInt(constants[3]);
		
	}
}
