import javax.swing.*;
import java.awt.*;

public class ExpressionTreeTest {
    public static void main(String[] args) {
        ExpressionTree tree = new ExpressionTree();
        String input;
        boolean done = false;

        while (!done) {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("MathIcon.png"));
            Image image = icon.getImage();
            Image newImg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            input = (String) JOptionPane.showInputDialog(
                null,
                "Enter prefix expression (cancel to quit):",
                "Prefix To Infix Calculator",
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                null,
                null
            );

            if (input == null) {
                done = true;
            } else {
                tree.setExpression(input);
                JOptionPane.showMessageDialog(
                    null,
                    input + "\nEvaluates to:\n" + tree + " = " + String.format("%.3f", tree.evaluate()),
                    "Prefix To Infix Calculator",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon
                );
            }
        }
        System.exit(0);
    }
}
