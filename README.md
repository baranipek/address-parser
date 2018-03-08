## User Story
     An address provider returns addresses only with concatenated street
     names and numbers. Our own system on the other hand has split
     fields for street name and street number.
     Input: string of address
     Output: string of street and string of street-number
     1. Write a simple program that does the task for the most simple
     cases, e.g.
     a. “Winterallee 3” -> {“ Winterallee”, “3”}
     b. “Musterstrasse 45” -> { “Musterstrasse”, “45”}
     c. “Blaufeldweg 123B” -> {“Blaufeldweg”, “123B”}
     2. Consider more complicated cases
     a. “Am Bächle 23” -> {“Am Bächle”, “23”}
     b. “Auf der Vogelwiese 23 b” -> {“Auf der Vogelwiese”, ”23 b”}
     3. BONUS: Consider other countries (complex cases)
     a. “Calle Aduana, 29” -> {“Calle Aduana”, “29”}
     b. “Calle 39 No 1540” -> {“Calle 39”, “No 1540”}
    

 ## How to run
    This is a simple spring boot project you can run test with the command mvn  clean test and run spring-boot:run
