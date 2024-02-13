package npcs.Upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

public class Upload {
	 private static final String DESTINATION_FOLDER = "src/main/java/com/reifiedfiles";
	 private static final String SOURCE_FOLDER = "src/main/java/com/reifiedfiles";
	 public static String importRDFFile(File rdfFile, String repositoryURL) {
	        String res="";
	        try {
	            // Display the name of the file
	            System.out.println("File name: " + rdfFile.getName());
	            //  System.out.println("File name: " + rdfFile.get);
	            // Read the content of the file
	            try (BufferedReader br = new BufferedReader(new FileReader(rdfFile))) {
	                String line;
	                System.out.println("File content:");
	                while ((line = br.readLine()) != null) {
	                    System.out.println(line);
	                }
	            } catch (IOException e) {
	                System.out.println("An error occurred while reading the file: " + e.getMessage());
	            }
	            // Get the repository by its ID
	            HTTPRepository repository = new HTTPRepository(repositoryURL);

	            // Open the repository connection
	            try (RepositoryConnection conn = repository.getConnection()) {
	                // Read the RDF data from the file
	                Model model = Rio.parse(new FileInputStream(rdfFile), "", RDFFormat.TURTLESTAR);

	                // Add the RDF data to the repository
	                conn.add(model);
	 res="RDF data imported successfully!";
	                System.out.println("RDF data imported successfully!");
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                // Shut down the repository connection
	                repository.shutDown();
	            }
	        } catch (RepositoryException e) {
	            e.printStackTrace();
	        }
	        return res;
	    }
	 public static void main(String[] args) throws Exception {
		{
		String repoURL=args[0];	
		File file=new File(SOURCE_FOLDER);
	/*if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("Please select a file to upload.");
    }
    */

    // Process the uploaded file
    //String fileName = file.getOriginalFilename();
    // Destination file for saving or processing
    File destinationFile = new File(DESTINATION_FOLDER);

    // Save the file to the destination
    //file.transferTo(destinationFile.toPath());
    //String filePath=DESTINATION_FOLDER+"/"+fileName;
    //System.out.println(filePath);
    //String results = runJar(destinationFile,stringValue);
    String results=importRDFFile(destinationFile,repoURL);
		}
    
}}

