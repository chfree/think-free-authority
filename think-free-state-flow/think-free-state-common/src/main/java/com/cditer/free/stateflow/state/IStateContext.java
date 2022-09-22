package com.cditer.free.stateflow.state;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/22 12:32
 * @comment
 */

public interface IStateContext {
    IState getStatue();

    IBusData getBusData();

    void setStatue(IState statue);

    void setBusData(IBusData busData);

    void doWork();
}
