package org.lt.ownersample.typeconv;

import java.util.List;

import org.aeonbits.owner.Config;

public interface TypeConvServerConfig extends Config {
	
	int port();
	
	String hostname();
	
	int maxThreads();
	
	@Separator(";")
	int[] fibonacci();
	
	@Key("ip.whitelist")
	List<String> ipWhiteList();
	
	@ConverterClass(ServerConverter.class)
	Server[] outServers();
}
