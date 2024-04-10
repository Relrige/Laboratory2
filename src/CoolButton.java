import javax.swing.*;
import java.awt.*;

public class CoolButton extends JButton {
   public CoolButton(String text) {
         super(text);
            setFont(new Font("Arial", Font.BOLD, 18));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            setBackground(new Color(1, 50, 32));
            setForeground(Color.LIGHT_GRAY);
            setFocusPainted(false);
   }
}
