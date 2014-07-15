package org.flowdev.base.op;

import org.flowdev.base.Port;
import org.flowdev.base.data.NoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class CheckTest {
    private static class Data {
        boolean b;
    }

    private static class MyCheck extends Check<Data, NoConfig> {
        @Override
        protected boolean isOk(Data data) {
            return data.b;
        }
    }

    private static Port<Data> successPort = data -> {
    };
    private static Port<Data> failPort = data -> fail("Wrong port! Data.b: " + data.b);

    @Parameterized.Parameters
    public static Collection<?> checkPorts() {
        return Arrays.asList(new Object[][]{
                {true, successPort, failPort},
                {false, failPort, successPort}
        });
    }

    private Check<Data, NoConfig> op;
    private boolean isOk;

    public CheckTest(boolean isOk, Port<Data> outPort, Port<Data> errPort) {
        this.isOk = isOk;

        this.op = new MyCheck();
        op.setOutPort(outPort);
        op.setErrorOutPort(errPort);
    }

    @Test
    public void test() {
        Data data = new Data();
        data.b = this.isOk;
        this.op.getInPort().send(data);
    }

}
