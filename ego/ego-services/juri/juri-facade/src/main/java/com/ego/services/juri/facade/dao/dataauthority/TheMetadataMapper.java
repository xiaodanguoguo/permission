package com.ego.services.juri.facade.dao.dataauthority;

import com.ego.services.juri.facade.model.dataauthority.TheMetadata;
import com.ego.services.juri.facade.model.dataauthority.TheMetadata;

import java.util.List;

public interface TheMetadataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TheMetadata record);

    int insertSelective(TheMetadata record);

    TheMetadata selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TheMetadata record);

    int updateByPrimaryKey(TheMetadata record);

    List<TheMetadata> select(TheMetadata record);

    List<TheMetadata> selectTitle(TheMetadata record);
}