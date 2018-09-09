import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class assn3checker
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		RoutingMapTree r = new RoutingMapTree();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions.txt"));
			int i = 1;
			while ((actionString = br.readLine()) != null) {
				System.out.println(i +": " +  r.performAction(actionString));
				i++;
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
