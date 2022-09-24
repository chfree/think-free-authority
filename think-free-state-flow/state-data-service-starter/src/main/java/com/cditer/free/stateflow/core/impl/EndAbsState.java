package com.cditer.free.stateflow.core.impl;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/24 11:47
 * @comment
 */

public abstract class EndAbsState extends AbstractState{
    @Override
    public String getBsnStatus() {
        return "end";
    }
}
