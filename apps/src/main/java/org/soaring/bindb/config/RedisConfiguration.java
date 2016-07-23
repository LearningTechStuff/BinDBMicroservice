package org.soaring.bindb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfiguration {

    private String host;
    private Integer port;
    private Integer ttl;

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

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Integer getTtl() {
        return ttl;
    }
}
