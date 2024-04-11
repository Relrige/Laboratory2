import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StandartFrame extends JFrame {
    private JPanel infoPanel;
    private JPanel searchPanel;
    private JPanel buttonPanel;
    private JPanel textInputPanel;
    public  JComboBox<String> list = new JComboBox<>();
    public StandartFrame(String title) {
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(title);
        setLayout(new GridLayout(2, 1));
    }
    public void setTextInputPanel(JTextField[] textField, JLabel[] label) {
       textInputPanel = new JPanel();
       textInputPanel.setLayout(new GridLayout(textField.length, 2));
       textInputPanel.setBackground(new Color(217, 185, 155));
         for (int i = 0; i < textField.length; i++) {
             textInputPanel.add(label[i]);
             label[i].setFont(new Font("Arial", Font.BOLD, 24));
             textInputPanel.add(textField[i]);
             textField[i].setFont(new Font("Arial", Font.BOLD, 18));
         }
         add(textInputPanel);
    }
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
    public void setButtonPanel(JButton[] buttons) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(buttons.length,1));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        add(buttonPanel);
    }
}
