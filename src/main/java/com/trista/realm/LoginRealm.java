package com.trista.realm;

import com.trista.pojo.Role;
import com.trista.pojo.UserLogin;
import com.trista.service.RoleServiceImpl;
import com.trista.service.UserLoginServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserLoginServiceImpl userLoginService;

    @Autowired
    private RoleServiceImpl roleService;

    /**
     *  获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     *  当调用权限验证时，就会调用此方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) getAvailablePrincipal(principalCollection);
        Role role = null;
        try {
            UserLogin userLogin = userLoginService.queryUserLoginByName(username);
            role = roleService.queryRoleById(userLogin.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> set = new HashSet<String>();
        if (role != null){
            set.add(role.getRoleName());
            info.setRoles(set);
        }

        return info;
    }

    /**
     * 在这个方法中，进行身份验证
     * login时调用
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户名
        String username = (String) authenticationToken.getPrincipal();
        //密码
        String password = new String((char[]) authenticationToken.getCredentials());

        UserLogin userLogin = null;
        try {
            userLogin = userLoginService.queryUserLoginByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userLogin == null){
            //没有该用户名
            throw new UnknownAccountException();
        }else if (!password.equals(userLogin.getPassword())){
            throw new IncorrectCredentialsException();
        }

        //身份验证通过，返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username, password, getName());
        return aInfo;
    }
}
