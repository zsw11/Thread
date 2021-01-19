package com.thread.callable;

/**
 * @author zsw
 * @date 2021/1/19 9:43
 * @description : 　由于run()方法返回值为void类型，所以在执行完任务之后无法返回任何结果。
 */
public interface Runnable {
    void run();
}
