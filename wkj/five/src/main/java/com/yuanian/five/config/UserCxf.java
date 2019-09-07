package com.yuanian.five.config;

import com.yuanian.five.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

/**
 * @author ：mmzs
 * @date ：Created in 2019/9/6 9:22
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
public class UserCxf {
    @Autowired
    private Bus bus;

    @Autowired
    UserService userService;

    @SuppressWarnings("all")

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/user");
        return endpoint;
    }

}
