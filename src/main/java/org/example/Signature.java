package org.example;


import java.util.UUID;
import java.util.function.Consumer;

public class Signature<T> extends Task<T> {
    public Consumer<T> consumer;
    public Signature(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void apply(T arg) {
        this.freeze();
        consumer.accept(arg);
    }

    @Override
    public void stamp(Visitor visitor) {
        if (visitor != null) {
            this.setHeader("in_group", (String) visitor.onSignature(this).get("in_group"));
        }
    }
}
