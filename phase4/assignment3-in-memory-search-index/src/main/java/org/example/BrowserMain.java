package org.example;

public class BrowserMain {
    public static void main(String[] args) {
        BrowserHistory bh = new BrowserHistory(5);

        // -- Basic navigation --
        bh.visit("google.com");
        bh.visit("github.com");
        bh.visit("stackoverflow.com");
        bh.visit("docs.oracle.com");
        bh.visit("baeldung.com");

        System.out.println("Current : " + bh.getCurrentPage());
        System.out.println("Back    : " + bh.getBackHistory());
        System.out.println("Forward : " + bh.getForwardHistory());

        // -- Go back --
        System.out.println("\n-- back(2) --");
        System.out.println("Now at  : " + bh.back(2));
        System.out.println("Back    : " + bh.getBackHistory());
        System.out.println("Forward : " + bh.getForwardHistory());

        // -- Go forward --
        System.out.println("\n-- forward(1) --");
        System.out.println("Now at  : " + bh.forward(1));
        System.out.println("Forward : " + bh.getForwardHistory());

        // -- New visit clears forward --
        System.out.println("\n-- visit(reddit.com) --");
        bh.visit("reddit.com");
        System.out.println("Now at  : " + bh.getCurrentPage());
        System.out.println("Forward : " + bh.getForwardHistory());

        // -- maxSize trim test --
        System.out.println("\n-- overflow test (maxSize=5) --");
        BrowserHistory small = new BrowserHistory(5);
        for (int i = 1; i <= 7; i++) small.visit("page" + i + ".com");
        System.out.println("Current : " + small.getCurrentPage());
        System.out.println("Back    : " + small.getBackHistory());

        // -- Can't go back past oldest --
        System.out.println("\n-- back(999) --");
        System.out.println("Now at  : " + small.back(999));

        // -- Can't go forward past newest --
        System.out.println("-- forward(999) --");
        System.out.println("Now at  : " + small.forward(999));
    }
}
