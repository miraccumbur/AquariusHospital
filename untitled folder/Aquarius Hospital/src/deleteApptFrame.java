import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class deleteApptFrame extends JFrame {

private JPanel contentPane;
private JTable table;
DefaultTableModel model=new DefaultTableModel();
	Object [] columns= {"PERSONAL ID","DEPARTMENT","DOCTOR","DAY","MONTH","YEAR","TIME"};
	Object [] row=new Object[100];

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
	deleteApptFrame frame = new deleteApptFrame();
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
public deleteApptFrame() {
	setTitle("AQUARIUS APPOINTMENT SYSTEM");
	setBackground(new Color(230, 230, 250));
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 800, 650);
contentPane = new JPanel();
contentPane.setBackground(new Color(230, 230, 250));
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

JButton btnNewButton = new JButton("DELETE");

btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
btnNewButton.setBounds(413, 562, 165, 35);
contentPane.add(btnNewButton);

JButton btnNewButton_1 = new JButton("BACK");
btnNewButton_1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		mainFrame ma = new mainFrame();
		ma.setVisible(true);
		dispose();
	}
});
btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
btnNewButton_1.setBounds(609, 562, 165, 35);
contentPane.add(btnNewButton_1);

table = new JTable();

model.setColumnIdentifiers(columns);

table.setBounds(6, 6, 788, 547);
//contentPane.add(table);
JScrollPane scrollPane = new JScrollPane();
scrollPane.setBounds(6, 6, 788, 544);
scrollPane.setViewportView(table);
contentPane.add(scrollPane);
ResultSet rs=connection.showappt();
	
	model.setRowCount(0);
	try {
		while(rs.next()) {
			row[0]=rs.getString("personalid");
			row[1]=rs.getString("department");
			row[2]=rs.getString("doctor");
			row[3]=rs.getString("day");
			row[4]=rs.getString("month");
			row[5]=rs.getString("year");
			row[6]=rs.getString("apptTime");
			model.addRow(row);
		}
		
	} catch (Exception e2) {
		// TODO: handle exception
		e2.printStackTrace();
	}
	table.setModel(model);
	table.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		String doctor,day,month,year,time,department,personalid;
		personalid=String.valueOf(model.getValueAt(table.getSelectedRow(),0));
		department=String.valueOf(model.getValueAt(table.getSelectedRow(),1));
		doctor=String.valueOf(model.getValueAt(table.getSelectedRow(),2));
		day=String.valueOf(model.getValueAt(table.getSelectedRow(),3));
		month=String.valueOf(model.getValueAt(table.getSelectedRow(),4));
		year=String.valueOf(model.getValueAt(table.getSelectedRow(),5));
		time=String.valueOf(model.getValueAt(table.getSelectedRow(),6));
		String sql_ask="Delete  FROM appointment WHERE personalid='"+personalid+"' and "+"department='"+department+"' and doctor='"+
		doctor+"' and day='"+day+"' and month='"+month+"' and year='"+year+"' and apptTime='"+time+"'";
													 
		connection.delete(sql_ask);
		
		deleteApptFrame frame=new deleteApptFrame();
		frame.setVisible(true);
		dispose();
		
		
		
		
		
		
	}
});
		}
	});
}
}