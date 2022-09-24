package com.cditer.free.stateflow.test.flow01;

import com.cditer.free.stateflow.core.impl.AbstractState;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/23 12:20
 * @comment
 */

public abstract class Flow01State extends AbstractState {
    @Override
    public String getBsnType() {
        return "flow01";
    }
}
