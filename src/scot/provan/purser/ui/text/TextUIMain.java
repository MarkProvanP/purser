package scot.provan.purser.ui.text;

import scot.provan.purser.core.objects.Organisation;

/**
 * Created by Mark on 11/06/2015.
 */
public class TextUIMain {
    private Organisation org;

    public static void main(String[] args) {
        Organisation o = new Organisation();
        TextUIMain tui = new TextUIMain(o);
        tui.run();
    }

    public TextUIMain(Organisation org) {
        this.org = org;
    }

    private void run() {
        clearTerminal();

    }

    private void clearTerminal() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }
}
