import com.silly.entity.Customer;
import com.silly.repository.AllUseRepository;
import com.silly.repository.LodgerRepository;
import com.silly.repository.impl.AllUseRepositoryImpl;
import com.silly.repository.impl.LodgerRepositoryImpl;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Customer> list;
        AdminService adminService=new AdminServiceImpl();
        Customer h=null;
        list=adminService.AllCustomer();
        for (Customer a:list){
            System.out.println(a.getName());
            h=a;
        }

        System.out.println("--");
        adminService.DeleteCustomer(h);

        list=adminService.AllCustomer();
        for (Customer a:list){
            System.out.println(a.getName());
        }

        adminService.AddCustomer(h);
        System.out.println("--");
        list=adminService.AllCustomer();
        for (Customer a:list){
            System.out.println(a.getName());
        }
    }
}


