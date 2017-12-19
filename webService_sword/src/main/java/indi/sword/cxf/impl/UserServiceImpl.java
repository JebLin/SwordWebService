package indi.sword.cxf.impl;


import indi.sword.cxf.bean.Cat;
import indi.sword.cxf.bean.User;
import indi.sword.cxf.iservice.UserService;

import java.util.*;

public class UserServiceImpl implements UserService {

    // 用一个 HashMap 来模拟内存中的数据库
    static Map<User,List<Cat>> catDb = new HashMap<>();

    static{
        List<Cat> catList1 = new ArrayList<>();
        User user1 = new User(1,"User1","000000","深圳");
        catList1.add(new Cat(1,"grafield","橙色"));
        catList1.add(new Cat(2,"机器猫","蓝色"));
        catDb.put(user1,catList1);

        List<Cat> catList2 = new ArrayList<>();
        User user2 = new User(2,"User2","123456","北京");
        catList2.add(new Cat(3,"Kitty","咖啡色"));
        catList2.add(new Cat(4,"熊猫","黑白色"));
        catDb.put(user2,catList2);
    }

    @Override
    public List<Cat> getCatsByUser(User user) {
        List<Cat> cats = catDb.get(user);
        cats.forEach(System.out::println);
        return cats;
    }

    public  Map<String, Cat> getAllCats(){
        Map<String,Cat> stringCatMap = new HashMap<>();
        Collection<List<Cat>> coll = catDb.values();
        coll.forEach(lists ->{
            lists.forEach(cat -> {
                stringCatMap.put(cat.getName(),cat);
            });
        });
        return stringCatMap;
    }
}
