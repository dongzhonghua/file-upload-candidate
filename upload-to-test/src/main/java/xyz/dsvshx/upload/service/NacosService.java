package xyz.dsvshx.upload.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import xyz.dsvshx.upload.model.HostInfo;

/**
 * @author dongzhonghua
 * Created on 2021-04-24
 */
@Service
public class NacosService {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosServer;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private int port;

    public List<HostInfo> getAllHostInfo() throws Exception {
        NamingService naming = NamingFactory.createNamingService(nacosServer);
        List<Instance> allInstances = naming.getAllInstances(applicationName);
        return allInstances.stream().map(instance -> new HostInfo(
                instance.getMetadata().get("hostname"),
                instance.getIp(),
                instance.getPort(),
                String.format("http://%s:%d/", instance.getMetadata().get("hostname"), port))
        ).collect(Collectors.toList());
    }
}
