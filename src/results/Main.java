package results;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main implements ActionListener {

	JEditorPane text,iname,iyear,ibranch;
	JButton submit1,submit2;
	JPanel pane, pane2;
	JTextField error ;
	JLabel name , year , branch;
	
	Main(JFrame frame)
	{
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(125,75,85));
		
		pane = new JPanel();	
		pane.setOpaque(true);
        pane.setBackground(new Color(248, 213, 131));
        pane.setPreferredSize(new Dimension(250	, 500));
		frame.add(pane,BorderLayout.WEST);
        
		text = new JEditorPane();
		text.setText("1MS");
		text.setBackground(new Color(150,50,70));
		pane.add(text);
		submit1 = new JButton("Submit");
		pane.add(submit1,BorderLayout.SOUTH);
		submit1.addActionListener(this);
		
		
		pane2 = new JPanel();
		pane2.setBackground(new Color(100, 100, 100));
		pane2.setPreferredSize(new Dimension(250,500));		
		frame.add(pane2,BorderLayout.EAST);
				
		name = new JLabel("Name:");
		pane2.add(name);	
		iname = new JEditorPane();
		iname.setText("");
		iname.setBackground(new Color(150,50,70));
		pane2.add(iname);	
		year = new JLabel("            Year:");
		pane2.add(year);
		iyear = new JEditorPane();
		iyear.setText("");
		iyear.setBackground(new Color(150,50,70));
		pane2.add(iyear);
		branch = new JLabel("Branch:");
		pane2.add(branch);
		ibranch = new JEditorPane();
		ibranch.setText("");
		ibranch.setBackground(new Color(150,50,70));
		pane2.add(ibranch);
		submit2 = new JButton("Submit");
		pane2.add(submit2);
		submit2.addActionListener(this);
		
		error = new JTextField();
		error.setText("");
		frame.getContentPane().add(error,BorderLayout.SOUTH);
		
		
        frame.setPreferredSize(new Dimension(500,500));
        frame.pack();
        frame.setVisible(true);
   
	}
	
	public static void main(String[] args) {

		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Main(new JFrame("RESULTS"));
	            }
	        });
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==submit1)
		{
			String str = text.getText();
			
			if(str.substring(0,3).compareTo("1MS")!=0)
			{
				error.setText("Start with 1MS");
			}
			else if(str.length()<10)
			{
				error.setText("Some character(s) missing");
			}
			else if(str.length()>10)
			{
				error.setText("Extra character(s)");
			}
			else
			{
				error.setText("");
				String string[] = null;
				
				try {
					string = new extractor().extract(text.getText()).split("#");
					} 				
				catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    "Try again later.");
					return;
				}
				try{
					new dialoger().JDialog(string[0],string[1],string[2],text);
				}
				catch (Exception e)
				{
					error.setText("Invalid Usn.");
				}
				
				
			}
	
		}
		if(ae.getSource()==submit2)
		{
			try {
				fetch2(iname.getText(),iyear.getText(),ibranch.getText());
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(new JFrame(),
					    "Try again later.");
			}
		}
		
	}
	
	void fetch2(String name,String year, String branch) throws IOException
	{
		boolean flag = false;
		
		for( int k = 1 , j = 0; k < 150 && !flag && j < 5; k++ )
		{	
			String usn = "1MS"+year+branch , str[] = null, name1 = null; ;
			
			if(k < 10)
				usn = usn+"00" + k ;
			else if(k < 100)
				usn = usn+"0" + k ;
			else
				usn = usn+k;
		
			
			str = new extractor().extract(usn).split("#") ;
			
			try{
				name1 = str[0];
			}
			catch(Exception e)
			{
				j++;
				continue;
			}
			
			String s[] = name1.split(" ");
			if( (name1+"  ").compareToIgnoreCase(name) == 0 )
			{
				flag = true ;
			}
			else 
			{
				for( int p = 0 ; p < s.length ; p++ )
				{
					if(name.compareToIgnoreCase(s[p]) == 0)
					{
						flag = true ;
						break;
					}
				}
			}
			if(flag)
				new dialoger().JDialog(name1,str[1],str[2],text);
		}
		if(!flag)
		error.setText("Not Found");
	}
	
}
