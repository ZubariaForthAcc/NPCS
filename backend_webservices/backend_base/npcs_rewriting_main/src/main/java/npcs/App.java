package npcs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import npcs.Rewrite.Rewrite;
import npcsExecute.Execute;



public class App {
	static String rdfstar="SPARQL_Star";
	static String namedgraph="Namedgraph";
	static String wikidata="Wikidata";
	static String wikidatareal="Wikidatareal";
	static String sparql="SPARQL";
	static String standard="Standard";
	public static String replaceQuotes(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("\"", "'");
    }
	private static String readQueryFromPath(String path) throws FileNotFoundException {
		String strLine="";
		try {

			
			String strFileDirectoryPath =path;	
			FileInputStream fstream = new FileInputStream(strFileDirectoryPath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String st;
			// Read File Line By Line
			while ((st = br.readLine()) != null) {
				strLine=strLine+st+"\n";
				//System.out.println(st);
			}
			in.close();
			} catch (Exception e) {// Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		return strLine;
	}
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(args.length);
		if(args.length==3)
		{
			Rewrite rw=new Rewrite();
			String pathh=args[2];
			String querypath=args[1];
			String reification_scheme=args[0];
			String query="";
			if(querypath.equals("path"))
			{
		query=readQueryFromPath(pathh);
			}
	else if(querypath.equals("query"))
			{
		query=pathh;
			}
	else
	{
	System.out.println("write query/path in second argument to tell you are sending query or path");	
	}
		
			long startTime = System.currentTimeMillis();

			// Perform the operation
			System.out.println(rw.runJar(reification_scheme, "query", query));

			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;

			System.out.println("Time taken by rewriting: " + elapsedTime + " miliseconds");
	
			 
			
		}
		else if(args.length==4)
		{
			Rewrite rw=new Rewrite();
			String pathh=args[2];
			String querypath=args[1];
			String reification_scheme=args[0];
			String endpoint_url=args[3];
			
			/*String reification_scheme="SPARQL_Star";
			String querypath="query";
			String pathh = "PREFIX gn: <http://www.geonames.org/ontology#> " +
		              "PREFIX ex: <http://example.org/> " +
		              "SELECT ?city ?country " +
		              "WHERE { " +
		              "?city gn:parentCountry ?country . " +  
		              "}";

			
			
			String endpoint_url="http://zubaria:7200/repositories/CheckReifyServiceData";
			
			*/
			//int number_of_times=Integer.parseInt(args[4]);
			String query="";
			if(querypath.equals("path"))
			{
		query=readQueryFromPath(pathh);
			}
	else if(querypath.equals("query"))
			{
		query=pathh;
			}
	else
	{
	System.out.println("write query/path in second argument to tell you are sending query or path");	
	}
		
			long startTime = System.currentTimeMillis();
String rewritten_query=rw.runJar(reification_scheme, "query", query);
			// Perform the operation
			//System.out.println("yyf"+rewritten_query);

			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;

			System.out.println("Time taken by rewriting: " + elapsedTime + " miliseconds");
			Execute ex=new Execute();
			String re=replaceQuotes(rewritten_query);
			System.out.println(re);
			long startTime1 = System.currentTimeMillis();
			ArrayList<ArrayList<String>> res=ex.runJar( endpoint_url,re);
			long endTime1 = System.currentTimeMillis();
			long elapsedTime1 = endTime1 - startTime1;
			System.out.println("Time taken by execution: " + elapsedTime1 + " miliseconds");
			ex.displayTable(res);
			
			
		}

	}

}
