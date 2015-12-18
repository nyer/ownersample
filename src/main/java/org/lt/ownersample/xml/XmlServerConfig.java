package org.lt.ownersample.xml;

import org.aeonbits.owner.Accessible;

public interface XmlServerConfig extends Accessible {
	
	@Key("server.http.port")
    int httpPort();

    @Key("server.http.hostname")
    String httpHostname();

    @Key("server.ssh.port")
    int sshPort();

    @Key("server.ssh.address")
    String sshAddress();

    @Key("server.ssh.alive.interval")
    int aliveInterval();

    @Key("server.ssh.user")
    String sshUser();
	
}
