package com.teamcos.modularsystems.functions;
public interface MyFunction<A> {
    A getValue(int x, int y, int z);
    A sum(A...values);
    A defaultValue();
    boolean shouldReturn(A sum);
}
