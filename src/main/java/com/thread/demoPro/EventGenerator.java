package com.thread.demoPro;

/**
 * @author zsw
 * @date 2021/1/26 17:48
 * @description :
 */
public class EventGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EventGenerator());
    }
}
