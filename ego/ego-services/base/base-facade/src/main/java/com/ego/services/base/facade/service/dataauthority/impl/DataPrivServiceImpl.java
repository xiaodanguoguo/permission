package com.ego.services.base.facade.service.dataauthority.impl;

import java.util.List;
import java.util.Set;

import com.ego.services.base.api.vo.dataauthority.DataPrivVO;
import com.ego.services.base.facade.dao.dataauthority.DataPrivMapper;
import com.ego.services.base.facade.model.dataauthority.DataPriv;
import com.ego.services.base.facade.service.dataauthority.DataPrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;

/**
 * dal Interface:DataPriv
 * @author Mrli
 * @date 2018-11-1
 */
@Service
@Transactional
public class DataPrivServiceImpl implements DataPrivService {

    @Autowired
    private DataPrivMapper dataPrivMapper;

	@Override
    public List<DataPrivVO> findAll() {
        //List<DataPriv> dataPrivs = dataPrivMapper.selectAll();
        //List<DataPrivVO> dataPrivVOs = BeanCopyUtil.copyList(dataPrivs, DataPrivVO.class);
        //return dataPrivVOs;
        return null;
    }

	@Override
    public List<DataPrivVO> findSelective(DataPrivVO record){
		DataPriv model = BeanCopyUtil.copy(record, DataPriv.class);
		List<DataPriv> dataPrivs = dataPrivMapper.select(model);
		List<DataPrivVO> dataPrivVOs = BeanCopyUtil.copyList(dataPrivs, DataPrivVO.class);
		return dataPrivVOs;
    }

	@Override
    public DataPrivVO getByPrimaryKey(Long key){
    	DataPriv dataPriv = dataPrivMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(dataPriv, DataPrivVO.class);
    }

	@Override
    public Integer insert(DataPrivVO record){
    	DataPriv dataPriv = BeanCopyUtil.copy(record, DataPriv.class);
        return dataPrivMapper.insert(dataPriv);

    }

	@Override
    public Integer insertSelective(DataPrivVO record){
        DataPriv dataPriv = BeanCopyUtil.copy(record, DataPriv.class);
        return dataPrivMapper.insertSelective(dataPriv);
    }
    
    @Override
    public Integer updateByPrimaryKey(DataPrivVO record){
    	DataPriv dataPriv = BeanCopyUtil.copy(record, DataPriv.class);
        return dataPrivMapper.updateByPrimaryKey(dataPriv);
    }

	@Override
    public Integer updateByPrimaryKeySelective(DataPrivVO record){
        DataPriv dataPriv = BeanCopyUtil.copy(record, DataPriv.class);
        return dataPrivMapper.updateByPrimaryKeySelective(dataPriv);
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
        return dataPrivMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return dataPrivMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<DataPrivVO> dataPrivVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (DataPrivVO dataPrivVO : dataPrivVOs) {
			String opt = dataPrivVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(dataPrivVO.getId());
				int i = deleteByPrimaryKey(dataPrivVO.getPrivId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(dataPrivVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(dataPrivVO);
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