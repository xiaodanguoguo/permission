package com.ego.services.juri.facade.dao.dataauthority;

import com.ego.services.juri.facade.model.dataauthority.DataPriv;
import com.ego.services.juri.facade.model.dataauthority.DataPriv;

import java.util.List;

public interface DataPrivMapper {
    int deleteByPrimaryKey(Long privId);

    int insert(DataPriv record);

    int insertSelective(DataPriv record);

    DataPriv selectByPrimaryKey(Long privId);

    int updateByPrimaryKeySelective(DataPriv record);

    int updateByPrimaryKey(DataPriv record);

    List<DataPriv> select(DataPriv record);
}