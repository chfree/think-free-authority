package com.cditer.free.stateflow.test.flow01.state;

import com.cditer.free.stateflow.test.flow01.Flow01State;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/23 12:19
 * @comment
 */

public class SoftState extends Flow01State {
    @Override
    public String getBsnStatus() {
        return "soft";
    }
}
