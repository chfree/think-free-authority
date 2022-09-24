package com.cditer.free.stateflow.test.flow01;

import com.cditer.free.stateflow.test.flow01.state.StartState;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/23 12:23
 * @comment
 */

public class Flow01Service {
    public static void startFlow(){
        FlowStateContext stateContext = new FlowStateContext();

        new StartState().doWork(stateContext);
    }
}
