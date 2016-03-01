package Formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class F_Usuario extends JInternalFrame implements ActionListener {
	
	//VAriables--------------------------- 
		private JLabel lblNombre;
		private JLabel lblUsuario;
		private JLabel lblPass;
		//private JLabel lblTelefono;
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
		
		
		
		//private JButton btnLogin;
//----------------------------------------------------------------------------		

	
	
	public  F_Usuario(){
		initComponents();
		
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
		String[] opcPerfil = { "Tecnico", "Administrador"};
		txtPerfil=new JComboBox(opcPerfil);
		txtPerfil.setBounds(200,110,150,20);
		txtPerfil.setSelectedIndex(0);
		this.add(txtPerfil);
		//-----------------------------------------
		
		//boton de crear------------------------------------
		btnCrear = new JButton();
		btnCrear.setBounds(100,150,50,50);
		btnCrear.setFocusable(false);
		btnCrear.setMargin(new Insets(5, 5, 5, 5));
		btnCrear.setBackground(Color.WHITE);
		ImageIcon iconoCrear = new ImageIcon(getClass().getResource("/Iconos/crear.png"));
		ImageIcon iconoEscalaCrear = new ImageIcon(iconoCrear.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
		btnCrear.setIcon(iconoEscalaCrear);
		/*btnCrear.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evento){
						loguear();     
					}
				}); */    
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
		/*btnCrear.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent evento){
								loguear();     
							}
						}); */    
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
		/*btnCrear.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent evento){
										loguear();     
									}
								}); */    
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
		/*btnCrear.addActionListener(new ActionListener(){
											public void actionPerformed(ActionEvent evento){
												loguear();     
											}
										}); */    
		this.add(btnEliminar);
		//------------------------------------------
	}
}

	
