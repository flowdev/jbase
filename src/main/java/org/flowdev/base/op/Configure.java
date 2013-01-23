package org.flowdev.base.op;

import org.flowdev.base.Port;


/**
 * Base class for almost all operations since almost everything has to be configurable.
 */
public abstract class Configure<C> {
	private volatile C conf;
	private final Port<C> config = new Port<C>() {
		@Override
		public void send(C data) {
			if (data != null) {
				configure(data);
			}
		}
	};

	/**
	 * Subclasses can override this to have complete control of config handling.
	 * This implementation simply calls mergeConfig.
	 */
	protected void configure(C data) {
		conf = mergeConfig(data, conf);
	}

	/**
	 * Subclasses can override this to merge configurations.
	 * This implementation simply returns newConfig.
	 * <strong>ATTENTION: The currentConfig is still active during the merge!</strong>
	 */
	protected C mergeConfig(C newConfig, C currentConfig) {
		// subclasses should override this
		return newConfig;
	}

	/** Called during initialization phase. */
	public Port<C> getConfig() {
		return config;
	}

	/** Result should be kept in local variable if used more than once. */
	protected C getVolatileConfig() {
		return conf;
	}
}
