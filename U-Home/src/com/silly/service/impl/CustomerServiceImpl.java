package com.silly.service.impl;

import com.silly.entity.*;
import com.silly.repository.*;
import com.silly.repository.impl.*;
import com.silly.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private static AdminRepository adminRepository=new AdminRepositoryImpl();
    private static WorkerRepository workerRepository=new WorkerRepositoryImpl();
    private static RentRepository rentRepository=new RentRepositoryImpl();
    private static RoomRepository roomRepository=new RoomRepositoryImpl();
    private static AllUseRepository allUseRepository=new AllUseRepositoryImpl();
    private static LodgerRepository lodgerRepository=new LodgerRepositoryImpl();

    @Override
    public void AddAOrder(Customer a, Room b, boolean TimeMode, java.sql.Date Bdate,java.sql.Date Edate) {
        lodgerRepository.CreateOrder(a, b, TimeMode, Bdate,Edate);
        //将Room的当前人数加1
        b.setEmptyOrNot(b.getEmptyOrNot()+1);
        adminRepository.alterRoom(b);
    }

    @Override
    public List<Room> ShowSuitableRoom() {
        return roomRepository.FindRoom_C();
    }

    @Override
    public void MakeComplaint(Customer a, String Content) {
        lodgerRepository.MakeComplaint(Content, a);
    }

    @Override
    public void ChangeMyInfo(Customer a) {
        adminRepository.alterLodgerInfo(a);
    }

    @Override
    public List<Order> ShowMyOrder(Customer a) {
        return lodgerRepository.GetMyOrder(a);
    }

    @Override
    public List<Fix> ShowMyFix(Customer a) {
        return lodgerRepository.GetMyFix(a);
    }

    @Override
    public Worker GetWorkerByFix(Fix a) {
        return lodgerRepository.GetWorkerInfo(a);
    }

    @Override
    public void JudgeOnFix(Fix a,int star) {
        lodgerRepository.JudgeOnFix(a, star);
        Worker tmp=lodgerRepository.GetWorkerInfo(a);
        double point=tmp.getDealTime()*tmp.getScore();
        tmp.setDealTime(tmp.getDealTime()+1);
        point=(double)(point+star)/tmp.getDealTime();
        tmp.setScore(point);
        workerRepository.AlterWorkerInfo(tmp);
    }

}
