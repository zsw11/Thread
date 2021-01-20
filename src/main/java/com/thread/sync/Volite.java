package com.thread.sync;

/**
 * @author zsw
 * @date 2021/1/20 10:41
 * @description : 局部变量和成员变量线程安全问题
 * 如果它们没有共享，则线程安全
 * 如果它们被共享了，根据它们的状态是否能够改变，又分两种情况
 * 如果只有读操作，则线程安全
 * 如果有读写操作，则这段代码是临界区，需要考虑线程安全
 */
public class Volite {
}
