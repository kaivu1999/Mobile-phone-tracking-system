import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class assn2checker
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		RoutingMapTree r = new RoutingMapTree();
		int i = 1;
		try {
			String actionString;
			br = new BufferedReader(new FileReader("nactions1.txt"));

			while ((actionString = br.readLine()) != null) {
				System.out.println(i);
				i++;
				System.out.println(r.performAction(actionString));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
