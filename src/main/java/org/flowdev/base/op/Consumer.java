package org.flowdev.base.op;

import org.flowdev.base.Port;

/**
 * Base class for simple operations with a single input and a single output of
 * the same type.
 */
public abstract class Consumer<T, C> extends Configure<C> {
    private Port<T> in = new Port<T>() {
	@Override
	public void send(T data) {
	    consume(data);
	}
    };

    protected abstract void consume(T data);

    /** Called during initialization phase. */
    public Port<T> getIn() {
	return in;
    }
}
