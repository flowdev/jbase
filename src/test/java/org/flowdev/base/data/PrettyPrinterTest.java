package org.flowdev.base.data;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.flowdev.base.data.PrettyPrinter.escapeString;
import static org.flowdev.base.data.PrettyPrinter.prettyPrint;
import static org.junit.Assert.assertEquals;

public class PrettyPrinterTest {

    @Test
    public void testPrettyPrintSimpleObject() throws Exception {
        TestData data = buildDefaultTestData();

        assertEquals("Simple objects aren't printed correctly.", "TestData {\n" +
                "    aBoolean : false ;\n" +
                "    aByte : -128 ;\n" +
                "    aDouble : 4.9E-324 ;\n" +
                "    aFloat : 1.4E-45 ;\n" +
                "    aInteger : -2147483648 ;\n" +
                "    aLong : -9223372036854775808 ;\n" +
                "    aShort : -32768 ;\n" +
                "    aString : \"Test\\n string!\" ;\n" +
                "    aboolean : true ;\n" +
                "    abyte : 127 ;\n" +
                "    adouble : 1.7976931348623157E308 ;\n" +
                "    afloat : 3.4028235E38 ;\n" +
                "    aint : 2147483647 ;\n" +
                "    along : 9223372036854775807 ;\n" +
                "    ashort : 32767 ;\n" +
                "} ;\n", prettyPrint(data));
    }


    @Test
    public void testPrettyPrintEmptyObject() throws Exception {
        TestData data = buildDefaultTestData();
        data.setAString("");

        assertEquals("Empty objects aren't printed correctly.", "TestData {\n" +
                "    aBoolean : false ;\n" +
                "    aByte : -128 ;\n" +
                "    aDouble : 4.9E-324 ;\n" +
                "    aFloat : 1.4E-45 ;\n" +
                "    aInteger : -2147483648 ;\n" +
                "    aLong : -9223372036854775808 ;\n" +
                "    aShort : -32768 ;\n" +
                "    aString : \"\" ;\n" +
                "    aboolean : true ;\n" +
                "    abyte : 127 ;\n" +
                "    adouble : 1.7976931348623157E308 ;\n" +
                "    afloat : 3.4028235E38 ;\n" +
                "    aint : 2147483647 ;\n" +
                "    along : 9223372036854775807 ;\n" +
                "    ashort : 32767 ;\n" +
                "} ;\n", prettyPrint(data));
    }

    @Test
    public void testPrettyPrintNulls() throws Exception {
        TestData data = buildDefaultTestData();
        data.setABoolean(null);
        data.setAByte(null);
        data.setADouble(null);
        data.setAFloat(null);
        data.setAInteger(null);
        data.setALong(null);
        data.setAShort(null);
        data.setAString(null);

        assertEquals("Null objects aren't printed correctly.", "TestData {\n" +
                "    aBoolean : NULL ;\n" +
                "    aByte : NULL ;\n" +
                "    aDouble : NULL ;\n" +
                "    aFloat : NULL ;\n" +
                "    aInteger : NULL ;\n" +
                "    aLong : NULL ;\n" +
                "    aShort : NULL ;\n" +
                "    aString : NULL ;\n" +
                "    aboolean : true ;\n" +
                "    abyte : 127 ;\n" +
                "    adouble : 1.7976931348623157E308 ;\n" +
                "    afloat : 3.4028235E38 ;\n" +
                "    aint : 2147483647 ;\n" +
                "    along : 9223372036854775807 ;\n" +
                "    ashort : 32767 ;\n" +
                "} ;\n", prettyPrint(data));
    }

    @Test
    public void testPrettyPrintComplexObject() throws Exception {
        ComplexTestData data = new ComplexTestData();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "val1");
        map.put("key2", "val\f2");
        data.setTestData(buildDefaultTestData());
        data.setStrArray(new String[]{"", " ", "s", "b\rla"});
        data.setStrList(asList("1", "2", "s", "b\tla"));
        data.setStrMap(map);

        assertEquals("Complex objects aren't printed correctly.", "ComplexTestData {\n" +
                "    strArray : Array [\n" +
                "        0 : \"\" ;\n" +
                "        1 : \" \" ;\n" +
                "        2 : \"s\" ;\n" +
                "        3 : \"b\\rla\" ;\n" +
                "    ] ;\n" +
                "    strList : List [\n" +
                "        0 : \"1\" ;\n" +
                "        1 : \"2\" ;\n" +
                "        2 : \"s\" ;\n" +
                "        3 : \"b\\tla\" ;\n" +
                "    ] ;\n" +
                "    strMap : Map {\n" +
                "        \"key1\" : \"val1\" ;\n" +
                "        \"key2\" : \"val\\f2\" ;\n" +
                "    } ;\n" +
                "    testData : TestData {\n" +
                "        aBoolean : false ;\n" +
                "        aByte : -128 ;\n" +
                "        aDouble : 4.9E-324 ;\n" +
                "        aFloat : 1.4E-45 ;\n" +
                "        aInteger : -2147483648 ;\n" +
                "        aLong : -9223372036854775808 ;\n" +
                "        aShort : -32768 ;\n" +
                "        aString : \"Test\\n string!\" ;\n" +
                "        aboolean : true ;\n" +
                "        abyte : 127 ;\n" +
                "        adouble : 1.7976931348623157E308 ;\n" +
                "        afloat : 3.4028235E38 ;\n" +
                "        aint : 2147483647 ;\n" +
                "        along : 9223372036854775807 ;\n" +
                "        ashort : 32767 ;\n" +
                "    } ;\n" +
                "} ;\n", prettyPrint(data));
    }

    @Test
    public void testPrettyPrintMap() throws Exception {
        Map<String, String> simpleMap;
        assertEquals("Null maps aren't printed correctly.", "NULL ;\n", prettyPrint(null));

        simpleMap = new HashMap<>();
        assertEquals("Empty maps aren't printed correctly.", "Map {\n" +
                "} ;\n", prettyPrint(simpleMap));

        simpleMap = new HashMap<>();
        simpleMap.put("key1", "val1");
        simpleMap.put("key2", "val\f2");
        assertEquals("Simple maps aren't printed correctly.", "Map {\n" +
                "    \"key1\" : \"val1\" ;\n" +
                "    \"key2\" : \"val\\f2\" ;\n" +
                "} ;\n", prettyPrint(simpleMap));

        Map<Integer, TestData> complexMap = new HashMap<>();
        complexMap.put(-123, buildDefaultTestData());
        complexMap.put(456, null);
        assertEquals("Complex maps aren't printed correctly.", "Map {\n" +
                "    \"-123\" : TestData {\n" +
                "        aBoolean : false ;\n" +
                "        aByte : -128 ;\n" +
                "        aDouble : 4.9E-324 ;\n" +
                "        aFloat : 1.4E-45 ;\n" +
                "        aInteger : -2147483648 ;\n" +
                "        aLong : -9223372036854775808 ;\n" +
                "        aShort : -32768 ;\n" +
                "        aString : \"Test\\n string!\" ;\n" +
                "        aboolean : true ;\n" +
                "        abyte : 127 ;\n" +
                "        adouble : 1.7976931348623157E308 ;\n" +
                "        afloat : 3.4028235E38 ;\n" +
                "        aint : 2147483647 ;\n" +
                "        along : 9223372036854775807 ;\n" +
                "        ashort : 32767 ;\n" +
                "    } ;\n" +
                "    \"456\" : NULL ;\n" +
                "} ;\n", prettyPrint(complexMap));
    }

    @Test
    public void testPrettyPrintList() throws Exception {
        List<String> simpleList = emptyList();
        assertEquals("Empty lists aren't printed correctly.", "List [\n" +
                "] ;\n", prettyPrint(simpleList));

        simpleList = asList("val1", "val\b2");
        assertEquals("Simple lists aren't printed correctly.", "List [\n" +
                "    0 : \"val1\" ;\n" +
                "    1 : \"val\\b2\" ;\n" +
                "] ;\n", prettyPrint(simpleList));

        List<TestData> complexList = asList(buildDefaultTestData(), null);
        assertEquals("Complex lists aren't printed correctly.", "List [\n" +
                "    0 : TestData {\n" +
                "        aBoolean : false ;\n" +
                "        aByte : -128 ;\n" +
                "        aDouble : 4.9E-324 ;\n" +
                "        aFloat : 1.4E-45 ;\n" +
                "        aInteger : -2147483648 ;\n" +
                "        aLong : -9223372036854775808 ;\n" +
                "        aShort : -32768 ;\n" +
                "        aString : \"Test\\n string!\" ;\n" +
                "        aboolean : true ;\n" +
                "        abyte : 127 ;\n" +
                "        adouble : 1.7976931348623157E308 ;\n" +
                "        afloat : 3.4028235E38 ;\n" +
                "        aint : 2147483647 ;\n" +
                "        along : 9223372036854775807 ;\n" +
                "        ashort : 32767 ;\n" +
                "    } ;\n" +
                "    1 : NULL ;\n" +
                "] ;\n", prettyPrint(complexList));

        List<List<List<Integer>>> nestedList = asList(asList(Collections.<Integer>emptyList(), asList(1), asList(2, 3)));
        assertEquals("Nested lists aren't printed correctly.", "List [\n" +
                "    0 : List [\n" +
                "        0 : List [\n" +
                "        ] ;\n" +
                "        1 : List [\n" +
                "            0 : 1 ;\n" +
                "        ] ;\n" +
                "        2 : List [\n" +
                "            0 : 2 ;\n" +
                "            1 : 3 ;\n" +
                "        ] ;\n" +
                "    ] ;\n" +
                "] ;\n", prettyPrint(nestedList));
    }

    @Test
    public void testPrettyPrintArray() throws Exception {
        String[] simpleArray = new String[]{};
        assertEquals("Empty arrays aren't printed correctly.", "Array [\n" +
                "] ;\n", prettyPrint(simpleArray));

        simpleArray = new String[]{"val1", "val\b2"};
        assertEquals("Simple arrays aren't printed correctly.", "Array [\n" +
                "    0 : \"val1\" ;\n" +
                "    1 : \"val\\b2\" ;\n" +
                "] ;\n", prettyPrint(simpleArray));

        TestData[] complexArray = new TestData[]{buildDefaultTestData(), null};
        assertEquals("Complex arrays aren't printed correctly.", "Array [\n" +
                "    0 : TestData {\n" +
                "        aBoolean : false ;\n" +
                "        aByte : -128 ;\n" +
                "        aDouble : 4.9E-324 ;\n" +
                "        aFloat : 1.4E-45 ;\n" +
                "        aInteger : -2147483648 ;\n" +
                "        aLong : -9223372036854775808 ;\n" +
                "        aShort : -32768 ;\n" +
                "        aString : \"Test\\n string!\" ;\n" +
                "        aboolean : true ;\n" +
                "        abyte : 127 ;\n" +
                "        adouble : 1.7976931348623157E308 ;\n" +
                "        afloat : 3.4028235E38 ;\n" +
                "        aint : 2147483647 ;\n" +
                "        along : 9223372036854775807 ;\n" +
                "        ashort : 32767 ;\n" +
                "    } ;\n" +
                "    1 : NULL ;\n" +
                "] ;\n", prettyPrint(complexArray));

        int[][][] nestedArray = new int[][][]{{{}, {1}, {2, 3}}};
        assertEquals("Nested arrays aren't printed correctly.", "Array [\n" +
                "    0 : Array [\n" +
                "        0 : Array [\n" +
                "        ] ;\n" +
                "        1 : Array [\n" +
                "            0 : 1 ;\n" +
                "        ] ;\n" +
                "        2 : Array [\n" +
                "            0 : 2 ;\n" +
                "            1 : 3 ;\n" +
                "        ] ;\n" +
                "    ] ;\n" +
                "] ;\n", prettyPrint(nestedArray));
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

    private static TestData buildDefaultTestData() {
        TestData data;
        data = new TestData();
        data.setAboolean(true);
        data.setABoolean(Boolean.FALSE);
        data.setAbyte(Byte.MAX_VALUE);
        data.setAByte(Byte.MIN_VALUE);
        data.setAshort(Short.MAX_VALUE);
        data.setAShort(Short.MIN_VALUE);
        data.setAint(Integer.MAX_VALUE);
        data.setAInteger(Integer.MIN_VALUE);
        data.setAlong(Long.MAX_VALUE);
        data.setALong(Long.MIN_VALUE);
        data.setAfloat(Float.MAX_VALUE);
        data.setAFloat(Float.MIN_VALUE);
        data.setAdouble(Double.MAX_VALUE);
        data.setADouble(Double.MIN_VALUE);
        data.setAString("Test\n string!");
        return data;
    }

    @SuppressWarnings("UnusedDeclaration")
    public static class TestData {
        private boolean aboolean;
        private Boolean aBoolean;
        private byte abyte;
        private Byte aByte;
        private short ashort;
        private Short aShort;
        private int aint;
        private Integer aInteger;
        private long along;
        private Long aLong;
        private float afloat;
        private Float aFloat;
        private double adouble;
        private Double aDouble;
        private String aString;

        public boolean isAboolean() {
            return aboolean;
        }

        public void setAboolean(boolean aboolean) {
            this.aboolean = aboolean;
        }

        public Boolean getABoolean() {
            return aBoolean;
        }

        public void setABoolean(Boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public byte getAbyte() {
            return abyte;
        }

        public void setAbyte(byte abyte) {
            this.abyte = abyte;
        }

        public Byte getAByte() {
            return aByte;
        }

        public void setAByte(Byte aByte) {
            this.aByte = aByte;
        }

        public short getAshort() {
            return ashort;
        }

        public void setAshort(short ashort) {
            this.ashort = ashort;
        }

        public Short getAShort() {
            return aShort;
        }

        public void setAShort(Short aShort) {
            this.aShort = aShort;
        }

        public int getAint() {
            return aint;
        }

        public void setAint(int aint) {
            this.aint = aint;
        }

        public Integer getAInteger() {
            return aInteger;
        }

        public void setAInteger(Integer aInteger) {
            this.aInteger = aInteger;
        }

        public long getAlong() {
            return along;
        }

        public void setAlong(long along) {
            this.along = along;
        }

        public Long getALong() {
            return aLong;
        }

        public void setALong(Long aLong) {
            this.aLong = aLong;
        }

        public float getAfloat() {
            return afloat;
        }

        public void setAfloat(float afloat) {
            this.afloat = afloat;
        }

        public Float getAFloat() {
            return aFloat;
        }

        public void setAFloat(Float aFloat) {
            this.aFloat = aFloat;
        }

        public double getAdouble() {
            return adouble;
        }

        public void setAdouble(double adouble) {
            this.adouble = adouble;
        }

        public Double getADouble() {
            return aDouble;
        }

        public void setADouble(Double aDouble) {
            this.aDouble = aDouble;
        }

        public String getAString() {
            return aString;
        }

        public void setAString(String aString) {
            this.aString = aString;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static class ComplexTestData {
        private TestData testData;
        private Map<String, String> strMap;
        private List<String> strList;
        private String[] strArray;

        public TestData getTestData() {
            return testData;
        }

        public void setTestData(TestData testData) {
            this.testData = testData;
        }

        public Map<String, String> getStrMap() {
            return strMap;
        }

        public void setStrMap(Map<String, String> strMap) {
            this.strMap = strMap;
        }

        public List<String> getStrList() {
            return strList;
        }

        public void setStrList(List<String> strList) {
            this.strList = strList;
        }

        public String[] getStrArray() {
            return strArray;
        }

        public void setStrArray(String[] strArray) {
            this.strArray = strArray;
        }
    }
}
