package com.visitor.service;

import com.visitor.domain.Role;
import com.visitor.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.transform.sax.SAXResult;

@Service
public class RoleServiceImpl implements RoleService {

//    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);

    @Qualifier("roleRepository")
    @Autowired
    RoleRepository roleRepository;

    @Override
    public String findByRoleId(int id) {
        return roleRepository.findRoleById(2).getRole();
    }

    @Override
    public void updateRoleByRoleId(String role, int id) {
        roleRepository.updateRoleByUserId(role, id);
    }
}
