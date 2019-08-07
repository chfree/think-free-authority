package com.tennetcn.free.authority.apimodel.button;

import com.tennetcn.free.authority.message.PagerResp;
import com.tennetcn.free.authority.model.Button;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class ButtonListResp extends PagerResp {
    private List<Button> buttons;
}
