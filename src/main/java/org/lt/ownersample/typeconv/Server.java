package org.lt.ownersample.typeconv;

public class Server {
	
	private String hostname;
	
	private int port;

	public Server(String hostname, int port) {
		super();
		this.hostname = hostname;
		this.port = port;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return "Server [hostname=" + hostname + ", port=" + port + "]";
	}
	
	
}
