package ownersample;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.event.ReloadEvent;
import org.aeonbits.owner.event.ReloadListener;
import org.junit.Assert;
import org.junit.Test;
import org.lt.ownersample.accessible.AccessibleServerConfig;
import org.lt.ownersample.basic.ServerConfig;
import org.lt.ownersample.basic.SourceSpecServerConfig;
import org.lt.ownersample.event.EventServerConfig;
import org.lt.ownersample.imports.ImportServerConfig;
import org.lt.ownersample.meta.MetaServerConfig;
import org.lt.ownersample.mutable.MutableServerConfig;
import org.lt.ownersample.policy.PolicyFirstServerConfig;
import org.lt.ownersample.policy.PolicyMergeServerConfig;
import org.lt.ownersample.reload.HotReloadServerConfig;
import org.lt.ownersample.typeconv.TypeConvServerConfig;
import org.lt.ownersample.var.ConfigWithExpansion;
import org.lt.ownersample.xml.XmlServerConfig;

public class OwnerTest {
	
	@Test
	public void testSimple() {
		
		ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
		System.out.println(cfg.port());
	}
	
	@Test
	public void testSourceSpecConfig() {

		SourceSpecServerConfig cfg = ConfigFactory.create(SourceSpecServerConfig.class);
		System.out.println(cfg.port());
		
	}
	
	@Test
	public void testPolicyFirst() {
		
		PolicyFirstServerConfig cfg = ConfigFactory.create(PolicyFirstServerConfig.class);
		System.out.println(cfg.hostname());
		System.out.println(cfg.port());
		System.out.println(cfg.maxThreads());
	}
	

	@Test
	public void testPolicyMerge() {
		
		PolicyMergeServerConfig cfg = ConfigFactory.create(PolicyMergeServerConfig.class);
		System.out.println(cfg.hostname());
		System.out.println(cfg.port());
		System.out.println(cfg.maxThreads());
	}
	
	@Test
	public void testImportConfig() {
		
		Properties props = new Properties();
		props.setProperty("hostname", "import.foo.bar.com");
		
		ImportServerConfig cfg = ConfigFactory.create(ImportServerConfig.class, props);
		System.out.println(cfg.hostname());
		System.out.println(cfg.port());
		System.out.println(cfg.maxThreads());
	}
	
	@Test
	public void testTypeConvConfig() {
		
		TypeConvServerConfig cfg = ConfigFactory.create(TypeConvServerConfig.class);
		System.out.println(cfg.hostname());
		System.out.println(cfg.port());
		System.out.println(cfg.maxThreads());
		System.out.println(Arrays.toString(cfg.fibonacci()));
		System.out.println(cfg.ipWhiteList());
		System.out.println(Arrays.toString(cfg.outServers()));
	}
	
	@Test
	public void testVariableExpanasion() {
		
		ConfigWithExpansion cfg = ConfigFactory.create(ConfigWithExpansion.class);
		System.out.println(cfg.story());
	}
	
	@Test
	public void testHotReload() throws Exception {
		final HotReloadServerConfig cfg = ConfigFactory.create(HotReloadServerConfig.class);
		

		final Timer timer = new Timer();
		TimerTask cfgMonitor = new TimerTask() {
			
			private int max = 5;
			
			private int count;
			
			@Override
			public void run() {
				if (count ++ >= max) {
					timer.cancel();
				}
				
				System.out.println(cfg.hostname());
				System.out.println(cfg.port());
				System.out.println(cfg.maxThreads());
			}
		};
		timer.scheduleAtFixedRate(cfgMonitor, 1000, 2);
		Thread.currentThread().join();
	}
	
	@Test
	public void testMutable() {
		
		MutableServerConfig cfg = ConfigFactory.create(MutableServerConfig.class);
		System.out.println(cfg.port());
		
		cfg.setProperty("port", "111");
		System.out.println(cfg.port());
		
	}
	
	@Test
	public void testAccessible() {
		AccessibleServerConfig cfg = ConfigFactory.create(AccessibleServerConfig.class);
		
		System.out.println(cfg.getProperty("port"));
		cfg.list(System.out);
	}
	
	@Test
	public void testMetaConfig() {
		
		ConfigFactory.setProperty("package", "org/lt/ownersample/basic");
		MetaServerConfig cfg = ConfigFactory.create(MetaServerConfig.class);
		System.out.println(cfg.hostname());
		System.out.println(cfg.port());
		System.out.println(cfg.maxThreads());
	}
	
	@Test
	public void testXmlSupport() throws Exception {
		
		XmlServerConfig cfg = ConfigFactory.create(XmlServerConfig.class);
		System.out.println(cfg.httpHostname());
		System.out.println(cfg.httpPort());
		System.out.println(cfg.sshAddress());
		System.out.println(cfg.sshPort());
		System.out.println(cfg.sshUser());
		System.out.println(cfg.aliveInterval());
		
		cfg.storeToXML(System.out, "output config to xml");
	}
	
	@Test
	public void testListener() {
		
		EventServerConfig cfg = ConfigFactory.create(EventServerConfig.class);
		cfg.addReloadListener(new ReloadListener() {
			
			public void reloadPerformed(ReloadEvent event) {
				
				System.out.println("Reload happended! source: " + event.getSource());
				List<PropertyChangeEvent> events = event.getEvents();
				for (PropertyChangeEvent propertyChangeEvent : events) {
					String property = propertyChangeEvent.getPropertyName();
					Object oldValue = propertyChangeEvent.getOldValue();
					Object newValue = propertyChangeEvent.getNewValue();
					
					System.out.println("Property changed, name: " + property + ", oldValue:" + oldValue
							 + ", newValue: " + newValue);
				}
			}
		});
		
		cfg.reload();
		
		cfg.addPropertyChangeListener("hostname", new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

				Object oldValue = propertyChangeEvent.getOldValue();
				Object newValue = propertyChangeEvent.getNewValue();
				
				System.out.println("hostname changed, oldValue:" + oldValue + ", newValue:" + newValue);
			}
		});
		
		String hostname = cfg.hostname();
		// property not change, no event will fire.
		cfg.setProperty("hostname", hostname);
		
		cfg.setProperty("hostname", "foo.bar");
		
		cfg.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				
				String property = propertyChangeEvent.getPropertyName();
				Object oldValue = propertyChangeEvent.getOldValue();
				Object newValue = propertyChangeEvent.getNewValue();
				
				System.out.println("Property changed, name: " + property + ", oldValue:" + oldValue
						 + ", newValue: " + newValue);
			}
		});
		
		cfg.setProperty("port", "8080");
	}
	
	@Test
	public void testSingleton() {
		
		ServerConfig cfg1 = ConfigCache.getOrCreate(ServerConfig.class);
		ServerConfig cfg2 = ConfigCache.getOrCreate(ServerConfig.class);
		Assert.assertTrue(cfg1 == cfg2);

		
		ServerConfig cfg3 = ConfigFactory.create(ServerConfig.class);
		ServerConfig cfg4 = ConfigFactory.create(ServerConfig.class);
		Assert.assertFalse(cfg3 == cfg4);
	}
}
