package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InGroupVisitor<T> implements Visitor<T> {

    public boolean inGroup = false;

    @Override
    public void onGroupStart(Group<T> group) {
        inGroup = true;
    }

    @Override
    public void onGroupEnd(Group<T> group) {
        inGroup = false;
    }

    public Map<String, List<Object>> onSignature(Signature<T> sign) {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> headers = new ArrayList<Object>();
        List<Object> group = new ArrayList <Object>();
        headers.add("in_group");
        group.add(inGroup);
        map.put("in_group", group);
        map.put("stamped_headers", headers);
        return map;
    }
}
