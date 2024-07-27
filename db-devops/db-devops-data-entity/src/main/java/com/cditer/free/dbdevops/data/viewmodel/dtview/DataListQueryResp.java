package com.cditer.free.dbdevops.data.viewmodel.dtview;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DataListQueryResp {
    private int totalCount;

    private List<Map<String,Object>> dataList;
}
