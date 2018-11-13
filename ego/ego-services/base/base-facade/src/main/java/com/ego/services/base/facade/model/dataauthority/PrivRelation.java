package com.ego.services.base.facade.model.dataauthority;

import java.util.List;

public class PrivRelation {

    private String roleName;

    private String orgName;

    private String acctName;

    private Integer type;

    private OrgPrivRelation orgPrivRelations;

    private List<PrivConditionConfig> children;

    private AccountPrivRelation accountPrivRelations;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public OrgPrivRelation getOrgPrivRelations() {
        return orgPrivRelations;
    }

    public void setOrgPrivRelations(OrgPrivRelation orgPrivRelations) {
        this.orgPrivRelations = orgPrivRelations;
    }

    public List<PrivConditionConfig> getChildren() {
        return children;
    }

    public void setChildren(List<PrivConditionConfig> children) {
        this.children = children;
    }

    public AccountPrivRelation getAccountPrivRelations() {
        return accountPrivRelations;
    }

    public void setAccountPrivRelations(AccountPrivRelation accountPrivRelations) {
        this.accountPrivRelations = accountPrivRelations;
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
