package com.forth_npcs.npcsrewrite.controller;

import com.forth_npcs.npcsrewrite.model.RewriteClass;
import com.forth_npcs.npcsrewrite.model.UploadRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class UploadDataToGDB {
    private static final String DESTINATION_FOLDER = "src/main/java/com/forth_npcs/npcsrewrite/reifiedfiles";
    @PostMapping("/uploadDataGDB")
    public ResponseEntity<String> handleSubmit(@RequestParam("file") MultipartFile file,
                                               @RequestParam("stringValue") String stringValue) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        // Process the uploaded file
        String fileName = file.getOriginalFilename();
        // Destination file for saving or processing
        File destinationFile = new File(DESTINATION_FOLDER, fileName);

        // Save the file to the destination
        file.transferTo(destinationFile.toPath());
        String filePath=DESTINATION_FOLDER+"/"+fileName;
        System.out.println(filePath);
        //String results = runJar(destinationFile,stringValue);
        String results=importRDFFile(destinationFile,stringValue);
        return ResponseEntity.ok(results);
    }
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
            Repository repository = new HTTPRepository(repositoryURL);

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
    private static String runJar(File path, String repository) {
        String results = "";
        try {
            String jarFilePath = "src/main/java/com/forth_npcs/npcsrewrite/jarfiles/UploadDataGDB.jar";
            List<String> command = new ArrayList<>();
            command.add("java");
            command.add("-jar");
            command.add(jarFilePath);
            command.add(path.getAbsolutePath());
            command.add(repository);


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
             System.out.println("Output from JAR file: " + output);

            // Parse the output as a byte array
          //  byte[] byteArray = parseByteArray(output);
            //String character2 = new String(byteArray, StandardCharsets.UTF_8);
            //results=character2;
results=output;

            // Wait for the process to complete
            int exitCode = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return results;
    }
}
