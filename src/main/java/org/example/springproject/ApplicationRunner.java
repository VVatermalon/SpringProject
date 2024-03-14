package org.example.springproject;

import org.example.springproject.server.TomcatServer;

public class ApplicationRunner {
    public static void main(String[] args) {
        new TomcatServer().start();
    }
}
