package org.flowdev.base.op;

import org.flowdev.base.Port;


public abstract class Transform<T, U, C> extends BaseOp<C> {
    protected Port<U> outPort;
    private final Port<T> inPort = data -> {
        try {
            transform(data);
        } catch (Throwable t) {
            sendError(t);
        }
    };

    protected abstract void transform(T data) throws Exception;

    /**
     * Called during initialization phase.
     */
    public Port<T> getInPort() {
        return inPort;
    }

    /**
     * Called during initialization phase.
     */
    public void setOutPort(Port<U> out) {
        this.outPort = out;
    }

    /**
     * Called during initialization phase.
     */
    public Transform<T, U, C> withOutPort(Port<U> out) {
        this.outPort = out;
        return this;
    }
}
