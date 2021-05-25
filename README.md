# Function：
1. Base on service register and discovery offered by zookeeper by default;
2. Change web server config file dynamiclly (Currently support nginx)


# Configuration<br />
#### /dynamic_sync_main/src/main/resources/config.properties
##### Dynamci Discovery Service configuration
service.discovery.method=zookeeper    # Zookeeper by default, Also you can implement ServiceDiscoveryListener and has customer implemention <br />
#service.discovery.method=com.gavin.job.mock.dynamic.sync.listener.zookeeper.listen.listener.impl.ZKNodeListener # Full class name <br />
service.discovery.host=192.168.1.107:2181,192.168.1.109:2181,192.168.1.110:2181                                  # Zk cluster host <br />
service.discovery.client.timeout=5000    <br />
service.discovery.listener.rootpath=/    <br />
service.discovery.listener.subpath=ThingWroxCluster1								 # master-slave cluster name


##### Web Server config:Listen from zk node and notify dynamically to server when node changes <br />
server.type=nginx												 # Default notify server:nginx，Also you can implement IServerNotify and has customer implemention <br />
server.reload.command=service nginx reload									 # Hup restart command<br />
server.config.file.path=/etc/nginx/conf.d/thingworx.conf							 # Config file directory<br />
server.upstream.name=thingworx_server_pool									 #  ("Upstream" in nginx)<br />


# Third-party implemention：
1. Based on ServiceDiscoveryListener and IServerNotify interface,you can define your own dynamic discovery logic
   config.properties: define your full class name which implement ServiceDiscoveryListener and IServerNotify 

2. ServiceDiscoveryListener callback <br />
   void registerListener(INotifyCallback callback) <br />
   ... <br />
   callback.notifyCallback(); <br />
   ... <br />
