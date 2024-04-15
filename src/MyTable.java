import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyTable extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> groupComboBox;
    private ArrayList<Group> groups;
    private JButton backButton;
    private JButton goodButton;
    private JTextField searchField;
    private JButton searchButton;
    public MyTable(ArrayList<Group> groups) {
        this.groups = groups;
        setTitle("Goods Table");
        setSize(1080, 720);
        setBackground(new Color(217, 185, 155));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the combo box
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(217, 185, 155));
        JLabel groupLabel = new JLabel("Select Group: ");
        topPanel.add(groupLabel);

        // Populate the combo box with group names
        groupComboBox = new JComboBox<>();
        //groupComboBox.setBackground(new Color(1, 50, 32));
        groupComboBox.addItem("All Groups");
        for (Group group : groups) {
            groupComboBox.addItem(group.getName());
        }
        topPanel.add(groupComboBox);
        // Create button for search
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchGoods();
            }
        });
        topPanel.add(searchButton);
        searchField = new JTextField(20);
        topPanel.add(searchField);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText(""); // Clear search text field
                refresh(); // Refresh table to show all goods
            }
        });
        topPanel.add(clearButton);
        // Add combo box to the frame
        add(topPanel, BorderLayout.NORTH);

        // Create table model with column names
        String[] columns = {"Name", "Description", "Producer", "Amount", "Price"};
        model = new DefaultTableModel(columns, 0);

        // Add action listener to the combo box
        groupComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear existing rows from the table
                model.setRowCount(0);

                // Get the selected group
                String selectedGroupName = (String) groupComboBox.getSelectedItem();
                if (selectedGroupName.equals("All Groups")) {
                    // If "All Groups" is selected, populate the table with goods from all groups
                    for (Group group : groups) {
                        for (Good good : group.getGoodsList()) {
                            Object[] row = {good.getName(), good.getDescription(), good.getProducer(), good.getAmount(), good.getPrice()};
                            model.addRow(row);
                        }
                    }
                } else {
                    // If a specific group is selected, populate the table with goods from that group
                    Group selectedGroup = null;
                    for (Group group : groups) {
                        if (group.getName().equals(selectedGroupName)) {
                            selectedGroup = group;
                            break;
                        }
                    }
                    if (selectedGroup != null) {
                        for (Good good : selectedGroup.getGoodsList()) {
                            Object[] row = {good.getName(), good.getDescription(), good.getProducer(), good.getAmount(), good.getPrice()};
                            model.addRow(row);
                        }
                    }
                }
            }
        });

        // Create table with the model
        table = new JTable(model);
        //table.setBackground(new Color(217, 185, 155));
        // Set table properties
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.getTableHeader().setReorderingAllowed(false);

        // Create button for "Back"
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        goodButton = new JButton("Робота з товарами");
        goodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // Create panel for the button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(goodButton);
        bottomPanel.add(backButton);
        for (Group group : groups) {
            for (Good good : group.getGoodsList()) {
                Object[] row = {good.getName(), good.getDescription(), good.getProducer(), good.getAmount(), good.getPrice()};
                model.addRow(row);
            }
        }



        // Add button panel to the frame
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

    }
    private void searchGoods() {
        String searchText = searchField.getText().trim().toLowerCase();

        // Clear existing rows from the table
        model.setRowCount(0);

        // Replace "*" with ".*" and "?" with "."
        String regex = searchText.replace("*", ".*").replace("?", ".");

        // Iterate through all goods and add matching ones to the table
        for (Group group : groups) {
            for (Good good : group.getGoodsList()) {
                if (good.getName().toLowerCase().matches(regex) ||
                        good.getDescription().toLowerCase().matches(regex)) {
                    Object[] row = {good.getName(), good.getDescription(), good.getProducer(), good.getAmount(), good.getPrice()};
                    model.addRow(row);
                }
            }
        }
    }

    public void refresh() {
        groupComboBox.setSelectedIndex(0);
        model.setRowCount(0);
        for (Group group : groups) {
            for (Good good : group.getGoodsList()) {
                Object[] row = {good.getName(), good.getDescription(), good.getProducer(), good.getAmount(), good.getPrice()};
                model.addRow(row);
            }
        }
    }
    public JButton getBackButton() {
        return backButton;
    }
    public JButton getGoodButton() {
        return goodButton;
    }
}
