package com.silly.service.impl;

import com.silly.repository.AdminRepository;
import com.silly.repository.LodgerRepository;
import com.silly.repository.WorkerRepository;
import com.silly.repository.impl.AdminRepositoryImpl;
import com.silly.repository.impl.LodgerRepositoryImpl;
import com.silly.repository.impl.WorkerRepositoryImpl;
import com.silly.service.SignLoginService;

public class SignLoginServiceImpl implements SignLoginService {
    private static AdminRepository adminRepository=new AdminRepositoryImpl();
    private static LodgerRepository lodgerRepository=new LodgerRepositoryImpl();
    private static WorkerRepository workerRepository=new WorkerRepositoryImpl();

    @Override
    public  Object login(String username,String password,String type){
        Object object = null;
        switch (type){
            case "lodger":
                object = lodgerRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username, password);
                break;
            case "worker":
                object = workerRepository.login(username, password);
        }
        return object;
    }

    @Override
    public String signup(int Cnum, String Name, String Email, String Phone, String Code) {
        return lodgerRepository.signup(Cnum, Name, Email, Phone, Code);
    }
}
