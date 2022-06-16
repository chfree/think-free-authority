package com.cditer.free.behavior.entity.viewmodel;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import lombok.Data;

import java.util.List;

@Data
public class WebVisitLimitSearch extends WebVisitLimit {
    private List<LimitMatcherItem> matcherItems;
}
