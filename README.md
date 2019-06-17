# bf-wemanity-tennis

Initial input: https://gist.github.com/MatteoPierro/22e09a2b5d9e41fdd8c226d318fc0984

Considerations:
 * A `set` is composed out of `games`;
 * the score in `game` can have the 0 (LOVE), 15 (FIVETEEN), 30 (THIRTY), 40 (FORTY), DEUCE/WIN for each of the players;
 
Target java:
 * target jdk is 8. The project was set in intellij to 11 out of convenience as I already had it on my machine;
 
Dependencies:
 * log4j/logback;
 * lombok;
 
How to build:
 * `mvn package`
 
How to run:
 * go into `target/` directory;
 * from command line type `java -jar ./bf-wemanity-tennis-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
 * the program will start and then wait for the user to enter the player name who is going to win this ball (press ENTER when done);
 * the steps will be repeated until the current set is being won;