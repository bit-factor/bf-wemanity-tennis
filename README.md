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
 * for simplicity, in main method I considered we have only 2 hardcoded players: `a` and `b`. The program expects as input a string formed from `a` and `b`. Each occurence means that respective player won that `game`. For example, string `aab` means that player `a` won the first to games and then `b` won the latest one;
   * as an improvement - I may need to find a way to limit the input to an exact game. Right now, is difficult to provide the exact `[ab]*` string to have a full match. A possible solution could be simply using `readKey`;
 * from command line type `java -jar ./bf-wemanity-tennis-0.0.1-SNAPSHOT-jar-with-dependencies.jar [ab]*`
   * where `[ab]*` is a string which defines a sequence in which each player will win the points;