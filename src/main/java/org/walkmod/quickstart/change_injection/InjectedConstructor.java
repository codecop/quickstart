package org.walkmod.quickstart.change_injection;

import javax.inject.Inject;

public class InjectedConstructor {

    private Service1 s1;
    private Service2 s2;

    @Inject
    public InjectedConstructor(Service1 s1, Service2 s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public long service() {
        return s1.hashCode() ^ s2.hashCode();
    }
}
