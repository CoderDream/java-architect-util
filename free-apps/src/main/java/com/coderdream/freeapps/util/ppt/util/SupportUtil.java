package com.coderdream.freeapps.util.ppt.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SupportUtil {

    /**
     * 获取 mapList 中指定 key 值的最大 num 个值的索引列表，数值比较
     *
     * @param sourMaps
     * @param keyWord
     * @param num
     * @return
     */
    public static List<Integer> getMaxValueIndexList(List<Map<String, Object>> sourMaps, String keyWord, int num) {
        // 创建一个临时 maps
        List<Map<String, Object>> tempMaps = getMapsWithIndex(sourMaps, "mapIndex");

        Collections.sort(tempMaps, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                BigDecimal val1 = new BigDecimal(nullToDefault(o1.get(keyWord), "0"));
                BigDecimal val2 = new BigDecimal(nullToDefault(o2.get(keyWord), "0"));
                return val2.compareTo(val1);    // 降序
            }
        });

        return new ArrayList<Integer>() {{
            addAll(getIntegerListFromMap(tempMaps, "mapIndex", "0").subList(0, num));
        }};
    }

    /**
     * 获取 mapList 中指定键的值中最大几个值的新 mapList
     *
     * @param sourMaps
     * @param keyWord
     * @param num
     * @return
     */
    public static List<Map<String, Object>> getMaxValueList(List<Map<String, Object>> sourMaps, String keyWord,
        int num) {
        List<Integer> indexForMax = getMaxValueIndexList(sourMaps, keyWord, 2);
        return getListByIndex(sourMaps, indexForMax);
    }

    /**
     * 获取 mapList 中指定 key 值的最小 num 个值的索引列表，数值比较
     *
     * @param sourMaps
     * @param keyWord
     * @param num
     * @return
     */
    public static List<Integer> getMinValueIndexList(List<Map<String, Object>> sourMaps, String keyWord, int num) {
        // 创建一个临时 maps
        List<Map<String, Object>> tempMaps = getMapsWithIndex(sourMaps, "mapIndex");

        Collections.sort(tempMaps, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                BigDecimal val1 = new BigDecimal(nullToDefault(o1.get(keyWord), "0"));
                BigDecimal val2 = new BigDecimal(nullToDefault(o2.get(keyWord), "0"));
                return val1.compareTo(val2);    // 升序
            }
        });

        return new ArrayList<Integer>() {{
            addAll(getIntegerListFromMap(tempMaps, "mapIndex", "0").subList(0, num));
        }};
    }

    /**
     * 获取 mapList 中指定 key 值的最小 num 个值的新列表
     *
     * @param sourMaps
     * @param keyWord
     * @param num
     * @return
     */
    public static List<Map<String, Object>> getMinValueList(List<Map<String, Object>> sourMaps, String keyWord,
        int num) {
        List<Integer> indexForMax = getMinValueIndexList(sourMaps, keyWord, 2);
        return getListByIndex(sourMaps, indexForMax);
    }

    /**
     * 获取 mapList 中指定键的值列表中大于 value 条件的值，数值比较
     *
     * @param sourMaps
     * @param keyWord
     * @param value
     * @return
     */
    public static List<Integer> getGtValIndexList(List<Map<String, Object>> sourMaps, String keyWord, String value,
        Boolean isEqual) {
        // 创建一个临时 maps
        List<Map<String, Object>> tempMaps = getMapsWithIndex(sourMaps, "mapIndex");

        List<Integer> indexList = new ArrayList<Integer>();
        for (Map<String, Object> tempMap : tempMaps) {
            BigDecimal bigVal1 = new BigDecimal(value);
            BigDecimal bigVal2 = new BigDecimal(nullToDefault(tempMap.get(keyWord), "0"));
            if (isEqual) {
                if (bigVal1.compareTo(bigVal2) <= 0) {
                    indexList.add(Integer.parseInt(tempMap.get("mapIndex").toString()));
                }
            } else {
                if (bigVal1.compareTo(bigVal2) < 0) {
                    indexList.add(Integer.parseInt(tempMap.get("mapIndex").toString()));
                }
            }
        }

        return indexList;
    }

    /**
     * 从 sourMaps 中获取指定键的值大于 value 的值的列表
     *
     * @param sourMaps
     * @param keyWord
     * @param value
     * @param isEqual
     * @return
     */
    public static List<Map<String, Object>> getGtValList(List<Map<String, Object>> sourMaps, String keyWord,
        String value, Boolean isEqual) {
        List<Integer> gtValIndexList = getGtValIndexList(sourMaps, keyWord, value, isEqual);
        return getListByIndex(sourMaps, gtValIndexList);
    }

    /**
     * 获取 mapList 中指定键的值列表中小于 value 条件的值，数值比较
     *
     * @param sourMaps
     * @param keyWord
     * @param value
     * @return
     */
    public static List<Integer> getLtValIndexList(List<Map<String, Object>> sourMaps, String keyWord, String value,
        Boolean isEqual) {
        // 创建一个临时 maps
        List<Map<String, Object>> tempMaps = getMapsWithIndex(sourMaps, "mapIndex");

        List<Integer> indexList = new ArrayList<Integer>();
        for (Map<String, Object> tempMap : tempMaps) {
            BigDecimal bigVal1 = new BigDecimal(value);
            BigDecimal bigVal2 = new BigDecimal(nullToDefault(tempMap.get(keyWord), "0"));
            if (isEqual) {
                if (bigVal1.compareTo(bigVal2) >= 0) {
                    indexList.add(Integer.parseInt(tempMap.get("mapIndex").toString()));
                }
            } else {
                if (bigVal1.compareTo(bigVal2) > 0) {
                    indexList.add(Integer.parseInt(tempMap.get("mapIndex").toString()));
                }
            }
        }

        return indexList;
    }

    /**
     * 从 sourMaps 中获取指定键的值小于 value 的值的列表
     *
     * @param sourMaps
     * @param keyWord
     * @param value
     * @param isEqual
     * @return
     */
    public static List<Map<String, Object>> getLtValList(List<Map<String, Object>> sourMaps, String keyWord,
        String value, Boolean isEqual) {
        List<Integer> ltValIndexList = getLtValIndexList(sourMaps, keyWord, value, isEqual);
        return getListByIndex(sourMaps, ltValIndexList);
    }

    /**
     * 获取 mapList 中指定键的值与 value 相等的值，数值比较
     *
     * @param sourMaps
     * @param keyWord
     * @param value
     * @return
     */
    public static List<Integer> getEqValIndexList(List<Map<String, Object>> sourMaps, String keyWord, String value) {
        // 创建一个临时 maps
        List<Map<String, Object>> tempMaps = getMapsWithIndex(sourMaps, "mapIndex");

        List<Integer> indexList = new ArrayList<Integer>();
        for (Map<String, Object> tempMap : tempMaps) {
            BigDecimal bigVal1 = new BigDecimal(value);
            BigDecimal bigVal2 = new BigDecimal(nullToDefault(tempMap.get(keyWord), "0"));
            if (bigVal1.compareTo(bigVal2) == 0) {
                indexList.add(Integer.parseInt(tempMap.get("mapIndex").toString()));
            }
        }

        return indexList;
    }

    /**
     * 从 sourMaps 中获取指定键的值和 value 的值相等的列表
     *
     * @param sourMaps
     * @param keyWord
     * @param value
     * @return
     */
    public static List<Map<String, Object>> getEqValList(List<Map<String, Object>> sourMaps, String keyWord,
        String value) {
        List<Integer> eqValIndexList = getEqValIndexList(sourMaps, keyWord, value);
        return getListByIndex(sourMaps, eqValIndexList);
    }

    /**
     * 从 mapList 中根据获取指定 key 列表
     *
     * @param maps
     * @param keyWord
     * @param defaultValue
     * @return
     */
    public static List<String> getStringListFromMap(List<Map<String, Object>> maps, String keyWord,
        String defaultValue) {
        List<String> results = new ArrayList<String>();
        for (Map<String, Object> map : maps) {
            results.add(nullToDefault(map.get(keyWord), "0"));
        }
        return results;
    }

    /**
     * 从 mapList 中根据获取指定 key 列表
     *
     * @param maps
     * @param keyWord
     * @param defaultValue
     * @return
     */
    public static List<Integer> getIntegerListFromMap(List<Map<String, Object>> maps, String keyWord,
        String defaultValue) {
        List<Integer> results = new ArrayList<Integer>();
        for (Map<String, Object> map : maps) {
            results.add(Integer.parseInt(nullToDefault(map.get(keyWord), "0")));
        }
        return results;
    }

    /**
     * 空对象转换成默认值
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String nullToDefault(Object obj, String defaultValue) {
        return obj == null ? defaultValue : "".equals(obj.toString()) ? defaultValue : obj.toString();
    }

    /**
     * 根据提供的索引列表从 mapList 中提取出新的 mapList
     *
     * @param objList
     * @param indexList
     * @return
     */
    public static List<Map<String, Object>> getListByIndex(List<Map<String, Object>> objList, List<Integer> indexList) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < indexList.size(); i++) {
            results.add(objList.get(indexList.get(i)));
        }
        return results;
    }

    // 在 maps 中添加一个键当做索引（唯一），返回带索引的 maps
    private static List<Map<String, Object>> getMapsWithIndex(List<Map<String, Object>> maps, String indexKey) {
        // 创建一个临时 maps
        List<Map<String, Object>> tempMaps = new ArrayList<Map<String, Object>>() {{
            addAll(maps);
        }};

        for (int i = 0; i < tempMaps.size(); i++) {
            tempMaps.get(i).put(indexKey, i);
        }

        return tempMaps;
    }
}
