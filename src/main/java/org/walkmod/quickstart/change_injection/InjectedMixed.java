package org.walkmod.quickstart.change_injection;

import javax.inject.Inject;

public class InjectedMixed {

    @Inject
    private Service1 s1;

    private Service2 s2;

    @Inject
    public InjectedMixed(Service2 s2) {
        this.s2 = s2;
        System.out.println("Something going on in constructor");
    }

    public long service() {
        return s1.hashCode() ^ s2.hashCode();
    }

}
