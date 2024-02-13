package com.forth_npcs.npcsrewrite.controller;


import com.forth_npcs.npcsrewrite.model.RewriteClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class RewriteController {
    @PostMapping("/rewrite")
    public ResponseEntity<String> handleSubmit(@RequestBody RewriteClass requestBody) {
        System.out.println("Received input:");
        System.out.println(requestBody.getInput1());
        System.out.println(requestBody.getInput2());
        System.out.println(requestBody.getInput3());

        String results = runJar(requestBody.getInput1(), requestBody.getInput2(), requestBody.getInput3());
        //System.out.println("JAR execution results: " + results);

        return ResponseEntity.ok(results);
    }

    private static String runJar(String reification, String type, String query) {
        String results = "";
        try {
            String jarFilePath = "src/main/java/com/forth_npcs/npcsrewrite/jarfiles/ReifySparqlByte.jar";
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




        private static String printProcessOutput(Process process) throws IOException {
            // Get the input stream of the process

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
            byte[] byteArray = parseByteArray(output);
            String character2 = new String(byteArray, StandardCharsets.UTF_8);
            System.out.println(character2);
                return outputBuilder.toString();
            }

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





