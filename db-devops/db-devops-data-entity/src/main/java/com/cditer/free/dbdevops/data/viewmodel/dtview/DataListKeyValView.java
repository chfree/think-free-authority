package com.cditer.free.dbdevops.data.viewmodel.dtview;

import lombok.Data;

import java.util.List;

@Data
public class DataListKeyValView {
    private String dataTabId;

    private List<DataColKeyValView> colKeyValList;

    private List<DataColKeyValView> oldColKeyValList;
}
