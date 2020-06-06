package com.silly.service.impl;

import com.silly.entity.*;
import com.silly.repository.*;
import com.silly.repository.impl.*;
import com.silly.service.AdminService;
//import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private static AdminRepository adminRepository=new AdminRepositoryImpl();
    private static WorkerRepository workerRepository=new WorkerRepositoryImpl();
    private static RentRepository rentRepository=new RentRepositoryImpl();
    private static RoomRepository roomRepository=new RoomRepositoryImpl();
    private static AllUseRepository allUseRepository=new AllUseRepositoryImpl();
    private static LodgerRepository lodgerRepository=new LodgerRepositoryImpl();

    public boolean ExistOrNot(String a,String b,String c){
        //查看表A是否存在b列的值=c，ture存在
        //暂时只适用于全部string的b，c；特殊需求再联系俺
        return allUseRepository.InOrNot(a,b,c);
    }

    //未处理申请

    public List<Order> UnsettledOrders(){
        //mode中1是未提交，2是未审核，3是未付款，4是已完成订单,可根据需求添加目标数字作为mode
        return rentRepository.GetOrder(2,-1);
    }

    public void changeUSOrders(Order a){
        //在servlet处直接更改Order实例，再作为参数塞回来，除了Onum全部可以改
        adminRepository.alterOrdersInfo(a);
    }

    //未处理投诉

    public List<Complaint> UnsettledComplaint(){
        return adminRepository.checkUndoneComplaint();
    }

    public void HaveSeenComplaint(int CoNum,String reply){
        //作为已阅的存在，建议在创立工单之前加一个已阅判定
        adminRepository.havenChecked(CoNum,reply);
    }

    public void AddFix(Fix a){
        //Fix同样在servlet处生成
        //可以在此处调用师傅显示和选择以生成Fix实例
        //建议新生成的取star=-2
        adminRepository.addOneInfo("Fix",a);
    }

    //未处理维修

    public List<Fix> UnsettledFix(){
        //此处默认新添加的star=-2
        return adminRepository.checkUndoneFix();
    }

    public void settleFixOnForce(int Wnum,int Fnum){
        //更改工单信息
        //个人想法，star=-2为设置时未处理未接单，-1为师傅接单，>=0用户评分
        adminRepository.alterFix(Wnum,Fnum);
    }

    @Override
    public void DealFix(int Fnum, String reply) {
        workerRepository.DealFix(Fnum,reply);
    }

    //租客相关

    public List<Customer> AllCustomer(){
        return lodgerRepository.GetLodger();
    }

    public void AddCustomer(Customer a){
        //与上面同理，都是在servlet内部生成实例
        adminRepository.addOneInfo("Customer",a);
    }

    public void DeleteCustomer(Customer a){
        adminRepository.deleteOneInfo("Customer",a);
    }

    public void ChangeCustomer(Customer a){
        adminRepository.alterLodgerInfo(a);
    }

    //师傅相关

    public List<Worker> AllWorker(){
        return workerRepository.getWorker();
    }

    public void AddWorker(Worker a){
        //与上面同理，都是在servlet内部生成实例
        adminRepository.addOneInfo("Worker",a);
    }

    public void DeleteWorker(Worker a){
        adminRepository.deleteOneInfo("Worker",a);
    }

    //管理合同

    public List<Order> AllOrder(){
        //mode=-1 && num=-1 全查
        return rentRepository.GetOrder(-1,-1);
    }

    public void DeleteOrder(Order a){
        adminRepository.deleteOneInfo("Orders",a);
    }
    //线下租房就是这个
    public void AddOrder(Order a){
        adminRepository.addOneInfo("Orders",a);
    }

    public void ChangeOrder(Order a){
        adminRepository.alterOrdersInfo(a);
    }

    //房子相关

    public List<Room> AllRoom(){
        return roomRepository.FindRoom_A();
    }

    public void DeleteRoom(Room a){
        adminRepository.deleteOneInfo("Room",a);
    }

    public void AddRoom(Room a){
        adminRepository.addOneInfo("Room",a);
    }

    public void ChangeRoom(Room a){
        adminRepository.alterRoom(a);
    }

    public Customer FindByCnum(int cnum)
    {
        return lodgerRepository.getbyCnum(cnum);
    }

    public Room FindByRnum(int rnum)
    {
        return roomRepository.getbyRnum(rnum);
    }

    @Override
    public List<Customer> InformList() {
        return allUseRepository.InformList();
    }

    @Override
    public List<Fix> WorkerFix(Worker worker) {
        return workerRepository.MyFix(worker);
    }
}
