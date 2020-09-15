package com.api.myapplication.util;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.myapplication.emuns.RoleName;
import com.api.myapplication.entity.Role;
import com.api.myapplication.repository.RoleRepository;

@Service
public class DataPopulator implements InitializingBean{

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (roleRepo.count()>0) {
        } else {
        	createRoles();
        }
	}
	public void createRoles() {
        List<Role> roles = new ArrayList<>();

        Role role = new Role();
        role.setAuthority(RoleName.ADMIN);
        roles.add(role);

        Role bu = new Role();
        bu.setAuthority(RoleName.USER);
        roles.add(bu);

        roleRepo.saveAll(roles);
    }

}
