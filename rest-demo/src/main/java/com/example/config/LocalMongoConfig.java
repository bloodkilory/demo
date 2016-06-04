package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * mongodb connection config
 *
 * @author yangkun
 *         generate on 16/6/4
 */
@Configuration
@ComponentScan(basePackages = "com.example.dao")
public class LocalMongoConfig {

    @Value("${mongodb.host}")
    private String hostname;

    @Value("${mongodb.port}")
    private Integer port;

    @Value("${mongodb.db}")
    private String db;

    @Autowired
    Environment env;

    @Bean
    public MongoClient mongo() {
        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址, 副本集设置读写分离
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress1 = new ServerAddress("127.0.0.1", 27017);
        ServerAddress serverAddress2 = new ServerAddress("192.168.1.137", 27017);
        ServerAddress serverAddress3 = new ServerAddress("192.168.1.138", 27017);
        List<ServerAddress> addrs = new ArrayList<>();
        addrs.add(serverAddress1);
        addrs.add(serverAddress2);
        addrs.add(serverAddress3);

        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential credential = MongoCredential.createScramSha1Credential("root", "phoneix", "toor".toCharArray());
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);

        //配置mongodb
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(50)
                .maxWaitTime(2000).build();

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(hostname, port);

//        MongoClient mongoClient = new MongoClient(addrs, credentials, options);
//        mongoClient.setReadPreference(ReadPreference.primary());
        return mongoClient;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        SimpleMongoDbFactory factory = new SimpleMongoDbFactory(mongo(), "phoneix");
        return factory;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
