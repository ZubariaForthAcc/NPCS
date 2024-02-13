#!/bin/bash

# Function to read files
read_files() {
  local directory="$1"
  # Loop through each file in the directory
  for file in "$directory"/*; do
    # Check if the current item is a file
    if [ -f "$file" ]; then
      for ((i=1; i<=5; i++)); do
         #echo "Processing file: $file"
         echo "sleep 10"
         echo "docker rm my_conatainer"
         echo "docker restart af4f62558143"
         echo "sleep 5"
         echo "docker run --name sparqlprov_conatainer sparqlprov:v2 bin/prove_wikidatareal.rb $file http://localhost:7200/repositories/wikidata-2023"
      done
      # Add your file processing commands here
      # For example, you can use 'cat' to display the file content:
      # cat "$file"
      # Or you can use other commands to process the file data.
    fi
  done


  # Recursively process subdirectories
  for subdir in "$directory"/*; do
    # Check if the current item is a directory
    if [ -d "$subdir" ]; then
      # Call the function recursively for the subdirectory
      read_files "$subdir"
    fi
  done
}

# Replace 'folder_path' with the actual path of the main directory containing subdirectories
folder_path="wikidataQ"

# Check if the main directory exists
if [ ! -d "$folder_path" ]; then
  echo "Main directory not found: $folder_path"
  exit 1
fi

# Call the function to read files
read_files "$folder_path"

