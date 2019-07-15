package org.walkmod.quickstart.refactor;

import java.io.File;

public class Bar {

    public void hello(File file) {
        Bar bar = new Bar();
        bar.open(file);
        Resource res = bar.getResource();
        if (res.isOpen()) {
            // TODO some code
        }
        // TODO some code
    }

    public void open(File file) {
        // TODO some code
    }

    public Resource getResource() {
        // TODO some code
        return null;
    }
}
