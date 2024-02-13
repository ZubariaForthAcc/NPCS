package npcsExecute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Execute {
	public static ArrayList<ArrayList<String>> runJar(String reositoryURL, String query) {
        ArrayList<ArrayList<String>> results =new ArrayList<>();
        try {
        	//System.out.println(reositoryURL);
        		//	System.out.println(query);
            String jarFilePath = "src\\main\\java\\jarfiles\\ExecuteQueryGDB.jar";

            List<String> command = new ArrayList<>();
            command.add("java");
            command.add("-jar");
            command.add(jarFilePath);
            
            command.add(reositoryURL);
            command.add(query);
           // command.add(type);
            

            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Redirect output and error streams
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the process
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line);
            }
            String output = outputBuilder.toString();
             //System.out.println("Output from JAR file: " + output);

            // Parse the output as a byte array
          //  byte[] byteArray = parseByteArray(output);
          //  String character2 = new String(byteArray, StandardCharsets.UTF_8);
          //  results=character2;
            ArrayList<ArrayList<String>> result = convertToList(output);
         results=result;
            // Printing the result
           /* for (ArrayList<String> sublist : result) {
                System.out.println(sublist);
            }
*/
            // Wait for the process to complete
            int exitCode = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return results;
    }
    public void displayTable(ArrayList<ArrayList<String>> data) {
        for (ArrayList<String> row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
    public static ArrayList<ArrayList<String>> convertToList(String input) {
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[([^\\[\\]]*?)\\]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String[] parts = matcher.group(1).split(",\\s*");
            List<String> subList = Arrays.asList(parts);
            resultList.add(new ArrayList<>(subList));
        }
        return resultList;
    }
}
