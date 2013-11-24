package acnbatch;

import javax.ejb.Singleton;

/**
 * Created by blystad on 11/24/13.
 */
@Singleton
public class RandomBean {

    public String getHelloWorld() {
        return "Hello World";
    }

    public String toString() {
        return "RandomBean: " + getHelloWorld();
    }

}
