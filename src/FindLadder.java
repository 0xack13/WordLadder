import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.net.URL;


public class FindLadder {

	private ArrayList<String> dictionary;

    public FindLadder(String dictName) {
        this.dictionary = new ArrayList<String>();

        try {
        	URL url = getClass().getResource(dictName);
            Scanner infile = new Scanner(new File(url.getPath()));
            while (infile.hasNext()) {
                this.dictionary.add(infile.next());
            }
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("DICTIONARY FILE NOT FOUND");
        }
    }

    public WordLadder findDepth1(String startWord, String endWord) {
        WordLadder startLadder = new WordLadder();
        startLadder.addWord(startWord);

        if (startWord.equals(endWord)) {
            return startLadder;
        }
        else {
            for (String word : dictionary) {
				if (this.differByOne(word, startWord)) {
                    WordLadder restLadder = findDepth1(word, endWord);
                    if (restLadder != null) {
                        startLadder.append(restLadder);
                        return startLadder;
                    }
				}
			}
            return null;
		}
    }

    public WordLadder findDepth2(String startWord, String endWord) {
        WordLadder ladder = new WordLadder();
        ladder.addWord(startWord);
        if (this.findDepth2(ladder, endWord)) {
            return ladder;
        }
        else {
            return null;
        }
    }
    private boolean findDepth2(WordLadder sofar, String endWord) {
        if (sofar.endWord().equals(endWord)) {
            return true;
        }
        else {
            for (String word : dictionary) {
				if (this.differByOne(word, sofar.endWord()) &&
                        !sofar.contains(word)) {
                    sofar.addWord(word);
                    if (findDepth2(sofar, endWord)) {
                        return true;
                    }
                    else {
                        sofar.removeWord();
                    }
				}
			}
            return false;
		}
    }


    public WordLadder findBreadth(String startWord, String endWord) {
        Queue<WordLadder> ladders = new LinkedList<WordLadder>();

        WordLadder startLadder = new WordLadder();
        startLadder.addWord(startWord);
        ladders.add(startLadder);

        while (!ladders.isEmpty() && !ladders.peek().endWord().equals(endWord)) {
			WordLadder shortestLadder = ladders.remove();
            for (String word : dictionary) {
				if (this.differByOne(word, shortestLadder.endWord())) {
					WordLadder copy = new WordLadder(shortestLadder);
					copy.addWord(word);
					ladders.add(copy);
				}
			}
		}

        if (ladders.isEmpty()) {
			return null;
		}
		else {
			return ladders.remove();
		}
    }


    private boolean differByOne(String word1, String word2)
    {
	    if (word1.length() != word2.length()) {
            return false;
        }

        int diffCount = 0;
	    for (int i = 0; i < word1.length(); i++) {
		    if (word1.charAt(i) != word2.charAt(i)) {
			    diffCount++;
		    }
	    }
	    return (diffCount == 1);
    }

    public static void main(String[] args) {
        FindLadder finder = new FindLadder("short.txt");
        //System.out.println(finder.findDepth2("white", "shade"));
        System.out.println(finder.findBreadth("white", "shade"));
    }

}
