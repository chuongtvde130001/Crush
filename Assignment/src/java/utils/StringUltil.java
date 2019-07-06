/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author meo
 */
public class StringUltil {

    public static List<String> getCrushList(String str) {
        List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
        return items;
    }

    public static boolean getCrushID(List<String> items, String crushId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase(crushId)) {
                return true;
            }
        }
        return false;
    }
}
