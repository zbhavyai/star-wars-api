package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * Writes or appends a string into a file
 */
public class ToFile {
    /**
     * Writes/appends a string into a file
     *
     * @param filename The file in which string is to be written/appended
     * @param string   The string which is to be written/appended in filename
     * @return <code>true</code> if successful
     */
    static boolean write(String filename, String string, boolean append) {
        boolean flag = false;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, append));
            bw.write(string);
            bw.flush();
            bw.close();

            flag = true;
        }

        catch (IOException e) {
            System.err.printf("%s%n", e.getMessage());
        }

        return flag;
    }
}
