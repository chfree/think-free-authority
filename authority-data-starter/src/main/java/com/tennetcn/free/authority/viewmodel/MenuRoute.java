package com.tennetcn.free.authority.viewmodel;

import com.tennetcn.free.authority.model.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 17:00
 * @comment
 */

@Data
public class MenuRoute extends Menu {
    private List<MenuRoute> children;

    private String parentName;

    private Meta meta;

    public Meta getMeta(){
        Meta meta = new Meta();
        meta.setTitle(this.getTitle());
        meta.setIcon(this.getIcon());

        return meta;
    }
}

@Data
class Meta{
    private String title;

    private String icon;
}