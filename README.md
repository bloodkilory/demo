##这里保存的都是一些练手用的demo.

##rest-demo项目基于spring-boot无xml配置

 -|- src-main-com.example.Application : spring-boot的启动类，包含序列化方式的配置、spring-security策略配置、
 tomcat服务器策略配置、属性文件读取策略配置。
 
 -|- spring-security简单用户名密码/角色验证demo：src-main-com.example.config.SpringSecurityUserDetailConfig;
   
 -|- jedis连接redis配置：src-main-com.example.config.LocalRedisConfig;
   *redis具体操作示例为 src-main-com.example.dao.PersonDaoImpl;
   *redis pub/sub 的消息接收器示例为 src-main-com.example.service.RedisListenerServiceImpl; 该接口的方法命名需遵循规定的命名规范，
   具体参考spring-data-redis的官方文档。
   
 -|- mongodb连接配置：src-main-com.example.config.LocalMongoConfig;
   *mongo操作示例为 src-main-com.example.dao.EmpDaoImpl; 
   这里使用了spring-data-mongo提供的orm模板来对mongodb中的document和pojo进行orm映射；
   当然这种做法在简单mongodb数据操作时还是可取的。
 
 -|- mysql连接配置：src-main-com.example.config.LocalMysqlConfig; 
   *这里使用了mybatis的注解@MapperScan(basePackages ="com.example.mapper")
   来达到无需在mapper接口中显式注入sqlSessionFactory的效果，查询直接调用mapper接口的方法即可。
   对应的mapper接口示例为 src-main-com.example.mapper.MemberMapper; 查询的具体示例为 src-main-com.example.service.MemberServiceImpl;
   
 -|- restful的demo位于：src-main-com.example.rest包下
 
 -|- netty的demo位于：src-main-com.example.netty包下
   
 -|- 文件操作工具类：src-main-com.example.util.FileUtil
   *使用三种方法对文件进行读取、写入操作。
   
##rest-demo项目中的src-test中包含了大量的数据结构、算法、Stream接口、多线程操作等的demo和测试类；

##rest-client项目是对apache httpClient http操作接口的封装，用于其他项目以rpc的方式进行调用

