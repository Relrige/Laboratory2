import javax.swing.*;
import java.awt.*;
/**
 * A custom JButton with a cool appearance.
 */
public class CoolButton extends JButton {
   /**
    * Constructs a CoolButton with the specified text.
    *
    * @param text The text to display on the button.
    */
   public CoolButton(String text) {
         super(text);
            setFont(new Font("Arial", Font.BOLD, 18));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            setBackground(new Color(1, 50, 32));
            setForeground(Color.LIGHT_GRAY);
            setFocusPainted(false);
   }
}
