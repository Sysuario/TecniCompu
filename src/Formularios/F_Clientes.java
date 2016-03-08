package Formularios;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import CAD.ModuloConexion;

import CAD.ModuloConexion;

public class F_Clientes extends JInternalFrame implements ActionListener {
	
	//VAriables--------------------------- 
			//private JLabel lblNombre;
			//private JLabel lblUsuario;
			
			
			
			Connection conectador=null;
			PreparedStatement pst = null;
			ResultSet rs=null;
			
			
			
			
	//----------------------------------------------------------------------------		

	
	
	public  F_Clientes(){
		initComponents();
		conectador= ModuloConexion.conexionDB();
		
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
	}

}
