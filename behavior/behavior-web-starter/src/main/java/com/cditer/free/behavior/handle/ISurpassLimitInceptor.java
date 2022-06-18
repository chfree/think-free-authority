package com.cditer.free.behavior.handle;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.core.message.security.LoginModel;

public interface ISurpassLimitInceptor {
    int getOrder();

    void surpassLimit(LoginModel loginModel, WebVisitLimit webVisitLimit, int visitSumCount);
}
