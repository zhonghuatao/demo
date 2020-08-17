package com.tao.service.impl;

import com.tao.dao.impl.TypeDaoImpl;
import com.tao.entity.Type;
import com.tao.service.GoodsTypeService;

import java.util.List;

public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Override
    public List<Type> getGoodsType() {
        TypeDaoImpl typeDao = new TypeDaoImpl();
        List<Type> query = typeDao.query();
        if (query!=null)
            return query;
        return null;
    }


}
