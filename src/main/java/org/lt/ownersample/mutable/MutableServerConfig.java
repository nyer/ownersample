package org.lt.ownersample.mutable;

import org.aeonbits.owner.Mutable;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:org/lt/ownersample/basic/ServerConfig.properties"})
public interface MutableServerConfig extends Mutable {
	
	int port();
	
	String hostname();
	
	int maxThreads();
}
