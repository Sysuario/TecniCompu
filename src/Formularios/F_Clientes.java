package Formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import CAD.ModuloConexion;

public class F_Clientes extends JInternalFrame implements ActionListener {

	//VAriables--------------------------- ----------------------------------------------
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblEmail;
	private JLabel lblRecordatorio;	
	private JLabel lblBuscar;	
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextArea txtDireccion;
	private JTextField txtEmail;	
	private JTextField txtBuscador;	
	private JButton btnCrear;
	private JButton btnBuscador;
	private JButton btnActualizar;
	private JButton btnEliminar;	
	private JScrollPane scrollTabla;
	private DefaultTableModel modelo;
	private JTable tbBuscador;
	private TableRowSorter textoFiltrador;
		
	Connection conectador=null;
	PreparedStatement pst = null;
	ResultSet rs=null;		
	//----------------------------------------------------------------------------	--------------------	
	
	
	public  F_Clientes(){		
		conectador= ModuloConexion.conexionDB();
		initComponents();
		cargaTabla();
				
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
		String sql="update tbclientes set cliente=?,direccion=?,telefono=?,email=? where idclientes=?";		
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, txtNombre.getText());
			pst.setString(2, txtDireccion.getText());
			pst.setString(3, txtTelefono.getText());
			pst.setString(4, txtEmail.getText());
			pst.setString(5, txtId.getText());
			if ((txtNombre.getText().isEmpty()) || (txtTelefono.getText().isEmpty())){
				JOptionPane.showMessageDialog(null, "*Datos  obligatorios");

			} else {
				int modificacion=pst.executeUpdate();
				if(modificacion>0){
					JOptionPane.showMessageDialog(null, "Cliente Modificado");
					txtId.setText(null);
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
	
	private void borrar	(){
		String sql="delete from tbclientes where idclientes=?";
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, txtId.getText());
			int eliminacion=pst.executeUpdate();
			if(eliminacion>0){
				JOptionPane.showMessageDialog(null, "Cliente Eliminado");
				txtId.setText(null);
				txtNombre.setText(null);
				txtDireccion.setText(null);
				txtTelefono.setText(null);
				txtEmail.setText(null);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
		
	public DefaultTableModel cargaTabla(){
		String cSql = "SELECT * FROM tbclientes ORDER BY cliente ASC";
		try{
			pst = conectador.prepareStatement(cSql);
			rs = pst.executeQuery();		
			
	        while(rs.next())
	        {
	            //SE CREA UNA ARRAY QUE SERA UNA DE LAS FILAS D ELA TABLA
	            Object[] fila = new Object[5];// HAY 4 COLUMNAS EN LA TABLA
	            // SE RELLENA CADA POSICION DEL ARRAY CON UNA DE LAS COLUMNAS DE LA TABLA EN LA BASE DE DATOS.
	            for(int i=0;i<5;i++)
	            {
	                fila[i]=rs.getObject(i+1); // EL PRIMER INDICE EN RESULTADO ES EL 1, NO EL CERO, POR ESO SE SUMA 1.
	            }
	            // SE AÑADE AL MODELO LA FILA COMPLETA
	            modelo.addRow(fila);
	        }			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);			
		}
		 return(modelo);		
	}	

	public void limpiarTabla() {
		for (int i = 0; i < tbBuscador.getRowCount(); i++) {
	           modelo.removeRow(i);
	           i-=1;
	       }
	}
	
	public void actionPerformed(ActionEvent evento) {		
	}	
	
	public void filtro() {
		//Obtenemos el valor del JTextField "txtBuscador" para el filtro
		String filtro ="(?i)"+ txtBuscador.getText();// el "(?i)"+ es para que no se sencible a mayusculas y minisculas
		int columna = 1;// es la columna 1 donde estan los nombres de clientes
		textoFiltrador.setRowFilter(RowFilter.regexFilter(filtro, columna));
		
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
		
				
		//etiqueta de buscar-------------------------------------------------------------------------------------------------------
		lblBuscar = new JLabel();
		lblBuscar.setText("Filtro Buscador:");
		lblBuscar.setHorizontalAlignment(JLabel.LEFT);
		lblBuscar.setFont(fuente);
		lblBuscar.setBounds(50,30,100,20);
		this.add(lblBuscar);
		
		//------------------------------------------
		

		//tabla buscador--------------------------------------------------------------------------------------

		modelo = new DefaultTableModel();
		tbBuscador = new JTable();
		tbBuscador.setModel(modelo);
		tbBuscador.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {// para al seleccionar fila cargue en los JTextField
				txtId.setText("");
				txtNombre.setText("");
				txtDireccion.setText("");
				txtTelefono.setText("");
				txtEmail.setText("");
				int row = tbBuscador.rowAtPoint(e.getPoint());
				txtId.setText(tbBuscador.getValueAt(row, 0).toString());
				txtNombre.setText(tbBuscador.getValueAt(row, 1).toString());
				txtDireccion.setText(tbBuscador.getValueAt(row, 2).toString());
				txtTelefono.setText(tbBuscador.getValueAt(row, 3).toString());
				txtEmail.setText(tbBuscador.getValueAt(row, 4).toString());    

			}
		});
		scrollTabla = new JScrollPane();
		modelo.addColumn("Id");
		modelo.addColumn("Cliente");
		modelo.addColumn("Direccion");
		modelo.addColumn("Telefono");
		modelo.addColumn("Email");	        
		tbBuscador.setEnabled(false);
		scrollTabla.setBounds(50,60,500,100);
		scrollTabla.setViewportView(tbBuscador);
		this.add(scrollTabla);
		//--------------------------------------------------------------------------------------------------

		//etiqueta id----------------------------------------------------------------------------
		lblId = new JLabel();
		lblId.setText("Id:");
		lblId.setHorizontalAlignment(JLabel.RIGHT);
		lblId.setFont(fuente);
		lblId.setBounds(80,190,150,20);
		this.add(lblId);
		//--------------------------------------------------------------------------------------

		//cuadro texto id-------------------------------------------------------------------------
		txtId=new JTextField();
		txtId.setBounds(250,190,50,20);
		txtId.setEditable(false);
		this.add(txtId);
		//-------------------------------------------------------------------------------------------
		
		//etiqueta nombre----------------------------------------------------------------------------
		lblNombre = new JLabel();
		lblNombre.setText("* Nombre y Apellido:");
		lblNombre.setHorizontalAlignment(JLabel.RIGHT);
		lblNombre.setFont(fuente);
		lblNombre.setBounds(80,220,150,20);
		this.add(lblNombre);
		//--------------------------------------------------------------------------------------

		//cuadro texto nombre-------------------------------------------------------------------------
		txtNombre=new JTextField();
		txtNombre.setBounds(250,220,250,20);
		this.add(txtNombre);
		//-------------------------------------------------------------------------------------------
		
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
				limpiarTabla();
				cargaTabla();					
			}
		});    
		this.add(btnCrear);
		//---------------------------------------------------------------------------------------------------
		

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
				limpiarTabla();
				cargaTabla();
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
					borrar();
					limpiarTabla();
					cargaTabla();

				}    
			}
		});     
		this.add(btnEliminar);
		//--------------------------------------------------------------------------------------------		
		
		//etiqueta recordatorio-------------------------------------------------------------------------
		lblRecordatorio = new JLabel();
		lblRecordatorio.setText("*Datos obligatorios");
		lblRecordatorio.setHorizontalAlignment(JLabel.CENTER);
		lblRecordatorio.setFont(fuente);
		lblRecordatorio.setBounds(200,450,200,20);
		this.add(lblRecordatorio);
		//--------------------------------------------------------------------------------------------------------
		
		//cuadro texto buscador------------------------------------------------------------------------------------
		txtBuscador=new JTextField();
		txtBuscador.setBounds(150,30,250,20);
		txtBuscador.requestFocus() ;
		txtBuscador.addKeyListener(new KeyAdapter() {// evento que captura al presinar teclas con sus 3 metodos
			// en los tres metodos se captura el evento llamando al metodo filto 

			public void keyTyped(final KeyEvent e) {
				filtro();
			}
			public void keyReleased(KeyEvent e) {
				filtro();
			}
			public void keyPressed(KeyEvent e) {
				filtro();
			}

		});

		textoFiltrador = new TableRowSorter(modelo);
		// Añadimos al Jtable el filtro textoFiltrador
		tbBuscador.setRowSorter(textoFiltrador);
		this.add(txtBuscador);
		//-----------------------------------------
	}

	
}
