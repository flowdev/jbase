package org.flowdev.base.op;

import org.flowdev.base.Port;

/**
 * Base class for simple operations with a single input and no output.
 */
public abstract class Consumer<T, C> extends Configure<C> {
    private Port<T> inPort = new Port<T>() {
        @Override
        public void send(T data) {
            consume(data);
        }
    };

    protected abstract void consume(T data);

    /**
     * Called during initialization phase.
     */
    public Port<T> getInPort() {
        return inPort;
    }
}
