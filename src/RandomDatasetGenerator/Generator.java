package RandomDatasetGenerator;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.lang.Object;



public class Generator {
	
	static ArrayList<DataVector> dataset = new ArrayList<DataVector>(); 
	//static ArrayList<HashMap<String, Integer>> stats;

	
	public static void main(String[] args) throws FileNotFoundException {
		
	dataset = readFile("path to file");
	for(int i = 0; i < dataset.size(); i++)
	{
		Random rand = new Random();
	
		int  n = rand.nextInt(100) + 1;
		//100 is the maximum and the 1 is our minimum
		
		//swap
		DataVector row = dataset.get(n);
		dataset.set(n, dataset.get(i));
		dataset.set(i, row);

	
	
	}
	
	writeToFile("path to file", dataset);
	
	
	}
	
	
	static ArrayList<DataVector> readFile(String filePath) {

		BufferedReader br = null;
		//ArrayList<String> row = null;
		ArrayList<DataVector> dataset = new ArrayList<DataVector>(); 
		
		try {
			br = new BufferedReader(new FileReader(filePath));

			String line = br.readLine();
			//row = new ArrayList<String>();
			while (line != null) {
				if (!line.trim().isEmpty()) {
					//row.add(line.trim());
					
					String token [] = line.trim().split(",");
					//split out row and labels and create datavector
					String label = token[token.length-1];
					String data[] = new String[token.length-1];
					for(int i=0; i < token.length-1; i++)
					{
						data[i]=token[i];
						
					}
					DataVector dv = new DataVector(label, data);
					
					//add datavector to arraylist
					dataset.add(dv);
				}
				line = br.readLine();
			}
		} catch (IOException ex) {
			System.out.println("File not found");
		}
		return dataset;
	}
	
	static void writeToFile(String filePath, ArrayList<DataVector> dataset) throws FileNotFoundException
	{
		PrintWriter writer2 = new PrintWriter(filePath);
		
		for(int i =0; i < dataset.size(); i++ )
			writer2.println(dataset.get(i).toRowCSVString());
	
	}

}
