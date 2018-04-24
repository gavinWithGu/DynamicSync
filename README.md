#实现功能：
1. 默认基于zookeeper的服务动态注册与发现
2. 动态更新webserver的配置文件(目前支持Nginx，达到webserver反向代理+一主多从热部署功能)
3. 细节：
   目前对于服务动态注册与发现，基于zookeeper，假设第一个注册的znode节点就是master
   zk通知订阅者以后，会调用Nginx替换对应的配置文件


#配置文件说明：
###/dynamic_sync_main/src/main/resources/config.properties <br />

### Dynamci Discovery Service configuration
<br />
service.discovery.method=zookeeper                                                                               # 监听实现方式,默认zookeeper,也可以自己实现ServiceDiscoveryListener接口,定义自己的逻辑 <br />
#service.discovery.method=com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl.ZKNodeListener # 测试使用的第三方实现类全限定名 <br />
service.discovery.host=192.168.1.107:2181,192.168.1.109:2181,192.168.1.110:2181                                  # zk服务器地址 <br />
service.discovery.client.timeout=5000    <br />
service.discovery.listener.rootpath=/    <br />
service.discovery.listener.subpath=ThingWroxCluster1								 # master-slave 集群名


### Web Server config:Listen from zk node and notify dynamically to server when node changes <br />
server.type=nginx												 # 动态服务发现以后通知服务器的类型,默认是nginx，也可以自己实现IServerNotify接口,定义自己的逻辑<br />
server.reload.command=service nginx reload									 # 平滑重启服务器的命令<br />
server.config.file.path=/etc/nginx/conf.d/thingworx.conf							 #  配置文件目录<br />
server.upstream.name=thingworx_server_pool									 #  配置文件中定义的集群("Upstream" in nginx)名称<br />


#第三方集成：
1. 第三方可以实现ServiceDiscoveryListener与IServerNotify接口，自定义服务发现与通知的逻辑
   同时将实现类配置在config.properties中，系统启动时，进行类加载，使用第三方指定的类，进行服务发现与动态更新配置文件

2. 注意：实现ServiceDiscoveryListener时,需要在最后调用方法入参的回调函数 <br />
   void registerListener(INotifyCallback callback) <br />
   ... <br />
   callback.notifyCallback(); <br />
   ... <br />