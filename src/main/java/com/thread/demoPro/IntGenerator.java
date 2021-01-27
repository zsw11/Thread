package com.thread.demoPro;

/**
 * @author zsw
 * @date 2021/1/26 17:27
 * @description : 考虑下面一个例子。一个任务产生偶数，其他任务消费这些数字，这里消费者唯一工作就是检查偶数的有效性
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false; // 可视性和原子性

    public abstract int next();

    public  void cancel(){
        canceled = false;
    }
    public boolean isCanceled(){
        return canceled;
    }
    public void stop(){
        canceled = true;
    }

}
