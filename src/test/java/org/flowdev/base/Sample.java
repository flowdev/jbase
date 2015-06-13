package org.flowdev.base;

public class Sample {
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
        // tag::connect[]
        TellNames tellNames = new TellNames();    // <1>
        SayHello sayHello = new SayHello();

        tellNames.setOutPort(sayHello.getInPort());    // <2>
        // end::connect[]
        tellNames.tell();
    }
}

