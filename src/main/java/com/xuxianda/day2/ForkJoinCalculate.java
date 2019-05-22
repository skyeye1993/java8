package com.xuxianda.day2;

import java.io.Serializable;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: xuxianda
 * @Date: 2019/5/22 14:34
 * @Version 1.0
 */
public class ForkJoinCalculate extends RecursiveTask<Long> implements Serializable {
    private static final long serialVersionUID = 4345007946589030057L;
    private static final long THRESHOLD = 10000L;
    private Long start;
    private Long end;

    public ForkJoinCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum=0;
        if(end-start<=THRESHOLD){
            for(long i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else {
            long mid = (end+start)/2;
            ForkJoinCalculate left = new ForkJoinCalculate(start,mid);
            left.fork();//拆分子任务，并将子任务压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(mid+1,end);
            right.fork();

            return left.join()+right.join();
        }
    }
}

