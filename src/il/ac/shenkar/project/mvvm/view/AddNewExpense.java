package il.ac.shenkar.project.mvvm.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/**
 * Class to display Add New Expense Page
 */
public class AddNewExpense {
    //Declaring UI vars
    private String logout = "Logout";
    private String goBack = "back";
    private String title = "Add An Expense:";
    private JLabel labelTitle,labelCategory, labelSum, labelCurrency, labelDescription, labelReturn, logoutLink;
    private JLabel arrowIcon;
    private JTextField tfSum, tfDescription;
    private JComboBox categoryBox, currencyBox;
    private JButton addExpenseBtn;
    private JFrame frame;
    private JPanel panelTop, panelMiddle, panelMidLeft, panelMidRight, panelBottom, navPanel1, navPanel2;
    Color bgColorBtn = new Color(233, 69, 96);
    private static final Insets WEST_INSETS = new Insets(0, 0, 0, 0);
    private static final Insets EAST_INSETS = new Insets(0, 0, 0, 0);

    //Constructor
    public AddNewExpense() {
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
        panelBottom = new JPanel();
        navPanel1 = new JPanel();
        navPanel2 = new JPanel();
        panelMidLeft = new JPanel();
        panelMidRight = new JPanel();

        //Setting TextFields
        tfSum = new JTextField(5);
        tfDescription = new JTextField(8);

        //Setting Labels
        //Set Title label of page
        labelTitle = new JLabel(title);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 23));

        //Set Form labels
        //Set Category label
        labelCategory = new JLabel("Category *");
        labelCategory.setFont(new Font("Arial", Font.BOLD, 15));
        //Set Sum label
        labelSum = new JLabel("Sum *");
        labelSum.setFont(new Font("Arial", Font.BOLD, 15));
        //Set Currency label
        labelCurrency = new JLabel("Currency *");
        labelCurrency.setFont(new Font("Arial", Font.BOLD, 15));
        //Set Description label
        labelDescription = new JLabel("Description *");
        labelDescription.setFont(new Font("Arial", Font.BOLD, 15));
        //Set Return label
        labelReturn = new JLabel(goBack);
//        BufferedImage arrow = ImageIO.read(new File("il/ac/shenkar/project/mvvm/view/images/left-arrow.png"));
//        System.out.println(arrow);
//        arrowIcon = new JLabel(new ImageIcon(arrow));

        //Setting Nav Panels - Size & Color
        navPanel1.setPreferredSize(new Dimension(20, 40));
        navPanel2.setPreferredSize(new Dimension(20, 40));
        navPanel1.setBackground(Color.lightGray);
        navPanel2.setBackground(Color.lightGray);
        //Set Logout label
        logoutLink = new JLabel(logout);
        logoutLink.setFont(new Font("Arial", Font.BOLD, 11));
        logoutLink.setForeground(Color.darkGray);

        //Change Logout Link attributes
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("a {color:#404040;}");
        styleSheet.addRule("a {text-decoration: none}");

        //Setting AddExpense Button
        addExpenseBtn = new JButton("Add Expense");

        //Set Items for ComboBoxes
        String[] Categories = {"Select Category", "Electronics", "Groceries", "Transportation", "Luxury"};
        String[] Currencies = {"Select Currency", "ILS", "EUR", "USD", "GBP"};

        //Initialize ComboBoxes
        categoryBox = new JComboBox(Categories);
        currencyBox = new JComboBox(Currencies);

        //Validate Category chosen
        categoryBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                //Check if the object on which the event initially occurred equals to categoryBox
                if (event.getSource() == categoryBox) {
                    //Checks if element is selected or not selected
                    int state = event.getStateChange();
                    //Check if currency has not been chosen
                    if (state == ItemEvent.SELECTED) {
                        //Assign choice to str
                        String category = (String) categoryBox.getSelectedItem();
                        //Print to console
                        System.out.println(category);
                    }
                }
            }
        });

        //Validate Currency chosen
        currencyBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                //Check if the object on which the event initially occurred equals to currencyBox
                if (event.getSource() == currencyBox) {
                    //Checks if element is selected or not selected
                    int state = event.getStateChange();

                    //Check if category has not been chosen
                    if (state == ItemEvent.SELECTED) {
                        //Assign choice to str
                        String currency = (String) currencyBox.getSelectedItem();
                        //Print to console
                        System.out.println(currency);
                    }
                }
            }
        });

        //Set Logout link
        //Set cursor to Hand icon once the cursor is on top of the link
        logoutLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Set Empty Border to logoutLink
        EmptyBorder border = new EmptyBorder(0,160,0,0);
        logoutLink.setBorder(border);

        //Logout Link events handling
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
                    //Close Current Window
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

        //Set Return link
        //Set cursor to Hand icon once the cursor is on top of the link
        labelReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Return Label events handling
        labelReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    //Close AddNewExpense Window
                    frame.dispose();
                    //Go back to Main Desktop Window
                    Desktop obj = new Desktop();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                labelReturn.setText(goBack);
            }
        });

        //Move to View Expenses List page after adding a new expense
        //TBD: Need to add code that updates the table along with DB
        addExpenseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Validate inserted value in textfields
                String sum = tfSum.getText().toString();
                String description = tfDescription.getText().toString();

                //if Sum field is empty
                if (tfSum.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Error- Sum has not been filled");
                }

                //if Sum field starts with whitespace
                else if (Character.isWhitespace(sum.charAt(0))) {
                    JOptionPane.showMessageDialog(frame, "Error- No leading whitespaces are allowed");
                }

                //if Description field is empty
                if (tfDescription.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Error- Description has not been filled");
                }

                //if Description field starts with whitespace
                else if (Character.isWhitespace(description.charAt(0))) {
                    JOptionPane.showMessageDialog(frame, "Error- No leading whitespaces are allowed");
                }

                else {
                    //Display Confirmation Message
                    JOptionPane.showMessageDialog(frame, "A new expense has been added successfully!");
                    //Close Register Window
                    frame.dispose();
                    //Open ViewExpensesList Window
                    ViewExpensesList obj = new ViewExpensesList();
                }
            }
        });

            //Check whether a choice was made in either of the ComboBoxes
            //TBD: Need to receive categories & currencies from DB
            categoryBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(categoryBox.getSelectedItem() == "Select Category") {
                        JOptionPane.showMessageDialog(frame, "Error! Category has not been chosen");
                    }
                }
            });

            currencyBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(currencyBox.getSelectedItem() == "Select Currency") {
                        JOptionPane.showMessageDialog(frame, "Error! Currency has not been chosen");
                    }
                }
            });

        //Setting margins & borders to AddExpense button
        setButtonShape();

        //Setting the layout managers
        frame.setLayout(new BorderLayout(0,0));

        //Building Top Panel - Header
        buildTopPanel();
        //Middle Panel
        buildMiddlePanel();
        //Bottom Panel
        buildBottomPanel();

        //Adding panels to frame
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelMiddle, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.SOUTH);
    }

    private void buildTopPanel() {
        /*
         * Method to build Top Panel
         */
        //Setting background color for Top Panel
        panelTop.setBackground(Color.PINK);
        //Setting border
        panelTop.setBorder(new EmptyBorder(8, 10, 10, 10));

        //Define GroupLayout manager for Top Panel with gaps
        GroupLayout groupLayout = new GroupLayout(panelTop);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
//
//        //Setting empty border for spacing between labels
//        EmptyBorder border = new EmptyBorder(0, 250, 0, 0);

        panelTop.setLayout(groupLayout);

        //Setting labels at Group Layout sequentially
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(labelReturn).addComponent(labelTitle))
                        .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(logoutLink)));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(labelReturn).addComponent(logoutLink))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(labelTitle)));
    }


    private void buildMiddlePanel() {
        /*
         * Method to build Middle-Top Panel Located inside Middle Panel
         */

        panelMiddle.setLayout(new GridBagLayout());
        panelMiddle.setBackground(Color.lightGray);

        //Setting borders to labels & panel
        EmptyBorder panBorder = new EmptyBorder(0,20,0,20);
        EmptyBorder border = new EmptyBorder(25,0,25,0);
        Border tfBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        panelMiddle.setBorder(panBorder);
        labelCategory.setBorder(border);
        labelSum.setBorder(border);
        labelCurrency.setBorder(border);
        labelDescription.setBorder(border);
//        tfSum.setBorder(tfBorder);
//        tfDescription.setBorder(tfBorder);
        tfSum.setBorder(BorderFactory.createCompoundBorder(tfBorder,
                BorderFactory.createEmptyBorder(7, 0, 7, 0)));
        tfDescription.setBorder(BorderFactory.createCompoundBorder(tfBorder,
                BorderFactory.createEmptyBorder(7, 0, 7, 0)));

        panelMiddle.add(labelCategory, createGbc(0, 0));
        panelMiddle.add(categoryBox,createGbc(1, 0));
        panelMiddle.add(labelSum, createGbc(0, 1));
        panelMiddle.add(tfSum, createGbc(1,1));
        panelMiddle.add(labelCurrency, createGbc(0, 2));
        panelMiddle.add(currencyBox, createGbc(1,2));
        panelMiddle.add(labelDescription, createGbc(0,3));
        panelMiddle.add(tfDescription, createGbc(1,3));
    }

    private void buildBottomPanel() {
        /*
         * Method to build Bottom Panel
         */
        addExpenseBtn.setPreferredSize(new Dimension(135,30));
        panelBottom.setBorder(new EmptyBorder(0, 0, 30, 230));
        panelBottom.add(addExpenseBtn, FlowLayout.LEFT);
        panelBottom.setBackground(Color.lightGray);
    }

    public void setButtonShape() {
        /*
         * Method to set rounded shape & color of AddExpense button
         */
        addExpenseBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        addExpenseBtn.setBackground(bgColorBtn);
        addExpenseBtn.setFont(new Font("arial", Font.BOLD, 14));
        addExpenseBtn.setForeground(Color.BLACK);
        addExpenseBtn.setPreferredSize(new Dimension(110,30));
    }

    //Set Constraints to GridBag Layout Components
    private static GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        //Gridx = the gridx position
        gbc.gridx = x;
        //Gridy = the gridy position
        gbc.gridy = y;
        //Gridwidth = the cell span in x-direction
        gbc.gridwidth = 1;
        //Gridheight = the cell span in y-direction
        gbc.gridheight = 1;

        //Determine where to place the component within the display area
        gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        //Resize component's area if needed
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Set spacing between elements
        gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;

        //Weightx = cell weight in x-direction
        gbc.weightx = (x == 0) ? 0.1 : 1.0;
        //Weighty = cell weight in y-direction
        gbc.weighty = 0.0;

        return gbc;
    }
}
