package com.silly.repository;


import com.silly.entity.Worker;

import java.util.List;

public interface WorkerRepository {
    public Worker login(String username, String password);
    public List<Worker> getWorker();
}
