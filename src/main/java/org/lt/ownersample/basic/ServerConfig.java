package org.lt.ownersample.basic;

import org.aeonbits.owner.Config;

public interface ServerConfig extends Config {
	
	int port();
	
	String hostname();
	
	@Key("maxThreads")
	int maxThreads();
	
}
