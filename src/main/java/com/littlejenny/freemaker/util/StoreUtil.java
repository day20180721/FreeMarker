package com.littlejenny.freemaker.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/12/1
 */
public class StoreUtil {
    public static List<String> getStoreList() {
        List<String> storeList = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            if (i < 10) {
                storeList.add("0" + i );
            } else {
                storeList.add(i + "");
            }

        }
        storeList.remove(15 + "");

        for (int i = 30; i < 40; i++) {
            storeList.add(i + "");
        }
        storeList.remove(35 + "");
        storeList.remove(38 + "");
        storeList.add(66 + "");
        storeList.add(67 + "");
        storeList.add(68 + "");
        storeList.add(69 + "");
        storeList.add(96 + "");
        return storeList;
    }
}
