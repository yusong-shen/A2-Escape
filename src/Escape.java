/**
 * 
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
/**
 * Given graph G, a subset of nodes X and S, this algorithm
 * will return "YES" if a valid evacuation path set exists,"NO" otherwise.
 * See more explanation on pdf instruction.
 * <p>
 * It can be reduced to a Maxflow problem.
 * @author yusong
 *
 */
public class Escape {

	/**
	 * Escape input.txt
	 * --> "YES" (or "NO")
	 * @param args
	 */
	public static void main(String[] args) {
		String file = args[0];
		try{
			List<String> lines = Files.readAllLines(Paths.get(file));
			DirectedGraph G = new DirectedGraph(lines);
			
			// add a source node and target node
			// the weights from source node(-1) to populated cities is 1
			for (int i=0; i<G.X(); i++){
				G.addEdge(new Edge(-1, G.getPopulatedCities()[i], 0, 1));
			}
			// the weights from safe cities to target node(-2) is X
			for (int i=0; i<G.S(); i++){
				G.addEdge(new Edge(G.getSaftCities()[i], -2, 0, G.X()));
			}
			System.out.println(G);
	        DirectedGraph residualG = G.makeResidualGraph(lines);
	        System.out.println(residualG);
			// TODO : apply maxflow algorithm to the modified graph G
			// if the maxflow = X , return 'YES'
			// else return 'NO'
			
		} catch (IOException e){
			e.printStackTrace();
		}

	}
	

}
