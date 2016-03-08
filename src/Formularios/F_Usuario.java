package Formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.sql.*;
import CAD.ModuloConexion;

public class F_Usuario extends JInternalFrame implements ActionListener {
	
	//VAriables--------------------------- 
		private JLabel lblNombre;
		private JLabel lblUsuario;
		private JLabel lblPass;
		private JLabel lblRecordatorio;
		private JLabel lblPerfil;
		
		private JTextField txtNombre;
		private JTextField txtUsuario;
		private JTextField txtPass;
		//private JTextField txtTelefono;
		private JComboBox txtPerfil;
		
		private JButton btnCrear;
		private JButton btnBuscar;
		private JButton btnActualizar;
		private JButton btnEliminar;
		
		
		Connection conectador=null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		
		
		
		
//----------------------------------------------------------------------------		

	
	
	public  F_Usuario(){
		initComponents();
		conectador= ModuloConexion.conexionDB();
		
	}
	
	private void consultar(String buscador){
		String sql="select * from tbusuarios where login=?";
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, buscador);
			rs=pst.executeQuery();
			if (rs.next()) {
				txtNombre.setText(rs.getString(2));
				txtUsuario.setText(rs.getString(4));
				txtPass.setText(rs.getString(5));
				String perfil=(rs.getString(6));
				if (perfil.equals("A")) {
					txtPerfil.setSelectedIndex(2);
				} else {
					txtPerfil.setSelectedIndex(1);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Usuario no encontrado");
				txtNombre.setText(null);
				txtUsuario.setText(null);
				txtPass.setText(null);
				txtPerfil.setSelectedIndex(0);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}


	}
	private void crear(){
		String sql="insert into tbusuarios(usuario,login,pass,nivel) values (?,?,?,?) ";
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, txtNombre.getText());
			pst.setString(2, txtUsuario.getText());
			pst.setString(3, txtPass.getText());
			String perfil=(txtPerfil.getSelectedItem().toString());
			if (perfil.equals("Administrador")) {
				pst.setString(4, "A");
			} else {
				pst.setString(4, "T");
			}
			if ((txtNombre.getText().isEmpty()) || (txtUsuario.getText().isEmpty()) || (txtPass.getText().isEmpty()) || (perfil.equals(""))){
				JOptionPane.showMessageDialog(null, "*Todos los datos son obligatorios");

			} else {
				int adicion=pst.executeUpdate();
				if(adicion>0){
					JOptionPane.showMessageDialog(null, "Adicionado usuario");
					txtNombre.setText(null);
					txtUsuario.setText(null);
					txtPass.setText(null);
					txtPerfil.setSelectedIndex(0);
				}
			}				

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void editar	(){
		String sql="update tbusuarios set usuario=?,login=?,pass=?,nivel=? where login=?";
		
		
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, txtNombre.getText());
			pst.setString(2, txtUsuario.getText());
			pst.setString(3, txtPass.getText());
			String perfil=(txtPerfil.getSelectedItem().toString());
			if (perfil.equals("Administrador")) {
				pst.setString(4, "A");
			} else {
				pst.setString(4, "T");
			}
			pst.setString(5, txtUsuario.getText());
			if ((txtNombre.getText().isEmpty()) || (txtUsuario.getText().isEmpty()) || (txtPass.getText().isEmpty()) || (perfil.equals(""))){
				JOptionPane.showMessageDialog(null, "*Todos los datos son obligatorios");

			} else {
				int modificacion=pst.executeUpdate();
				if(modificacion>0){
					JOptionPane.showMessageDialog(null, "Usuario Modificado");
					txtNombre.setText(null);
					txtUsuario.setText(null);
					txtPass.setText(null);
					txtPerfil.setSelectedIndex(0);
				}
			}		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void borrar	(){
		String sql="delete from tbusuarios where login=?";
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			int eliminacion=pst.executeUpdate();
			if(eliminacion>0){
				JOptionPane.showMessageDialog(null, "Usuario Eliminado");
				txtNombre.setText(null);
				txtUsuario.setText(null);
				txtPass.setText(null);
				txtPerfil.setSelectedIndex(0);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
    public void actionPerformed(ActionEvent evento){

		

	}
	
	private void initComponents() {
		//this.setSize(500,400);
		this.setBounds(50,50,500,400);
		this.setResizable(false);
		this.setTitle("Usuario-System");
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		//this.setMaximizable(true);
		//this.setIconifiable(true);
		this.setClosable(true);
		this.setResizable(false);
		this.setLayout(null);

		Font fuente=new Font("Tahoma",Font.BOLD,11);
		
		
		//etiqueta nombre------------------------------------
		lblNombre = new JLabel();
		lblNombre.setText("Nombre y Apellido:");
		lblNombre.setHorizontalAlignment(JLabel.RIGHT);
		lblNombre.setFont(fuente);
		lblNombre.setBounds(30,20,150,20);
		this.add(lblNombre);
		//------------------------------------------

		//cuadro texto nombre-----------------------
		txtNombre=new JTextField();
		txtNombre.setBounds(200,20,250,20);
		this.add(txtNombre);
		//-----------------------------------------

		//etiqueta usuario------------------------------------
		lblUsuario = new JLabel();
		lblUsuario.setText("Usuario:");
		lblUsuario.setHorizontalAlignment(JLabel.RIGHT);
		lblUsuario.setFont(fuente);
		lblUsuario.setBounds(30,50,150,20);
		
		this.add(lblUsuario);
		//------------------------------------------

		//cuadro texto usuario-----------------------
		txtUsuario=new JTextField();
		txtUsuario.setBounds(200,50,150,20);
		//txtUsuario.requestFocus();
		this.add(txtUsuario);
		//-----------------------------------------

		//etiqueta pass------------------------------------
		lblPass = new JLabel();
		lblPass.setText("Password:");
		lblPass.setHorizontalAlignment(JLabel.RIGHT);
		lblPass.setFont(fuente);
		lblPass.setBounds(30,80,150,20);
		this.add(lblPass);
		//------------------------------------------

		//cuadro texto pass-----------------------
		txtPass=new JTextField();
		txtPass.setBounds(200,80,150,20);
		this.add(txtPass);
		//-----------------------------------------
		
		//etiqueta perfil------------------------------------
		lblPerfil = new JLabel();
		lblPerfil.setText("Perfil:");
		lblPerfil.setHorizontalAlignment(JLabel.RIGHT);
		lblPerfil.setFont(fuente);
		lblPerfil.setBounds(30,110,150,20);
		this.add(lblPerfil);
		//------------------------------------------

		//cuadro texto perfi-----------------------
		String[] opcPerfil = { "","Tecnico", "Administrador"};
		txtPerfil=new JComboBox(opcPerfil);
		txtPerfil.setBounds(200,110,150,20);
		txtPerfil.setSelectedIndex(0);
		this.add(txtPerfil);
		//-----------------------------------------
		
		//etiqueta recordatorio------------------------------------
		lblRecordatorio = new JLabel();
		lblRecordatorio.setText("*Todos los datos son obligatorios");
		lblRecordatorio.setHorizontalAlignment(JLabel.CENTER);
		lblRecordatorio.setFont(fuente);
		lblRecordatorio.setBounds(150,250,200,20);
		this.add(lblRecordatorio);
		//------------------------------------------
		
		//boton de crear------------------------------------
		btnCrear = new JButton();
		btnCrear.setBounds(100,150,50,50);
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

		//boton de Buscar------------------------------------
		btnBuscar = new JButton();
		btnBuscar.setBounds(175,150,50,50);
		btnBuscar.setFocusable(false);
		btnBuscar.setMargin(new Insets(5, 5, 5, 5));
		btnBuscar.setBackground(Color.WHITE);
		ImageIcon iconoBuscar = new ImageIcon(getClass().getResource("/Iconos/buscar.png"));
		ImageIcon iconoEscalaBuscar = new ImageIcon(iconoBuscar.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
		btnBuscar.setIcon(iconoEscalaBuscar);
		btnBuscar.setToolTipText("Buscar Usuario");
		btnBuscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				String busqueda;  
				busqueda = JOptionPane.showInputDialog(null,"¿Usuario a buscar?","Busqueda",JOptionPane.QUESTION_MESSAGE);
				consultar(busqueda);

			}
		}); 
		this.add(btnBuscar);
		//------------------------------------------

		//boton de Actualizar------------------------------------
		btnActualizar = new JButton();
		btnActualizar.setBounds(250,150,50,50);
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
		btnEliminar.setBounds(325,150,50,50);
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

				}    
			}
		});     
		this.add(btnEliminar);
		//------------------------------------------
	}
}

	
