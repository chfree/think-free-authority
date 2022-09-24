package com.cditer.free.stateflow.core;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/22 12:33
 * @comment
 */

public interface IState {
    void doWork(IStateContext stateContext);

    String getBsnType();

    String getBsnStatus();
}
