package com.ego.services.juri.facade.service.dataauthority.impl;

import java.util.List;
import java.util.Set;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.dataauthority.MetadataFieldVO;
import com.ego.services.juri.facade.dao.dataauthority.MetadataFieldMapper;
import com.ego.services.juri.facade.model.dataauthority.MetadataField;
import com.ego.services.juri.facade.service.dataauthority.MetadataFieldService;
import com.ego.services.juri.facade.model.dataauthority.MetadataField;
import com.ego.services.juri.facade.service.dataauthority.MetadataFieldService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;

/**
 * dal Interface:MetadataField
 * @author Mrli
 * @date 2018-10-24
 */
@Service
@Transactional
public class MetadataFieldServiceImpl implements MetadataFieldService {

    @Autowired
    private MetadataFieldMapper metadataFieldMapper;

	@Override
    public List<MetadataFieldVO> findAll() {
        //List<MetadataField> metadataFields = metadataFieldMapper.selectAll();
        //List<MetadataFieldVO> metadataFieldVOs = BeanCopyUtil.copyList(metadataFields, MetadataFieldVO.class);
        //return metadataFieldVOs;
        return null;
    }

	@Override
    public PageInfo<MetadataFieldVO> findSelective(MetadataFieldVO record){
		MetadataField model = BeanCopyUtil.copy(record, MetadataField.class);
		PageHelper.startPage(record.getPageNum(), record.getPageSize());
		//PageInfo<TheMetadata> pageInfo = new PageInfo<>(theMetadatas);
		//转成 vo 对象

		List<MetadataField> metadataFields = metadataFieldMapper.select(model);
		PageInfo<MetadataFieldVO> pageVo = new PageInfo(metadataFields);
		return pageVo;
    }

	@Override
    public MetadataFieldVO getByPrimaryKey(Long key){
    	MetadataField metadataField = metadataFieldMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(metadataField, MetadataFieldVO.class);
    }

	@Override
    public Integer insert(MetadataFieldVO record){
    	MetadataField metadataField = BeanCopyUtil.copy(record, MetadataField.class);
        return metadataFieldMapper.insert(metadataField);

    }

	@Override
    public Integer insertSelective(MetadataFieldVO record){
        MetadataField metadataField = BeanCopyUtil.copy(record, MetadataField.class);
		List<MetadataField> list=metadataFieldMapper.selectTitle(metadataField);
		if(!CollectionUtils.isEmpty(list)){
			return -1;
		}else {
			return metadataFieldMapper.insertSelective(metadataField);
		}
    }
    
    @Override
    public Integer updateByPrimaryKey(MetadataFieldVO record){
    	MetadataField metadataField = BeanCopyUtil.copy(record, MetadataField.class);
        return metadataFieldMapper.updateByPrimaryKey(metadataField);
    }

	@Override
    public Integer updateByPrimaryKeySelective(MetadataFieldVO record){
        MetadataField metadataField = BeanCopyUtil.copy(record, MetadataField.class);
		List<MetadataField> list=metadataFieldMapper.selectTitle(metadataField);
		if(!CollectionUtils.isEmpty(list)){
			return -1;
		}else{
			return metadataFieldMapper.updateByPrimaryKeySelective(metadataField);
		}
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
        return metadataFieldMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return metadataFieldMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<MetadataFieldVO> metadataFieldVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (MetadataFieldVO metadataFieldVO : metadataFieldVOs) {
			String opt = metadataFieldVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(metadataFieldVO.getId());
				int i = deleteByPrimaryKey(metadataFieldVO.getFieldId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(metadataFieldVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(metadataFieldVO);
				if (i > 0) {
					result++;
				}
			}
		}
//		if(!keys.isEmpty())
//			result = result + deleteByPrimaryKeys(keys);
		return result;
	}
}