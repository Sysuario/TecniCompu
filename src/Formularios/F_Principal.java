package Formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




public class F_Principal extends JFrame {
	
	
	
	//VAriables------------------------------------------------------------------
	
	private JMenuBar barraMenu;
	
	private JMenu menuArchivo;
	private JMenuItem menuArchivoClientes;
	private JMenuItem menuArchivoOS;
	public static JMenuItem menuArchivoUsuarios;
	
	private JMenu menuReportes;
	public static JMenuItem menuReportesServicios;
	
	private JMenu menuAyuda;
	private JMenuItem menuAyudaSobre;
	
	private JMenu menuOpciones;
	private JMenuItem menuOpcionesSalir;
	
	private JDesktopPane escritorioTrabajo;
	
	private JLabel etImagenGNU;
	
	private JLabel etFecha;
	
	public static JLabel etUsuario;
	
	
	//-----------------------------------------------------------------------------------------

	public F_Principal(){
		initComponents();

	}

	private void initComponents() {
		this.setSize(800,600);
		this.setResizable(false);
		this.setTitle("Principal-System");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		Font fuente=new Font("Tahoma",Font.BOLD,12);
		//-el Menu---------------------------------------------------------------------------
		barraMenu = new JMenuBar();
		barraMenu.setFont(fuente);
		barraMenu.setBounds(0,0,800,25);
		
		menuArchivo = new JMenu("Archivo");
		menuArchivoClientes = new JMenuItem();
		menuArchivoClientes.setText("Clientes");
		menuArchivo.add(menuArchivoClientes);
		menuArchivoOS = new JMenuItem();
		menuArchivoOS.setText("OS");
		menuArchivo.add(menuArchivoOS); 
		menuArchivoUsuarios = new JMenuItem();
		menuArchivoUsuarios.setText("Usuarios");
		menuArchivoUsuarios.setEnabled(false);
		menuArchivo.add(menuArchivoUsuarios); 
		
		
		menuReportes = new JMenu("Reportes");
		menuReportesServicios = new JMenuItem();
		menuReportesServicios.setText("Servicios");
		menuReportesServicios.setEnabled(false);
		menuReportes.add(menuReportesServicios);
		
		
		menuAyuda = new JMenu("Ayuda");
		menuAyudaSobre = new JMenuItem();
		menuAyudaSobre.setText("Sobre");
		menuAyudaSobre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){

				F_Sobre sobre = new F_Sobre();

			}
		});
		
		
		menuAyuda.add(menuAyudaSobre);
		
		
		menuOpciones = new JMenu("Opciones");
		menuOpcionesSalir = new JMenuItem();
		menuOpcionesSalir.setText("Salir");
		menuOpciones.add(menuOpcionesSalir);
		
		barraMenu.add(menuArchivo);
		barraMenu.add(menuReportes);
		barraMenu.add(menuAyuda);
		barraMenu.add(menuOpciones);
		
		this.add(barraMenu);
		//-------------------------------------------------------------------------------------
		
		//Escritorio de trabajo----------------------------------------------------------------
		escritorioTrabajo =new JDesktopPane(){
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		escritorioTrabajo.setBounds(0,25,600,575);

		this.add(escritorioTrabajo);
		//---------------------------------------------------------------------------------------
		
		//etiqueta imagen--------------------------
        etImagenGNU = new JLabel(){
        	public void paintComponent(Graphics g) { 
        		Image image = ((ImageIcon)getIcon()).getImage(); 
        		g.drawImage(image,0,0,getWidth(),getHeight(),this); 
        		}
        };
        etImagenGNU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/gnu.png")));
        etImagenGNU.setBounds(650,450,100,100);
		this.add(etImagenGNU);
		//---------------------------------------------------------------------------------------
		
		//etiqueta fecha-----------------------------------------------------------------------
        etFecha = new JLabel();
        etFecha.setText("Fecha");
        etFecha.setHorizontalAlignment(JLabel.CENTER);
        etFecha.setBounds(650,100,100,20);
		this.add(etFecha);
		//---------------------------------------------------------------------------------------
		
		//etiqueta usuario-----------------------------------------------------------------------
		etUsuario = new JLabel();
        etUsuario.setText("Usuario");
        etUsuario.setHorizontalAlignment(JLabel.CENTER);
        etUsuario.setBounds(650,50,100,20);
		this.add(etUsuario);
		//---------------------------------------------------------------------------------------
		
		
	}

}
