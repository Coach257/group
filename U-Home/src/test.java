import com.silly.repository.LodgerRepository;
import com.silly.repository.impl.LodgerRepositoryImpl;


public class test {
    public static void main(String[] args) {
        LodgerRepository a=new LodgerRepositoryImpl();
        a.signin(123,"wang","123@qq.com","1575747","a12345");


    }
}


