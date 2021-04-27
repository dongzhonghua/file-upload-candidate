package xyz.dsvshx.upload.config;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author dongzhonghua
 * Created on 2021-04-24
 */
@Configuration
public class NacosInstanceHostNameConfig {
    @Bean
    @Primary
    public NacosDiscoveryProperties nacosProperties() throws UnknownHostException, SocketException {
        NacosDiscoveryProperties nacosDiscoveryProperties = new NacosDiscoveryProperties();
        InetAddress localHost = InetAddress.getLocalHost();
        //域名
        String hostName = localHost.getHostName();
        //ip
        String hostAddress = localHost.getHostAddress();
        System.out.println(">>>>>>>>>>" + localHost + "," + hostName + ", " + hostAddress);
        nacosDiscoveryProperties.getMetadata().put("hostname", hostName);
        return nacosDiscoveryProperties;
    }
}
