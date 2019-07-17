package com.api.core;

import com.alibaba.fastjson.JSON;
import com.api.core.util.WhereLink;
import com.api.core.util.WhereProperty;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public abstract class Ctrl {
    protected void buildWhere(String where, Example.Criteria criteria) {
        List<WhereProperty> whereLinks= JSON.parseArray(where, WhereProperty.class);
        if(whereLinks!=null){
            for (WhereProperty m:whereLinks) {
                if(m.getWhereLink()== WhereLink.LIKE){
                    criteria.andLike(m.getField(),m.getWhereLink().getSign()+(m.getValue())+m.getWhereLink().getSign());
                }else{
                    criteria.andEqualTo(m.getField(),m.getValue());
                }
            }
        }

    }

}
