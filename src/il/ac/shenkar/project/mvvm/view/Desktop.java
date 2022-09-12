package il.ac.shenkar.project.mvvm.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.GroupLayout.Alignment.*;

/**
 * Class to display Main Desktop operations
 */
public class Desktop {
    //Declaring UI vars
    private String greeting = "Welcome, "; //Need to figure out how to attach username inside
    private String logout = "Logout";
    private String title = "What Would You Like To Do Today?";
    private String addExpense = "Add New Expense";
    private String addCategory = "Add New Category";
    private String viewList = "View Expenses List";
    private JLabel labelTitle, greetingTitle, logoutLink;
    private JButton addExpenseBtn, addCategoryBtn, viewExpensesListBtn;
    private JFrame frame;
    private JPanel panelTop, panelMiddle;
    Color bgColorBtn = new Color(81, 173, 253);
    Color fontColor = new Color(0, 0, 0);

    //Constructor
    public Desktop() {
        //Setting UI elements

        //Setting frame
        frame = new JFrame("Cost Manager App");

        //Display Frame
        frame.setSize(450, 530);
        frame.setVisible(true);

        //Exit on window close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting Panels
        panelTop = new JPanel();
        panelMiddle = new JPanel();

        //Setting Labels
        //Set Title label of Page
        labelTitle = new JLabel(title);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));

        //Set Logout Link label
        logoutLink = new JLabel(logout);
        logoutLink.setFont(new Font("Arial", Font.BOLD, 11));
        logoutLink.setForeground(Color.darkGray);

        //Set Empty Border to logoutLink
        EmptyBorder borderLink = new EmptyBorder(0,20,0,0);
        logoutLink.setBorder(borderLink);

        //Change Logout Link attributes
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("a {color:#404040;}");
        styleSheet.addRule("a {text-decoration: none}");


        //Set Greeting label
        greetingTitle = new JLabel(greeting);
        greetingTitle.setFont(new Font("Arial", Font.BOLD, 18));

        //Setting Buttons
        addExpenseBtn = new JButton(addExpense);
        addCategoryBtn = new JButton(addCategory);
        viewExpensesListBtn = new JButton(viewList);

        //Set Logout link
        //Set cursor to Hand icon once the cursor is on top of the link
        logoutLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Cursor events handling
        logoutLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Display Confirmation Dialog before user logs out
                String[] options = {"Yes", "Not Now"};
                int confirmLogout = JOptionPane.showOptionDialog(
                        frame,
                        "Are You Sure?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);

                //If user clicks "YES"
                if(confirmLogout == JOptionPane.YES_OPTION) {
                    //Close Login Window
                    frame.dispose();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutLink.setText(logout);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logoutLink.setText("<html><a href=''>" + logout + "</a></html>");
            }
        });


        //Setting margins & borders to buttons
        setButtonShape();

        //Move to AddNewExpense Page when user clicks "Add New Expense"
        addExpenseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close Desktop Window
                frame.dispose();
                //Open New Expense Window
                    AddNewExpense obj = new AddNewExpense();
            }
        });

        //Move to AddNewCategory Page when user clicks "Add New Category"
        addCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close Desktop Window
                frame.dispose();
                //Open New Category Window
                AddNewCategory obj = new AddNewCategory();
            }
        });

        //Move to Login Expenses Page when user clicks "Login Expenses List"
        viewExpensesListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close Desktop Window
                frame.dispose();
                //Open Expenses List Window
                ViewExpensesList obj = new ViewExpensesList();
            }
        });


        //Setting the layout managers
        //Top Panel
        GroupLayout groupLayout = new GroupLayout(panelTop);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        //Setting empty border for spacing between labels
        EmptyBorder border = new EmptyBorder(0, 250, 0, 0);

        panelTop.setLayout(groupLayout);

        //Setting labels at Group Layout sequentially
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(greetingTitle).addComponent(labelTitle))
                        .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(logoutLink)));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(greetingTitle).addComponent(logoutLink))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(labelTitle)));

        //Setting background color for Top Panel
        panelTop.setBackground(Color.lightGray);


        //Middle Panel
        //Set elements vertically
        panelMiddle.setLayout(new BoxLayout(panelMiddle, BoxLayout.Y_AXIS));
        panelMiddle.setBackground(Color.WHITE);
        //Moves Middle Panel to the right
        panelMiddle.add(Box.createRigidArea(new Dimension(20, 0)));

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        int top = 60;
        int left = top;
        int bottom = 2 * top;
        int right = left;
        panelMiddle.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        buttonPanel.add(addExpenseBtn);
        buttonPanel.add(addCategoryBtn);
        buttonPanel.add(viewExpensesListBtn);
        panelMiddle.add(buttonPanel);
        //Set Frame layout
        frame.setLayout(new BorderLayout());

        //Adding panels to frame
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelMiddle, BorderLayout.CENTER);
    }

//    public void setPreferredSize(Dimension preferredSize) {
//        super.setPreferredSize(new Dimension(preferredSize.width+100, preferredSize.height+100));
//    }

    public void setButtonShape() {
        /*
         * Method to set rounded shape of buttons and buttons colors
         */
        addExpenseBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        addExpenseBtn.setBackground(bgColorBtn);
        addExpenseBtn.setFont(new Font("arial", Font.BOLD, 18));
        addExpenseBtn.setForeground(fontColor);
        addCategoryBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        addCategoryBtn.setBackground(bgColorBtn);
        addCategoryBtn.setFont(new Font("arial", Font.BOLD, 18));
        addCategoryBtn.setForeground(fontColor);
        viewExpensesListBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        viewExpensesListBtn.setBackground(bgColorBtn);
        viewExpensesListBtn.setFont(new Font("arial", Font.BOLD, 18));
        viewExpensesListBtn.setForeground(fontColor);

    }
}
