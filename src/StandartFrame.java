import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * A standard frame with predefined panels for input fields, information display, search, and buttons.
 */
public class StandartFrame extends JFrame {
    private JPanel infoPanel;
    private JPanel searchPanel;
    private JPanel buttonPanel;
    private JPanel textInputPanel;
    public  JComboBox<String> list = new JComboBox<>();
    /**
     * Constructs a StandartFrame object with the specified title.
     *
     * @param title The title of the frame.
     */
    public StandartFrame(String title) {
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(title);
        setLayout(new GridLayout(2, 1));
    }
    /**
     * Sets up the panel for text input fields.
     *
     * @param textField An array of JTextField objects for input.
     * @param label     An array of JLabel objects for labels corresponding to the text fields.
     */
    public void setTextInputPanel(JTextField[] textField, JLabel[] label) {
        textInputPanel = new JPanel();
        textInputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        textInputPanel.setLayout(new GridLayout(textField.length, 2));
        textInputPanel.setBackground(new Color(217, 185, 155));
        for (int i = 0; i < textField.length; i++) {
            textInputPanel.add(label[i]);
            label[i].setHorizontalAlignment(SwingConstants.CENTER);
            label[i].setFont(new Font("Arial", Font.BOLD, 24));
            textInputPanel.add(textField[i]);
            textField[i].setFont(new Font("Arial", Font.BOLD, 18));
        }
        add(textInputPanel);
    }
    /**
     * Sets up the information panel with the provided text.
     *
     * @param text The text to display in the information panel.
     */
    public void setInfoPanel(String text) {
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 1));
        infoPanel.setBackground(new Color(217, 185, 155));
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        infoPanel.add(label);

        add(infoPanel);
    }
    /**
     * Sets up the search panel with a list of items to select from.
     *
     * @param items The list of items to display in the search panel.
     */
    public void setSearchPanel(List<String> items) {
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 1));
        list.setFont(new Font("Arial", Font.BOLD, 18));
        for (String item : items) {
            list.addItem(item);
        }
        searchPanel.add(list);
        add(searchPanel);
    }
    /**
     * Sets up the panel for buttons.
     *
     * @param buttons An array of JButtons to display in the button panel.
     */
    public void setButtonPanel(JButton[] buttons) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(buttons.length,1));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        add(buttonPanel);
    }
}
