# Just Eat Complete at Home Coding Assignment

This project was completed as part of the recruitment process for the Just Eat Early Careers Software Engineering Program. It aims to use the API provided by Just Eat to find restaurant data and return it on the console. The project aims to use the API to send a postcode to return the below filtered data:

● Name

● Cuisines

● Rating - as a number

● Address


# How to build, compile and run my solution 
IDE: I am not very familiar with how to clone the file from Git, but it is possible to run the file through an IDE like Eclipse. For this, you can copy + paste the code into a file titled Restaurant.java, and run by selecting 'run Restaurant' in the small green arrow icon. 

Command line: To compile and execute from the command line you can copy + paste the code into a file titled Restaurant.java. Once this is done, the file can be compiled by navigating into the folder with the file through the command line and typing 'javac Restaurant.java'. Once the file has been compiled, it can be executed with the command 'java Restaurant'.

I did not initially get the programme to work as I did not have a GSON JAR file installed and was producing error. Once I downloaded this, it began working. I believe this is essential for the programme to run, as such this would need to be downloaded if one were to use my programme. 

# Assumptions or things that were not clear to me
 
-I assumed we were to use starRating rather than userRating for ratings as userRatings appeared empty.

-I assumed we were to print address as Street, City, Postcode for the address.

-I listed all restaurants in order they were in the JSON file, rather than for example in an alphabetical order, or by highest rating, as no specific order was stated.

-I listed first 3 cuisines if the 3rd one was Halal. Although Halal is not strictly a cuisine, and is more a dietary requirement, I would also have included vegetarian and vegan into the cuisines list filtering but found that these were always included in the first 2 cuisines, as such I only included Halal. I searched and could not find 'Kosher', as such Halal was the only third cuisine option selected. 

-I included error handling as I thought it might be good practice to include this even though the assignment did not specifically ask for it. I have only used error handling in Python previously, as such I hope the error handling I have included is acceptable. 


# Improvements I would make to my solution

-I tried using methods to ensure modularity of the code, as per the principles of Object Oriented Programming, however, I did not get it to run without errors. I would like to have separate methods for 'getting' the different elements, and calling them through the public static void main method. 

-I feel I could have used a for loop instead of 3 if statements to find the cuisines, however I did not get this to run without printing a comma at the end of the 3 cuisines so chose to opt for this approach instead.

-I have never used APIs before and as such the code is quite long and clunky, I would like to practice more on how to make the code more sleek.

-I have not put in handling of typos in the JSON file, as I have not worked with JSON files before I am unsure if this is generally a large concern, but have found that typos can often cause preventable bugs and as such these should be controlled as much as possible. 

-I did not have space on my computer to download Git to connect my Eclipse to GitHub, and as such had to copy and paste the code. 

-The comments are quite descriptive, and I left them in to be able to revisit how APIs work, when I need to use it for the next time. I would not necessarily comment a regular piece of code this heavily. 
