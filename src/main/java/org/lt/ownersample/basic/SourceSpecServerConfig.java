package org.lt.ownersample.basic;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:org/lt/ownersample/basic/ServerConfig.properties"})
public interface SourceSpecServerConfig extends Config {
	
	int port();
	
	String hostname();
	
	int maxThreads();
}
