package org.flowdev.base;

public class Greeter {
    // tag::connect[]
    private TellNames tellNames;    // <1>
    private SayHello sayHello;

    public Greeter() {
        tellNames = new TellNames();    // <2>
        sayHello = new SayHello();

        createConnections();
    }

    private void createConnections() {
        tellNames.setOutPort(sayHello.getInPort());    // <3>
    }
    // end::connect[]

    public void greet() {
        tellNames.tell();
    }

    // tag::TellNames[]
    public static class TellNames {
        private Port<String> outPort;    // <1>

        public void tell() {
            outPort.send("Harry");
            outPort.send("Joanne");
            outPort.send("Ron");
            outPort.send("Lily");
        }

        public void setOutPort(Port<String> outPort) {    // <2>
            this.outPort = outPort;
        }
    }
    // end::TellNames[]

    // tag::SayHello[]
    public static class SayHello {
        private Port<String> inPort = name -> {    // <1>
            System.out.println("Hello, " + name + "!");
        };

        public Port<String> getInPort() {    // <2>
            return inPort;
        }
    }
    // end::SayHello[]

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.greet();
    }
}

