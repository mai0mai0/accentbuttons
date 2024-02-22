import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AccentButtonsMain {
    public static void main(String[] args) {
        JFrame menu = new JFrame();
        menu.setTitle("AccentButtons");
        final String[] accentArray = new String[] {"é","è","ê","ë","à","â","ô","û","ç"};

        for(int i = 0; i<9; i++){
            String s = accentArray[i];
            JButton test = new JButton(s);

            //add a new action listener to the new button that calls the setClipboard method with the matching string.
            test.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //change the name of the window pane to show if the letter was copied properly
                    if(setClipboard(s)){
                        menu.setTitle("Copied '" + s + "' !");
                    } else {
                        menu.setTitle("Failed to copy!");
                    }
                    delaySeconds(1);
                    menu.setTitle("AccentButtons");
                }
            });

            menu.add(test);
        }

        menu.setLayout(new GridLayout(3,3,20,20));
        menu.setSize(300,300);
        menu.setResizable(false);
        menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        menu.setVisible(true);
    }

    /**
     * setClipboard method takes in a String and sets the clipboard of the computer to that string.
     * @param str The string to copy to the clipboard
     */
    public static boolean setClipboard(String str){
        StringSelection stringSelection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        String clipboardCheck = null;
        Transferable contents = clipboard.getContents(null);
        boolean hasStringText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);

        if (hasStringText) {
            try {
                clipboardCheck = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }

        assert clipboardCheck != null;
        if(clipboardCheck.equals(str)){
            System.out.println("set clipboard to " + str);
            return true;
        } else {
            System.out.println("failed to set clipboard");
            return false;
        }
    }

    /**
     * delaySeconds method adds a delay of s seconds before executing the next line
     * @param s the number of seconds to delay
     */
    public static void delaySeconds(int s){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
