package org.lt.ownersample.imports;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:org/lt/ownersample/basic/ServerConfig.properties"})
public interface ImportServerConfig extends Config {
	
	int port();
	
	String hostname();
	
	int maxThreads();
}
