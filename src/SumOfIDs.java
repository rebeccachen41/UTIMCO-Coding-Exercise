import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class SumOfIDs 
{	
	/** Parses the file and prints the sums
	 * 
	 * @param f   the JSON file to be parsed
	 */
	public static void scan(File f)
	{
		ArrayList<Integer> IDs = new ArrayList <Integer>();
		
		try 
		{
			Scanner scanner = new Scanner(f);
									
			String labelSearch = scanner.nextLine();	// the line currently being read
			String IDSearch = null;						// the previous line read
			
			while(scanner.hasNextLine())
			{
				IDSearch = labelSearch;
				labelSearch = scanner.nextLine();
				if(IDSearch.contains("id") && labelSearch.contains("Label"))
				{
					//remove any trailing comma
					if(IDSearch.charAt(IDSearch.length() - 1) == ',')
					{	
						IDSearch = IDSearch.substring(0, IDSearch.length() - 1);
					}
					
					int start = IDSearch.indexOf('i') + 4;
					int end = IDSearch.length();
					String sub = IDSearch.substring(start, end);
					
					int toAdd = Integer.parseInt(sub);
					IDs.add(toAdd);
				}
				// check for end of JSON object
				else if(labelSearch.equals("  },") || labelSearch.equals("]"))
				{
					System.out.println(SumOfIDs.sum(IDs));
					IDs.clear();
				}
			}
			scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/** Sums all integers stored in the list
	 * 
	 * @param array   the list of integers to be summed
	 * @return        the sum of the integers in the list
	 */
	public static int sum(ArrayList<Integer> array)
	{	
		int sum = 0;
		for(int i : array)
		{
			sum = sum + i;
		}
		return sum;
	}
	
	public static void main(String[] args) 
	{
		String path = args[0];
		
		SumOfIDs.scan(new File(path));
		
		
		
	}

}
