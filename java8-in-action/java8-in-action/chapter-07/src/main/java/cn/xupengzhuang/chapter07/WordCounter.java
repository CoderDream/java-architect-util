package cn.xupengzhuang.chapter07;

import java.util.stream.Stream;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c){
        if (Character.isWhitespace(c)){
            return lastSpace ? this : new WordCounter(counter,true);
        }else {
            return lastSpace ? new WordCounter(counter+1,false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter){
        WordCounter counter = new WordCounter(this.counter + wordCounter.counter, wordCounter.lastSpace);
        return counter;
    }

    public int getCounter(){
        return counter;
    }

    private int countWords(Stream<Character> stream){
        WordCounter counter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return counter.getCounter();
    }
}
