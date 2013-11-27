package org.flowdev.base.op;

import org.flowdev.base.Port;


/**
 * Base class for simple operations with a single input and a single output
 * of the same type.
 */
public abstract class Filter<T, C> extends BaseOp<C> {
	protected Port<T> outPort;
    private Port<T> inPort = (data) -> {
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

	/** Called during initialization phase. */
	public Port<T> getInPort() {
		return inPort;
	}

	/** Called during initialization phase. */
	public void setOutPort(Port<T> out) {
		this.outPort = out;
	}
}
