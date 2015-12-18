package org.lt.ownersample.var;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:org/lt/ownersample/story/Story.properties"})
public interface ConfigWithExpansion extends Config {

	String story();
}
