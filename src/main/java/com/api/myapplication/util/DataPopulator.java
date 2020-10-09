package com.api.myapplication.util;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.myapplication.emuns.RoleName;
import com.api.myapplication.entity.Admin;
import com.api.myapplication.entity.Role;
import com.api.myapplication.repository.AdminRepository;
import com.api.myapplication.repository.RoleRepository;

@Service
public class DataPopulator implements InitializingBean{

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (roleRepo.count()>0) {
        } else {
        	createRoles();
        }
		if(adminRepository.getUserAdministrator().size() <= 0) {
			generateAdminUser();
		}
	}
	public void createRoles() {
        List<Role> roles = new ArrayList<>();

        Role role = new Role();
        role.setAuthority(RoleName.ADMIN);
        roles.add(role);

        Role seller = new Role();
        seller.setAuthority(RoleName.SELLER);
        roles.add(seller);
        
        Role buyer = new Role();
        buyer.setAuthority(RoleName.BUYER);
        roles.add(buyer);

        roleRepo.saveAll(roles);
    }
	public void generateAdminUser() {
		Admin user = Admin.builder()
				.firstName("Admin")
				.lastName("Admin")
				.email("admin@admin.com")
				.username("admin123")
				.password("admin123")
				.build();
		adminRepository.save(user);
	}

}
