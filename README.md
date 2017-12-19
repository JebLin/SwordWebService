

# Web Service
<hr />

    webService 不是一种框架。更甚至不是一种技术。而是一种跨平台，跨语言的规范。
    
    为了解决这样的应用场景：
        不同平台、不同语言所编写的应用之间如何进行相互调用。
    
    Web Service集中解决：
        - 远程调用。
        - 跨平台调用。
        - 跨语言调用。
        
    Web Service实际中的用途：
        1、同一个公司的新、旧系统的整合。
            Linux上的JAVA应用，去调用windows平台的Delphi应用。
        2、不同公司的业务整合。
            业务的整合就要带来，不同公司系统整合。
            不同公司的系统可能存在平台、语言不同的问题。
        3、内容聚合
            一个应用需要提供天气预报、股票行情、黄金行情等
            这个内容聚合的应用，需要调用大量不同平台、不同语言编写的应用的方法。
           
    CXF
        Axis（Apache） -->    Axis 2（Apache）
        XFire --> CXF(XFire + Celtrix)
    
    XFire        (Web Service 框架）
    Celtrix        (ESB 框架）
    
    SOA（面向服务的架构）
        Service1, Service2, Service3 -- 所有组件都是“即插即用”的。
        
        IBM提倡的SOA架构：希望以“组装电脑”的方式来开发软件。
        
        1、各种提供服务的组件。（Web Service)
        2、企业服务总线（Enterprise Service Bus, ESB)
    
    CXF 号称是 SOA 框架。
    CXF(Apache)
        CXF 内置了一个 Jetty Web 服务器。
        
    
    服务器端：
        使用 CXF 开发 Web Service服务器端：
    
    /***************************/
    每个 Web Service 组件需要两个部分：接口和实现类
    /***************************/
    
    （1）开发一个 Web Service 业务接口。该接口要用 @WebService修饰
    （2）开发一个 Web Service 实现类。开发类也需要用 @WebService修饰
    （3）使用 EndPoinit类的静态方法来发布 webService
    
        a.ServerMain 启动服务器端
        b.浏览器输入：http://192.168.106.1:5202/ljb?wsdl 可看到相关参数，找到 <wsdl:import location="http://192.168.106.1:5202/ljb?wsdl=HelloworldWsInterface.wsdl" ...
        c.在新浏览器窗口输入 http://192.168.106.1/ljb?wsdl=HelloworldWsInterface.wsdl 可以看到具体参数定义
    --------------------------------------------------------------
    客户端：
        使用 CXF 开发 Web Service 客户端：
    
    （1） 调用 CXF 提供的 wsdl2java工具，根据 WSDL 文档生成相应的 JAVA代码。
          WSDL - web Servcie Definition Language
          任何语言实现了 WebService，都需要提供、并暴露 WSDL文档。
          
        a.先下载apache-cxf-3.1.4.zip
        b.在path目录下配置环境变量 D:\SOFTWARE\CODE\CXF\apache-cxf-3.1.4\bin
        c.在cmd找个目录执行命令 wsdl2java http://192.168.106.1:5202/ljb?wsdl 生成客户端代码
        注意：此处有一个异常，若你自动生成的代码的路径需要改变的话，那么就必须用下面的语句生成指定目录下的代码，否则会报错！！！
        wsdl2java -p indi.sword.util.webService._01_helloworld.client.wsdl2java -d e:/temp -verbose http://192.168.106.1:5202/ljb?wsdl
        
    （2） 找到 WSDL2JAVA所生成类中，一个继承了Service的类。该类的实例可以当成工厂来使用。
    （3） 调用 Service 子类的实例的GetXXXPort方法，返回远程 WebService的代理。
    
        
    形参、返回值：
        1、当形参、返回值类型是String、基本数据类型时，CXF肯定可以轻松地处理。
        2、当形参、返回值的类型是 JavaBean式的复合类、List集合、数组等时。CXF也可以很好的处理。
        3、其他一些像 Map、非JavaBean式的复合类，CXF是处理不了的。
    
    
    
    /***************************/    
    XML里的两个属性：
        targetNameSpace    - 相当于 JAVA 语言里的 package
        xmlns            - 相当于 JAVA 语言里的 import
        （假如支持下面格式，模拟 xmlns）
        import:u java.util.*;
        import:s java.sql.*;
        
        u.Date d = new d:Date();
        
    /***************************/
    
    WebService的三个技术基础：
        1、WSDL - web Service Definition language - Web Service定义语言。
        2、SOAP - Simple Object Access Protocol，简单对象访问协议。
        3、UDDI
        
    一次 Web Service 的调用 ，其实并不是方法调用，而是发送 SOAP 消息（即XML文档片段）
    
    对于 sayHi 操作来说,
        传入的消息是：

        <sayHi>
            <arg0>字符串</arg0>
        </sayHi>
        传出的消息是：
        <sayHiResponse>
            <arg0>字符串</arg0>
        </sayHiResponse>

    对于 getCatsByUser 操作来说，
        传入的消息是：
        <getCatsByUser>
            <arg0>
                <address>字符串</address>
                <id>整数值</id>
                <name>字符串</name>
                <password>字符串</password>
            </arg0>
        </getCatsByUser>
        传出的消息是：
        <getCatsByUserResponse>
            <return>    -- 可出现 0~N 次
                <color>字符串</color>
                <id>整数值</id>
                <name>字符串</name>
            </return>
        </getCatsByUserResponse>
        
    通俗来讲，WSDL 文档描述了 Web Service如下3个方面：
        -- WHAT  : 该web Service包含“什么”操作。
        -- HOW  : 该web Service的操作应该“怎样”调用。
        -- WHERE : 该web Service的服务地址。
    
    /***************************/
        只要得到 Web Service的WSDL文档，
        接下来的程序就可以调用 Web Service.
        
    /***************************/
    
    调用一次 Web Service的本质：
        1、客户端把调用方法参数，转换成 XML 文档片段（SOAP消息，input消息）
            -- 该文档片段必须符合 WSDL 定义的格式。
        2、通过网络、把XML文档片段传给服务器。
        3、服务器接收到XML文档片段。
        4、服务器解析 XML 文档片段，提取其中的数据。并把数据转换调用 Web Service所需要的参数值。
        5、服务器执行方法。
        6、把执行方法得到的返回值，再次转换生成为 XML文档片段（SOAP消息，output消息）
            -- 该文档片段必须符合 WSDL 定义的格式。
        7、通过网络、把XML文档片断传给客户端。
        8、客户端接收到XML文档片断。
        9、客户端解析XML文档片断，提取其中的数据。并把数据转换成 Web Service的返回值。
        
    从调用的本质来看，要一个语言支持 Web Service，唯一的要求是：该语言支持XML文档解析、生成、支持网络传输。
    
    如何处理 CXF 无法处理的类型？
    处理思路是：提供一个转换器，该转换器负责把 CXF 搞不定的类型，转换成 CXF 搞得定的类型。
        （1）使用 @XmlJavaTypeAdapter 修饰 CXF 无法处理的类型。使用该 Annotation 时，通过 value 属性指定一个转换器。
        （2）实现自己的转换器，实现转换器时，需要开发一个 CXF 搞得定的类型。
    eg.
        接口方法定义为：@XmlJavaTypeAdapter(FkXmlAdapter.class) Map<String,Cat> getAllCats();
        自定义转换器处理复杂类型：public class FkXmlAdapter extends XmlAdapter<StringCat,Map<String,Cat>>;
    
    对于 getAllCats 操作来说，
        传入的消息：
            <getAllCats>
            </getAllCats>
        传出的消息：
            <getAllCatsResponse>
                <return>
                    <entries> --可以出现 0~N 次
                        <key> String </key>
                        <value> 
                            <color> String </color>
                            <id> Integer </id>
                            <name> String </name>
                        </value>
                    </entries>
                </return>
            </getAllCatsResponse>
            
            
            
    WebService 的三个技术基础：
        - WSDL
            WebService 接口
                1.types（标准的Schema）
                2.2N的message
                3.portType -- N个Opeartion
            WebService 实现
                1.binding元素 -- N个更加详细的operation
                2.service    -- 指定 WebService 的服务器地址
                
        - SOAP(下面拦截器例子可以展示）
            根元素 Envolope
            
            Header 
                Header 是可选的。默认情况下，Header元素不是强制出现的。
                Header元素由程序员控制添加，主要用于携带一些额外的信息，比如用户名、密码等信息。
            Body
                Body 元素总是默认的。Body元素里可有两种情况
                -- 当Web Service交互正确时，Body 元素里的内容由 WSDL 控制。
                -- 当Web Service交互出错时，Body 元素的内容由 Fault 子元素控制。
            
    
    WebService 急需解决的问题：如何进行权限控制？
    
        解决的思路：服务器要求 input 消息总是携带有用户名、密码的信息。
        ---- 如果没有用户名密码信息或者信息不正确，拒绝远程调用。
    
    如果不用 CXF 等框架，SOAP消息的生成、解析都是由程序员负责的。
    （无论是添加还是提取用户名密码信息，都可由程序员的代码完成。）
    
    如果使用 CXF 框架，SOAP 消息的生成、解析都是由服务员负责的。
    
    
    ===============================
    拦截器：
        为了让程序员能访问、并修改 CXF 框架所生成的 SOAP 消息，CXF 提供了拦截器。
        
        服务器端添加拦截器
            （1）获取 Endpoint 的 publish 方法返回值
            （2）调用该方法的返回值的 getInInterceptor、getOutInterceptor 方法来获取 In、Out 拦截器列表，接下来就可以添加拦截器了。
        
        客户端添加拦截器：
            （1）获取 ClientProxy 的 getClient 方法，调用该方法以远程 Web Service的代理为参数
            （2）调用 client 对象的 GetInInterceptor、getOutInterceptor 方法来获取 In、Out 拦截器列表，接下来就可以添加拦截器了。

    ep.getInInterceptors().add(new LoggingInInterceptor(new PrintWriter(System.out))); // 服务器端 In 拦截器，输出到控制台
    ep.getOutInterceptors().add(new LoggingOutInterceptor(new PrintWriter(System.out))); // 服务器端 Out 拦截器，输出到控制台
    对于 SayHi 操作：
        传入消息（Inbound Message）：
            Headers: {
                Accept=[*/*],
                Cache-Control=[no-cache],
                connection=[keep-alive],
                Content-Length=[232],
                content-type=[text/xml;charset=UTF-8],
                Host=[192.168.106.1],
                Pragma=[no-cache],
                SOAPAction=[""],
                User-Agent=[ApacheCXF3.1.4]
            }
            Payload: （这个有点类似于 body）
                <soap: Envelopexmlns: soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap: Body>
                        <ns2: sayHixmlns: ns2="http://iservice.server._01_helloworld.webService.util.sword.indi/">
                            <arg0>许文强</arg0>
                        </ns2: sayHi>
                    </soap: Body>
                </soap: Envelope>
            
            传出消息（Outbound Message）：
            Headers: {}
            Payload:
                <soap: Envelopexmlns: soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap: Body>
                        <ns2: sayHiResponsexmlns: ns2="http://iservice.server._01_helloworld.webService.util.sword.indi/">
                            <return>许文强,您好，现在时间是：SunDec1709: 22: 33CST2017</return>
                        </ns2: sayHiResponse>
                    </soap: Body>
                </soap: Envelope>
        
    对于 getAllCats 操作：（Headers 与 Payload 不写出来了）
        传入消息（Inbound Message）：
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                <soap:Body>
                    <ns2:getAllCats xmlns:ns2="http://iservice.server._01_helloworld.webService.util.sword.indi/"/>
                </soap:Body>
            </soap:Envelope>
            
        传出消息（Outbound Message）：
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
            <soap:Body>
                <ns2:getAllCatsResponse xmlns:ns2="http://iservice.server._01_helloworld.webService.util.sword.indi/">
                    <return>
                        <entrySet>
                            <key>grafield</key>
                            <value>
                                <color>橙色</color>
                                <id>1</id>
                                <name>grafield</name>
                            </value>
                        </entrySet>
                        <entrySet>
                            <key>熊猫</key>
                            <value>
                                <color>黑白色</color>
                                <id>4</id>
                                <name>熊猫</name>
                            </value>
                        </entrySet>
                        <entrySet>
                            <key>Kitty</key>
                            <value>
                                <color>咖啡色</color>
                                <id>3</id>
                                <name>Kitty</name>
                            </value>
                        </entrySet>
                        <entrySet>
                            <key>机器猫</key>
                            <value>
                                <color>蓝色</color>
                                <id>2</id>
                                <name>机器猫</name>
                            </value>
                        </entrySet>
                    </return>
                </ns2:getAllCatsResponse>
            </soap:Body>
        </soap:Envelope>
        
    CXF拦截器的理论：
    
        自定义拦截器：
            需要继承 Interceptor 接口。实际上，我们一般会继承 AbstractPhaseIntercetor 接口。
        
    ==============================================
    
    CXF 与 Spring 的整合：
        可以在传统的 Java EE 应用的基础上添加一层 Web Service 层。
        我们可的Java EE 应用就可以对外暴露成 Web Service， 
        这样就允许任何平台、任何语言编写的程序来调用这个 Java EE 应用。
        
        在传统的ssh项目上增加 Web Service 的步骤：
        （1） 复制 CXF 的Jar 包。（最核心的6个）
        （2） 在 web.xml 配置 CXF 的核心控制器： org.apache.cxf.transport.servlet.CXFServlet
        （3） 在Spring的配置文件中导入 CXF 提供的 Schema，XML配置文件。
        （4） 在Spring配置文件中使用 jaxwx:endpoint 元素来暴露 WebService。
        （5） 如果要添加拦截器。在 jaxws:endpoint 元素里添加 inInterceptors、outInterceptors 子元素。
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    