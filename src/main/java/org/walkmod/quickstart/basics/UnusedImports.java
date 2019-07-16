package org.walkmod.quickstart.basics;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;;

public class UnusedImports {

    public String convertString(String str, String unused) {
        return str.toLowerCase();
    }
}
