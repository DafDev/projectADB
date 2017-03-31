package ATM;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;


public class Interface extends JFrame {


	private JPanel contentPane;
	private Security security= new Security(this);
	private JPasswordField textField_password;
	JButton btnValidate;
	JPanel panel1;
	private JPanel panel_table;
	JTextArea textArea_consult = new JTextArea();
	private JTextField textField_userid;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	
	
	public Interface(){
		
		super();
		initGUI();
		
		
	}
	
	
	public void initGUI(){
		//Initialization
		
		panel1 = new JPanel();
		panel_table = new JPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
						.addComponent(panel_table, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_table, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
					.addGap(344))
		);
		
		JLabel lblNewLabel = new JLabel("USER AUTHENTICATED");
		
		table = new JTable();
		
		
		GroupLayout gl_panel_tabla = new GroupLayout(panel_table);
		gl_panel_tabla.setHorizontalGroup(
			gl_panel_tabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_tabla.createSequentialGroup()
					.addGroup(gl_panel_tabla.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_tabla.setVerticalGroup(
			gl_panel_tabla.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_tabla.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_table.setLayout(gl_panel_tabla);
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[]{132, 132, 132, 132, 132, 0};
		gbl_panel1.rowHeights = new int[] {30, 30};
		gbl_panel1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, 0.0};
		panel1.setLayout(gbl_panel1);
		
		JLabel lbl_userid = new JLabel("Enter user ID");
		GridBagConstraints gbc_lbl_userid = new GridBagConstraints();
		gbc_lbl_userid.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_userid.gridx = 0;
		gbc_lbl_userid.gridy = 0;
		panel1.add(lbl_userid, gbc_lbl_userid);
		
		textField_userid = new JTextField();
		textField_userid.setColumns(10);
		GridBagConstraints gbc_textField_userid = new GridBagConstraints();
		gbc_textField_userid.gridwidth = 3;
		gbc_textField_userid.fill = GridBagConstraints.BOTH;
		gbc_textField_userid.insets = new Insets(0, 0, 5, 5);
		gbc_textField_userid.gridx = 1;
		gbc_textField_userid.gridy = 0;
		panel1.add(textField_userid, gbc_textField_userid);
		
		btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
									
				String user_id= new String(textField_userid.getText()).trim();
				String password= new String(textField_password.getPassword()).trim();
				if(security.validateUser(user_id,password)){
					panel_table.setVisible(true);
					btnValidate.setEnabled(false);
					textField_password.setEditable(false);					
				}
				else{
					
					JOptionPane.showMessageDialog(null,"Invalid user password. \n"+"Log in again","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnValidate = new GridBagConstraints();
		gbc_btnValidate.gridheight = 2;
		gbc_btnValidate.fill = GridBagConstraints.BOTH;
		gbc_btnValidate.gridx = 4;
		gbc_btnValidate.gridy = 0;
		panel1.add(btnValidate, gbc_btnValidate);
		
		JLabel lbl_pin = new JLabel("Enter PIN");
		GridBagConstraints gbc_lbl_pin = new GridBagConstraints();
		gbc_lbl_pin.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_pin.gridx = 0;
		gbc_lbl_pin.gridy = 1;
		panel1.add(lbl_pin, gbc_lbl_pin);
		textField_password = new JPasswordField();
		
		
		textField_password.setToolTipText("");
		textField_password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent a) {
				
				if(a.getKeyCode()==KeyEvent.VK_ENTER)
					btnValidate.doClick();
			}
		});
		textField_password.setColumns(10);
		GridBagConstraints gbc_textField_password = new GridBagConstraints();
		gbc_textField_password.gridwidth = 3;
		gbc_textField_password.fill = GridBagConstraints.BOTH;
		gbc_textField_password.insets = new Insets(0, 0, 0, 5);
		gbc_textField_password.gridx = 1;
		gbc_textField_password.gridy = 1;
		panel1.add(textField_password, gbc_textField_password);
		contentPane.setLayout(gl_contentPane);
		panel_table.setVisible(false);
	}
	
}


