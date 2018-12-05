package com.ego.services.juri.facade.service.dataauthority.impl;

import java.util.List;
import java.util.Set;

import com.ego.services.juri.api.vo.dataauthority.RolePrivRelationVO;
import com.ego.services.juri.facade.dao.dataauthority.RolePrivRelationMapper;
import com.ego.services.juri.facade.model.dataauthority.RolePrivRelation;
import com.ego.services.juri.facade.service.dataauthority.RolePrivRelationService;
import com.ego.services.juri.facade.model.dataauthority.RolePrivRelation;
import com.ego.services.juri.facade.service.dataauthority.RolePrivRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;

/**
 * dal Interface:RolePrivRelation
 * @author Mrli
 * @date 2018-11-1
 */
@Service
@Transactional
public class RolePrivRelationServiceImpl implements RolePrivRelationService {

    @Autowired
    private RolePrivRelationMapper rolePrivRelationMapper;

	@Override
    public List<RolePrivRelationVO> findAll() {
        //List<RolePrivRelation> rolePrivRelations = rolePrivRelationMapper.selectAll();
        //List<RolePrivRelationVO> rolePrivRelationVOs = BeanCopyUtil.copyList(rolePrivRelations, RolePrivRelationVO.class);
        //return rolePrivRelationVOs;
        return null;
    }

	@Override
    public List<RolePrivRelationVO> findSelective(RolePrivRelationVO record){
		RolePrivRelation model = BeanCopyUtil.copy(record, RolePrivRelation.class);
		List<RolePrivRelation> rolePrivRelations = rolePrivRelationMapper.select(model);
		List<RolePrivRelationVO> rolePrivRelationVOs = BeanCopyUtil.copyList(rolePrivRelations, RolePrivRelationVO.class);
		return rolePrivRelationVOs;
    }

	@Override
    public RolePrivRelationVO getByPrimaryKey(Long key){
    	RolePrivRelation rolePrivRelation = rolePrivRelationMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(rolePrivRelation, RolePrivRelationVO.class);
    }

	@Override
    public Integer insert(RolePrivRelationVO record){
    	RolePrivRelation rolePrivRelation = BeanCopyUtil.copy(record, RolePrivRelation.class);
        return rolePrivRelationMapper.insert(rolePrivRelation);

    }

	@Override
    public Integer insertSelective(RolePrivRelationVO record){
        RolePrivRelation rolePrivRelation = BeanCopyUtil.copy(record, RolePrivRelation.class);
        return rolePrivRelationMapper.insertSelective(rolePrivRelation);
    }
    
    @Override
    public Integer updateByPrimaryKey(RolePrivRelationVO record){
    	RolePrivRelation rolePrivRelation = BeanCopyUtil.copy(record, RolePrivRelation.class);
        return rolePrivRelationMapper.updateByPrimaryKey(rolePrivRelation);
    }

	@Override
    public Integer updateByPrimaryKeySelective(RolePrivRelationVO record){
        RolePrivRelation rolePrivRelation = BeanCopyUtil.copy(record, RolePrivRelation.class);
        return rolePrivRelationMapper.updateByPrimaryKeySelective(rolePrivRelation);
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
        return rolePrivRelationMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return rolePrivRelationMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<RolePrivRelationVO> rolePrivRelationVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (RolePrivRelationVO rolePrivRelationVO : rolePrivRelationVOs) {
			String opt = rolePrivRelationVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(rolePrivRelationVO.getId());
				int i = deleteByPrimaryKey(rolePrivRelationVO.getId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(rolePrivRelationVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(rolePrivRelationVO);
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