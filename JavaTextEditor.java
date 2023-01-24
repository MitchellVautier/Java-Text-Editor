import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

class Javapad extends JFrame {
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem n, open, save, exit;

	public Javapad(){
		super("Javapad");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildGUI();
		pack();
		setVisible(true);
	}

	private void buildGUI(){
		Container ct = getContentPane();

		menuBar = new JMenuBar();
		ct.add(menuBar, BorderLayout.NORTH);

		fileMenu = new JMenu ("File");
		menuBar.add(fileMenu);

		n = new JMenuItem ("New");
		fileMenu.add(n);
		fileMenu.addSeparator();

		open = new JMenuItem("Open");
		fileMenu.add(open);
		fileMenu.addSeparator();

		save = new JMenuItem("Save");
		fileMenu.add(save);
		fileMenu.addSeparator();

		exit = new JMenuItem("Exit");
		fileMenu.add(exit);

		JTextArea edit = new JTextArea(30,20);
		edit.setFont(new Font("Menlo", Font.PLAIN, 14));
		JScrollPane sp = new JScrollPane(edit);
		sp.setPreferredSize(new Dimension(450,600));
		ct.add(sp, BorderLayout.CENTER);

		MenuListener ml = new MenuListener(edit);
		n.addActionListener(ml);
		open.addActionListener(ml);
		save.addActionListener(ml);
		exit.addActionListener(ml);
	}

	private class MenuListener implements ActionListener {

		private JTextArea edit;
		private JFileChooser fc;

		public MenuListener (JTextArea edit){

			this.edit = edit;
			fc = new JFileChooser();
		}

		public void actionPerformed (ActionEvent e){

			//New action complete
			if (e.getSource() == n){
				edit.setText("");

			}

			//Open action complete
			else if (e.getSource() == open){
				
				int returnVal = fc.showOpenDialog(Javapad.this);
				Scanner inFile = null;

				if (returnVal == JFileChooser.APPROVE_OPTION){
					try{

						File file = new File (fc.getSelectedFile().getAbsolutePath());
						inFile = new Scanner(new FileReader(file));
						edit.setText("");

						while (inFile.hasNextLine()){
							edit.append(inFile.nextLine());
							edit.append("\n");
						}
					}
					catch(IOException ioe){

						ioe.printStackTrace();
						System.out.println("File not found");
					}
					finally{
						if(inFile != null)
							inFile.close();
					}
				}
			}
			

			else if (e.getSource() == save){

				int returnVal = fc.showOpenDialog(Javapad.this);
				PrintWriter outFile = null;

				if (returnVal == JFileChooser.APPROVE_OPTION)
			

					try{

						outFile = new PrintWriter(fc.getSelectedFile().getAbsolutePath());
						outFile.print(edit.getText());
					}
					catch (IOException ioe){

						ioe.printStackTrace();
						System.out.println("File not found");
					}
					finally{
						if (outFile != null)
							outFile.close();
					}
			}

			else if (e.getSource() == exit)

					System.exit(0);		
		}
	}
}
public class JavaTextEditor{

	public static void main (String[] args) {

		Javapad jp = new Javapad();
	}	
}
	
