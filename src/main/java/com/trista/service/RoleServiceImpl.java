package com.trista.service;

import com.trista.mapper.RoleMapper;
import com.trista.pojo.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    public int deleteRoleById(int roleID) {
        return roleMapper.deleteRoleById(roleID);
    }

    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    public Role queryRoleById(int roleID) {
        return roleMapper.queryRoleById(roleID);
    }

    public List<Role> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }
}
