/*
 * Autor: Kevin Esteban Garibo Bracamontes
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class aplicacion extends JFrame implements ActionListener{
    String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@!#$";
    JLabel fondo = new JLabel( new ImageIcon( "imagenes/home.png" ) );
    JMenuBar MenuBar;
    JMenu clientes,principal,productos,peliculas;
    JMenuItem altasClientes,bajasClientes,buscarClientes,editarClientes;
    JMenuItem home,salir,altasProductos,bajasProductos,buscarProductos,editarProductos,venderProductos;
    JMenuItem altasPeliculas,bajasPeliculas,inventarioPeliculas,editarPeliculas,rentarPeliculas;
    
    JTextField campo1,campo2,campo3,campo4,campo5,campo6,campo7;
    JButton agregarAltasClientes,buscarEditarClientes,guardarEditarClientes;
    JButton buscarEliminarClientes,eliminarEliminarCliente,eliminarEliminarProductos,buscarEliminarProductos;
    JButton buscarEditarProductos,guardarEditarProductos;
    JButton agregarAltasPeliculas,buscarEliminarPeliculas,eliminarEliminarPeliculas;
    JButton buscarEditarPeliculas, guardarEditarPeliculas;
    
    JButton agregarAltasProductos,rentar,vender;
    
    JTable tablaClientes;
    DefaultTableModel modeloClientes;
    JPanel panelClientes;
    JScrollPane scrollPaneClientes; 
    Object[][] data = null ;
    
    JTable tablaClientesEli;
    DefaultTableModel modeloClientesEli;
    JPanel panelClientesEli;
    JScrollPane scrollPaneClientesEli; 
    
    
    JTable tablaProductos;
    DefaultTableModel modeloProductos;
    JPanel panelProductos;
    JScrollPane scrollPaneProductos;
    Object[][] dataProductos = null ;
    
    JTable tablaProductosEli;
    DefaultTableModel modeloProductosEli;
    JPanel panelProductosEli;
    JScrollPane scrollPaneProductosEli; 
    
    
    
    JTable tablaPeliculas;
    DefaultTableModel modeloPeliculas;
    JPanel panelPeliculas;
    JScrollPane scrollPeliculas;
    Object[][] dataPeliculas = null ;
    
    JTable tablaPeliculasEli;
    DefaultTableModel modeloPeliculasEli;
    JPanel panelPeliculasEli;
    JScrollPane scrollPanePeliculasEli; 
    
    int contador=0;
    int x=50;
    String nombre[] = new String[x];
    String ID[]= new String[x];
    String apellidoP[]=new String [x];
    String apellidoM[] = new String[x];
    String direccion[]=new String[x];
    String telefono1[]=new String[x];
    String telefono2[] = new String[x];
    
    int contador_producto=0;
    String ID_producto[] = new String [x];
    String nombre_producto[]=new String[x];
    int cantidad_producto[] = new int[x];
    float precio_producto[] = new float[x];
    
    int contador_pelicula=0;
    String ID_pelicula[]=new String [x];
    String nombre_pelicula[]=new String [x];
    String genero_pelicula[]=new String [x];
    int cantidad_pelicula[]=new int [x];
    float precio_pelicula[]= new float[x];
    
    int posicion1=0;
    
    
    public aplicacion() throws IOException
    {
        tablas();
        Menu();
        camposTexto();
        botones();
        fichero();
        fichero_productos();
        fichero_pelicula();
        
        
        fondo.setBounds(0,0,670,495);
        fondo.setVisible(true);
        add(fondo);
        
        setLayout(null);	
        setSize( 680, 550);
        setVisible( true );
        setLocationRelativeTo( null );
        setTitle("Sistema de control - BLUCKBUSTER");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        
    }
    
    public void Menu()
    {
        MenuBar=new JMenuBar();
        setJMenuBar(MenuBar); 
        
        principal=new JMenu("Principal");
        MenuBar.add(principal);
        
        home=new JMenuItem("Home");
        home.addActionListener(this);
        principal.add(home);
        
        salir=new JMenuItem("Salir");
        salir.addActionListener(this);
        principal.add(salir);
        
        
        clientes=new JMenu("Clientes");
        MenuBar.add(clientes);
        
        altasClientes=new JMenuItem("Altas");
        altasClientes.addActionListener(this);
        clientes.add(altasClientes);
        
        bajasClientes=new JMenuItem("Eliminar");
        bajasClientes.addActionListener(this);
        clientes.add(bajasClientes);
        
        buscarClientes=new JMenuItem("Buscar");
        buscarClientes.addActionListener(this);
        clientes.add(buscarClientes);
        
        editarClientes=new JMenuItem("Editar");
        editarClientes.addActionListener(this);
        clientes.add(editarClientes);
        
        productos=new JMenu("Productos");
        MenuBar.add(productos);
        
        altasProductos=new JMenuItem("Altas");
        altasProductos.addActionListener(this);
        productos.add(altasProductos);
        
        bajasProductos=new JMenuItem("Eliminar");
        bajasProductos.addActionListener(this);
        productos.add(bajasProductos);
        
        buscarProductos=new JMenuItem("Inventario");
        buscarProductos.addActionListener(this);
        productos.add(buscarProductos);
        
        editarProductos=new JMenuItem("Editar");
        editarProductos.addActionListener(this);
        productos.add(editarProductos);
        
        venderProductos=new JMenuItem("Ventas");
        venderProductos.addActionListener(this);
        productos.add(venderProductos);
        
        peliculas=new JMenu("Peliculas");
        MenuBar.add(peliculas);
        
        altasPeliculas=new JMenuItem("Altas");
        altasPeliculas.addActionListener(this);
        peliculas.add(altasPeliculas);
        
        bajasPeliculas=new JMenuItem("Eliminar");
        bajasPeliculas.addActionListener(this);
        peliculas.add(bajasPeliculas);
        
        inventarioPeliculas=new JMenuItem("Inventario");
        inventarioPeliculas.addActionListener(this);
        peliculas.add(inventarioPeliculas);
        
        editarPeliculas=new JMenuItem("Editar");
        editarPeliculas.addActionListener(this);
        peliculas.add(editarPeliculas);
        
        rentarPeliculas=new JMenuItem("Rentar");
        rentarPeliculas.addActionListener(this);
        peliculas.add(rentarPeliculas);
        
    }
    
    public void camposTexto()
    {
        campo1 = new JTextField();
        campo1.reshape(265,110,120,20);
        campo1.setVisible(false);
        add(campo1);
        
        campo2 = new JTextField();
        campo2.reshape(265,166,120,20);
        campo2.setVisible(false);
        add(campo2);
        
        campo3 = new JTextField();
        campo3.reshape(265,222,120,20);
        campo3.setVisible(false);
        add(campo3);
        
        campo4 = new JTextField();
        campo4.reshape(265,278,120,20);
        campo4.setVisible(false);
        add(campo4);
        
        campo5 = new JTextField();
        campo5.reshape(265,334,120,20);
        campo5.setVisible(false);
        add(campo5);
        
        campo6 = new JTextField();
        campo6.reshape(265,390,120,20);
        campo6.setVisible(false);
        add(campo6);
        
        campo7 = new JTextField();
        campo7.reshape(265,450,120,20);
        campo7.setVisible(false);
        add(campo7);
        
    }
    
    public void botones()
    {
        Border thickBorder = new LineBorder(Color.WHITE, 0);
         
        rentar= new JButton("");
        rentar.setIcon(new ImageIcon( "imagenes/rentar.png" ) );
        rentar.setVisible(false);
        rentar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rentar.setBorder(thickBorder);
        rentar.reshape(268,430,120,45);
        rentar.addActionListener(this);
        add(rentar);
        
        vender= new JButton("");
        vender.setIcon(new ImageIcon( "imagenes/vender.png" ) );
        vender.setVisible(false);
        vender.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vender.setBorder(thickBorder);
        vender.reshape(268,430,120,45);
        vender.addActionListener(this);
        add(vender);
        
        
        agregarAltasClientes= new JButton("");
        agregarAltasClientes.setIcon(new ImageIcon( "imagenes/agregar.png" ) );
        agregarAltasClientes.setVisible(false);
        agregarAltasClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        agregarAltasClientes.setBorder(thickBorder);
        agregarAltasClientes.reshape(298,430,120,45);
        agregarAltasClientes.addActionListener(this);
        add(agregarAltasClientes);
        
        buscarEditarClientes= new JButton("");
        buscarEditarClientes.setIcon(new ImageIcon( "imagenes/buscar.png" ) );
        buscarEditarClientes.setVisible(false);
        buscarEditarClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buscarEditarClientes.setBorder(thickBorder);
        buscarEditarClientes.reshape(340,430,120,45);
        buscarEditarClientes.addActionListener(this);
        add(buscarEditarClientes);
        
        guardarEditarClientes= new JButton("");
        guardarEditarClientes.setIcon(new ImageIcon( "imagenes/guardar.png" ) );
        guardarEditarClientes.setVisible(false);
        guardarEditarClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guardarEditarClientes.setBorder(thickBorder);
        guardarEditarClientes.reshape(500,430,120,45);
        guardarEditarClientes.addActionListener(this);
        add(guardarEditarClientes);
        
        buscarEliminarClientes= new JButton("");
        buscarEliminarClientes.setIcon(new ImageIcon( "imagenes/buscar.png" ) );
        buscarEliminarClientes.setVisible(false);
        buscarEliminarClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buscarEliminarClientes.setBorder(thickBorder);
        buscarEliminarClientes.reshape(200,430,120,45);
        buscarEliminarClientes.addActionListener(this);
        add(buscarEliminarClientes);
        
        eliminarEliminarCliente= new JButton("");
        eliminarEliminarCliente.setIcon(new ImageIcon( "imagenes/eliminar.png" ) );
        eliminarEliminarCliente.setVisible(false);
        eliminarEliminarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eliminarEliminarCliente.setBorder(thickBorder);
        eliminarEliminarCliente.reshape(350,430,120,45);
        eliminarEliminarCliente.addActionListener(this);
        add(eliminarEliminarCliente);
        
        
        agregarAltasProductos= new JButton("");
        agregarAltasProductos.setIcon(new ImageIcon( "imagenes/agregar.png" ) );
        agregarAltasProductos.setVisible(false);
        agregarAltasProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        agregarAltasProductos.setBorder(thickBorder);
        agregarAltasProductos.reshape(298,430,120,45);
        agregarAltasProductos.addActionListener(this);
        add(agregarAltasProductos);
        
        buscarEliminarProductos= new JButton("");
        buscarEliminarProductos.setIcon(new ImageIcon( "imagenes/buscar.png" ) );
        buscarEliminarProductos.setVisible(false);
        buscarEliminarProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buscarEliminarProductos.setBorder(thickBorder);
        buscarEliminarProductos.reshape(200,430,120,45);
        buscarEliminarProductos.addActionListener(this);
        add(buscarEliminarProductos);
        
        eliminarEliminarProductos= new JButton("");
        eliminarEliminarProductos.setIcon(new ImageIcon( "imagenes/eliminar.png" ) );
        eliminarEliminarProductos.setVisible(false);
        eliminarEliminarProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eliminarEliminarProductos.setBorder(thickBorder);
        eliminarEliminarProductos.reshape(350,430,120,45);
        eliminarEliminarProductos.addActionListener(this);
        add(eliminarEliminarProductos);
        
        buscarEditarProductos= new JButton("");
        buscarEditarProductos.setIcon(new ImageIcon( "imagenes/buscar.png" ) );
        buscarEditarProductos.setVisible(false);
        buscarEditarProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buscarEditarProductos.setBorder(thickBorder);
        buscarEditarProductos.reshape(340,430,120,45);
        buscarEditarProductos.addActionListener(this);
        add(buscarEditarProductos);
        
        guardarEditarProductos= new JButton("");
        guardarEditarProductos.setIcon(new ImageIcon( "imagenes/guardar.png" ) );
        guardarEditarProductos.setVisible(false);
        guardarEditarProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guardarEditarProductos.setBorder(thickBorder);
        guardarEditarProductos.reshape(500,430,120,45);
        guardarEditarProductos.addActionListener(this);
        add(guardarEditarProductos);
        
        agregarAltasPeliculas= new JButton("");
        agregarAltasPeliculas.setIcon(new ImageIcon( "imagenes/agregar.png" ) );
        agregarAltasPeliculas.setVisible(false);
        agregarAltasPeliculas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        agregarAltasPeliculas.setBorder(thickBorder);
        agregarAltasPeliculas.reshape(298,430,120,45);
        agregarAltasPeliculas.addActionListener(this);
        add(agregarAltasPeliculas);
        
        buscarEliminarPeliculas= new JButton("");
        buscarEliminarPeliculas.setIcon(new ImageIcon( "imagenes/buscar.png" ) );
        buscarEliminarPeliculas.setVisible(false);
        buscarEliminarPeliculas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buscarEliminarPeliculas.setBorder(thickBorder);
        buscarEliminarPeliculas.reshape(200,430,120,45);
        buscarEliminarPeliculas.addActionListener(this);
        add(buscarEliminarPeliculas);
        
        eliminarEliminarPeliculas= new JButton("");
        eliminarEliminarPeliculas.setIcon(new ImageIcon( "imagenes/eliminar.png" ) );
        eliminarEliminarPeliculas.setVisible(false);
        eliminarEliminarPeliculas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eliminarEliminarPeliculas.setBorder(thickBorder);
        eliminarEliminarPeliculas.reshape(350,430,120,45);
        eliminarEliminarPeliculas.addActionListener(this);
        add(eliminarEliminarPeliculas);
        
        buscarEditarPeliculas= new JButton("");
        buscarEditarPeliculas.setIcon(new ImageIcon( "imagenes/buscar.png" ) );
        buscarEditarPeliculas.setVisible(false);
        buscarEditarPeliculas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buscarEditarPeliculas.setBorder(thickBorder);
        buscarEditarPeliculas.reshape(340,430,120,45);
        buscarEditarPeliculas.addActionListener(this);
        add(buscarEditarPeliculas);
        
        guardarEditarPeliculas= new JButton("");
        guardarEditarPeliculas.setIcon(new ImageIcon( "imagenes/guardar.png" ) );
        guardarEditarPeliculas.setVisible(false);
        guardarEditarPeliculas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guardarEditarPeliculas.setBorder(thickBorder);
        guardarEditarPeliculas.reshape(500,430,120,45);
        guardarEditarPeliculas.addActionListener(this);
        add(guardarEditarPeliculas);
    }
    
    public void tablas()
    {
        String[] columnasClientes = {"ID","Nombre","Apellido paterno","Apellido Materno","Direccion","Telefono 1", "Telefono 2 "};
        String[] columnasProductos = {"ID","Nombre","Precio","Cantidad"};
        String[] columnasPeliculas = {"ID","Nombre","Genero","Cantidad","Precio"};
        
        
        
        modeloClientes = new DefaultTableModel(data, columnasClientes);
        tablaClientes = new JTable(modeloClientes);
        tablaClientes.setPreferredScrollableViewportSize(new Dimension(650, 300));
        tablaClientes.setFillsViewportHeight(true);
        
        
        
       scrollPaneClientes = new JScrollPane(tablaClientes);
       scrollPaneClientes.setVisible(true);

       
        add(scrollPaneClientes);
        
        panelClientes= new JPanel();
        panelClientes.setBounds(0, 120, 670,400);
        panelClientes.setVisible(false);
        panelClientes.setBackground(Color.white);
        add(panelClientes);
        panelClientes.add(scrollPaneClientes);
        
        
        /***********************************************************************/
        
        Object[][] datos = {{"", "","","", "","",""},};
        
        modeloClientesEli = new DefaultTableModel(datos, columnasClientes);
        tablaClientesEli = new JTable(modeloClientesEli);
        tablaClientesEli.setPreferredScrollableViewportSize(new Dimension(650, 100));
        tablaClientesEli.setFillsViewportHeight(true);
        
        
        
       scrollPaneClientesEli = new JScrollPane(tablaClientesEli);
       scrollPaneClientesEli.setVisible(true);

       
        add(scrollPaneClientesEli);
        
        panelClientesEli= new JPanel();
        panelClientesEli.setBounds(0, 220, 670,200);
        panelClientesEli.setVisible(false);
        panelClientesEli.setBackground(Color.white);
        add(panelClientesEli);
        panelClientesEli.add(scrollPaneClientesEli);
        
        /***********************************************************************/
        
        modeloProductos = new DefaultTableModel(dataProductos, columnasProductos);
        tablaProductos = new JTable(modeloProductos);
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(650, 300));
        tablaProductos.setFillsViewportHeight(true);
        
        
        
       scrollPaneProductos = new JScrollPane(tablaProductos);
       scrollPaneProductos.setVisible(true);

       
        add(scrollPaneProductos);
        
        panelProductos= new JPanel();
        panelProductos.setBounds(0, 120, 670,400);
        panelProductos.setVisible(false);
        panelProductos.setBackground(Color.white);
        add(panelProductos);
        panelProductos.add(scrollPaneProductos);
        
        /***********************************************************************/
        
        Object[][] datos2 = {{"", "","",""},};
        
        modeloProductosEli = new DefaultTableModel(datos2, columnasProductos);
        tablaProductosEli = new JTable(modeloProductosEli);
        tablaProductosEli.setPreferredScrollableViewportSize(new Dimension(650, 100));
        tablaProductosEli.setFillsViewportHeight(true);
        
        
        
       scrollPaneProductosEli = new JScrollPane(tablaProductosEli);
       scrollPaneProductosEli.setVisible(true);

       
        add(scrollPaneProductosEli);
        
        panelProductosEli= new JPanel();
        panelProductosEli.setBounds(0, 220, 670,200);
        panelProductosEli.setVisible(false);
        panelProductosEli.setBackground(Color.white);
        add(panelProductosEli);
        panelProductosEli.add(scrollPaneProductosEli);
        
        
        /***********************************************************************/
        
        modeloPeliculas = new DefaultTableModel(dataPeliculas, columnasPeliculas);
        tablaPeliculas = new JTable(modeloPeliculas);
        tablaPeliculas.setPreferredScrollableViewportSize(new Dimension(650, 300));
        tablaPeliculas.setFillsViewportHeight(true);
        
        
        
       scrollPeliculas = new JScrollPane(tablaPeliculas);
       scrollPeliculas.setVisible(true);

       
        add(scrollPeliculas);
        
        panelPeliculas= new JPanel();
        panelPeliculas.setBounds(0, 120, 670,400);
        panelPeliculas.setVisible(false);
        panelPeliculas.setBackground(Color.white);
        add(panelPeliculas);
        panelPeliculas.add(scrollPeliculas);
        
        /***********************************************************************/
        
        Object[][] datos3 = {{"", "","","",""},};
        
        modeloPeliculasEli = new DefaultTableModel(datos2, columnasPeliculas);
        tablaPeliculasEli = new JTable(modeloPeliculasEli);
        tablaPeliculasEli.setPreferredScrollableViewportSize(new Dimension(650, 100));
        tablaPeliculasEli.setFillsViewportHeight(true);
        
        
        
       scrollPanePeliculasEli = new JScrollPane(tablaPeliculasEli);
       scrollPanePeliculasEli.setVisible(true);

       
        add(scrollPanePeliculasEli);
        
        panelPeliculasEli= new JPanel();
        panelPeliculasEli.setBounds(0, 220, 670,200);
        panelPeliculasEli.setVisible(false);
        panelPeliculasEli.setBackground(Color.white);
        add(panelPeliculasEli);
        panelPeliculasEli.add(scrollPanePeliculasEli);
    }
    
    public void guardar()
    {
           try {
                guardarTodo();
            } catch (IOException ex) {
                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void limpiar()
    {
        campo1.setVisible(false);
        campo2.setVisible(false);
        campo3.setVisible(false);
        campo4.setVisible(false);
        campo5.setVisible(false);
        campo6.setVisible(false);
        campo7.setVisible(false);
        agregarAltasClientes.setVisible(false);
        buscarEditarClientes.setVisible(false);
        guardarEditarClientes.setVisible(false);
        eliminarEliminarCliente.setVisible(false);
        buscarEliminarClientes.setVisible(false);
        panelClientes.setVisible(false);
        panelClientesEli.setVisible(false);
        panelProductos.setVisible(false);
        
        campo1.setText("");
        campo2.setText("");
        campo3.setText("");
        campo4.setText("");
        campo5.setText("");
        campo6.setText("");
        campo7.setText("");
        
        setEliminar("",0,0);
        setEliminar("",0,1);
        setEliminar("",0,2);
        setEliminar("",0,3);
        setEliminar("",0,4);
        setEliminar("",0,5);
        setEliminar("",0,6);
        
        setEliminarProductos("",0,0);
        setEliminarProductos("",0,1);
        setEliminarProductos("",0,2);
        setEliminarProductos("",0,3);
        
        setEliminarPeliculas("",0,0);
        setEliminarPeliculas("",0,1);
        setEliminarPeliculas("",0,2);
        setEliminarPeliculas("",0,3);
        setEliminarPeliculas("",0,4);
        agregarAltasProductos.setVisible(false);
        buscarEliminarProductos.setVisible(false);
        eliminarEliminarProductos.setVisible(false);
        guardarEditarProductos.setVisible(false);
        buscarEditarProductos.setVisible(false);
        
        agregarAltasPeliculas.setVisible(false);
        buscarEliminarPeliculas.setVisible(false);
        eliminarEliminarPeliculas.setVisible(false);
        guardarEditarPeliculas.setVisible(false);
        buscarEditarPeliculas.setVisible(false);
        panelProductosEli.setVisible(false);
        panelPeliculas.setVisible(false);
        panelPeliculasEli.setVisible(false);
        
        rentar.setVisible(false);
        vender.setVisible(false);

    }
    
    public void irPrincipal()
    {
         limpiar();
            fondo.setIcon(new ImageIcon("imagenes/home.png"));
            
    }
    
    public void aparecerAltasClientes()
    {
            limpiar();
            fondo.setIcon(new ImageIcon("imagenes/altas-clientes.png"));
            campo1.setVisible(true);
            campo2.setVisible(true);
            campo3.setVisible(true);
            campo4.setVisible(true);
            campo5.setVisible(true);
            campo6.setVisible(true);
            agregarAltasClientes.setVisible(true);
            
            campo1.reshape(265,110,120,20);
            campo2.reshape(265,166,120,20);
            campo3.reshape(265,222,120,20);
            campo4.reshape(265,278,120,20);
            campo5.reshape(265,334,120,20);
            campo6.reshape(265,390,120,20);
    }
    
    public void aparecerEditarClientes()
    {
           limpiar();
            fondo.setIcon(new ImageIcon("imagenes/editar-clientes.png"));
            campo1.setVisible(true);
            campo2.setVisible(true);
            campo3.setVisible(true);
            campo4.setVisible(true);
            campo5.setVisible(true);
            campo6.setVisible(true);
            campo7.setVisible(true);
            buscarEditarClientes.setVisible(true);
            guardarEditarClientes.setVisible(true);
            
            
            campo2.reshape(265,110,120,20);
            campo3.reshape(265,166,120,20);
            campo4.reshape(265,222,120,20);
            campo5.reshape(265,278,120,20);
            campo6.reshape(265,334,120,20);
            campo7.reshape(265,390,120,20);
            
            campo1.reshape(205,455,120,20);
    }
    
    public void aparecerBuscarClientes()
    {
            limpiar();
            fondo.setIcon(new ImageIcon("imagenes/buscar-clientes.png"));
            panelClientes.setVisible(true);
    }
    
    public void aparecerBajasClientes()
    {
            limpiar();
            fondo.setIcon(new ImageIcon("imagenes/eliminar-clientes.png"));
            
            campo1.setVisible(true);
            campo1.reshape(315,155,120,20);
            eliminarEliminarCliente.setVisible(true);
            buscarEliminarClientes.setVisible(true);
            panelClientesEli.setVisible(true);
    }
    
    public void agregarCliente()
    {
        contador++;
        
        Random Posicionar = new Random();
        int lugar=Posicionar.nextInt(65);
        int lugar2=Posicionar.nextInt(9);
        int lugar3=Posicionar.nextInt(9);
        int lugar4=Posicionar.nextInt(9);
        int lugar5=Posicionar.nextInt(9);
        ID[contador]=base.substring(lugar,lugar+1)+""+lugar2+""+lugar3+""+lugar4+""+lugar5;
             
            nombre[contador]=campo1.getText();
            apellidoP[contador]=campo2.getText();
            apellidoM[contador]=campo3.getText();
            direccion[contador]=campo4.getText();
            telefono1[contador]=campo5.getText();
            telefono2[contador]=campo6.getText();
            
            Object[][] data = { {ID[contador],nombre[contador],apellidoP[contador],apellidoM[contador],direccion[contador],telefono1[contador],telefono2[contador]}};
            JOptionPane.showMessageDialog(null,"Se ha agregado el registro: "+ID[contador] ,"Dato Agregado",JOptionPane.PLAIN_MESSAGE);
            modeloClientes.addRow(new Object[]{ID[contador],nombre[contador],apellidoP[contador],apellidoM[contador],direccion[contador],telefono1[contador],telefono2[contador]});
            campo1.setText("");
            campo2.setText("");
            campo3.setText("");
            campo4.setText("");
            campo5.setText("");
            campo6.setText("");
    }
    
    public void busquedaClientes()
    {
        
            for(int i=0; i<contador+1;i++)
            {
                if(campo1.getText().equals(ID[i]))
                {
                    campo2.setText(nombre[i]);
                    campo3.setText(apellidoP[i]);
                    campo4.setText(apellidoM[i]);
                    campo5.setText(direccion[i]);
                    campo6.setText(telefono1[i]);
                    campo7.setText(telefono2[i]);
                    
                    
                }
            }
            
    }
    
    public void guardadoClientes()
    {
        
        
        for(int i=0; i<contador+1;i++)
            {
                if(campo1.getText().equals(ID[i]))
                {
                    nombre[i]=campo2.getText();
                    apellidoP[i]=campo3.getText();
                    apellidoM[i]=campo4.getText();
                    direccion[i]=campo5.getText();
                    telefono1[i]=campo6.getText();
                    telefono2[i]=campo7.getText();

                    setTablaCliente(nombre[i],i-1,1);
                    setTablaCliente(apellidoP[i],i-1,2);
                    setTablaCliente(apellidoM[i],i-1,3);
                    setTablaCliente(direccion[i],i-1,4);
                    setTablaCliente(telefono1[i],i-1,5);
                    setTablaCliente(telefono2[i],i-1,6);

                    JOptionPane.showMessageDialog(null,"Se ha actualizado el registro: "+ID[i] ,"Dato actualizado",JOptionPane.PLAIN_MESSAGE);

                    campo1.setText("");
                    campo2.setText("");
                    campo3.setText("");
                    campo4.setText("");
                    campo5.setText("");
                    campo6.setText("");
                    campo7.setText("");
                }
                
            }
        
            
            
            
            guardar();
    }
    
    public void busquedaEliminarClientes()
    {
         
            for(int i=0; i<contador+1;i++)
            {
                if(campo1.getText().equals(ID[i]))
                {
                    
                    campo2.setText(nombre[i]);
                    campo3.setText(apellidoP[i]);
                    campo4.setText(apellidoM[i]);
                    campo5.setText(direccion[i]);
                    campo6.setText(telefono1[i]);
                    campo7.setText(telefono2[i]);
                    
                    setEliminar(ID[i],0,0);
                    setEliminar(nombre[i],0,1);
                    setEliminar(apellidoP[i],0,2);
                    setEliminar(apellidoM[i],0,3);
                    setEliminar(direccion[i],0,4);
                    setEliminar(telefono1[i],0,5);
                    setEliminar(telefono2[i],0,6);
                    
                    
                }
            }
            
    }
    
    public void aparecerAltasProductos()
    {
            limpiar();
            fondo.setIcon(new ImageIcon("imagenes/altas-productos.png"));
            campo1.setVisible(true);
            campo2.setVisible(true);
            campo3.setVisible(true);
            agregarAltasProductos.setVisible(true);
            
            campo1.reshape(195,123,120,20);
            campo2.reshape(195,205,120,20);
            campo3.reshape(195,293,120,20);
    }
    
    public void aparecerBajasProductos()
    {
             limpiar();
            fondo.setIcon(new ImageIcon("imagenes/eliminar-productos.png"));
            buscarEliminarProductos.setVisible(true);
            eliminarEliminarProductos.setVisible(true);
            campo1.setVisible(true);
            campo1.reshape(315,155,120,20);
            panelProductosEli.setVisible(true);
    }
    
    public void aparecerBuscarProductos()
    {
        limpiar();
        fondo.setIcon(new ImageIcon("imagenes/buscar-productos.png"));
        panelProductos.setVisible(true);
    }
    
    public void aparecerEditarProductos()
    {
            limpiar();
            fondo.setIcon(new ImageIcon("imagenes/editar-productos.png"));
            guardarEditarProductos.setVisible(true);
            buscarEditarProductos.setVisible(true);
            
            campo1.reshape(185,440,120,20);
           
            campo2.reshape(195,123,120,20);
            campo3.reshape(195,205,120,20);
            campo4.reshape(195,293,120,20);
            
        
            campo1.setVisible(true);
            campo2.setVisible(true);
            campo3.setVisible(true);
            campo4.setVisible(true);
    }
    
    public void aparecerAltasPeliculas()
    {
        limpiar();
            fondo.setIcon(new ImageIcon("imagenes/altas-peliculas.png"));
            campo1.setVisible(true);
            campo2.setVisible(true);
            campo3.setVisible(true);
            campo4.setVisible(true);
            agregarAltasPeliculas.setVisible(true);
            
            campo1.reshape(185,123,120,20);
            campo2.reshape(185,190,120,20);
            campo3.reshape(185,263,120,20);
            campo4.reshape(185,335,120,20);
    }
    
    public void aparecerBajasPeliculas()
    {
        limpiar();
            fondo.setIcon(new ImageIcon("imagenes/eliminar-peliculas.png"));
            campo1.setVisible(true);
            campo1.reshape(305,155,120,20);
            buscarEliminarPeliculas.setVisible(true);
            eliminarEliminarPeliculas.setVisible(true);
            panelPeliculasEli.setVisible(true);
    }
    
    public void aparecerInventarioPeliculas()
    {
        limpiar();
        fondo.setIcon(new ImageIcon("imagenes/inventario-peliculas.png"));
        panelPeliculas.setVisible(true);

    }
    
    public void aparecerEditarPeliculas()
    {
        limpiar();
            fondo.setIcon(new ImageIcon("imagenes/editar-peliculas.png"));
            buscarEditarPeliculas.setVisible(true);
            guardarEditarPeliculas.setVisible(true);
            
            campo1.setVisible(true);
            campo2.setVisible(true);
            campo3.setVisible(true);
            campo4.setVisible(true);
            campo5.setVisible(true);
            
            
            campo2.reshape(185,123,120,20);
            campo3.reshape(185,190,120,20);
            campo4.reshape(185,263,120,20);
            campo5.reshape(185,335,120,20);
            campo1.reshape(170,435,120,20);
    }
    
    public void agregarProductos()
    {
         contador_producto++;
         
         
         Random Posicionar = new Random();
        int lugar=Posicionar.nextInt(65);
        int lugar2=Posicionar.nextInt(9);
        int lugar3=Posicionar.nextInt(9);
        int lugar4=Posicionar.nextInt(9);
        int lugar5=Posicionar.nextInt(9);
        ID_producto[contador_producto]=base.substring(lugar,lugar+1)+""+lugar2+""+lugar3+""+lugar4+""+lugar5;
                
        
            
            nombre_producto[contador_producto]=campo1.getText();
            cantidad_producto[contador_producto]=Integer.parseInt(campo2.getText());
            precio_producto[contador_producto]=Float.parseFloat(campo3.getText());
            JOptionPane.showMessageDialog(null,"Se ha agregado el registro: "+ID_producto[contador_producto] ,"Dato Agregado",JOptionPane.PLAIN_MESSAGE);
            modeloProductos.addRow(new Object[]{ID_producto[contador_producto],nombre_producto[contador_producto],precio_producto[contador_producto],cantidad_producto[contador_producto]});
            campo1.setText("");
            campo2.setText("");
            campo3.setText("");
            try {
                guardar_producto();
            } catch (IOException ex) {
                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
    }
    
    public void busquedaEditarProducto()
    {
        for(int i=0;i<contador_producto+1;i++)
            {
                if(campo1.getText().equals(ID_producto[i]))
                {
                    campo2.setText(nombre_producto[i]);
                    campo3.setText(cantidad_producto[i]+"");
                    campo4.setText(precio_producto[i]+"");
                }
            }
    }
    
    public void agregarPeliculas()
    {
        contador_pelicula++;
        
        Random Posicionar = new Random();
        int lugar=Posicionar.nextInt(65);
        int lugar2=Posicionar.nextInt(9);
        int lugar3=Posicionar.nextInt(9);
        int lugar4=Posicionar.nextInt(9);
        int lugar5=Posicionar.nextInt(9);
        ID_pelicula[contador_pelicula]=base.substring(lugar,lugar+1)+""+lugar2+""+lugar3+""+lugar4+""+lugar5;
         
            nombre_pelicula[contador_pelicula]=campo1.getText();
            genero_pelicula[contador_pelicula]=campo2.getText();
            cantidad_pelicula[contador_pelicula]=Integer.parseInt(campo3.getText());
            precio_pelicula[contador_pelicula]=Float.parseFloat(campo4.getText());
            
            JOptionPane.showMessageDialog(null,"Se ha agregado el registro: "+ID_pelicula[contador_pelicula] ,"Dato Agregado",JOptionPane.PLAIN_MESSAGE);
              modeloPeliculas.addRow(new Object[]{ID_pelicula[contador_pelicula],nombre_pelicula[contador_pelicula],genero_pelicula[contador_pelicula],cantidad_pelicula[contador_pelicula],precio_pelicula[contador_pelicula]});
            campo1.setText("");
            campo2.setText("");
            campo3.setText("");
            campo4.setText("");
            try {
                guardar_pelicula();
            } catch (IOException ex) {
                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void guardadoProductos()
    {
        for(int i=0;i<contador_producto+1;i++)
            {
                if(campo1.getText().equals(ID_producto[i]))
                {
                    nombre_producto[i]=campo2.getText();
                    cantidad_producto[i]=Integer.parseInt(campo3.getText());
                    precio_producto[i]=Float.parseFloat(campo4.getText());
                    
                    JOptionPane.showMessageDialog(null,"Se ha actualizado el registro: "+ID_producto[i] ,"Dato actualizado",JOptionPane.PLAIN_MESSAGE);
                    
                    setProductos(nombre_producto[i],i-1,1);
                    setProductos(cantidad_producto[i],i-1,3);
                    setProductos(precio_producto[i],i-1,2);
                    
                    campo1.setText("");
                    campo2.setText("");
                    campo3.setText("");
                    campo4.setText("");
                    
                    try {
                        guardar_producto();
                    } catch (IOException ex) {
                        Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }
    
    public void eliminarCliente()
    {
        boolean a=false;
            
            
            for(int i=0;i<contador+1;i++)
            {
                if((campo1.getText().equals(ID[i]))||a==true)
                {
                    if(a==false)
                    {
                        modeloClientes.removeRow(i-1);
                        JOptionPane.showMessageDialog(null,"Se ha eliminado el registro: "+ID[i] ,"Dato eliminado",JOptionPane.PLAIN_MESSAGE);
                        setEliminar("",0,0);
                        setEliminar("",0,1);
                        setEliminar("",0,2);
                        setEliminar("",0,3);
                        setEliminar("",0,4);
                        setEliminar("",0,5);
                        setEliminar("",0,6);
                    }
                    ID[i]=ID[i+1];
                    nombre[i]=nombre[i+1];
                    apellidoP[i]=apellidoP[i+1];
                    apellidoM[i]=apellidoM[i+1];
                    direccion[i]=direccion[i+1];
                    telefono1[i]=telefono1[i+1];
                    telefono2[i]=telefono2[i+1];
                    a=true;
                }
            }
            if(a==true)
            {
                contador--;
            }            
            guardar();
    }
    
    public void busquedaEliminarProductos()
    {
        for(int i=0;i<contador_producto+1;i++)
            {
                if(campo1.getText().equals(ID_producto[i]))
                {
                    setEliminarProductos(ID_producto[i],0,0);
                    setEliminarProductos(nombre_producto[i],0,1);
                    setEliminarProductos(precio_producto[i],0,2);
                    setEliminarProductos(cantidad_producto[i],0,3);
                }
            }
    }
          
    public void eliinarProducto() throws IOException
    {
         boolean a=false;
            
            
            for(int i=0;i<contador_producto+1;i++)
            {
                if((campo1.getText().equals(ID_producto[i]))||a==true)
                {
                    if(a==false)
                    {
                        modeloProductos.removeRow(i-1);
                        JOptionPane.showMessageDialog(null,"Se ha eliminado el registro: "+ID_producto[i] ,"Dato eliminado",JOptionPane.PLAIN_MESSAGE);
                        setEliminarProductos("",0,0);
                        setEliminarProductos("",0,1);
                        setEliminarProductos("",0,2);
                        setEliminarProductos("",0,3);
                        
                    }
                    ID_producto[i]=ID_producto[i+1];
                    nombre_producto[i]=nombre_producto[i+1];
                    precio_producto[i]=precio_producto[i+1];
                    cantidad_producto[i]=cantidad_producto[i+1];
                    
                    a=true;
                }
            }
            if(a==true)
            {
                contador_producto--;
            }            
            guardar_producto();
    }
    
    public void busquedaEliminarPeliculas()
    {
        for(int i=0;i<contador_pelicula+1;i++)
            {
                if(campo1.getText().equals(ID_pelicula[i]))
                {
                    setEliminarPeliculas(ID_pelicula[i],0,0);
                    setEliminarPeliculas(nombre_pelicula[i],0,1);
                    setEliminarPeliculas(genero_pelicula[i],0,2);
                    setEliminarPeliculas(cantidad_pelicula[i],0,3);
                    setEliminarPeliculas(precio_pelicula[i],0,4);
                }
            }
    }
    
    public void eliinarPelicula() throws IOException
    {
         boolean a=false;
            
            
            for(int i=0;i<contador_pelicula+1;i++)
            {
                if((campo1.getText().equals(ID_pelicula[i]))||a==true)
                {
                    if(a==false)
                    {
                        modeloPeliculas.removeRow(i-1);
                        JOptionPane.showMessageDialog(null,"Se ha eliminado el registro: "+ID_pelicula[i] ,"Dato eliminado",JOptionPane.PLAIN_MESSAGE);
                        setEliminarPeliculas("",0,0);
                        setEliminarPeliculas("",0,1);
                        setEliminarPeliculas("",0,2);
                        setEliminarPeliculas("",0,3);
                        setEliminarPeliculas("",0,4);
                        
                    }
                    ID_pelicula[i]=ID_pelicula[i+1];
                    nombre_pelicula[i]=nombre_pelicula[i+1];
                    genero_pelicula[i]=genero_pelicula[i+1];
                    precio_pelicula[i]=precio_pelicula[i+1];
                    cantidad_pelicula[i]=cantidad_pelicula[i+1];
                    
                    
                    a=true;
                }
            }
            if(a==true)
            {
                contador_pelicula--;
            }            
            guardar_pelicula();
    }
    
    public void busquedaEditarPelicula()
    {
        for(int i=0;i<contador_pelicula+1;i++)
            {
                if(campo1.getText().equals(ID_pelicula[i]))
                {
                    campo2.setText(nombre_pelicula[i]);
                    campo3.setText(genero_pelicula[i]+"");
                    campo4.setText(cantidad_pelicula[i]+"");
                    campo5.setText(precio_pelicula[i]+"");
                }
            }
    }
    
    public void guardadoPeliculas() throws IOException
    {
        for(int i=0;i<contador_pelicula+1;i++)
            {
                if(campo1.getText().equals(ID_pelicula[i]))
                {
                    nombre_pelicula[i]=campo2.getText();
                    genero_pelicula[i]=campo3.getText();
                    cantidad_pelicula[i]=Integer.parseInt(campo4.getText());
                    precio_pelicula[i]=Float.parseFloat(campo5.getText());
                    
                    
                    JOptionPane.showMessageDialog(null,"Se ha actualizado el registro: "+ID_pelicula[i] ,"Dato actualizado",JOptionPane.PLAIN_MESSAGE);
                    
                    setPeliculas(nombre_pelicula[i],i-1,1);
                    setPeliculas(genero_pelicula[i],i-1,2);
                    setPeliculas(cantidad_pelicula[i],i-1,3);
                    setPeliculas(precio_pelicula[i],i-1,4);
                    
                    
                    campo1.setText("");
                    campo2.setText("");
                    campo3.setText("");
                    campo4.setText("");
                    campo5.setText("");
                    
                    guardar_pelicula();
                    
                    
                }
            }
    }
    
    public void aparecerRentar()
    {
        limpiar();
            fondo.setIcon(new ImageIcon("imagenes/rentar-pelicula.png"));
             campo1.setVisible(true);
             campo1.reshape(235,137,120,20);
             
             campo2.setVisible(true);
             campo2.reshape(235,249,120,20);
             rentar.setVisible(true);
    }
    
    public void aparecerVender()
    {
        
            limpiar();
            fondo.setIcon(new ImageIcon("imagenes/vender-productos.png"));
            
            campo1.setVisible(true);
             campo1.reshape(239,147,120,20);
             
             campo2.setVisible(true);
             campo2.reshape(239,260,120,20);
             vender.setVisible(true);
            
    }
    
    public void rentarPelicula()
    {
        boolean a= false;
            boolean b=false;
            int pos1=0;
            int pos2=0;
            
            for(int i=0;i<=contador_pelicula;i++)
            {
                if(campo1.getText().equals(ID_pelicula[i]))
                {
                    a=true;
                    pos1=i;
                }
            }
            
            for(int i=0;i<=contador;i++)
            {
                if(campo2.getText().equals(ID[i]))
                {
                    b=true;
                    pos2=i;
                }
            }
            
            if(a==false)
            {
                JOptionPane.showMessageDialog(null,"El ID de la pelicula no existe","Dato Agregado",JOptionPane.ERROR_MESSAGE);
            }
            if(b==false)
            {
                JOptionPane.showMessageDialog(null,"El ID del cliente no existe","Dato Agregado",JOptionPane.ERROR_MESSAGE);
            }
            
            if((a==true)&&(b==true))
            {
                JOptionPane.showMessageDialog(null,"---------Pelicula Rentada---------\n "+
                        "ID pelicula: "+ID_pelicula[pos1]+"\n"+
                        "Nombre pelicula: "+nombre_pelicula[pos1]+"\n"+
                        "ID cliente: "+ID[pos2]+"\n"+
                        "Nombre cliente: "+nombre[pos2]+"\n"+
                        "---------------------------------------\n"+
                        "Total: "+precio_pelicula[pos1],"PELICULA RENTADA",JOptionPane.PLAIN_MESSAGE);
                
                campo1.setText("");
                campo2.setText("");
                
                 try {
                guardar_pelicula();
            } catch (IOException ex) {
                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }
    
    public void venderProductos()
    {
        boolean a= false;
            int pos =0;
            
            for(int i=0;i<=contador_producto;i++)
            {
                if(campo1.getText().equals(ID_producto[i]))
                {
                    a=true;
                    pos=i;
                }
            }
            
            if(a==false)
            {
                 JOptionPane.showMessageDialog(null,"El ID del producto no existe","X(",JOptionPane.ERROR_MESSAGE);
            }
            
            if(a==true)
            {
                        if((Integer.parseInt(campo2.getText()))>cantidad_producto[pos])
                    {
                        JOptionPane.showMessageDialog(null,"No hay suficientes unidades para vender","X(",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"---------Producto Vendido---------\n"+
                                                           "ID producto: "+ID_producto[pos]+"\n"+
                                                           "Nombre producto: "+nombre_producto[pos]+"\n"+
                                                           "Cantidad productos: "+campo2.getText()+"\n"+
                                                           "Precio producto: $"+precio_producto[pos]+"\n"+
                                                           "Total: $"+((Integer.parseInt(campo2.getText()))*precio_producto[pos]),"VENTAS",JOptionPane.PLAIN_MESSAGE);
                        
                        
                            cantidad_producto[pos]=cantidad_producto[pos]-Integer.parseInt(campo2.getText());
                            setProductos(cantidad_producto[pos],pos-1,3);
                            campo1.setText("");
                            campo2.setText("");
                            try {
                                guardar_producto();
                            } catch (IOException ex) {
                                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                
            }
    }
    

   
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==home)
        {
           irPrincipal();
        }
        
        if(ae.getSource()==salir)
        {
            System.exit(0);
        }
        
        if(ae.getSource()==altasClientes)
        {
            aparecerAltasClientes();
        }
        
        if(ae.getSource()==editarClientes)
        {
           aparecerEditarClientes();            
        }
        
        if(ae.getSource()==buscarClientes)
        {
            aparecerBuscarClientes();
        }
        
        if(ae.getSource()==bajasClientes)
        {
            aparecerBajasClientes();
        }
        
        if(ae.getSource()==agregarAltasClientes)
        {
            agregarCliente();
            guardar();
        }
        if(ae.getSource()==buscarEditarClientes)
        {
            busquedaClientes();
        }
        
        if(ae.getSource()==guardarEditarClientes)
        {
            guardadoClientes();
        }
        if(ae.getSource()==buscarEliminarClientes)
        {
           busquedaEliminarClientes();
        }
        
        if(ae.getSource()==eliminarEliminarCliente)
        {
            eliminarCliente();
        }
        
        if(ae.getSource()==altasProductos)
        {
            aparecerAltasProductos();
        }
        
        if(ae.getSource()==bajasProductos)
        {
            aparecerBajasProductos();
        }
        
        if(ae.getSource()==buscarProductos)
        {
            aparecerBuscarProductos();
        }
        
        if(ae.getSource()==editarProductos)
        {
            aparecerEditarProductos();       
        }
        
        if(ae.getSource()==altasPeliculas)
        {
            aparecerAltasPeliculas();
        }
        
        if(ae.getSource()==bajasPeliculas)
        {
            aparecerBajasPeliculas();
        }
        
        if(ae.getSource()==inventarioPeliculas)
        {
            aparecerInventarioPeliculas();
        }
        
        if(ae.getSource()==editarPeliculas)
        {
            aparecerEditarPeliculas();
        }
        
        if(ae.getSource()==agregarAltasProductos)
        {
           agregarProductos();
        }
        
        if(ae.getSource()==buscarEliminarProductos)
        {
            busquedaEliminarProductos();
        }
        
        if(ae.getSource()==eliminarEliminarProductos)
        {
            try {
                eliinarProducto();
            } catch (IOException ex) {
                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(ae.getSource()==buscarEditarProductos)
        {
            busquedaEditarProducto();
        }
        if(ae.getSource()==guardarEditarProductos)
        {
            guardadoProductos();
        }
        
        if(ae.getSource()==agregarAltasPeliculas)
        {
            agregarPeliculas();
        }
        
        if(ae.getSource()==buscarEliminarPeliculas)
        {
            busquedaEliminarPeliculas();
        }
        
        if(ae.getSource()==eliminarEliminarPeliculas)
        {
            try {
                eliinarPelicula();
            } catch (IOException ex) {
                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(ae.getSource()==buscarEditarPeliculas)
        {
            busquedaEditarPelicula();
        }
        
        if(ae.getSource()==guardarEditarPeliculas)
        {
            try {
                guardadoPeliculas();
            } catch (IOException ex) {
                Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(ae.getSource()==rentarPeliculas)
        {
            aparecerRentar();
        }
        
        if(ae.getSource()==venderProductos)
        {
            aparecerVender();
        }
        
        if(ae.getSource()==rentar)
        {
            rentarPelicula();
        }
        
        if(ae.getSource()==vender)
        {
            venderProductos();
        }
        
        
        
        
        
        
        
        
        
        
    }
    
    
    public void fichero() throws IOException
    {
        
        File directorio = new File("clientes");
        directorio.mkdir(); 	
        
        File contadorTXT = new File("clientes/contador.txt");
        File nombreTXT = new File("clientes/nombre.txt");
        File apellidoPTXT = new File("clientes/apellidoP.txt");
        File apellidoMTXT = new File("clientes/apellidoM.txt");
        File direccionTXT = new File("clientes/direccion.txt");
        File telefono1TXT = new File("clientes/telefono1.txt");
        File telefono2TXT = new File("clientes/telefono2.txt");
        File idTXT = new File("clientes/id.txt");
        
        if(contadorTXT.exists())
        {
            Scanner leer_contador=new Scanner(contadorTXT);
            Scanner leer_nombre=new Scanner(nombreTXT);
            Scanner leer_apellidoP=new Scanner(apellidoPTXT);
            Scanner leer_apellidoM=new Scanner(apellidoMTXT);
            Scanner leer_direccion=new Scanner(direccionTXT);
            Scanner leer_telefono1=new Scanner(telefono1TXT);
            Scanner leer_telefono2=new Scanner(telefono2TXT);
            Scanner leer_id=new Scanner(idTXT);
            
            contador=leer_contador.nextInt();
            
            for(int i=1;i<contador+1;i++)
            {
                nombre[i]=leer_nombre.nextLine();
                apellidoP[i]=leer_apellidoP.nextLine();
                apellidoM[i]=leer_apellidoM.nextLine();
                direccion[i]=leer_direccion.nextLine();
                telefono1[i]=leer_telefono1.nextLine();
                telefono2[i]=leer_telefono2.nextLine();
                ID[i]=leer_id.nextLine();
                
                modeloClientes.addRow(new Object[]{ID[i],nombre[i],apellidoP[i],apellidoM[i],direccion[i],telefono1[i],telefono2[i]});
            }
            
            
        }
    }
    
    private void guardarTodo()throws IOException
    {
        FileWriter contadorTXT2 = new FileWriter("clientes/contador.txt");            
        FileWriter nombreTXT2 = new FileWriter("clientes/nombre.txt"); 
        FileWriter apellidoPTXT2 = new FileWriter("clientes/apellidoP.txt");
        FileWriter apellidoMTXT2 = new FileWriter("clientes/apellidoM.txt");
        FileWriter direccionTXT2 = new FileWriter("clientes/direccion.txt");   
        FileWriter telefono1TXT2 = new FileWriter("clientes/telefono1.txt");   
        FileWriter telefono2TXT2 = new FileWriter("clientes/telefono2.txt");   
        FileWriter idTXT2 = new FileWriter("clientes/id.txt");   
        
        for(int i=1;i<contador+1;i++)
        {
            nombreTXT2.write(nombre[i] + "\r\n");
            apellidoPTXT2.write(apellidoP[i] + "\r\n");
            apellidoMTXT2.write(apellidoM[i] + "\r\n");
            direccionTXT2.write(direccion[i] + "\r\n");
            telefono1TXT2.write(telefono1[i] + "\r\n");
            telefono2TXT2.write(telefono2[i] + "\r\n");
            idTXT2.write(ID[i] + "\r\n");
            
        }
        contadorTXT2.write((contador) + "\r\n");
        
        contadorTXT2.close();
        nombreTXT2.close();
        direccionTXT2.close();
        telefono1TXT2.close();
        telefono2TXT2.close();
        idTXT2.close();
        apellidoPTXT2.close();
        apellidoMTXT2.close();
    }
    
    public void fichero_productos() throws IOException
    {
        
        File directorio = new File("productos");
        directorio.mkdir(); 	
        
        File contador_productosTXT = new File("productos/contador.txt");
        File nombre_productoTXT = new File("productos/nombre.txt");
        File precio_productoTXT = new File("productos/precio.txt");
        File cantidad_productoTXT = new File("productos/cantidad.txt");
        File id_productoTXT = new File("productos/id.txt");
        
        if(contador_productosTXT.exists())
        {
            Scanner leer_contador=new Scanner(contador_productosTXT);
            Scanner leer_nombre=new Scanner(nombre_productoTXT);
            Scanner leer_precio_produco=new Scanner(precio_productoTXT);
            Scanner leer_cantidad_produto=new Scanner(cantidad_productoTXT);
            Scanner leer_id=new Scanner(id_productoTXT);
            
            contador_producto=leer_contador.nextInt();
            
            for(int i=1;i<contador_producto+1;i++)
            {
                nombre_producto[i]=leer_nombre.nextLine();
                precio_producto[i]=leer_precio_produco.nextFloat();
                cantidad_producto[i]=leer_cantidad_produto.nextInt();
                ID_producto[i]=leer_id.nextLine();
                
                modeloProductos.addRow(new Object[]{ID_producto[i],nombre_producto[i],precio_producto[i],cantidad_producto[i]});
            }
            
            
        }
    }
    
    private void guardar_producto()throws IOException
    {
        FileWriter contador_productoTXT2 = new FileWriter("productos/contador.txt");            
        FileWriter nombre_productoTXT2 = new FileWriter("productos/nombre.txt"); 
        FileWriter precio_productoTXT2 = new FileWriter("productos/precio.txt");
        FileWriter cantidad_productosTXT2 = new FileWriter("productos/cantidad.txt");   
        FileWriter id_productosTXT2 = new FileWriter("productos/id.txt");   
        
        for(int i=1;i<contador_producto+1;i++)
        {
            nombre_productoTXT2.write(nombre_producto[i] + "\r\n");
            precio_productoTXT2.write(precio_producto[i] + "\r\n");
            cantidad_productosTXT2.write(cantidad_producto[i] + "\r\n");
            id_productosTXT2.write(ID_producto[i] + "\r\n");
            
        }
        contador_productoTXT2.write((contador_producto) + "\r\n");
        
        contador_productoTXT2.close();
        nombre_productoTXT2.close();
        id_productosTXT2.close();
        precio_productoTXT2.close();
        cantidad_productosTXT2.close();
    }
    
    public void fichero_pelicula() throws IOException
    {
        
        File directorio = new File("peliculas");
        directorio.mkdir(); 	
        
        File contador_peliculaTXT = new File("peliculas/contador.txt");
        File nombre_peliculaTXT = new File("peliculas/nombre.txt");
        File genero_peliculaTXT = new File("peliculas/genero.txt");
        File precio_peliculaTXT = new File("peliculas/precio.txt");
        File cantidad_peliculaTXT = new File("peliculas/cantidad.txt");
        File id_peliculaTXT = new File("peliculas/id.txt");
        
        if(contador_peliculaTXT.exists())
        {
            Scanner leer_contador_pelicula=new Scanner(contador_peliculaTXT);
            Scanner leer_nombre_pelicula=new Scanner(nombre_peliculaTXT);
            Scanner leer_genero_pelicula=new Scanner(genero_peliculaTXT);
            Scanner leer_precio_pelicula=new Scanner(precio_peliculaTXT);
            Scanner leer_cantidad_pelicula=new Scanner(cantidad_peliculaTXT);
            Scanner leer_id_pelicula=new Scanner(id_peliculaTXT);
            
            contador_pelicula=leer_contador_pelicula.nextInt();
            
            for(int i=1;i<contador_pelicula+1;i++)
            {
                nombre_pelicula[i]=leer_nombre_pelicula.nextLine();
                genero_pelicula[i]=leer_genero_pelicula.nextLine();
                precio_pelicula[i]=leer_precio_pelicula.nextFloat();
                cantidad_pelicula[i]=leer_cantidad_pelicula.nextInt();
                ID_pelicula[i]=leer_id_pelicula.nextLine();
                
                modeloPeliculas.addRow(new Object[]{ID_pelicula[i],nombre_pelicula[i],genero_pelicula[i],cantidad_pelicula[i],precio_pelicula[i]});
            }
            
            
        }
    }
    
    private void guardar_pelicula()throws IOException
    {
        FileWriter contador_peliculaTXT2 = new FileWriter("peliculas/contador.txt");            
        FileWriter nombre_peliculaTXT2 = new FileWriter("peliculas/nombre.txt"); 
        FileWriter genero_peliculaTXT2 = new FileWriter("peliculas/genero.txt"); 
        FileWriter precio_peliculaTXT2 = new FileWriter("peliculas/precio.txt");
        FileWriter cantidad_peliculaTXT2 = new FileWriter("peliculas/cantidad.txt");   
        FileWriter id_peliculasTXT2 = new FileWriter("peliculas/id.txt");   
        
        for(int i=1;i<contador_pelicula+1;i++)
        {
            nombre_peliculaTXT2.write(nombre_pelicula[i] + "\r\n");
            genero_peliculaTXT2.write(genero_pelicula[i] + "\r\n");
            precio_peliculaTXT2.write(precio_pelicula[i] + "\r\n");
            cantidad_peliculaTXT2.write(cantidad_pelicula[i] + "\r\n");
            id_peliculasTXT2.write(ID_pelicula[i] + "\r\n");
            
        }
        contador_peliculaTXT2.write((contador_pelicula) + "\r\n");
        
        contador_peliculaTXT2.close();
        nombre_peliculaTXT2.close();
        id_peliculasTXT2.close();
        precio_peliculaTXT2.close();
        cantidad_peliculaTXT2.close();
        genero_peliculaTXT2.close();
    }
    
    public void setTablaCliente(Object obj, int row_index, int col_index)
    {
        tablaClientes.getModel().setValueAt(obj,row_index,col_index);
    }
    
    public void setEliminar(Object obj, int row_index, int col_index)
    {
        tablaClientesEli.getModel().setValueAt(obj,row_index,col_index);   
     }
    
    public void setProductos(Object obj, int row_index, int col_index)
    {
        tablaProductos.getModel().setValueAt(obj,row_index,col_index);   
     }
    
    public void setEliminarProductos(Object obj, int row_index, int col_index)
    {
        tablaProductosEli.getModel().setValueAt(obj,row_index,col_index);   
     }
    
    public void setPeliculas(Object obj, int row_index, int col_index)
    {
        tablaPeliculas.getModel().setValueAt(obj,row_index,col_index);   
     }
    
    public void setEliminarPeliculas(Object obj, int row_index, int col_index)
    {
        tablaPeliculasEli.getModel().setValueAt(obj,row_index,col_index);   
     }
    
}
