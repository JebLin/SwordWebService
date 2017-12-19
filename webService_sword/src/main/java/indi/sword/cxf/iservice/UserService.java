package indi.sword.cxf.iservice;


import indi.sword.cxf.bean.Cat;
import indi.sword.cxf.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @Decription
 * @Author: rd_jianbin_lin
 * @Date : 2017/12/16 15:21
 */
public interface UserService {

    List<Cat> getCatsByUser(User user);

    Map<String, Cat> getAllCats();
}
