package com.cditer.free.stateflow.test.flow01.state;

import com.cditer.free.stateflow.core.IStateContext;
import com.cditer.free.stateflow.core.impl.StartAbsState;
import com.cditer.free.stateflow.test.flow01.Flow01State;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/22 13:19
 * @comment 简易的请假流程,纯状态机
 */

public class StartState extends StartAbsState {
    @Override
    public void doWork(IStateContext stateContext) {
        // 处理一些业务状态

        // 设置下一步状态
        stateContext.setStatue(new DeptManageState());

        // 执行下一步状态
        super.doWork(stateContext);
    }

    @Override
    public String getBsnType() {
        return null;
    }
}
