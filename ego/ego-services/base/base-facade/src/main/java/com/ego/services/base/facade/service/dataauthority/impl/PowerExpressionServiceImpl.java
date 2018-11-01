package com.ego.services.base.facade.service.dataauthority.impl;

import java.util.List;
import java.util.Set;

import com.ego.services.base.api.vo.dataauthority.PowerExpressionVO;
import com.ego.services.base.facade.dao.dataauthority.*;
import com.ego.services.base.facade.model.dataauthority.PowerExpression;
import com.ego.services.base.facade.model.dataauthority.PrivConditionConfig;
import com.ego.services.base.facade.service.dataauthority.PowerExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;

/**
 * dal Interface:PowerExpression
 * @author Mrli
 * @date 2018-11-1
 */
@Service
@Transactional
public class PowerExpressionServiceImpl implements PowerExpressionService {

    @Autowired
    private PowerExpressionMapper powerExpressionMapper;

	@Autowired
	private PrivConditionConfigMapper privConditionConfigMapper;

	@Autowired
	private AccountPrivRelationMapper accountPrivRelationMapper;

	@Autowired
	private RolePrivRelationMapper rolePrivRelationMapper;

	@Autowired
	private OrgPrivRelationMapper orgPrivRelationMapper;

	@Override
    public List<PowerExpressionVO> findAll() {
        //List<PowerExpression> powerExpressions = powerExpressionMapper.selectAll();
        //List<PowerExpressionVO> powerExpressionVOs = BeanCopyUtil.copyList(powerExpressions, PowerExpressionVO.class);
        //return powerExpressionVOs;
        return null;
    }

	@Override
    public List<PowerExpressionVO> findSelective(PowerExpressionVO record){
		PowerExpression model = BeanCopyUtil.copy(record, PowerExpression.class);
		List<PowerExpression> powerExpressions = powerExpressionMapper.select(model);
		List<PowerExpressionVO> powerExpressionVOs = BeanCopyUtil.copyList(powerExpressions, PowerExpressionVO.class);
		return powerExpressionVOs;
    }

	@Override
    public PowerExpressionVO getByPrimaryKey(Long key){
    	PowerExpression powerExpression = powerExpressionMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(powerExpression, PowerExpressionVO.class);
    }

	@Override
    public Integer insert(PowerExpressionVO record){
    	PowerExpression powerExpression = BeanCopyUtil.copy(record, PowerExpression.class);
        return powerExpressionMapper.insert(powerExpression);

    }

	/**
	 * 添加表达式权限
	 * @param record
	 * @return
	 */
	@Override
    public Integer insertSelective(PowerExpressionVO record){
        PowerExpression powerExpression = BeanCopyUtil.copy(record, PowerExpression.class);
		Integer num=powerExpressionMapper.insertSelective(powerExpression);

		for(int i=0;i<powerExpression.getAcctConfig().size();i++){
			PrivConditionConfig privConditionConfig=powerExpression.getAcctConfig().get(i);
			privConditionConfig.setPowerExpressionId(powerExpression.getPowerExpressionId());
			privConditionConfigMapper.insertSelective(privConditionConfig);
		}

        return num;
    }
    
    @Override
    public Integer updateByPrimaryKey(PowerExpressionVO record){
    	PowerExpression powerExpression = BeanCopyUtil.copy(record, PowerExpression.class);
        return powerExpressionMapper.updateByPrimaryKey(powerExpression);
    }

	@Override
    public Integer updateByPrimaryKeySelective(PowerExpressionVO record){
        PowerExpression powerExpression = BeanCopyUtil.copy(record, PowerExpression.class);
        return powerExpressionMapper.updateByPrimaryKeySelective(powerExpression);
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
        return powerExpressionMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return powerExpressionMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<PowerExpressionVO> powerExpressionVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (PowerExpressionVO powerExpressionVO : powerExpressionVOs) {
			String opt = powerExpressionVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(powerExpressionVO.getId());
				int i = deleteByPrimaryKey(powerExpressionVO.getPowerExpressionId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(powerExpressionVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(powerExpressionVO);
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