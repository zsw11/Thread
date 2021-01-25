package com.thread.demoPro;

/**
 * @author zsw
 * @date 2021/1/22 15:29
 * @description : 票
 */
public class SaleNum {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // 售票
    public synchronized int sale(int count){
        if(this.count>=count){
            this.count -=count;
        }else {
            this.count -=0;
            return 0;
        }
        return this.count;
    }
}
