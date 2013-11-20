package org.flowdev.base.op;

import org.flowdev.base.Port;


public abstract class Transform<T, U, C> extends Configure<C> {
	private Port<U> outPort;
	private final Port<T> inPort = new Port<T>() {
		@Override
		public void send(T data) {
			U result = transform(data);

			if (result != null) {
				outPort.send(result);
			}
		}
	};

	protected abstract U transform(T data);

	/** Called during initialization phase. */
	public Port<T> getInPort() {
		return inPort;
	}

	/** Called during initialization phase. */
	public void setOutPort(Port<U> out) {
		this.outPort = out;
	}
}
