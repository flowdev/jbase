package org.flowdev.base.op;

import org.flowdev.base.Port;


@SuppressWarnings("WeakerAccess")
public abstract class Check<T, C> extends BaseOp<C> {
    private Port<T> outPort;
    private Port<T> errorOutPort;
    private final Port<T> inPort = data -> {
        if (isOk(data)) {
            outPort.send(data);
        } else {
            errorOutPort.send(data);
        }
    };

    protected abstract boolean isOk(T data);

    public Port<T> getInPort() {
        return inPort;
    }

    public void setOutPort(Port<T> outPort) {
        this.outPort = outPort;
    }

    public void setErrorOutPort(Port<T> errorOutPort) {
        this.errorOutPort = errorOutPort;
    }
}
