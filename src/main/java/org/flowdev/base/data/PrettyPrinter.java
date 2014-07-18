package org.flowdev.base.data;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Utility class for printing data and config objects. This is useful for
 * 'toString()' methods, debugging, ...
 */
public final class PrettyPrinter {
    final static String INDENT = "    ";
    final static String NL = System.lineSeparator();
    final static String NULL = "NULL";

    private static class Entry implements Comparable<Entry> {
        private String name;
        private Object value;

        private Entry(String nam, Object val) {
            name = (nam == null) ? NULL : nam;
            value = val;
        }

        public int compareTo(Entry e) {
            return name.compareTo(e.name);
        }
    }

    /**
     * Prevent instantiation.
     */
    private PrettyPrinter() {
    }

    public static String prettyPrint(Object obj) {
        return prettyPrintEntry("", new StringBuilder(4096), "", "", obj).toString();
    }

    private static StringBuilder prettyPrintObject(String indentation, StringBuilder buf, Object obj) {
        if (obj == null) {
            buf.append(NULL);
        } else {
            buf.append(obj.getClass().getSimpleName()).append(" {").append(NL);

            prettyPrintEntries(indentation + INDENT, buf,
                    methodsToEntries(obj.getClass().getMethods(), obj));

            buf.append(indentation).append("}");
        }
        return buf;
    }

    private static List<Entry> methodsToEntries(Method[] methods, Object obj) {
        List<Entry> entries = new ArrayList<>(methods.length);

        for (Method method : methods) {
            if (isGetter(method)) {
                entries.add(new Entry(getEntryName(method), getValueOfGetter(method, obj)));
            }
        }

        return entries;
    }

    private static boolean isGetter(Method method) {
        String name = method.getName();
        return method.getParameterCount() <= 0 && ((name.startsWith("get") && !name.equals("getClass")) || name.startsWith("is"));
    }

    private static String getEntryName(Method getter) {
        String name = getter.getName();
        if (name.startsWith("get")) {
            return cutPrefix(name, 3);
        } else {
            return cutPrefix(name, 2);
        }
    }

    private static String cutPrefix(String name, int prefixLen) {
        String subName = name.substring(prefixLen);
        return subName.substring(0, 1).toLowerCase() + subName.substring(1);
    }

    private static Object getValueOfGetter(Method method, Object obj) {
        try {
            return method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static StringBuilder prettyPrintEntries(String indentation, StringBuilder buf, List<Entry> entries) {
        Collections.sort(entries);

        for (Entry entry : entries) {
            prettyPrintEntry(indentation, buf, entry.name, " : ", entry.value);
        }

        return buf;
    }

    private static StringBuilder prettyPrintEntry(String indentation,
                                                  StringBuilder buf, String name, String relation, Object value) {

        String type = (value == null) ? "" : value.getClass().getName();

        buf.append(indentation).append(name).append(relation);

        if (value == null) {
            buf.append(NULL);
        } else if (value.getClass().isEnum() || value.getClass().isPrimitive()) {
            buf.append(value.toString());
        } else if (value instanceof Map) {
            prettyPrintMap(indentation, buf, (Map<?, ?>) value);
        } else if (value instanceof List) {
            prettyPrintList(indentation, buf, (List<?>) value);
        } else if (value.getClass().isArray()) {
            prettyPrintArray(indentation, buf, value);
        } else {
            switch (type) {
                case "java.lang.Boolean":
                case "java.lang.Byte":
                case "java.lang.Character":
                case "java.lang.Short":
                case "java.lang.Integer":
                case "java.lang.Long":
                case "java.lang.Float":
                case "java.lang.Double":
                    buf.append(value.toString());
                    break;
                case "java.lang.String":
                    buf.append(escapeString(value.toString()));
                    break;
                default:
                    prettyPrintObject(indentation, buf, value);
                    break;
            }
        }

        buf.append(" ;").append(NL);
        return buf;
    }

    private static StringBuilder prettyPrintMap(String indentation, StringBuilder buf, Map<?, ?> map) {
        buf.append("Map {").append(NL);

        prettyPrintEntries(indentation + INDENT, buf, mapToEntries(map));

        buf.append(indentation).append("}");
        return buf;
    }

    private static List<Entry> mapToEntries(Map<?, ?> map) {
        List<Entry> entries = new ArrayList<>(map.size());

        for (Object key : map.keySet()) {
            entries.add(new Entry(escapeString(key.toString()), map.get(key)));
        }

        return entries;
    }

    private static StringBuilder prettyPrintList(String indentation, StringBuilder buf, List<?> list) {
        return prettyPrintListType(indentation, buf, "List", list, list.size(), (obj, i) -> ((List) obj).get(i));
    }

    private static StringBuilder prettyPrintArray(String indentation, StringBuilder buf, Object array) {
        return prettyPrintListType(indentation, buf, "Array", array, Array.getLength(array), Array::get);
    }

    private static StringBuilder prettyPrintListType(String indentation, StringBuilder buf,
                                                     String type, Object list, int N, ListAccessor accessor) {
        String innerIndentation = indentation + INDENT;

        buf.append(type).append(" [").append(NL);

        for (int i = 0; i < N; i++) {
            prettyPrintEntry(innerIndentation, buf, "" + i, " : ", accessor.get(list, i));
        }

        buf.append(indentation).append("]");
        return buf;
    }

    public static String escapeString(String s) {
        return "\""
                + s.replace("\\", "\\\\").replace("\t", "\\t").replace("\"", "\\\"")
                .replace("\r", "\\r").replace("\n", "\\n")
                .replace("\f", "\\f").replace("\b", "\\b")
                + "\"";
    }

    public interface ListAccessor {
        Object get(Object list, int i);
    }
}
