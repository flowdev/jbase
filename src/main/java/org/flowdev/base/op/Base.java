package org.flowdev.base.op;

import org.flowdev.base.Port;

public interface Base<C> {
    /**
     * Called during initialization phase.
     */
    Port<C> getConfigPort();

    /**
     * Called during initialization phase.
     */
    void setErrorPort(Port<Throwable> port);
}
