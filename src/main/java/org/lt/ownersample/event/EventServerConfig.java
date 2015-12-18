package org.lt.ownersample.event;

import org.aeonbits.owner.Reloadable;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

@Sources({"classpath:org/lt/ownersample/basic/ServerConfig.properties"})
public interface EventServerConfig extends Reloadable, Mutable {
		
	int port();
	
	String hostname();
	
	int maxThreads();
}
