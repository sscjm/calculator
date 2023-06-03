import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Calculator extends JFrame {

	JLabel display;
	JLabel history;
	Font font;
	JPanel keypad;
	JButton[] btnKey = new JButton[24];
	String[] command = { "%", "CE", "C", "<-",
						"1/x", "x^2", "sqrt", "/",
						"7", "8", "9", "*",
						"4", "5", "6", "-",
						"1", "2", "3", "+",
						"+/-", "0", ".", "=" };
	Double op1;
	Double op2;
	
	Calculator() {
		this.setTitle("Calculator");
		this.setBounds(100,100,300,500);
		this.setLayout(new BorderLayout());
		Image icon = Toolkit.getDefaultToolkit().getImage("black1.png");
		this.setIconImage(icon);
		
		
		display = new JLabel("0");
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		font = new Font( "궁서체", Font.BOLD, 40);
		display.setFont(font);
		display.setOpaque(true);
		display.setBackground(Color.white);
		this.add(display, BorderLayout.NORTH);

		history = new JLabel(" ");
		font = new Font( "굴림체", Font.BOLD, 15);
		history.setFont(font);
		this.add(history, BorderLayout.SOUTH );
		
		keypad = new JPanel();
		keypad.setLayout(new GridLayout(6,4));
		
		for(int i=0;i<24;i++) {
			btnKey[i] = new JButton(command[i]);
			keypad.add(btnKey[i]);
			switch( command[i] ) {
			case "0": case "1": case "2": case "3": case "4": case "5": 
			case "6": case "7": case "8": case "9": case ".": case "+/-":  
				btnKey[i].addActionListener( e -> numberClick(e) );
				break;	
			default:
			}			
		}
		
		this.add(keypad, BorderLayout.CENTER );
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private void numberClick(ActionEvent e) {
		switch( e.getActionCommand() ) {
		case "0":
			if( !display.getText().equals("0") ) display.setText( display.getText() + e.getActionCommand() );
			break;
		case ".":
			if( !display.getText().contains(".") ) display.setText( display.getText() + e.getActionCommand() );
			break;
		case "+/-":
			if( !display.getText().equals("0") ) {
				if( display.getText().contains("-") ) display.setText( display.getText().replace("-", "") ); 
				else display.setText( "-" + display.getText() );
			}
			break;
		default:
			if( display.getText().equals("0") ) display.setText( e.getActionCommand() );
			else display.setText( display.getText() + e.getActionCommand() );
		}
	}
	
	public static void main(String[] args) {
		
		new Calculator().setVisible(true);		
		
	}
}
