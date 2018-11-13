package com.ego.services.base.api.vo.dataauthority;

import java.util.List;

public class PrivRelationVO {

    private String roleName;

    private String orgName;

    private String acctName;

    private Integer type;

    private List<PrivConditionConfigVO> children;

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public List<PrivConditionConfigVO> getChildren() {
        return children;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setChildren(List<PrivConditionConfigVO> children) {
        this.children = children;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
