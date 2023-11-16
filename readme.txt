Welcome to my Hangman game!! I began writing the code for this challenge at 11:20 AM on November 16, 2023, and I have
finished the code at 2:26 PM on that same day. This project took a total of about 3 hours and 6 minutes.

Run Instructions:
I have packaged this program into a Jar file you can use to run on your machine assuming you have JAVA 11 installed.
The Jar file is located in the target folder.
To run the jar file, here is the command I use on my machine, replacing FILEPATH with the path to the dictionary text file:

java -cp OITCodingChallenge-1.0.jar MainRunner FILEPATH

You can also import this project using Maven into IntelliJ (That's the IDE I used) and run it there.

The dictionary file I used is the WordList.txt file in this repo. This program can take in any text file and read as many
words as you want to be chosen from at random. Each word must be separated by a newline.

Outside Resources Used -
Javadoc for String, BufferedReader, and FileReader classes
Stack Overflow article on clearing the terminal programmatically - https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
W3Schools example for the use of String.indexOf() - https://www.w3schools.com/java/ref_string_indexof.asp
Stack Overflow article for ideas on how to draw the hangman - https://stackoverflow.com/questions/34013812/printing-out-body-parts-for-hangman-game-to-console

