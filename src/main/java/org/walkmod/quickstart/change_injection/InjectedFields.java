package org.walkmod.quickstart.change_injection;

import javax.inject.Inject;

public class InjectedFields {

    @Inject
    private Service1 s1;
    @Inject
    private Service2 s2;

    public InjectedFields() {
        System.out.println("Something going on in constructor");
    }

    public long service() {
        return s1.hashCode() ^ s2.hashCode();
    }

}
