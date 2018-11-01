//package com.ego.services.base.facade.controller.test;
//
//import com.ebase.core.page.Page;
//import com.ego.services.base.facade.dao.jurisdiction.RoleInfoMapper;
//import com.ego.services.base.facade.model.jurisdiction.RoleInfo;
//import org.apache.ibatis.session.RowBounds;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 测试类
// * @author Kim
// *
// */
//@RestController
//public class BaseController {
//	@Autowired
//	private RoleInfoMapper roleInfoMapper;
//
//
//	@Test
//	public void testPage() {
//		int pageNo = 1;
//		int pageSize = 10;
//		RowBounds bounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
//        RoleInfo roleInfo=new RoleInfo();
//		Page<RoleInfo> list = roleInfoMapper.findTwo(bounds,roleInfo);
//		System.out.println(list);
//	}
//
//}
