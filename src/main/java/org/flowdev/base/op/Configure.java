package org.flowdev.base.op;

import org.flowdev.base.Port;


/**
 * Base class for almost all operations since almost everything has to be configurable.
 */
public abstract class Configure<C> {
	private volatile C config;
	private final Port<C> configPort = new Port<C>() {
		@Override
		public void send(C data) {
			if (data != null) {
				configure(data);
			}
		}
	};

	/**
	 * Subclasses can override this to have complete control of config handling.
	 * This implementation simply stores the new config.
	 */
	protected void configure(C data) {
		config = data;
	}

	/** Called during initialization phase. */
	public Port<C> getConfigPort() {
		return configPort;
	}

	/** Result should be kept in local variable if used more than once. */
	protected C getVolatileConfig() {
		return config;
	}
}
