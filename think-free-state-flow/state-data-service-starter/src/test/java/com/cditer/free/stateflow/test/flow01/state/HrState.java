package com.cditer.free.stateflow.test.flow01.state;

import com.cditer.free.stateflow.test.flow01.Flow01State;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/23 12:17
 * @comment
 */

public class HrState extends Flow01State {
    @Override
    public String getBsnStatus() {
        return "hr";
    }
}
