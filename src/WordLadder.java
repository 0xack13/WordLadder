import java.util.ArrayList;

public class WordLadder {
    private ArrayList<String> words;

    public WordLadder() {
        this.words = new ArrayList<String>();
    }

    //Copy words from "copy" into words
    public WordLadder(WordLadder copy) {
        //Call the default constructor
        this();
        for (String nextWord: copy.words) {
            this.words.add(nextWord);
        }
    }

    public void addWord(String newWord) {
        this.words.add(newWord);
    }

    public void removeWord() {
        this.words.remove(this.words.size()-1);
    }

    public String endWord() {
        return this.words.get(this.words.size()-1);
    }

    public boolean contains(String word) {
        return this.words.contains(word);
    }

    public void append(WordLadder other) {
        for (String str : other.words) {
            this.addWord(str);
        }
    }

    public String toString() {
        return this.words.toString();
    }
}
