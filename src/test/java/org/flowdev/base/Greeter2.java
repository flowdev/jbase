package org.flowdev.base;

// tag::initReusableComponent[]
public class Greeter2 {
    private GreetingsFlow greetingsFlow;
    private OutputText<String> outputText;    // <1>

    public Greeter2() {
        greetingsFlow = new GreetingsFlow();
        OutputText.Params<String> outputTextParams = new OutputText.Params<>();    // <2>
        outputTextParams.getText = data -> data;    // <3>
        outputText = new OutputText<>(outputTextParams);    // <4>
        // end::initReusableComponent[]

        createConnections();
    }

    private void createConnections() {
        greetingsFlow.setOutPort(outputText.getInPort());
    }

    public void greet() {
        greetingsFlow.greet();
    }

    // tag::GreetingsFlow[]
    public static class GreetingsFlow {
        private TellNames tellNames;
        private SayHello sayHello;

        public GreetingsFlow() {
            tellNames = new TellNames();
            sayHello = new SayHello();

            createConnections();
        }

        private void createConnections() {
            tellNames.setOutPort(sayHello.getInPort());
        }

        public void setOutPort(Port<String> outPort) {
            sayHello.setOutPort(outPort);
        }

        public void greet() {
            tellNames.tell();
        }
    }
    // end::GreetingsFlow[]

    // tag::OutputText[]
    public static class OutputText<T> {    // <1>
        public static class Params<T> {    // <2>
            public Getter<T, String> getText;
        }

        private final Params<T> params;    // <3>
        private Port<T> inPort = this::outputText;

        public OutputText(Params<T> params) {
            this.params = params;
        }

        private void outputText(T data) {
            System.out.println(params.getText.get(data));    // <4>
        }

        public Port<T> getInPort() {
            return inPort;
        }
    }
    // end::OutputText[]

    // tag::TellNames[]
    public static class TellNames {
        private Port<String> outPort;

        public void tell() {
            outPort.send("Harry");
            outPort.send("Joanne");
            outPort.send("Ron");
            outPort.send("Lily");
        }

        public void setOutPort(Port<String> outPort) {
            this.outPort = outPort;
        }
    }
    // end::TellNames[]

    // tag::SayHello[]
    public static class SayHello {
        private Port<String> outPort;
        private Port<String> inPort = name -> outPort.send("Hello, " + name + "!");

        public Port<String> getInPort() {
            return inPort;
        }

        public void setOutPort(Port<String> outPort) {
            this.outPort = outPort;
        }
    }
    // end::SayHello[]

    public static void main(String[] args) {
        Greeter2 greeter = new Greeter2();
        greeter.greet();
    }
}

