package il.ac.shenkar.project.mvvm.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * Class to Register User to the System
 */
public class Register{

    //Declaring UI vars
    private JFrame frame;
    private String title = "Sign In";
    private JLabel signLink = new JLabel(title);
    private EmptyBorder borderLabel;
    private LineBorder lineBorder;
    private CompoundBorder compoundBorder;
    private JLabel labelHeader, labelName, labelUsername, labelPass, labelPhoto, labelSigned;
    private JPanel panelTop, panelMidTop, panelMidBottom, panelMiddle, panelBottom, panelMidMidBottom, navPanel1, navPanel2;
    private JTextField tfName, tfUsername, tfPassword;
    private JButton registerBtn;
//    private IViewModel vm;

    //Constructor
    public Register() {
        //Setting UI elements
        frame = new JFrame("Cost Manager App");
//        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setLayout(new BorderLayout(5, 5));

        //Setting Panels
        panelTop = new JPanel();
        panelMidBottom = new JPanel();
        panelMidTop = new JPanel();
        panelMiddle = new JPanel();
        panelMidMidBottom = new JPanel();
        panelBottom = new JPanel();
        navPanel1 = new JPanel();
        navPanel2 = new JPanel();
        navPanel1.setPreferredSize(new Dimension(20,40));
        navPanel2.setPreferredSize(new Dimension(20,40));
        lineBorder = new LineBorder(Color.black, 1, false);
        borderLabel = new EmptyBorder(5, 0, 5, 0);
        compoundBorder = new CompoundBorder(lineBorder,borderLabel);

        //Setting Labels
        labelHeader = new JLabel("Welcome To Cost Manager!", JLabel.CENTER);
        labelName = new JLabel("Name *");
        labelName.setFont(new Font("Arial", Font.BOLD, 15));
        labelUsername = new JLabel("Username *");
        labelUsername.setFont(new Font("Arial", Font.BOLD, 15));
        labelPass = new JLabel("Password *");
        labelPass.setFont(new Font("Arial", Font.BOLD, 15));
        labelSigned = new JLabel("Already Registered?", JLabel.CENTER);
        labelSigned.setFont(new Font("Arial", Font.BOLD, 15));
        signLink.setFont(new Font("Arial", Font.BOLD, 15));

        //Setting Text Fields
        tfName = new JTextField(10);
        tfName.setBorder(compoundBorder);
        tfUsername = new JTextField(10);
        tfUsername.setBorder(compoundBorder);
        tfPassword = new JTextField(10);
        tfPassword.setBorder(compoundBorder);

        //Setting Buttons

        //Setting Buttons Colors
        Color btnRegColor = new Color(63,197,84);
        Color btnPhotoColor = new Color(181,217,232);

        //Setting Buttons Labels
        registerBtn = new JButton("REGISTER");

        //Setting Buttons Shapes & Background Colors
        registerBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        registerBtn.setBackground(btnRegColor);



        //setting the layout managers
        //Building Top Panel - Header
        buildTopPanel();

        //Building Middle Panel
        //PanelMiddle acting as component
        panelMiddle.setLayout(new BorderLayout());

        //Building Middle Panel
        buildMidTopPanel();

        //Building MidBottom Panel
        buildMidBottomPanel();

        //Building MidMidBottom Panel
        panelMidMidBottom.setBorder(new EmptyBorder(60, 0, 10, 0));
        panelMiddle.add(panelMidMidBottom, BorderLayout.SOUTH);

        //Building Bottom Panel
        buildBottomPanel();

        //Move to Desktop Main page after registration
        registerBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //Validate inserted values in textfields

                    //Get value written in tfName
                    String name = tfName.getText().toString();
                    //Get value written in tfUsername
                    String username = tfUsername.getText().toString();
                    //Get value written in tfPassword
                    String password = tfPassword.getText().toString();

                    //if Name field is empty
                    if(tfName.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Error- Name has not been filled");
                    }

                    //if Name field starts with whitespace
                    else if(Character.isWhitespace(name.charAt(0))) {
                        JOptionPane.showMessageDialog(frame, "Error- No leading whitespaces are allowed");
                    }

                    //if Username field is empty
                    else if(tfUsername.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Error- Username has not been filled");

                    }
                    //if Username field starts with whitespace
                    else if(Character.isWhitespace(username.charAt(0))) {
                        JOptionPane.showMessageDialog(frame, "Error- No leading whitespaces are allowed");
                    }

                    //if Password field is empty
                    else if(tfPassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Error- Password has not been filled");

                    }

                    //if Password field starts with whitespace
                    else if(Character.isWhitespace(password.charAt(0))) {
                        JOptionPane.showMessageDialog(frame, "Error- No leading whitespaces are allowed");
                    }

                    else {
                        //Close Register Window
                        frame.dispose();
                        //Open Desktop Window
                        Desktop obj = new Desktop();
                    }
                }
            });

        //Set color Dark Blue for signLink
        Color color = new Color(24,35,88);
        signLink.setForeground(color);

        //Change Link color
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("a {color:#554994;}");

        //Set cursor to Hand icon once the cursor is on top of the link
        signLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Cursor events handling
        signLink.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Close Login Window
                frame.dispose();
                //Open Register Window
                Login obj = new Login();
                obj.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signLink.setText(title);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                signLink.setText("<html><a href=''>" + title + "</a></html>");
            }
        });

        //Adding panels to frame
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelMiddle, BorderLayout.CENTER);

        panelMiddle.add(panelMidTop, BorderLayout.NORTH);
        panelMiddle.add(panelMidBottom, BorderLayout.CENTER);

        frame.add(navPanel1, BorderLayout.EAST);
        frame.add(navPanel2, BorderLayout.WEST);
        frame.add(panelBottom, BorderLayout.SOUTH);

        //Display Frame
        frame.setSize(450, 530);
        frame.setVisible(true);

        //Exit on window close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buildTopPanel() {
        /*
         * Method to build Top Panel
         */
        panelTop.setBackground(Color.lightGray);
        panelTop.setBorder(new EmptyBorder(8, 10, 8, 10));
        labelHeader.setFont(new Font("Arial", Font.BOLD, 25));
        panelTop.add(labelHeader);
    }

    private void buildMidTopPanel() {
        /*
         * Method to build Middle-Top Panel Located inside Middle Panel
         */
        panelMidTop.setLayout(new GridBagLayout());
        //Set constraints for GridBagLayout components
        GridBagConstraints gbc = createGbc(0, 0);
        //Set Empty border between components
        panelMidTop.setBorder(new EmptyBorder(90, 0, 30, 0));

        //Create Registration Form
        panelMidTop.add(labelName,createGbc(0, 0));
        panelMidTop.add(tfName, createGbc(1, 0));
        panelMidTop.add(labelUsername,createGbc(0, 1));
        panelMidTop.add(tfUsername, createGbc(1,1));
        panelMidTop.add(labelPass, createGbc(0, 2));
        panelMidTop.add(tfPassword, createGbc(1, 2));
    }

    private void buildMidBottomPanel() {
        /*
         * Method to build Middle-Bottom Panel Located inside Middle Panel
         */
        panelMidBottom.setLayout(new GridLayout(2,1));
//        panelMidBottom.setPreferredSize(new Dimension(50, 20));
        panelMidBottom.setBackground(Color.WHITE);
        panelMidBottom.add(labelSigned);
        signLink.setHorizontalAlignment(JLabel.CENTER);
        panelMidBottom.add(signLink);
    }

    private void buildBottomPanel() {
        /*
         * Method to build Bottom Panel
         */
        //Set size for Register button
        registerBtn.setPreferredSize(new Dimension(110,30));

        //Set empty border for Register button
        EmptyBorder border = new EmptyBorder(0, 0, 20,0);

        //Add to Bottom panel
        panelBottom.setBorder(border);
        panelBottom.add(registerBtn, BorderLayout.NORTH);
    }


    //Set Constraints to GridBag Layout Components
    private static GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int gap = 6;
        gbc.insets = new Insets(gap, gap + 2 * gap * x, gap, gap);
        return gbc;
    }
}