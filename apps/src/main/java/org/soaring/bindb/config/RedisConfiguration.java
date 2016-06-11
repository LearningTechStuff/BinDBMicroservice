package org.soaring.bindb.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfiguration {

    private String host;
    private Integer port;

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }
}
