package il.ac.shenkar.project.mvvm.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

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
    private JLabel labelTitle,labelSubTitle, labelTo, labelFrom, labelReturn, logoutLink;
    private JLabel arrowIcon;
    private JTextField tfCategory;
    private JButton displayListBtn;
    private JFrame frame;
    private JPanel panelTop, panelMiddle, panelBottom, anotherPanel;
    private DatePicker datePickerFrom, datePickerTo;
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

        //Set labelFrom & labelTo
        labelFrom = new JLabel("From:");
        labelFrom.setFont(new Font("Arial", Font.BOLD, 13));
        labelTo = new JLabel("To:");
        labelTo.setFont(new Font("Arial", Font.BOLD, 13));

        //Set Return label
        labelReturn = new JLabel(goBack);

        //Set datePickers

        //Set DatePickerSettings
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        DatePickerSettings datePickerSettings1 = new DatePickerSettings();
        datePickerSettings.setFormatForDatesBeforeCommonEra("dd.MM.yyyy");
        datePickerSettings.setFormatForDatesCommonEra("dd.MM.yyyy");
        datePickerSettings1.setFormatForDatesBeforeCommonEra("dd.MM.yyyy");
        datePickerSettings1.setFormatForDatesCommonEra("dd.MM.yyyy");

        //DatePickerFrom
        datePickerFrom = new DatePicker(datePickerSettings);
        datePickerFrom.setToolTipText("Choose Time Period");

        //DatePickerTo
        datePickerTo = new DatePicker(datePickerSettings1);
        datePickerTo.setToolTipText("Choose Time Period");

        // Set Table
        //Fetch data from DB for Rows
        String data[][] = {
                //fetch data from DB
        };

        //Set Columns
        String columns[] = {"Category", "Currency", "Sum", "Description", "Date", "Edit/Delete"};

        table = new JTable(data, columns);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setBounds(60,100,400,300);
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

        //Display Table according to selected dates
        displayListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Store dates chosen by user
                LocalDate localDateFrom = datePickerFrom.getDate();
                LocalDate localDateTo = datePickerTo.getDate();

                //Display popup error message if dateFrom is *after* dateTo
                if(localDateFrom.isAfter(localDateTo)) {
                    JOptionPane.showMessageDialog(frame, "Error! 'From' date must be before 'To' date");
                }
                //Check if dates selected
                if (localDateFrom != null && localDateTo != null) {
                    //Show selected dates in terminal
                    System.out.println(localDateFrom);
                    System.out.println(localDateTo);
                }
                //Else - NullPointerException will be thrown
            }

        });

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

        //Add Empty Border to DatePickers
        EmptyBorder dateBorderFrom = new EmptyBorder(0,0,0,20);
        EmptyBorder dateBorderTo = new EmptyBorder(0,0,0,20);

        //Add to panel
        anotherPanel.add(labelFrom, createGbc(0,0));
        anotherPanel.add(datePickerFrom, createGbc(1,0));
        anotherPanel.add(labelTo, createGbc(2,0));
        anotherPanel.add(datePickerTo, createGbc(3,0));
        anotherPanel.add(displayListBtn, createGbc(4,0));
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

