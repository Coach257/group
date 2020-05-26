package com.silly.repository;


import com.silly.entity.Worker;

public interface WorkerRepository {
    public Worker login(String username, String password);
}
