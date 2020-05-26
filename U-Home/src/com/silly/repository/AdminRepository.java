package com.silly.repository;

import com.silly.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
