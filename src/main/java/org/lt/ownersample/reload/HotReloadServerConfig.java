package org.lt.ownersample.reload;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.Sources;

@HotReload
@Sources({"classpath:org/lt/ownersample/basic/ServerConfig.properties"})
public interface HotReloadServerConfig extends Config {
	
	int port();
	
	String hostname();
	
	int maxThreads();
}
