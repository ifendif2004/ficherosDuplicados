package com.vista;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.Dimension;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;

import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Window.Type;

public class BuscarDuplicados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public JTextField textDirectorio;
	public JTextField textContDirectorios;
	public JTextField textContFicheros;
	public int contFicheros;
	public int contDirectorios;
	public int contDuplicados;
	private JLabel lblFicherosDuplicados;
	public JTextField textContDuplicados;
	private JTable tableDuplicados;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarDuplicados frame = new BuscarDuplicados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public BuscarDuplicados() {
		setFont(new Font("Courier 10 Pitch", Font.PLAIN, 13));
		setForeground(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"));
		setTitle("Ficheros duplicados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 467);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("controlDkShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

// Panel resumen		
		JPanel panelResumen = new JPanel();
		panelResumen.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelResumen.setBackground(UIManager.getColor("Button.select"));
		panelResumen.setBounds(12, 265, 655, 161);
		contentPane.add(panelResumen);
		panelResumen.setLayout(null);
		
		lblFicherosDuplicados = new JLabel("Ficheros duplicados:");
		lblFicherosDuplicados.setBounds(411, 130, 153, 15);
		panelResumen.add(lblFicherosDuplicados);
		
		textContDuplicados = new JTextField();
		textContDuplicados.setBorder(new LineBorder(new Color(0, 0, 0)));
		textContDuplicados.setHorizontalAlignment(SwingConstants.RIGHT);
		textContDuplicados.setEditable(false);
		textContDuplicados.setBounds(573, 130, 70, 19);
		panelResumen.add(textContDuplicados);
		textContDuplicados.setColumns(10);
		
		JScrollPane scrollPaneTableDuplicados = new JScrollPane();
		scrollPaneTableDuplicados.setBounds(12, 12, 631, 117);
		panelResumen.add(scrollPaneTableDuplicados);
		
		tableDuplicados = new JTable();
		tableDuplicados.setBackground(UIManager.getColor("control"));
		tableDuplicados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Carpeta", "Fichero"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableDuplicados.getColumnModel().getColumn(0).setPreferredWidth(500);
		tableDuplicados.getColumnModel().getColumn(1).setPreferredWidth(300);
		scrollPaneTableDuplicados.setViewportView(tableDuplicados);
				
// Panel entrada		
		JPanel panelEntrada = new JPanel();
		panelEntrada.setBackground(UIManager.getColor("controlShadow"));
		panelEntrada.setBounds(14, 12, 653, 228);
		contentPane.add(panelEntrada);
		panelEntrada.setLayout(null);
				
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(12, 12, 629, 122);
		panelEntrada.add(scrollPaneTable);

		table = new JTable();
		table.setBackground(UIManager.getColor("inactiveCaption"));
		table.setSurrendersFocusOnKeystroke(true);
		table.setDragEnabled(true);
		table.setPreferredScrollableViewportSize(new Dimension(0, 0));
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null},
				},
				new String[] {
						"Carpeta", "Fichero", "Hash"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		scrollPaneTable.setViewportView(table);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(12, 146, 116, 25);
		panelEntrada.add(btnSeleccionar);
		btnSeleccionar.addActionListener(new ControlEventos());
		btnSeleccionar.setActionCommand("btnSeleccionar");

		textDirectorio = new JTextField();
		textDirectorio.setBackground(UIManager.getColor("control"));
		textDirectorio.setBounds(140, 145, 501, 28);
		panelEntrada.add(textDirectorio);
		textDirectorio.setColumns(10);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(12, 178, 116, 25);
		panelEntrada.add(btnCalcular);
		btnCalcular.setToolTipText("Calcula ");
		btnCalcular.setActionCommand("btnCalcular");

		JLabel lblDirectoriosLeidos = new JLabel("Directorios leidos:");
		lblDirectoriosLeidos.setBounds(140, 178, 130, 15);
		panelEntrada.add(lblDirectoriosLeidos);

		textContDirectorios = new JTextField();
		textContDirectorios.setBounds(274, 178, 70, 19);
		panelEntrada.add(textContDirectorios);
		textContDirectorios.setBorder(new LineBorder(new Color(0, 0, 0)));
		textContDirectorios.setEditable(false);
		textContDirectorios.setHorizontalAlignment(SwingConstants.RIGHT);
		textContDirectorios.setColumns(10);

		JLabel lblFicherosLeidos = new JLabel("Ficheros leidos:");
		lblFicherosLeidos.setBounds(449, 178, 116, 15);
		panelEntrada.add(lblFicherosLeidos);

		textContFicheros = new JTextField();
		textContFicheros.setBounds(571, 178, 70, 19);
		panelEntrada.add(textContFicheros);
		textContFicheros.setBackground(UIManager.getColor("controlShadow"));
		textContFicheros.setBorder(new LineBorder(new Color(0, 0, 0)));
		textContFicheros.setEditable(false);
		textContFicheros.setHorizontalAlignment(SwingConstants.RIGHT);
		textContFicheros.setColumns(10);
		btnCalcular.addActionListener(new ControlEventos());
		
	}

//=========================================================================================	
	/*
	 * Clase que controla todos los eventos
	 */

	class ControlEventos implements ActionListener {
		
		ArrayList<FicherosList> ficherosList = new ArrayList<FicherosList>();
		DefaultTableModel modelo;
		DefaultTableModel modeloDuplicados;
		File sDirectorio;
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("btnCalcular")) {
				contentPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (textDirectorio.getText() == null){
				    sDirectorio = new File("/home/alfonso");
				}else{    
				    sDirectorio = new File(textDirectorio.getText());
				}    
				modelo = ((DefaultTableModel) table.getModel());
				modeloDuplicados = ((DefaultTableModel) tableDuplicados.getModel());
				borrarTabla();
				contDirectorios = 0;
				contFicheros = 0;
				contDuplicados = 0;
				listarDirectorio(sDirectorio);
				printFicheros();
				contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} //final if btnCalcular

			if (e.getActionCommand().equals("btnSeleccionar")) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int opcion = chooser.showDialog(chooser, "Abrir");
				if (opcion == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					textDirectorio.setText(file.getAbsolutePath());
				}
			}
		}
		
		
		public void listarDirectorio(File sDirectorio){
			
			DefaultTableModel modelo = ((DefaultTableModel) table.getModel());
			
			byte[] resumen;
			if (sDirectorio.isDirectory()) {
				textContDirectorios.setText(String.valueOf(++contDirectorios));
				textContDirectorios.update(textContDirectorios.getGraphics());
				File[] ficheros = sDirectorio.listFiles();
				for (int i = 0; i < ficheros.length; i++) {
					if (ficheros[i].isFile()) {
						try {
							textContFicheros.setText(String.valueOf(++contFicheros));
							textContFicheros.update(textContFicheros.getGraphics());
							resumen = CalculoHash.calcularResumen(ficheros[i]);
							int  hashCode = Arrays.hashCode(resumen);
							Object [] datos = {ficheros[i].getAbsolutePath(),ficheros[i].getName(),hashCode};
							modelo.addRow(datos);
							ficherosList.add(new FicherosList(ficheros[i].getAbsolutePath(), ficheros[i].getName(), hashCode));
//							table.update(table.getGraphics());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
	
					} else {
						if (ficheros[i].isDirectory()) {
							listarDirectorio((File)ficheros[i]);
						}
					}
				}		
			}
			
			
		}

		private void borrarTabla(){

			int max = modelo.getRowCount() - 1;
			for (int i = max ; i > -1 ; i--) {
				modelo.removeRow(i);
			}
			
			max = modeloDuplicados.getRowCount() - 1;
			for (int i = max ; i > -1 ; i--) {
				modeloDuplicados.removeRow(i);
			}
			table.update(table.getGraphics());
			tableDuplicados.update(tableDuplicados.getGraphics());
			textContDirectorios.setText(String.valueOf(0));
			textContFicheros.setText(String.valueOf(0));
			textContDuplicados.setText(String.valueOf(0));
			textContDirectorios.update(textContDirectorios.getGraphics());
			textContFicheros.update(textContFicheros.getGraphics());
			textContDuplicados.update(textContDuplicados.getGraphics());
			ficherosList.clear();
		}
		
		private void printFicheros() {
			
			Collections.sort(ficherosList,new OrdenarPorNumero());
			Object [] datos = new Object[3];

			boolean duplicado = false;
			for (int i=0; i<ficherosList.size()-1; i++) {
				if (ficherosList.get(i).getArrayHash() == ficherosList.get(i+1).getArrayHash()) {
//					System.out.println("Fichero["+i+ "]= "+ficherosList.get(i).getArrayHash());
					duplicado = true;
					datos[0] = ficherosList.get(i).getNombreDirectorio();
					datos[1] = ficherosList.get(i).getNombreFichero();
					datos[2] = ficherosList.get(i).getArrayHash();
					modeloDuplicados.addRow(datos);
					++contDuplicados;
				}else if (duplicado){
					duplicado = false;
					datos[0] = ficherosList.get(i).getNombreDirectorio();
					datos[1] = ficherosList.get(i).getNombreFichero();
					datos[2] = ficherosList.get(i).getArrayHash();
					modeloDuplicados.addRow(datos);
					++contDuplicados;
//					System.out.println("Fichero["+i+ "]= "+ficherosList.get(i).getArrayHash());
				}
			}
			textContDuplicados.setText(String.valueOf(contDuplicados));
		}

	}
}
