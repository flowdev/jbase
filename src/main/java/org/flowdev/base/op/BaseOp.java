package org.flowdev.base.op;

import org.flowdev.base.Port;


/**
 * Base class for almost all operations since almost everything has to be configurable.
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseOp<C> {
    private volatile C config;
    private Port<Throwable> errorPort;
    private final Port<C> configPort = (data) -> {
        if (data != null) {
            configure(data);
        }
    };

    /**
     * Subclasses can override this to have complete control of config handling.
     * This implementation simply stores the new config.
     */
    protected void configure(C data) {
        config = data;
    }

    /**
     * Result should be kept in local variable if used more than once.
     */
    protected C getVolatileConfig() {
        return config;
    }

    protected void sendError(Throwable t) {
        if (errorPort == null) {
            System.err.println("ERROR: errorPort is null and Exception occured:");
            t.printStackTrace();
        } else {
            System.err.println("ERROR: BaseOp handles exception:");
            t.printStackTrace();
            errorPort.send(t);
        }
    }

    /**
     * Called during initialization phase.
     */
    public Port<C> getConfigPort() {
        return configPort;
    }

    /**
     * Called during initialization phase.
     */
    public void setErrorPort(Port<Throwable> port) {
        this.errorPort = port;
    }
}
