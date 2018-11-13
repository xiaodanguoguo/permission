package com.ego.services.base.facade.service.dataauthority.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.dataauthority.TheMetadataVO;
import com.ego.services.base.facade.dao.dataauthority.TheMetadataMapper;
import com.ego.services.base.facade.model.dataauthority.TheMetadata;
import com.ego.services.base.facade.service.dataauthority.TheMetadataService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;
import org.springframework.util.CollectionUtils;

/**
 * dal Interface:TheMetadata
 * @author Mrli
 * @date 2018-10-24
 */
@Service
@Transactional
public class TheMetadataServiceImpl implements TheMetadataService {

    @Autowired
    private TheMetadataMapper theMetadataMapper;

	@Override
    public List<TheMetadataVO> findAll() {
        //List<TheMetadata> theMetadatas = theMetadataMapper.selectAll();
        //List<TheMetadataVO> theMetadataVOs = BeanCopyUtil.copyList(theMetadatas, TheMetadataVO.class);
        //return theMetadataVOs;
        return null;
    }

	@Override
    public PageInfo<TheMetadataVO> findSelective(TheMetadataVO record){
		TheMetadata model = BeanCopyUtil.copy(record, TheMetadata.class);
		model.setDeleteStatus(Byte.parseByte("0"));
		PageHelper.startPage(record.getPageNum(), record.getPageSize());
		List<TheMetadata> theMetadatas = theMetadataMapper.select(model);
		//PageInfo<TheMetadata> pageInfo = new PageInfo<>(theMetadatas);
		//转成 vo 对象
		PageInfo<TheMetadataVO> pageVo = new PageInfo(theMetadatas);
//		pageVo.setTotal(pageInfo.getTotal());
//		pageVo.setPages(pageInfo.getPages());
//		pageVo.setPageNum(pageInfo.getPageNum());
//		pageVo.setPageSize(pageInfo.getPageSize());
		return pageVo;
    }

	@Override
    public TheMetadataVO getByPrimaryKey(Long key){
    	TheMetadata theMetadata = theMetadataMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(theMetadata, TheMetadataVO.class);
    }

	@Override
    public Integer insert(TheMetadataVO record){
    	TheMetadata theMetadata = BeanCopyUtil.copy(record, TheMetadata.class);
        return theMetadataMapper.insert(theMetadata);

    }

	@Override
    public Integer insertSelective(TheMetadataVO record){
        TheMetadata theMetadata = BeanCopyUtil.copy(record, TheMetadata.class);
		theMetadata.setDeleteStatus(Byte.parseByte("0"));
        theMetadata.setCreatedDate(new Date());
		List<TheMetadata> list=theMetadataMapper.selectTitle(theMetadata);
		if(!CollectionUtils.isEmpty(list)){
			return -1;
		}else{
			return theMetadataMapper.insertSelective(theMetadata);
		}
    }
    
    @Override
    public Integer updateByPrimaryKey(TheMetadataVO record){
    	TheMetadata theMetadata = BeanCopyUtil.copy(record, TheMetadata.class);
        return theMetadataMapper.updateByPrimaryKey(theMetadata);
    }

	@Override
    public Integer updateByPrimaryKeySelective(TheMetadataVO record){
        TheMetadata theMetadata = BeanCopyUtil.copy(record, TheMetadata.class);
		List<TheMetadata> list=theMetadataMapper.selectTitle(theMetadata);
		if(!CollectionUtils.isEmpty(list)){
			return -1;
		}else{
			return theMetadataMapper.updateByPrimaryKeySelective(theMetadata);
		}
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
		TheMetadata theMetadata=new TheMetadata();
		theMetadata.setId(key);
		theMetadata.setDeleteStatus(Byte.parseByte("1"));
		return theMetadataMapper.updateByPrimaryKeySelective(theMetadata);
        //return theMetadataMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return theMetadataMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<TheMetadataVO> theMetadataVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (TheMetadataVO theMetadataVO : theMetadataVOs) {
			String opt = theMetadataVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(theMetadataVO.getId());
				int i = deleteByPrimaryKey(theMetadataVO.getId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(theMetadataVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(theMetadataVO);
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