package org.lt.ownersample.accessible;


import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:org/lt/ownersample/basic/ServerConfig.properties"})
public interface AccessibleServerConfig extends Accessible {
	
	int port();
	
	String hostname();
	
	int maxThreads();
}
