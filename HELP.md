# A Simple BI System Using Spring Boot and ClickHouse

## ClickHouse 连接信息 
### ClickHouse config.xml配置
```xml
<clickhouse>
    <logger>
        <level>trace</level>
        <log>/Users/data/clickhouse/logs/clickhouse.log</log>
        <errorlog>/Users/data/clickhouse/logs/error.log</errorlog>
        <size>500M</size>
        <count>5</count>
    </logger>
    <http_port>8123</http_port>
    <tcp_port>9009</tcp_port>
    <interserver_http_port>9000</interserver_http_port>
    <interserver_http_host>127.0.0.1</interserver_http_host>  
    <listen_host>0.0.0.0</listen_host>
    <max_connections>4096</max_connections>
    <keep_alive_timeout>300</keep_alive_timeout>
    <max_concurrent_queries>1000</max_concurrent_queries>
    <uncompressed_cache_size>8589934592</uncompressed_cache_size>
    <mark_cache_size>5368709120</mark_cache_size>

    <!-- Path to data directory, with trailing slash. -->
    <path>/Users/data/clickhouse/</path>

    <!-- Path to temporary data for processing hard queries. -->
    <tmp_path>/Users/data/clickhouse/tmp/</tmp_path>
    
    <!-- Directory with user provided files that are accessible by 'file' table function. -->
    <user_files_path>/Users/data/clickhouse/user_files/</user_files_path>


    <!-- Sources to read users, roles, access rights, profiles of settings, quotas. -->
    <user_directories>
        <users_xml>
            <!-- Path to configuration file with predefined users. -->
            <path>users.xml</path>
        </users_xml>
        <local_directory>
            <!-- Path to folder where users created by SQL commands are stored. -->
            <path>/Users/data/clickhouse/users/</path>
        </local_directory>
    </user_directories>

    <default_profile>default</default_profile>
    <default_database>default</default_database>
    <builtin_dictionaries_reload_interval>3600</builtin_dictionaries_reload_interval>
    <max_session_timeout>3600</max_session_timeout>
    <default_session_timeout>300</default_session_timeout>
    <max_table_size_to_drop>0</max_table_size_to_drop>
    <merge_tree>
        <parts_to_delay_insert>300</parts_to_delay_insert>
        <parts_to_throw_insert>600</parts_to_throw_insert>
        <max_delay_to_insert>2</max_delay_to_insert>
    </merge_tree>
    <max_table_size_to_drop>0</max_table_size_to_drop>
    <max_partition_size_to_drop>0</max_partition_size_to_drop>
</clickhouse>
```

### users.xml配置
```xml
<clickhouse>
    <profiles>
        <default>
            <max_memory_usage>10000000000</max_memory_usage>
            <use_uncompressed_cache>0</use_uncompressed_cache>
            <load_balancing>random</load_balancing>
        </default>
        <readonly>
            <max_memory_usage>10000000000</max_memory_usage>
            <use_uncompressed_cache>0</use_uncompressed_cache>
            <load_balancing>random</load_balancing>
            <readonly>1</readonly>
        </readonly>
    </profiles>
    <quotas>
        <!-- Name of quota. -->
        <default>
            <interval>
                <queries>0</queries>
                <errors>0</errors>
                <result_rows>0</result_rows>
                <read_rows>0</read_rows>
                <execution_time>0</execution_time>
            </interval>
        </default>
    </quotas>
    <users>
        <default>
            <!-- PASSWORD=$(base64 < /dev/urandom | head -c8); echo  "$PASSWORD"; echo -n  "$PASSWORD" | sha256sum | tr -d '-'   -->
            <!-- password 7Dv7Ib0g -->
            <password_sha256_hex>0c9858b4a1fb6c66d637e6b3a5e0977912c22a9d2f77e007ef7594226af409f5</password_sha256_hex>
            <networks>
                <ip>::/0</ip>
            </networks>
            <profile>default</profile>
            <quota>default</quota>
            <!-- User can create other users and grant rights to them. -->
            <access_management>1</access_management>
        </default>
        <ck>
            <password_sha256_hex>0c9858b4a1fb6c66d637e6b3a5e0977912c22a9d2f77e007ef7594226af409f5</password_sha256_hex>
            <networks>
                <ip>::/0</ip>
            </networks>
            <profile>readonly</profile>
            <quota>default</quota>
        </ck>
    </users>
</clickhouse>
```

### TCP连接
```shell
clickhouse client -h 127.0.0.1 -u default --password 7Dv7Ib0g --port 9009

ClickHouse client version 22.4.1.1.
Connecting to 127.0.0.1:9009 as user default.
Connected to ClickHouse server version 22.4.1 revision 54455.
```
### HTTP连接
port = 8123



## Maven 依赖

```xml
<!-- https://mvnrepository.com/artifact/com.clickhouse/clickhouse-jdbc -->
<dependency>
    <groupId>com.clickhouse</groupId>
    <artifactId>clickhouse-jdbc</artifactId>
    <version>0.3.2</version>
</dependency>
```


## Groovy 模板引擎
Groovy 模板页面扩展名是 .tpl























---

# Spring Boot Documents
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#boot-features-developing-web-applications)
* [Groovy Templates](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#production-ready)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#using-boot-devtools)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

