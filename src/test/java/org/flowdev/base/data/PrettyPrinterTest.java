package org.flowdev.base.data;

import org.junit.Test;

import static org.flowdev.base.data.PrettyPrinter.escapeString;
import static org.flowdev.base.data.PrettyPrinter.prettyPrint;
import static org.junit.Assert.assertEquals;

public class PrettyPrinterTest {

    @Test
    public void testPrettyPrint() throws Exception {
        TestData data = new TestData();
        data.setBla(true);
        data.setBlue("Test!");
        data.setI(123);

        assertEquals("Objects aren't printed correctly.", "TestData {\n" +
                "    bla : true ;\n" +
                "    blue : \"Test!\" ;\n" +
                "    i : 123 ;\n" +
                "} ;\n", prettyPrint(data));
    }

    @Test
    public void testPrettyPrintMap() throws Exception {

    }

    @Test
    public void testPrettyPrintList() throws Exception {

    }

    @Test
    public void testPrettyPrintArray() throws Exception {

    }

    @Test
    public void testEscapeString() throws Exception {
        assertEquals("'\\' not escaped properly.", "\"\\\\\"", escapeString("\\"));
        assertEquals("'\\' not escaped properly.", "\"a]\\\\adf=af+af\\\\sfd'\"", escapeString("a]\\adf=af+af\\sfd'"));
        assertEquals("'\"' not escaped properly.", "\"\\\"\"", escapeString("\""));
        assertEquals("'\"' not escaped properly.", "\"as\\\"df\\\";;:\"", escapeString("as\"df\";;:"));
        assertEquals("Tabulator not escaped properly.", "\"\\t\"", escapeString("\t"));
        assertEquals("Tabulator not escaped properly.", "\"12\\t34\\t]]-\"", escapeString("12\t34\t]]-"));
        assertEquals("Newline not escaped properly.", "\"\\n\"", escapeString("\n"));
        assertEquals("Newline not escaped properly.", "\"12\\n34\\n]]-\"", escapeString("12\n34\n]]-"));
        assertEquals("Carriage Return not escaped properly.", "\"\\r\"", escapeString("\r"));
        assertEquals("Carriage Return not escaped properly.", "\"12\\r34\\r]]-\"", escapeString("12\r34\r]]-"));
        assertEquals("CR LF not escaped properly.", "\"\\r\\n\"", escapeString("\r\n"));
        assertEquals("CR LF not escaped properly.", "\"1\\r\\n3\\r\\n]-\"", escapeString("1\r\n3\r\n]-"));
        assertEquals("Formfeed not escaped properly.", "\"\\f\"", escapeString("\f"));
        assertEquals("Formfeed not escaped properly.", "\"12\\f34\\f]]-\"", escapeString("12\f34\f]]-"));
        assertEquals("Backspace not escaped properly.", "\"\\b\"", escapeString("\b"));
        assertEquals("Backspace not escaped properly.", "\"12\\b34\\b]]-\"", escapeString("12\b34\b]]-"));
    }

    public static class TestData {
        private boolean bla;
        private String blue;
        private int i;

        public boolean isBla() {
            return bla;
        }

        public void setBla(boolean bla) {
            this.bla = bla;
        }

        public String getBlue() {
            return blue;
        }

        public void setBlue(String blue) {
            this.blue = blue;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}
