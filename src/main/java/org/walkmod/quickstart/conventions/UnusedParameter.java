package org.walkmod.quickstart.conventions;

public class UnusedParameter {

    public String convertString(String str, String unused) {
        String lower = str.toLowerCase();
        return lower;
    }
}
