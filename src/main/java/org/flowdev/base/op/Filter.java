package org.flowdev.base.op;

import org.flowdev.base.Port;


/**
 * Base class for simple operations with a single input and a single output
 * of the same type.
 */
public abstract class Filter<T, C> extends Configure<C> {
	private Port<T> out;
	private Port<T> in = new Port<T>() {
		@Override
		public void send(T data) {
			T result = filter(data);

			if (result != null) {
				out.send(result);
			}
		}
	};

	/**
	 * The result is send to the output port if it isn't null.
	 * A result of null is droped.
	 */
	protected abstract T filter(T data);

	/** Called during initialization phase. */
	public Port<T> getIn() {
		return in;
	}

	/** Called during initialization phase. */
	public void setOut(Port<T> out) {
		this.out = out;
	}
}
