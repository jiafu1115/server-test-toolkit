package com.googlecode.test.toolkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.googlecode.test.toolkit.common.Handle;

/**
 * @author fu.jian date Jul 26, 2012
 */
public final class CollectionUtil {

    public static <T> List<T> toList(T atLeastOneT, T... otherTs) {
        ArrayList<T> allTs = new ArrayList<T>(Arrays.asList(otherTs));
        allTs.add(atLeastOneT);

        return allTs;
    }

    public static <K, OldV, NewV> Map<K, NewV> processMapValue(Map<K, OldV> map, Handle<OldV, NewV> handle) {
        Map<K, NewV> covertedResult = new HashMap<K, NewV>(map.size());

        Set<Entry<K, OldV>> entrySet = map.entrySet();
        for (Entry<K, OldV> entry : entrySet) {
            covertedResult.put(entry.getKey(), handle.process(entry.getValue()));
        }
        return covertedResult;
    }

    public static <K> Map<K, String> trimMapValue(Map<K, String> map) {
        Map<K, String> covertedResult = new HashMap<K, String>(map.size());

        Set<Entry<K, String>> entrySet = map.entrySet();
        for (Entry<K, String> entry : entrySet) {
            covertedResult.put(entry.getKey(), entry.getValue().trim());
        }
        return covertedResult;
    }

    public static <K> Map<K, Long> convertMapValueToLong(Map<K, String> map) {
        Map<K, Long> covertedResult = new HashMap<K, Long>(map.size());

        Set<Entry<K, String>> entrySet = map.entrySet();
        for (Entry<K, String> entry : entrySet) {
            covertedResult.put(entry.getKey(), Long.valueOf(entry.getValue().trim()));
        }
        return covertedResult;
    }

    /**
     * merge map's value to string
     *
     * @param map
     * @return
     */
    public static <T> String mergeValues(Map<T, String> map) {
        Collection<String> values = map.values();

        StringBuffer stringBuffer = new StringBuffer();
        for (String value : values) {
            stringBuffer.append(value);
        }

        return stringBuffer.toString();
    }

    private CollectionUtil() {
    }

}
