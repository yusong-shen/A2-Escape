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
			// add some comments
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
