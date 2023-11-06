package cn.xupengzhuang.chapter07;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;//返回null表示要解析的String已经足够小，可以顺序处理
        }
        for (int splitPos = currentSize / 2 + currentChar;
             splitPos < string.length(); splitPos++) {//将试探拆分位置设定为要解析的String的中间
            if (Character.isWhitespace(string.charAt(splitPos))) {//让拆分位置前进直到下一个空格
                Spliterator<Character> spliterator =    //创建一个新WordCounter-Spliterator来解析String从开始到拆分位置的部分
                new WordCounterSpliterator(string.substring(currentChar,
                        splitPos));
                currentChar = splitPos;    //将这个WordCounter-Spliterator 的起始位置设为拆分位置
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
