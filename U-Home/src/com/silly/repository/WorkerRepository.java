package com.silly.repository;


import com.silly.entity.Fix;
import com.silly.entity.Worker;

import java.util.List;

public interface WorkerRepository {
    public Worker login(String username, String password);
    public List<Worker> getWorker();
    public void AlterWorkerInfo(Worker a);
    public List<Fix> MyFix(Worker worker);
    public void DealFix(int Fnum,String reply);
}
