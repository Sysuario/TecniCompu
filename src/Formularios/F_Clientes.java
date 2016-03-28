package Formularios;

import java.awt.Color;

import java.awt.Font;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import CAD.ModuloConexion;



public class F_Clientes extends JInternalFrame implements ActionListener {
	
	//VAriables--------------------------- 
	private JLabel lblNombre;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblEmail;
	private JLabel lblRecordatorio;
	

	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextArea txtDireccion;
	private JTextField txtEmail;
	
	private JTextField txtBuscador;
	
	private JTable tbBuscador;
	

	private JButton btnCrear;
	private JButton btnBuscador;
	private JButton btnActualizar;
	private JButton btnEliminar;
	
	private JScrollPane scrollTabla;
	private DefaultTableModel modelo;
	
	private Object[][] data;
	
	Connection conectador=null;
	PreparedStatement pst = null;
	ResultSet rs=null;	
	Statement s = null;
	ResultSetMetaData rsmd=null;
	


	//----------------------------------------------------------------------------		

	
	
	public  F_Clientes(){
		
		initComponents();
		conectador= ModuloConexion.conexionDB();
		
	}
		
	
	private void crear(){
		String sql="insert into tbclientes(cliente,direccion,telefono,email) values (?,?,?,?) ";
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, txtNombre.getText());
			pst.setString(2, txtDireccion.getText());
			pst.setString(3, txtTelefono.getText());
			pst.setString(4, txtEmail.getText());
			
			if ((txtNombre.getText().isEmpty()) || (txtTelefono.getText().isEmpty())){
				JOptionPane.showMessageDialog(null, "*Datos obligatorios");

			} else {
				int adicion=pst.executeUpdate();
				if(adicion>0){
					JOptionPane.showMessageDialog(null, "Adicionado Cliente");
					txtNombre.setText(null);
					txtDireccion.setText(null);
					txtTelefono.setText(null);
					txtEmail.setText(null);
				}
			}				

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void editar	(){
		String sql="update tbclientes set cliente=?,direccion=?,telefono=?,email=? where cliente=?";
		
		
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, txtNombre.getText());
			pst.setString(2, txtDireccion.getText());
			pst.setString(3, txtTelefono.getText());
			pst.setString(4, txtEmail.getText());
			if ((txtNombre.getText().isEmpty()) || (txtTelefono.getText().isEmpty())){
				JOptionPane.showMessageDialog(null, "*Datos  obligatorios");

			} else {
				int modificacion=pst.executeUpdate();
				if(modificacion>0){
					JOptionPane.showMessageDialog(null, "Cliente Modificado");
					txtNombre.setText(null);
					txtDireccion.setText(null);
					txtTelefono.setText(null);
					txtEmail.setText(null);
				}
			}		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public Object[][] Carga_Matriz() {
		int registros=0;
		String sql = "Select cliente,direccion,telefono,email FROM tbclientes ";
		String sql2 = "SELECT COUNT(*) AS total FROM tbclientes";
		//obtenemos la cantidad de registros existentes en la tabla
		try{
			pst = conectador.prepareStatement(sql2);
			rs = pst.executeQuery();
			rs.next();
			registros = rs.getInt("total");
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		//se crea una matriz con tantas filas y columnas que necesite
		data = new Object[registros][4];
		//realizamos la consulta sql y llenamos los datos en la matriz "Object"
		try{
			pst = conectador.prepareStatement(sql);
			rs = pst.executeQuery();
			int i = 0;
			while(rs.next()){
				data[i][0] = rs.getString( "cliente" );
				data[i][1] = rs.getString( "direccion" );
				data[i][2] = rs.getString( "telefono" );
				data[i][3] = rs.getString( "email" );
				i++;
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return data;
	}


	
	public void actionPerformed(ActionEvent evento) {
		
		
	}
	
	private void initComponents() {
		this.setBounds(0,25,600,500);
		this.setResizable(false);
		this.setTitle("Clientes-System");
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setClosable(true);
		this.setResizable(false);
		this.setLayout(null);
		Font fuente=new Font("Tahoma",Font.BOLD,11);
		
		
		//cuadro texto buscador-----------------------
		txtBuscador=new JTextField();
		txtBuscador.setBounds(50,30,250,20);
		this.add(txtBuscador);
		//-----------------------------------------
		
				
		//boton de buscador------------------------------------
		btnBuscador = new JButton();
		btnBuscador.setBounds(300,30,20,20);
		btnBuscador.setFocusable(false);
		btnBuscador.setMargin(new Insets(5, 5, 5, 5));
		btnBuscador.setBackground(Color.WHITE);
		ImageIcon iconoBuscador = new ImageIcon(getClass().getResource("/Iconos/buscador.png"));
		ImageIcon iconoEscalaBuscador = new ImageIcon(iconoBuscador.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_DEFAULT));
		btnBuscador.setIcon(iconoEscalaBuscador);
		btnBuscador.setToolTipText("Buscar");
		btnBuscador.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX();     
			}
		});    
		this.add(btnBuscador);
		//------------------------------------------
		

		//tabla buscador--------------------------------------------------------------------------------------


		modelo = new DefaultTableModel();
		tbBuscador = new JTable();
		scrollTabla = new JScrollPane();

		String titulos[] = { "Cliente", "Direccion", "Telefono", "Email"};
		Object informacion[][] =Carga_Matriz();

		tbBuscador = new JTable(informacion, titulos);
		tbBuscador.setEnabled(false);
		//tbBuscador.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollTabla.setBounds(50,60,500,100);
		scrollTabla.setViewportView(tbBuscador);
		this.add(scrollTabla);




		//----------------------------------------------------------------------------------------
		
		
		
		
		
		//etiqueta nombre------------------------------------
		lblNombre = new JLabel();
		lblNombre.setText("* Nombre y Apellido:");
		lblNombre.setHorizontalAlignment(JLabel.RIGHT);
		lblNombre.setFont(fuente);
		lblNombre.setBounds(80,220,150,20);
		this.add(lblNombre);
		//------------------------------------------

		//cuadro texto nombre-----------------------
		txtNombre=new JTextField();
		txtNombre.setBounds(250,220,250,20);
		this.add(txtNombre);
		//-----------------------------------------
		
		//etiqueta telefono------------------------------------
		lblTelefono = new JLabel();
		lblTelefono.setText("* Telefono:");
		lblTelefono.setHorizontalAlignment(JLabel.RIGHT);
		lblTelefono.setFont(fuente);
		lblTelefono.setBounds(80,250,150,20);

		this.add(lblTelefono);
		//------------------------------------------

		//cuadro texto telefono-----------------------
		txtTelefono=new JTextField();
		txtTelefono.setBounds(250,250,150,20);
		this.add(txtTelefono);
		//-----------------------------------------
		
		//etiqueta direccion------------------------------------
		lblDireccion = new JLabel();
		lblDireccion.setText("Direccion:");
		lblDireccion.setHorizontalAlignment(JLabel.RIGHT);
		lblDireccion.setFont(fuente);
		lblDireccion.setBounds(80,295,150,20);

		this.add(lblDireccion);
		//------------------------------------------

		//Area de  texto direccion-----------------------	
		txtDireccion=new JTextArea();
		Border borde = BorderFactory.createLineBorder(Color.GRAY);// para darle borde al Area de texto
		txtDireccion.setBorder(BorderFactory.createCompoundBorder(borde, 
	            BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		txtDireccion.setBounds(250,280,250,50);
		this.add(txtDireccion);
		//-----------------------------------------
		
		//etiqueta email------------------------------------
		lblEmail = new JLabel();
		lblEmail.setText("Email:");
		lblEmail.setHorizontalAlignment(JLabel.RIGHT);
		lblEmail.setFont(fuente);
		lblEmail.setBounds(80,340,150,20);

		this.add(lblEmail);
		//------------------------------------------

		//cuadro texto email-----------------------
		txtEmail=new JTextField();
		txtEmail.setBounds(250,340,250,20);
		this.add(txtEmail);
		//-----------------------------------------
		
		
		//---------------------------------------------------------------------------------------------------------------
		//boton de crear------------------------------------
		btnCrear = new JButton();
		btnCrear.setBounds(195,380,50,50);
		btnCrear.setFocusable(false);
		btnCrear.setMargin(new Insets(5, 5, 5, 5));
		btnCrear.setBackground(Color.WHITE);
		ImageIcon iconoCrear = new ImageIcon(getClass().getResource("/Iconos/crear.png"));
		ImageIcon iconoEscalaCrear = new ImageIcon(iconoCrear.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
		btnCrear.setIcon(iconoEscalaCrear);
		btnCrear.setToolTipText("Agregar Usuario");
		btnCrear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				crear();     
			}
		});    
		this.add(btnCrear);
		//------------------------------------------

		

		//boton de Actualizar------------------------------------
		btnActualizar = new JButton();
		btnActualizar.setBounds(275,380,50,50);
		btnActualizar.setFocusable(false);
		btnActualizar.setMargin(new Insets(5, 5, 5, 5));
		btnActualizar.setBackground(Color.WHITE);
		ImageIcon iconoActualizar = new ImageIcon(getClass().getResource("/Iconos/edit.png"));
		ImageIcon iconoEscalaActualizar = new ImageIcon(iconoActualizar.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
		btnActualizar.setIcon( iconoEscalaActualizar);
		btnActualizar.setToolTipText("Actualizar Usuario");
		btnActualizar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				editar();     
			}
		});     
		this.add(btnActualizar);
		//------------------------------------------

		//boton de Eliminar------------------------------------
		btnEliminar = new JButton();
		btnEliminar.setBounds(355,380,50,50);
		btnEliminar.setFocusable(false);
		btnEliminar.setMargin(new Insets(5, 5, 5, 5));
		btnEliminar.setBackground(Color.WHITE);
		ImageIcon iconoEliminar = new ImageIcon(getClass().getResource("/Iconos/borrar.png"));
		ImageIcon iconoEscalaEliminar = new ImageIcon(iconoEliminar.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
		btnEliminar.setIcon(iconoEscalaEliminar);
		btnEliminar.setToolTipText("Eliminar Usuario");
		btnEliminar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				int eliminar = JOptionPane.showConfirmDialog(null,"¿Seguro quieres eliminar usuario?","Atencion",JOptionPane.YES_NO_OPTION);
				if (eliminar== JOptionPane.YES_NO_OPTION){
					//borrar();

				}    
			}
		});     
		this.add(btnEliminar);
		//------------------------------------------
		
		
		//etiqueta recordatorio------------------------------------
		lblRecordatorio = new JLabel();
		lblRecordatorio.setText("*Datos obligatorios");
		lblRecordatorio.setHorizontalAlignment(JLabel.CENTER);
		lblRecordatorio.setFont(fuente);
		lblRecordatorio.setBounds(200,450,200,20);
		this.add(lblRecordatorio);
		//------------------------------------------
	}

}
