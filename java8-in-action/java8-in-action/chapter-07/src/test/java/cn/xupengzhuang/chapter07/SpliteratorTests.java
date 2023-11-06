package cn.xupengzhuang.chapter07;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public class SpliteratorTests {

    public int countWordsIteratively(String s){
        int counter = 0;
        char[] chars = s.toCharArray();
        boolean lastSpace = true;
        for (int i = 0;i<chars.length;i++){
            char c = chars[i];
            if (Character.isWhitespace(c)){
                lastSpace = true;
            }else {
                if (lastSpace){
                    counter++;
                }
                lastSpace  = false;
            }
        }
        return counter;
    }

    @Test
    public void testWordsCount(){
        final String SENTENCE =
                " Nel   mezzo del cammin  di nostra  vita " +
                        "mi  ritrovai in una  selva oscura" +
                        " ché la  dritta via era   smarrita ";

        int i = countWordsIteratively(SENTENCE);
        System.out.println(i);
    }

    private int countWords(Stream<Character> stream){
        WordCounter counter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate,WordCounter::combine);
        return counter.getCounter();
    }

    @Test
    public void testWordsCountByStream(){
        final String SENTENCE =
                " Nel   mezzo del cammin  di nostra  vita " +
                        "mi  ritrovai in una  selva oscura" +
                        " ché la  dritta via era   smarrita ";

        Stream<Character> characterStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        //int count = countWords(characterStream);
        //并行执行时，计算结果不正确
        int count = countWords(characterStream.parallel());
        log.info("count={}",count);
    }

    @Test
    public void testSpliterator(){
        final String SENTENCE =
                " Nel   mezzo del cammin  di nostra  vita " +
                        "mi  ritrovai in una  selva oscura" +
                        " ché la  dritta via era   smarrita ";

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        int count = countWords(stream);
        System.out.println(count);

    }

}
