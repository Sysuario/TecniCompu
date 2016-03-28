/*
 * este es el formulario del loguin
 * donde se llama la clase Conexion en el paquete CAD
 * si pasa la verificacion llama F_Principal
 * 
 * 
 * */
 
package Formularios;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

import CAD.ModuloConexion;


public class Login extends JFrame {
	
	//VAriables--------------------------- 
	private JLabel etImagen;
	private JTextField txtUsuario;
	private JPasswordField txtPass;
	private JButton btnLogin; 

	Connection conectador=null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	
	//------------------------------------
	
	//--Constructor-----------------------------------------------------------------------------
	public Login(){//

		initComponents();
		conectador= ModuloConexion.conexionDB();// llama la instancia de conexion
		if (conectador!= null){
			etImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/conectado.png")));
		}else{
			etImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/NOconectado.png")));
		}
	}
	//------------------------------------------------------------------------------------------
	// Metodo para loguear----------------------------------------------------------------------
	public void loguear(){
		String sql="select * from tbusuarios where login=? and pass=?";
		try {
			pst=conectador.prepareStatement(sql);
			pst.setString(1,txtUsuario.getText());
			pst.setString(2,txtPass.getText());
			rs=pst.executeQuery();
			if(rs.next()){
				
				String perfil=rs.getString(6);// recibe el dato del campo perfil de la tabla
				if (perfil.equals("A")){
					F_Principal principal=new F_Principal();
					principal.setHabilitar_menuArchivoUsuarios();
					principal.setHabilitar_menuReportesServicios();
					principal.setMostrarUsuario_etUsuario(rs.getString(2));//recibe el campo usuario
					principal.setVisible(true);
					this.dispose();
					
				}else{
					F_Principal principal=new F_Principal();
					principal.setVisible(true);
					this.dispose();
				}
				


			}else{
				JOptionPane.showMessageDialog(null,"Datos Incorrectos");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);

		}

	}
	//------------------------------------------------------------------------------------------
	//Componentes-------------------------------------------------------------------------------
	private void initComponents() {
		this.setSize(400,200);
		this.setResizable(false);
		this.setTitle("Logueo-System");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);

		Font fuente=new Font("Tahoma",Font.PLAIN,11);

		//boton de logueo------------------------------------
		btnLogin = new JButton();
		btnLogin.setText("Login");
		btnLogin.setFont(fuente);
		btnLogin.setBounds(230,100,50,20);
		btnLogin.setFocusable(false);
		btnLogin.setMargin(new Insets(1, 1, 1, 1));
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				loguear();     
			}
		});     
		this.add(btnLogin);
        //------------------------------------------
        //etiqueta------------------------------------
		JLabel etUsuario = new JLabel();
		etUsuario.setText("Usuario:");
		etUsuario.setHorizontalAlignment(4);
		etUsuario.setFont(fuente);
		etUsuario.setBounds(50,20,50,20);
		this.add(etUsuario);
		//------------------------------------------
        //etiqueta------------------------------------
		JLabel etPass = new JLabel();
		etPass.setText("Pass:");
		etPass.setHorizontalAlignment(4);
		etPass.setFont(fuente);
		etPass.setBounds(50,60,50,20);
		this.add(etPass);
		//------------------------------------------
        //etiqueta imagen--------------------------
        etImagen = new JLabel();
		etImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/NOconectado.png")));
		etImagen.setBounds(100,80,50,50);
		this.add(etImagen);
		//------------------------------------------
        //cuadro texto usuario-----------------------
		txtUsuario=new JTextField();
		txtUsuario.setBounds(100,20,200,20);
		this.add(txtUsuario);
		//-----------------------------------------
        //cuadro texto pass------------------------
		txtPass=new JPasswordField();
		txtPass.setBounds(100,60,200,20);
		this.add(txtPass);
		//-----------------------------------------
        setVisible(true);
	}   

	public static void main(String[] args) {
		new Login();
	}
}

