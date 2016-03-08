package Formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.*;




public class F_Principal extends JFrame {
	
	
	
	//VAriables------------------------------------------------------------------
	
	private JMenuBar barraMenu;
	
	private JMenu menuArchivo;
	private JMenuItem menuArchivoClientes;
	private JMenuItem menuArchivoOS;
	private JMenuItem menuArchivoUsuarios;
	
	private JMenu menuReportes;
	private JMenuItem menuReportesServicios;
	
	private JMenu menuAyuda;
	private JMenuItem menuAyudaSobre;
	
	private JMenu menuOpciones;
	private JMenuItem menuOpcionesSalir;
	
	private JDesktopPane escritorioTrabajo;
	
	private JLabel etImagenGNU;
	
	private JLabel etFecha;
	
	private JLabel etUsuario;
	
	
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
		menuArchivoClientes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				F_Clientes Fr_Clientes=new F_Clientes();
				// verificar si es instancia de algun componente que ya este en el jdesktoppane
				boolean mostrar=true;
				for (int a=0;a<escritorioTrabajo.getComponentCount();a++){     
					if( Fr_Clientes.getClass().isInstance(escritorioTrabajo.getComponent(a))){
						mostrar=false;
					}
				}
				if(mostrar==true){
					Fr_Clientes.setVisible(true);
					escritorioTrabajo.add(Fr_Clientes);
				}
			}					
		});
		menuArchivo.add(menuArchivoClientes);
		
		menuArchivoOS = new JMenuItem();
		menuArchivoOS.setText("OS");
		menuArchivo.add(menuArchivoOS); 
		
		menuArchivoUsuarios = new JMenuItem();
		menuArchivoUsuarios.setText("Usuarios");
		menuArchivoUsuarios.setEnabled(false);
		menuArchivoUsuarios.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				F_Usuario Fr_Usuario=new F_Usuario();
				// verificar si es instancia de algun componente que ya este en el jdesktoppane
				boolean mostrar=true;
				for (int a=0;a<escritorioTrabajo.getComponentCount();a++){     
					if( Fr_Usuario.getClass().isInstance(escritorioTrabajo.getComponent(a))){
						mostrar=false;
					}
				}
				if(mostrar==true){
					Fr_Usuario.setVisible(true);
					escritorioTrabajo.add(Fr_Usuario);
				}
			}					
		});
		menuArchivo.add(menuArchivoUsuarios); 
		
		
		menuReportes = new JMenu("Reportes");
		menuReportesServicios = new JMenuItem();
		menuReportesServicios.setText("Servicios");
		menuReportesServicios.setEnabled(false);
		menuReportes.add(menuReportesServicios);
		
		
		menuAyuda = new JMenu("Ayuda");
		menuAyudaSobre = new JMenuItem();
		menuAyudaSobre.setText("Sobre");
		menuAyudaSobre.addActionListener(new F_Sobre());
		menuAyuda.add(menuAyudaSobre);
		
		
		menuOpciones = new JMenu("Opciones");
		menuOpcionesSalir = new JMenuItem();
		menuOpcionesSalir.setText("Salir");
		menuOpcionesSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				int opcSalir = JOptionPane.showConfirmDialog(null,"¿Desa Salir?","Atencion",JOptionPane.YES_NO_OPTION);
				if (opcSalir== JOptionPane.YES_NO_OPTION){
					System.exit(0);
					
				}
			}
		});
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
        Date  fecha= new Date();
        DateFormat formato= DateFormat.getDateInstance(DateFormat.SHORT);
        etFecha.setText(formato.format(fecha));
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
	
	public  void setHabilitar_menuArchivoUsuarios(){
		menuArchivoUsuarios.setEnabled(true);		
	}
	
	public  void setHabilitar_menuReportesServicios(){
		menuReportesServicios.setEnabled(true);
		
	}
	
	public  void setMostrarUsuario_etUsuario(String usuarioRecibido){
		etUsuario.setText(usuarioRecibido);
		
	}
	

}


