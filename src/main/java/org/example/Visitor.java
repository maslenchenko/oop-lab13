package org.example;

import java.util.List;
import java.util.Map;

public interface Visitor<T> {

    void onGroupStart(Group<T> group);

    void onGroupEnd(Group<T> group);

    Map<String, List<Object>> onSignature(Signature<T> sign);
}
