package npcs.Rewrite;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public class Rewrite {
	public static String runJar(String reification, String type, String query) {
		//System.out.println(query);
		//System.out.println(type);
		//System.out.println(reification);
		String results = "";
        try {
            String jarFilePath = "src\\main\\java\\jarfiles\\ReifySparqlByte.jar";
            List<String> command = new ArrayList<>();
            command.add("java");
            command.add("-jar");
            command.add(jarFilePath);
            command.add(reification);
            command.add(type);
            command.add(query);

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
           // System.out.println("Output from JAR file: " + output);

            // Parse the output as a byte array
            byte[] byteArray = parseByteArray(output);
            String character2 = new String(byteArray, StandardCharsets.UTF_8);
            results=character2;


            // Wait for the process to complete
            int exitCode = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return results;
    }
	/*private static byte[] parseByteArray(String output) {
	   // System.out.println("Input string: " + output); // Print the input string
	    
	    int startIndex = output.indexOf('[');
	    int endIndex = output.lastIndexOf(']');
	    
	    // Check if '[' and ']' characters are found
	    if (startIndex == -1 || endIndex == -1) {
	        // Handle error, throw exception or return null depending on your requirements
	        throw new IllegalArgumentException("Invalid format: '[' or ']' not found in the string");
	    }
	    
	    String byteString = output.substring(startIndex + 1, endIndex);
	    String[] byteStrings = byteString.split(", ");
	    byte[] byteArray = new byte[byteStrings.length];
	    for (int i = 0; i < byteStrings.length; i++) {
	        byteArray[i] = Byte.parseByte(byteStrings[i].trim());
	    }
	    return byteArray;
	}
	*/
	  private static byte[] parseByteArray(String output) {
	        int startIndex = output.indexOf('[');
	        int endIndex = output.lastIndexOf(']');
	        String byteString = output.substring(startIndex + 1, endIndex);
	        String[] byteStrings = byteString.split(", ");
	        byte[] byteArray = new byte[byteStrings.length];
	        for (int i = 0; i < byteStrings.length; i++) {
	            byteArray[i] = Byte.parseByte(byteStrings[i].trim());
	        }
	        return byteArray;
	    }


}
