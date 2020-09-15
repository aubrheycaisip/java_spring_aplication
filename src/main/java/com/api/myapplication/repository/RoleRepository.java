package com.api.myapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.myapplication.emuns.RoleName;
import com.api.myapplication.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByAuthority(RoleName roleName);
}
