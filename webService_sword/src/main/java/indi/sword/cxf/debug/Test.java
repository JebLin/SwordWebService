package indi.sword.cxf.debug;

import indi.sword.cxf.bean.Cat;
import indi.sword.cxf.bean.User;
import indi.sword.cxf.impl.HelloworldWsImpl;
import indi.sword.cxf.impl.UserServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Decription 测试下内部方法
 * @Author: rd_jianbin_lin
 * @Date : 2017/12/16 16:03
 */
public class Test {
    public static void main(String[] args) {
        User user = new User(1,"User1","000000","深圳");
        UserServiceImpl impl = new UserServiceImpl();
        List<Cat> cats = impl.getCatsByUser(user);
        cats.forEach(System.out::println);


        System.out.println("-----------------------------------------------------");
        HelloworldWsImpl helloworldWsImpl = new HelloworldWsImpl();
        Map<String,Cat> stringCatMap = helloworldWsImpl.getAllCats();
        stringCatMap.values().forEach(System.out::println);

    }
}
