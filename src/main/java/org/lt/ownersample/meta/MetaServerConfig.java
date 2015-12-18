package org.lt.ownersample.meta;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:${package}/ServerConfig.properties"})
public interface MetaServerConfig extends Config {
	
	int port();
	
	String hostname();
	
	int maxThreads();
	
}
