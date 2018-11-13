package com.ego.services.base.facade.service.dataauthority.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.dataauthority.PowerExpressionVO;
import com.ego.services.base.facade.dao.dataauthority.*;
import com.ego.services.base.facade.model.dataauthority.*;
import com.ego.services.base.facade.service.dataauthority.PowerExpressionService;
import com.github.pagehelper.PageHelper;
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
    public PageInfo<PowerExpressionVO> findSelective(PowerExpressionVO record){
		PowerExpression model = BeanCopyUtil.copy(record, PowerExpression.class);
		//PageHelper.startPage(record.getPageNum(), record.getPageSize());
		model.setNum(record.getPageNum()*record.getPageSize()-record.getPageSize());
		model.setSize(record.getPageNum()*record.getPageSize());
		List<PowerExpression> powerExpressions = powerExpressionMapper.select(model);
		PageInfo<PowerExpressionVO> powerExpressionVOs =new PageInfo(powerExpressions);
		powerExpressionVOs.setPageNum(record.getPageNum());
		powerExpressionVOs.setPageSize(record.getPageSize());
		Integer count = powerExpressionMapper.selectCount(model);
		powerExpressionVOs.setTotal(count);
		return powerExpressionVOs;
    }

	@Override
    public PowerExpressionVO getByPrimaryKey(Long key){
    	PowerExpression powerExpression = powerExpressionMapper.selectqueryPower(key);
        return BeanCopyUtil.copy(powerExpression, PowerExpressionVO.class);
    }

	@Override
	public PowerExpressionVO selectAcctConfig(Long acctId){
		PowerExpression powerExpression = powerExpressionMapper.selectAcctConfig(acctId);
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
		powerExpression.setOrgConfig(BeanCopyUtil.copyList(record.getOrgConfig(), PrivConditionConfig.class));
		powerExpression.setAcctConfig(BeanCopyUtil.copyList(record.getAcctConfig(), PrivConditionConfig.class));
		powerExpression.setRoleConfig(BeanCopyUtil.copyList(record.getRoleConfig(), PrivConditionConfig.class));
		powerExpression.setCreatedDate(new Date());
		Integer num=powerExpressionMapper.insertSelective(powerExpression);

		for(int i=0;i<powerExpression.getAcctConfig().size();i++){
			PrivConditionConfig privConditionConfig=powerExpression.getAcctConfig().get(i);
			privConditionConfig.setPowerExpressionId(powerExpression.getPowerExpressionId());
			privConditionConfig.setCreatedDate(new Date());
			privConditionConfig.setCreatedBy(powerExpression.getCreatedBy());
			privConditionConfigMapper.insertSelective(privConditionConfig);
			for(int acct=0;acct<powerExpression.getAcctIds().length;acct++){
				AccountPrivRelation accountPrivRelation=new AccountPrivRelation();
				accountPrivRelation.setAcctId(powerExpression.getAcctIds()[acct]);
				accountPrivRelation.setRelationType(Byte.parseByte("3"));
				accountPrivRelation.setPowerExpressionId(powerExpression.getPowerExpressionId());
				accountPrivRelation.setRelationId(privConditionConfig.getId());
				accountPrivRelation.setCreatedBy(powerExpression.getCreatedBy());
				accountPrivRelation.setCreatedDate(new Date());
				accountPrivRelationMapper.insertSelective(accountPrivRelation);
			}
		}

		for(int i=0;i<powerExpression.getRoleConfig().size();i++){
			PrivConditionConfig privConditionConfig=powerExpression.getRoleConfig().get(i);
			privConditionConfig.setPowerExpressionId(powerExpression.getPowerExpressionId());
			privConditionConfig.setCreatedDate(new Date());
			privConditionConfig.setCreatedBy(powerExpression.getCreatedBy());
			privConditionConfigMapper.insertSelective(privConditionConfig);
			for(int role=0;role<powerExpression.getRoleIds().length;role++){
				RolePrivRelation rolePrivRelation=new RolePrivRelation();
				rolePrivRelation.setRoleId(powerExpression.getRoleIds()[role]);
				rolePrivRelation.setRelationType(Byte.parseByte("3"));
				rolePrivRelation.setPowerExpressionId(powerExpression.getPowerExpressionId());
				rolePrivRelation.setRelationId(privConditionConfig.getId());
				rolePrivRelation.setCreatedDate(new Date());
				rolePrivRelation.setCreatedBy(powerExpression.getCreatedBy());
				rolePrivRelationMapper.insertSelective(rolePrivRelation);
			}
		}

		for(int i=0;i<powerExpression.getOrgConfig().size();i++){
			PrivConditionConfig privConditionConfig=powerExpression.getOrgConfig().get(i);
			privConditionConfig.setPowerExpressionId(powerExpression.getPowerExpressionId());
			privConditionConfig.setCreatedDate(new Date());
			privConditionConfig.setCreatedBy(powerExpression.getCreatedBy());
			privConditionConfigMapper.insertSelective(privConditionConfig);
			for(int org=0;org<powerExpression.getOrgIds().length;org++){
				OrgPrivRelation orgPrivRelation=new OrgPrivRelation();
				orgPrivRelation.setOrgId(powerExpression.getOrgIds()[org]);
				orgPrivRelation.setRelationType(Byte.parseByte("3"));
				orgPrivRelation.setPowerExpressionId(powerExpression.getPowerExpressionId());
				orgPrivRelation.setRelationId(privConditionConfig.getId());
				orgPrivRelation.setCreatedDate(new Date());
				orgPrivRelation.setCreatedBy(powerExpression.getCreatedBy());
				orgPrivRelationMapper.insertSelective(orgPrivRelation);
			}
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
		powerExpression.setOrgConfig(BeanCopyUtil.copyList(record.getOrgConfig(), PrivConditionConfig.class));
		powerExpression.setAcctConfig(BeanCopyUtil.copyList(record.getAcctConfig(), PrivConditionConfig.class));
		powerExpression.setRoleConfig(BeanCopyUtil.copyList(record.getRoleConfig(), PrivConditionConfig.class));
		powerExpression.setCreatedDate(new Date());
		Integer num=powerExpressionMapper.updateByPrimaryKeySelective(powerExpression);
		privConditionConfigMapper.deletePowerId(powerExpression.getPowerExpressionId());
		accountPrivRelationMapper.deletePowerId(powerExpression.getPowerExpressionId());
		rolePrivRelationMapper.deletePowerId(powerExpression.getPowerExpressionId());
		orgPrivRelationMapper.deletePowerId(powerExpression.getPowerExpressionId());

		for(int i=0;i<powerExpression.getAcctConfig().size();i++){
			PrivConditionConfig privConditionConfig=powerExpression.getAcctConfig().get(i);
			privConditionConfig.setPowerExpressionId(powerExpression.getPowerExpressionId());
			privConditionConfig.setCreatedDate(new Date());
			privConditionConfig.setCreatedBy(powerExpression.getCreatedBy());
			privConditionConfigMapper.insertSelective(privConditionConfig);
			for(int acct=0;acct<powerExpression.getAcctIds().length;acct++){
				AccountPrivRelation accountPrivRelation=new AccountPrivRelation();
				accountPrivRelation.setAcctId(powerExpression.getAcctIds()[acct]);
				accountPrivRelation.setRelationType(Byte.parseByte("3"));
				accountPrivRelation.setPowerExpressionId(powerExpression.getPowerExpressionId());
				accountPrivRelation.setRelationId(privConditionConfig.getId());
				accountPrivRelation.setCreatedBy(powerExpression.getCreatedBy());
				accountPrivRelation.setCreatedDate(new Date());
				accountPrivRelationMapper.insertSelective(accountPrivRelation);
			}
		}

		for(int i=0;i<powerExpression.getRoleConfig().size();i++){
			PrivConditionConfig privConditionConfig=powerExpression.getRoleConfig().get(i);
			privConditionConfig.setPowerExpressionId(powerExpression.getPowerExpressionId());
			privConditionConfig.setCreatedDate(new Date());
			privConditionConfig.setCreatedBy(powerExpression.getCreatedBy());
			privConditionConfigMapper.insertSelective(privConditionConfig);
			for(int role=0;role<powerExpression.getRoleIds().length;role++){
				RolePrivRelation rolePrivRelation=new RolePrivRelation();
				rolePrivRelation.setRoleId(powerExpression.getRoleIds()[role]);
				rolePrivRelation.setRelationType(Byte.parseByte("3"));
				rolePrivRelation.setPowerExpressionId(powerExpression.getPowerExpressionId());
				rolePrivRelation.setRelationId(privConditionConfig.getId());
				rolePrivRelation.setCreatedDate(new Date());
				rolePrivRelation.setCreatedBy(powerExpression.getCreatedBy());
				rolePrivRelationMapper.insertSelective(rolePrivRelation);
			}
		}

		for(int i=0;i<powerExpression.getOrgConfig().size();i++){
			PrivConditionConfig privConditionConfig=powerExpression.getOrgConfig().get(i);
			privConditionConfig.setPowerExpressionId(powerExpression.getPowerExpressionId());
			privConditionConfig.setCreatedDate(new Date());
			privConditionConfig.setCreatedBy(powerExpression.getCreatedBy());
			privConditionConfigMapper.insertSelective(privConditionConfig);
			for(int org=0;org<powerExpression.getOrgIds().length;org++){
				OrgPrivRelation orgPrivRelation=new OrgPrivRelation();
				orgPrivRelation.setOrgId(powerExpression.getOrgIds()[org]);
				orgPrivRelation.setRelationType(Byte.parseByte("3"));
				orgPrivRelation.setPowerExpressionId(powerExpression.getPowerExpressionId());
				orgPrivRelation.setRelationId(privConditionConfig.getId());
				orgPrivRelation.setCreatedDate(new Date());
				orgPrivRelation.setCreatedBy(powerExpression.getCreatedBy());
				orgPrivRelationMapper.insertSelective(orgPrivRelation);
			}
		}

		return num;
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){

		privConditionConfigMapper.deletePowerId(key);
		accountPrivRelationMapper.deletePowerId(key);
		rolePrivRelationMapper.deletePowerId(key);
		orgPrivRelationMapper.deletePowerId(key);
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