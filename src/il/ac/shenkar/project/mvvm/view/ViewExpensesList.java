package il.ac.shenkar.project.mvvm.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

/**
 * Class to display Login Expenses List Page
 */
public class ViewExpensesList {
    //Declaring UI vars
    private String logout = "Logout";
    private String goBack = "back";
    private String title = "Display Expenses List:";
    private JLabel labelTitle,labelSubTitle, labelReturn, logoutLink;
    private JLabel arrowIcon;
    private JTextField tfCategory;
    private JButton displayListBtn;
    private JFrame frame;
    private JPanel panelTop, panelMiddle, panelBottom, anotherPanel;
    private DatePicker datePicker;
    private JTable table;
    private JScrollPane sp;
    Color bgColorBtn = new Color(255, 221, 135);
    private static final Insets WEST_INSETS = new Insets(0, 0, 0, 0);
    private static final Insets EAST_INSETS = new Insets(0, 0, 0, 0);

    //Constructor
    public ViewExpensesList() {
        //Setting UI elements

        //Setting frame
        frame = new JFrame("Cost Manager App");

        //Display Frame
        frame.setSize(500, 580);
        frame.setVisible(true);

        //Exit on window close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting Panels
        panelTop = new JPanel();
        panelMiddle = new JPanel();
        panelBottom = new JPanel();
        anotherPanel = new JPanel();

        //Setting TextField
        tfCategory = new JTextField(8);

        //Setting Labels
        //Set Title label of page
        labelTitle = new JLabel(title);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 23));
        //Set Sub Title
        labelSubTitle = new JLabel("Choose Time Period:");
        labelSubTitle.setForeground(Color.BLACK);
        labelSubTitle.setFont(new Font("Arial", Font.BOLD, 15));

        //Set Return label
        labelReturn = new JLabel(goBack);

//        BufferedImage arrow = ImageIO.read(new File("il/ac/shenkar/project/mvvm/view/images/left-arrow.png"));
//        System.out.println(arrow);
//        arrowIcon = new JLabel(new ImageIcon(arrow));

        //Set datePicker
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        datePickerSettings.setFormatForDatesBeforeCommonEra("dd.MM.yyyy");
        datePickerSettings.setFormatForDatesCommonEra("dd.MM.yyyy");
        datePicker = new DatePicker(datePickerSettings);
        datePicker.setToolTipText("Choose Time Period");

        //Set Table
        //Fetch data from DB for Rows
        String data[][] = {
                {"2","1", "ILS", "3000", "Computer", "2022-08-22", "Edit/Delete"},
                {"2","1", "ILS", "3000", "Computer", "2022-08-22", "Edit/Delete"},
                //fetch data from DB
        };

        //Set Columns
        String columns[] = {"Id", "Category_Id", "Currency", "Sum", "Description", "Purchase Date", "Edit/Delete"};

        table = new JTable(data, columns);
        table.setBounds(70,50,200,300);
        sp = new JScrollPane(table);

        //Set Logout label
        logoutLink = new JLabel(logout);
        logoutLink.setFont(new Font("Arial", Font.BOLD, 11));
        logoutLink.setForeground(Color.darkGray);

        //Change Logout Link attributes
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("a {color:#404040;}");
        styleSheet.addRule("a {text-decoration: none}");

        //Setting DisplayList Button
        displayListBtn = new JButton("Display List");

        //Set Logout link
        //Set cursor to Hand icon once the cursor is on top of the link
        logoutLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Set Empty Border to logoutLink
        EmptyBorder border = new EmptyBorder(0,150,0,0);
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

        //Setting margins & borders to DisplayList button
        setButtonShape();

        //Check if Date has been selected
        LocalDate localDate = datePicker.getDate();
        if(localDate != null) {
            //Add event listener on Display List Button
            displayListBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Close Desktop Window
//                    frame.dispose();
                    //Open New Expense Window
//                    AddNewExpense obj = new AddNewExpense();
                }
            });
        } else
            System.out.println(localDate);
//        String date = datePicker.getDate().toString();


        //Setting the layout managers
        frame.setLayout(new BorderLayout(0,0));

        //Building Top Panel - Header
        buildTopPanel();
        //Build Middle Panel
        buildMiddlePanel();

        //Adding panels to frame
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelMiddle, BorderLayout.CENTER);
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
         * Method to build Middle Panel
         */

        //Set Layout Manager for panel
        panelMiddle.setLayout(new BorderLayout(0,10));

        //Add Empty Border to sub-title label
        EmptyBorder labBorder = new EmptyBorder(10,20,0,0);
        labelSubTitle.setBorder(labBorder);

        //Add Empty Border to DatePicker
        EmptyBorder dateBorder = new EmptyBorder(0,0,0,20);
        datePicker.setBorder(dateBorder);

        //Add to panel
        anotherPanel.add(datePicker, createGbc(0,0));
        anotherPanel.add(displayListBtn, createGbc(1,0));
        anotherPanel.add(sp, createGbc(2,0));

        panelMiddle.add(labelSubTitle,BorderLayout.NORTH);
        panelMiddle.add(anotherPanel, BorderLayout.CENTER);
    }

    private void buildBottomPanel() {
        /*
         * Method to build Bottom Panel
         */

//        panelBottom.setLayout(new GridBagLayout());
        panelBottom.setBackground(Color.yellow);

        //Setting borders to labels & panel
//        EmptyBorder panBorder = new EmptyBorder(0,20,0,20);
//        EmptyBorder border = new EmptyBorder(25,0,25,0);
//        Border tfBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

//        panelBottom.setBorder(panBorder);
        panelBottom.add(displayListBtn);
    }

    public void setButtonShape() {
        /*
         * Method to set rounded shape & color of AddCategory button
         */
        displayListBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        displayListBtn.setBackground(bgColorBtn);
        displayListBtn.setFont(new Font("Arial", Font.BOLD, 14));
        displayListBtn.setForeground(Color.BLACK);
        displayListBtn.setPreferredSize(new Dimension(110,30));
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

