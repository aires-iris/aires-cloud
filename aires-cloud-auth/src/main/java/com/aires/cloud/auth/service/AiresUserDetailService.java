package com.aires.cloud.auth.service;

import com.aires.cloud.auth.manager.UserManager;
import com.aires.cloud.common.entity.AiresUser;
import com.aires.cloud.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 樊正祥
 */
@Service
public class AiresUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManager userManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SystemUser systemUser = userManager.findByName(username);
        if (systemUser!=null){
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus())){
                notLocked = true;
            }
            AiresUser authUser = new AiresUser(systemUser.getUsername(),
                    systemUser.getPassword(),
                    true,
                    true,
                    true,
                    notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        }else {
            throw new UsernameNotFoundException("");
        }

    }
}
