package cn.xupengzhuang.chapter08;

import cn.xupengzhuang.chapter08.observer.Feed;
import cn.xupengzhuang.chapter08.observer.Guardian;
import cn.xupengzhuang.chapter08.observer.LeMonde;
import cn.xupengzhuang.chapter08.observer.NYTimes;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ObserverTests {
    @Test
    public void testObserver(){
        String tweet = "The queen said her favourite book is Java 8 in Action!";
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObserver(tweet);
    }

    @Test
    public void testObserverByLambda(){
        String tweet = "The queen said her favourite book is Java 8 in Action!";

        Feed f = new Feed();
        f.registerObserver(sweet -> {
            if (sweet != null && sweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        f.registerObserver(sweet -> {
            if (sweet != null && sweet.contains("wine")){
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });
        f.registerObserver(sweet -> {
            if (sweet != null && sweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });

        f.notifyObserver(tweet);
    }
}
