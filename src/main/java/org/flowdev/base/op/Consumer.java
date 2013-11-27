package org.flowdev.base.op;

import org.flowdev.base.Port;

/**
 * Base class for simple operations with a single input and no output.
 */
public abstract class Consumer<T, C> extends BaseOp<C> {
    private Port<T> inPort = (data) -> {
        try {
            consume(data);
        } catch (Throwable t) {
            sendError(t);
        }
    };

    protected abstract void consume(T data) throws Exception;

    /**
     * Called during initialization phase.
     */
    public Port<T> getInPort() {
        return inPort;
    }
}
