package org.flowdev.base.op;

import org.flowdev.base.Port;

public interface Filter<T, C> {
    /**
     * Called during initialization phase.
     */
    Port<T> getInPort();

    /**
     * Called during initialization phase.
     */
    void setOutPort(Port<T> outPort);

    /**
     * Called during initialization phase.
     */
    Port<C> getConfigPort();

    /**
     * Called during initialization phase.
     */
    void setErrorPort(Port<Throwable> port);
}
