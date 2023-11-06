package xupengzhuang.chapter13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {
    public static List<List<Integer>> subSets(List<Integer> list){

        if (list.isEmpty()){
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());
        List<List<Integer>> subans = subSets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        List<List<Integer>> result = concat(subans, subans2);
        return result;
    }

    public static List<List<Integer>> insertAll(Integer first,List<List<Integer>> lists){
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : lists){
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }

    public static List<List<Integer>> concat(List<List<Integer>> list1,List<List<Integer>> list2){
        List<List<Integer>> r = new ArrayList<>(list1);
        r.addAll(list2);
        return r;
    }
}
