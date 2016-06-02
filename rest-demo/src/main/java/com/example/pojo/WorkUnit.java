package com.example.pojo;

/**
 * @author yangkun
 *         generate on 16/4/17
 *         工作单元，用以封装元数据并进行拓展
 */
public class WorkUnit<T> {
    private T workUnit;

    public T getWork() {
        return workUnit;
    }

    public WorkUnit(T workUnit) {
        this.workUnit = workUnit;
    }
}
