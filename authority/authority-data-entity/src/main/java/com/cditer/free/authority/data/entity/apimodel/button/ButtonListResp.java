package com.cditer.free.authority.data.entity.apimodel.button;

import com.cditer.free.authority.data.entity.model.Button;
import com.cditer.free.core.message.web.BasePagerResp;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class ButtonListResp extends BasePagerResp {
    private List<Button> buttons;
}
