package com.ego.services.juri.facade.dao.dataauthority;

import com.ego.services.juri.facade.model.dataauthority.MetadataField;
import com.ego.services.juri.facade.model.dataauthority.MetadataField;

import java.util.List;

public interface MetadataFieldMapper {
    int deleteByPrimaryKey(Long fieldId);

    int insert(MetadataField record);

    int insertSelective(MetadataField record);

    MetadataField selectByPrimaryKey(Long fieldId);

    int updateByPrimaryKeySelective(MetadataField record);

    int updateByPrimaryKey(MetadataField record);

    List<MetadataField> select(MetadataField record);

    List<MetadataField> selectTitle(MetadataField record);
}