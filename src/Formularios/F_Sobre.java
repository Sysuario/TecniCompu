package Formularios;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class F_Sobre extends JDialog implements ActionListener {
	
	private JLabel etImagenGNU;
	private JLabel etMensaje;
	
	public F_Sobre(){

		initComponents();
	}
	
	public void actionPerformed(ActionEvent evento){

		this.setVisible(true);

	}
	
	private void initComponents() {
		this.setSize(400,200);
		this.setResizable(false);
		this.setTitle("Sobre-System");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);

		Font fuente=new Font("Tahoma",Font.BOLD,13);

		
        //------------------------------------------
        //etiqueta sobre----------------------------
		etMensaje = new JLabel();
		etMensaje.setText("Proyecto de practica GNU");
		etMensaje.setHorizontalAlignment(JLabel.CENTER);
		etMensaje.setVerticalAlignment(JLabel.CENTER);
		etMensaje.setFont(fuente);
		etMensaje.setBounds(100,25,200,25);
		this.add(etMensaje);
		//------------------------------------------
        
		//etiqueta imagen GNU--------------------------
        etImagenGNU = new JLabel(){
        	public void paintComponent(Graphics g) { 
        		Image image = ((ImageIcon)getIcon()).getImage(); 
        		g.drawImage(image,0,0,getWidth(),getHeight(),this); 
        		}
        };
        etImagenGNU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/gnu.png")));
        etImagenGNU.setBounds(150,50,100,100);
		this.add(etImagenGNU);
		//---------------------------------------------------------------------------------------
        
		setModal(true);
		//setVisible(true);
	}   

		

}
