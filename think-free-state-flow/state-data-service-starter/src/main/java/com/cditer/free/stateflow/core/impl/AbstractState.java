package com.cditer.free.stateflow.core.impl;

import com.cditer.free.stateflow.core.IState;
import com.cditer.free.stateflow.core.IStateContext;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/22 12:59
 * @comment 抽象状态
 */

public abstract class AbstractState implements IState {
    @Override
    public void doWork(IStateContext stateContext) {

        // 记录待办
        stateContext.doWork();
    }
}
