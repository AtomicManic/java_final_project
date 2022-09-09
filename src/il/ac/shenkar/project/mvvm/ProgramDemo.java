package il.ac.shenkar.project.mvvm;
import il.ac.shenkar.project.mvvm.view.Login;

import javax.swing.*;

public class ProgramDemo {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login ob = new Login();
                ob.start();
            }
        });
    }
}

