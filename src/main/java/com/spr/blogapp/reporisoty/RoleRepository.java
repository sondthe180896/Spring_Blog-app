package com.spr.blogapp.reporisoty;


import com.spr.blogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByType(Role.Type type);
}
