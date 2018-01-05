package net.dubbo.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */

@ConfigurationProperties(prefix = "spring.dubbo")
public class DubboProperties {

    private String appname;

    private String register;

    private String protocol = "dubbo";

    private int port = 20800;

    private int threads = 200;

    private String version = "";

    private String group = "";

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
