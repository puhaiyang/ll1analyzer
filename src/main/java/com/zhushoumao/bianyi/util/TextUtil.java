package com.zhushoumao.bianyi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author PuHaiyang
 * @createTime 2016年6月11日 上午9:12:53
 * @email 761396462@qq.com
 * @function 字符工具类
 *
 */
public class TextUtil {
    /**
     * (3)B->aA,=Follow(B)
     *
     * @param nvSet
     * @param itemCharStr
     * @param a
     * @param expressionMap
     * @return
     */
    public static boolean containsbA(TreeSet<Character> nvSet, String itemCharStr, Character a,
                                     HashMap<Character, ArrayList<String>> expressionMap) {
        String aStr = a.toString();
        String lastStr = itemCharStr.substring(itemCharStr.length() - 1);
        if (lastStr.equals(aStr)) {
            return true;
        }
        return false;

    }

    /**
     * 形如aBb,b=空
     *
     * @param nvSet
     * @param itemCharStr
     * @param a
     * @param expressionMap
     * @return
     */
    public static boolean containsbAbIsNull(TreeSet<Character> nvSet, String itemCharStr, Character a,
                                            HashMap<Character, ArrayList<String>> expressionMap) {
        String aStr = a.toString();
        if (containsAB(nvSet, itemCharStr, a)) {
            Character alastChar = getAlastChar(itemCharStr, a);
            System.out.println("----------------+++++++++++++++++++--" + expressionMap.toString());
            ArrayList<String> arrayList = expressionMap.get(alastChar);
            if (arrayList.contains("ε")) {
                System.out.println(alastChar + "  contains('ε')" + aStr);
                return true;
            }
        }
        return false;

    }

    /**
     * 是否包含这种的字符串<Br>
     * (2)Ab,=First(b)-ε,直接添加终结符
     *
     * @param itemCharStr
     * @param a
     * @return
     */
    public static boolean containsAb(TreeSet<Character> ntSet, String itemCharStr, Character a) {
        String aStr = a.toString();
        if (itemCharStr.contains(aStr)) {
            int aIndex = itemCharStr.indexOf(aStr);
            String findStr;
            try {
                findStr = itemCharStr.substring(aIndex + 1, aIndex + 2);
            } catch (Exception e) {
                return false;
            }
            if (ntSet.contains(findStr.charAt(0))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 是否包含这种的字符串<Br>
     * (2).2Ab,=First(b)-ε
     *
     * @param itemCharStr
     * @param a
     * @return
     */
    public static boolean containsAB(TreeSet<Character> nvSet, String itemCharStr, Character a) {
        String aStr = a.toString();
        if (itemCharStr.contains(aStr)) {
            int aIndex = itemCharStr.indexOf(aStr);
            String findStr;
            try {
                findStr = itemCharStr.substring(aIndex + 1, aIndex + 2);
            } catch (Exception e) {
                return false;
            }
            if (nvSet.contains(findStr.charAt(0))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取A后的字符
     *
     * @param itemCharStr
     * @param a
     * @return
     */
    public static Character getAlastChar(String itemCharStr, Character a) {
        String aStr = a.toString();
        if (itemCharStr.contains(aStr)) {
            int aIndex = itemCharStr.indexOf(aStr);
            String findStr = "";
            try {
                findStr = itemCharStr.substring(aIndex + 1, aIndex + 2);
            } catch (Exception e) {
                return null;
            }
            return findStr.charAt(0);
        }
        return null;
    }

    /**
     * 是否为ε开始的
     *
     * @param selectExp
     * @return
     */
    public static boolean isEmptyStart(String selectExp) {
        char charAt = selectExp.charAt(0);
        if (charAt == 'ε') {
            return true;
        }
        return false;
    }

    /**
     * 是否是终结符开始的
     *
     * @param ntSet
     * @param selectExp
     * @return
     */
    public static boolean isNtStart(TreeSet<Character> ntSet, String selectExp) {
        char charAt = selectExp.charAt(0);
        if (ntSet.contains(charAt)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是非终结符开始的
     *
     * @param nvSet
     * @param selectExp
     * @return
     */
    public static boolean isNvStart(TreeSet<Character> nvSet, String selectExp) {
        char charAt = selectExp.charAt(0);
        if (nvSet.contains(charAt)) {
            return true;
        }
        return false;
    }

    /**
     * 查找产生式
     *
     * @param selectMap
     * @param peek
     *            当前Nv
     * @param charAt
     *            当前字符
     * @return
     */
    public static String findUseExp(TreeMap<Character, HashMap<String, TreeSet<Character>>> selectMap, Character peek,
                                    char charAt) {
        try {
            HashMap<String, TreeSet<Character>> hashMap = selectMap.get(peek);
            Set<String> keySet = hashMap.keySet();
            for (String useExp : keySet) {
                TreeSet<Character> treeSet = hashMap.get(useExp);
                if (treeSet.contains(charAt)) {
                    return useExp;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}