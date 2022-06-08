# link-downloader

## Description
Download links recursively with download progress

This is a spring boot CommandLineRunner application that is capable of deep searching the links with-in any website and then can download the sub files in the local file system maintaining the online file hierarchy

There are four major components to the application

1. Link Service: Abstracts the functionality of fetching via any type of link, such as HTTP or FTP.
2. Directory Service: It has the functionality of creating new directories or other directory-related operations.
3. File Service: It has the functions of reading and writing to files in the local directory.
4. Utils: It has methods to take out the correct local file structure for the online link for persistence.


## List of Stories

- Requirement understanding 
- POC on console running application
- A recursive module for downloading files from links.
- Save the downloaded files to your computer's file system.
- Download status in the console
- Testing using unit test cases 

## How chose the feature

The stories are created in the order of the hierarchy, as we required (requirement understanding) before everything and the last piece was to test out using test cases (hence last but very important).

## Instructions
  - ### Pre-requisite 
    - Java 8
    - Maven 

  - ### How to install -
    - checkout (https://github.com/AnandAhlawat/link-downloader)
  - ### How to Build -
    - Execute below command in the root directory of the checkout location 
    > mvn clean install 
  - ### How to run - 
    - The application requires two run time arguments for running 
      - **link** - link from where it should download
      - **base.dir** - the base directory on local (**base.dir)
    - Run the application using the below maven command
      > mvn spring-boot:run -Dspring-boot.run.arguments="--link=https://tretton37.com --base.dir=/tmp/poc"
    
    
## Parallelism
  - For enabling parallesim, update the below flag in the application.properties 
  ```
  file-downloader.multi-threading.enabled=true
  file-downloader.thread-pool.size=10
