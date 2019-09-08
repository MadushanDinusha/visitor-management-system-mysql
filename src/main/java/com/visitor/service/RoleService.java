package com.visitor.service;

public interface RoleService {
    String findByRoleId(int id);
    void updateRoleByRoleId(String role, int id);
}