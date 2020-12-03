package com.trista.mapper;

import com.trista.pojo.Course;
import com.trista.pojo.Role;

import java.util.List;

public interface RoleMapper {
    //增加角色
    int addRole(Role role);

    //根据id删除角色
    int deleteRoleById(int roleID);

    //修改角色
    int updateRole(Role role);

    //根据id查询角色
    Role queryRoleById(int roleID);

    //查询全部角色
    List<Role> queryAllRoles();
}
