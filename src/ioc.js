var ioc = {
    // 读取配置文件
    config : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["conf.properties"]
        }
    },
    // 数据源
    dataSource : {
        type :"com.alibaba.druid.pool.DruidDataSource",
        events : {
            depose :"close"
        },
        fields : {
            driverClassName : {java :"$config.get('db-driver')"},
            url             : {java :"$config.get('db-url')"},
            username        : {java :"$config.get('db-username')"},
            password        : {java :"$config.get('db-password')"},

            // 配置初始化大小、最小、最大
            initialSize		:1,
            minIdle			:1, 
            maxActive		:20,
            // 配置获取连接等待超时的时间
            maxWait			:60000,

            // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            timeBetweenEvictionRunsMillis	:60000,

            // 配置一个连接在池中最小生存的时间，单位是毫秒
            minEvictableIdleTimeMillis		:300000,

            validationQuery					:"SELECT 'x'",
            testWhileIdle					:true,
            testOnBorrow:false,
            testOnReturn:false,

            // 打开PSCache，并且指定每个连接上PSCache的大小
            // 如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。分库分表较多的数据库，建议配置为false。
            poolPreparedStatements:false,
            maxPoolPreparedStatementPerConnectionSize:20,

            //<!-- 配置监控统计拦截的filters -->
            filters:"stat" 
        }
    },
    // Dao
    dao : {
        type :'org.nutz.dao.impl.NutDao',
        args : [ {refer :"dataSource"}]
    }
};