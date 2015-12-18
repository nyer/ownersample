package org.lt.ownersample.policy;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"classpath:org/lt/ownersample/policy/Env.properties",
	"classpath:org/lt/ownersample/policy/Common.properties" 
	})
@LoadPolicy(LoadType.MERGE)
public interface PolicyMergeServerConfig extends Config {
	
	int port();
	
	String hostname();
	
	int maxThreads();
}
