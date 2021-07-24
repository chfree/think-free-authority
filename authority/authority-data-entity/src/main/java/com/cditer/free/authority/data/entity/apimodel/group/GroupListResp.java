package com.cditer.free.authority.data.entity.apimodel.group;

import com.cditer.free.authority.data.entity.model.Group;
import com.cditer.free.core.message.web.BasePagerResp;
import lombok.Data;
import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-05-31 12:53:48
 * @comment     权限组
 */

@Data
public class GroupListResp extends BasePagerResp {
    private List<Group> groups;
}
