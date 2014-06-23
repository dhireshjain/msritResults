package results;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class dialoger {

	public void JDialog(String name, String cgpa , String sgpa, JEditorPane text)
	{
		JFrame frame = new JFrame(text.getText());

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        JLabel label = new JLabel("Name: ");
        JTextField textField = new JTextField(name);
        textField.setEditable(false);
        textField.setBackground(Color.CYAN);
        contentPane.add(label);
        contentPane.add(textField);

        layout.putConstraint(SpringLayout.WEST, label,5,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,5,SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textField,5,SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, textField,5,SpringLayout.NORTH, contentPane);

        label = new JLabel("CGPA: ");
        contentPane.add(label);
        textField = new JTextField(cgpa);
        textField.setEditable(false);
        textField.setBackground(Color.CYAN);
        contentPane.add(label);
        contentPane.add(textField);
        
        layout.putConstraint(SpringLayout.WEST, label,5,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,30,SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textField,5,SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, textField,30,SpringLayout.NORTH, contentPane);
        
        label = new JLabel("SGPA: ");
        contentPane.add(label);
        textField = new JTextField(sgpa);
        textField.setEditable(false);
        textField.setBackground(Color.CYAN);
        contentPane.add(label);
        contentPane.add(textField);
        
        layout.putConstraint(SpringLayout.WEST, label,5,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,55,SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textField,5,SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, textField,55,SpringLayout.NORTH, contentPane);
        
        frame.pack();
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(42,84,84));
	}
}
