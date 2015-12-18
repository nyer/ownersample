package org.lt.ownersample.policy;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"classpath:org/lt/ownersample/policy/Env.properties",
	"classpath:org/lt/ownersample/policy/Common.properties" 
	})
@LoadPolicy(LoadType.FIRST)
public interface PolicyFirstServerConfig extends Config {
	
	int port();
	
	String hostname();

	@DefaultValue("-1")
	int maxThreads();
}
