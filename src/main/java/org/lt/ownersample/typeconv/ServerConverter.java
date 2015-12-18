package org.lt.ownersample.typeconv;

import java.lang.reflect.Method;

import org.aeonbits.owner.Converter;

public class ServerConverter implements Converter<Server>{
	
	public Server convert(Method targetMethod, String text) {
		
		String[] split = text.split(":", -1);
        String name = split[0];
        Integer port = 80;
        if (split.length >= 2)
            port = Integer.valueOf(split[1]);
        
        return new Server(name, port);
	}
}
