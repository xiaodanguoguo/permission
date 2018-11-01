package com.ego.services.base.facade.service.dataauthority.impl;

import java.util.List;
import java.util.Set;

import com.ego.services.base.api.vo.dataauthority.PrivConditionConfigVO;
import com.ego.services.base.facade.dao.dataauthority.PrivConditionConfigMapper;
import com.ego.services.base.facade.model.dataauthority.PrivConditionConfig;
import com.ego.services.base.facade.service.dataauthority.PrivConditionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;

/**
 * dal Interface:PrivConditionConfig
 * @author Mrli
 * @date 2018-11-1
 */
@Service
@Transactional
public class PrivConditionConfigServiceImpl implements PrivConditionConfigService {

    @Autowired
    private PrivConditionConfigMapper privConditionConfigMapper;

	@Override
    public List<PrivConditionConfigVO> findAll() {
        //List<PrivConditionConfig> privConditionConfigs = privConditionConfigMapper.selectAll();
        //List<PrivConditionConfigVO> privConditionConfigVOs = BeanCopyUtil.copyList(privConditionConfigs, PrivConditionConfigVO.class);
        //return privConditionConfigVOs;
        return null;
    }

	@Override
    public List<PrivConditionConfigVO> findSelective(PrivConditionConfigVO record){
		PrivConditionConfig model = BeanCopyUtil.copy(record, PrivConditionConfig.class);
		List<PrivConditionConfig> privConditionConfigs = privConditionConfigMapper.select(model);
		List<PrivConditionConfigVO> privConditionConfigVOs = BeanCopyUtil.copyList(privConditionConfigs, PrivConditionConfigVO.class);
		return privConditionConfigVOs;
    }

	@Override
    public PrivConditionConfigVO getByPrimaryKey(Long key){
    	PrivConditionConfig privConditionConfig = privConditionConfigMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(privConditionConfig, PrivConditionConfigVO.class);
    }

	@Override
    public Integer insert(PrivConditionConfigVO record){
    	PrivConditionConfig privConditionConfig = BeanCopyUtil.copy(record, PrivConditionConfig.class);
        return privConditionConfigMapper.insert(privConditionConfig);

    }

	@Override
    public Integer insertSelective(PrivConditionConfigVO record){
        PrivConditionConfig privConditionConfig = BeanCopyUtil.copy(record, PrivConditionConfig.class);
        return privConditionConfigMapper.insertSelective(privConditionConfig);
    }
    
    @Override
    public Integer updateByPrimaryKey(PrivConditionConfigVO record){
    	PrivConditionConfig privConditionConfig = BeanCopyUtil.copy(record, PrivConditionConfig.class);
        return privConditionConfigMapper.updateByPrimaryKey(privConditionConfig);
    }

	@Override
    public Integer updateByPrimaryKeySelective(PrivConditionConfigVO record){
        PrivConditionConfig privConditionConfig = BeanCopyUtil.copy(record, PrivConditionConfig.class);
        return privConditionConfigMapper.updateByPrimaryKeySelective(privConditionConfig);
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
        return privConditionConfigMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return privConditionConfigMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<PrivConditionConfigVO> privConditionConfigVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (PrivConditionConfigVO privConditionConfigVO : privConditionConfigVOs) {
			String opt = privConditionConfigVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(privConditionConfigVO.getId());
				int i = deleteByPrimaryKey(privConditionConfigVO.getId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(privConditionConfigVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(privConditionConfigVO);
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