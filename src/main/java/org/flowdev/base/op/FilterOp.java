package org.flowdev.base.op;

import org.flowdev.base.Port;


/**
 * Base class for simple operations with a single input and a single output
 * of the same type.
 */
public abstract class FilterOp<T, C> extends BaseOp<C> implements Filter<T, C> {
    protected Port<T> outPort;
    private Port<T> inPort = data -> {
        try {
            filter(data);
        } catch (Throwable t) {
            sendError(t);
        }
    };

    /**
     * The result is send to the output port if it isn't null.
     * A result of null is droped.
     */
    protected abstract void filter(T data);

    /**
     * Called during initialization phase.
     */
    @Override
    public Port<T> getInPort() {
        return inPort;
    }

    /**
     * Called during initialization phase.
     */
    @Override
    public void setOutPort(Port<T> outPort) {
        this.outPort = outPort;
    }

    /**
     * Called during initialization phase.
     */
    public FilterOp<T, C> withOutPort(Port<T> outPort) {
        this.outPort = outPort;
        return this;
    }
}
