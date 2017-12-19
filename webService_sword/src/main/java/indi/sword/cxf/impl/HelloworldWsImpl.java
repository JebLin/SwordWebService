package indi.sword.cxf.impl;


import indi.sword.cxf.bean.Cat;
import indi.sword.cxf.bean.User;
import indi.sword.cxf.iservice.HelloworldWsInterface;
import indi.sword.cxf.iservice.UserService;

import javax.jws.WebService;
import java.util.*;

/**
 * 重要！！
 * @Decription
 * @Author: rd_jianbin_lin
 * @Date : 2017/12/16 10:29
 */
@WebService(endpointInterface = "indi.sword.cxf.iservice.HelloworldWsInterface",
        serviceName = "HelloworldWsImpl")
public class HelloworldWsImpl implements HelloworldWsInterface {

    private UserService us;

    public void setUs(UserService us) {
        this.us = us;
    }

    @Override
    public String sayHi(String name) {
        return name + ",您好，现在时间是：" + new Date();
    }

    @Override
    public List<Cat> getCatsByUser(User user) {

        // 在实际的项目中，Web Service组件自己不会去实现业务功能
        // 它只是调用业务逻辑组件的方法来暴露Web ServiceS
        return us.getCatsByUser(user);
    }

    @Override
    public Map<String, Cat> getAllCats() {
        return us.getAllCats();
    }
}

