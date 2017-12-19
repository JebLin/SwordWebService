package indi.sword.cxf.iservice;


import indi.sword.cxf.bean.Cat;
import indi.sword.cxf.bean.User;
import indi.sword.cxf.util.FkXmlAdapter;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Map;

/**
 * 重要！！
 * @Decription
 * @Author: rd_jianbin_lin
 * @Date : 2017/12/16 10:24
 */
@WebService
public interface HelloworldWsInterface {

    // 简单测试字符串 输入输出
    String sayHi(String name);

    // 测试对象的输入输出
    List<Cat> getCatsByUser(User user);

    // CXF 不能处理 Map<String,Cat> 类型，于是我们采用了 FKXmlAdapter 进行处理。
    @XmlJavaTypeAdapter(FkXmlAdapter.class) Map<String,Cat> getAllCats();
}

