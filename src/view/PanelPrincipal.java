package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

import model.DesenhaDDA2D;

import java.awt.Frame;

public class PanelPrincipal extends JFrame implements ActionListener {

	public static JPanel PanelPrincipal;
	public static JLabel lblX;
	public static JLabel lblY;
	public static JLabel lblNdcx;
	public static JLabel lblNdcy;
	public static JLabel lblDcx;
	public static JLabel lblDcy;
	DesenhaDDA2D reta;
	public static JButton btnLimpar;
	public static PanelDeDesenho2D panelDesenho2D;
	PanelAtividades ativ;
	boolean flagPanelA = true;
	boolean flagPanelB = true;
	JMenuItem mntmEquippe;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPrincipal frame = new PanelPrincipal();
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
	public PanelPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		//**************PANEL PRINCIPAL**************************
		PanelPrincipal = new JPanel();
		
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelPrincipal);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 720);
		setTitle("Projeto Computacao Grafica - Corte Suterland");
		// *****************PANEL DE DESENHO*********************
		
		panelDesenho2D = new PanelDeDesenho2D();
		panelDesenho2D.setLocation(409, 26);
		PanelPrincipal.add(panelDesenho2D);
		
		//Painel de transformações 2D
		PanelPrincipal.repaint();
		flagPanelA = true;
		flagPanelB = false;
		
		
		
		//**********BARRA DE MENUS*********************
		
		JMenuBar barraDeMenu = new JMenuBar();
		setJMenuBar(barraDeMenu);
		
		JMenu mnSobre = new JMenu("Sobre");
		barraDeMenu.add(mnSobre);
		
		mntmEquippe = new JMenuItem("Equipe");
		mnSobre.add(mntmEquippe);
		PanelPrincipal.setLayout(null);

		//************************LABELS***************************************************
		JLabel lblCoordenadaX = new JLabel("Coordenada X:");
		lblCoordenadaX.setBounds(481, 633, 88, 14);
		PanelPrincipal.add(lblCoordenadaX);
		
		JLabel lblCoordenadaY = new JLabel("Coordenada Y:");
		lblCoordenadaY.setBounds(481, 659, 88, 14);
		PanelPrincipal.add(lblCoordenadaY);
		
		lblX = new JLabel();
		lblX.setBounds(578, 633, 28, 14);
		PanelPrincipal.add(lblX);
		
		lblY = new JLabel();
		lblY.setBounds(579, 659, 32, 14);
		PanelPrincipal.add(lblY);
		
		JLabel lblCoordenadaNdcx = new JLabel("Coordenada NDCX:");
		lblCoordenadaNdcx.setBounds(658, 633, 112, 14);
		PanelPrincipal.add(lblCoordenadaNdcx);
		
		JLabel lblCoordenadaNdcy = new JLabel("Coordenada NDCY:");
		lblCoordenadaNdcy.setBounds(658, 659, 112, 14);
		PanelPrincipal.add(lblCoordenadaNdcy);
		
		JLabel lblCoordenadaDcx = new JLabel("Coordenada DCX:");
		lblCoordenadaDcx.setBounds(837, 633, 112, 14);
		PanelPrincipal.add(lblCoordenadaDcx);
		
		JLabel lblCoordenadaDcy = new JLabel("Coordenada DCY:");
		lblCoordenadaDcy.setBounds(837, 658, 98, 14);
		PanelPrincipal.add(lblCoordenadaDcy);
		
		lblNdcx = new JLabel();
		lblNdcx.setBounds(766, 633, 46, 14);
		PanelPrincipal.add(lblNdcx);
		
		lblNdcy = new JLabel();
		lblNdcy.setBounds(766, 659, 46, 14);
		PanelPrincipal.add(lblNdcy);
		
		lblDcx = new JLabel();
		lblDcx.setBounds(972, 633, 46, 14);
		PanelPrincipal.add(lblDcx);
		
		lblDcy = new JLabel();
		lblDcy.setBounds(972, 658, 46, 14);
		PanelPrincipal.add(lblDcy);
		
		JPanel panelFuncoes = new JPanel();
		panelFuncoes.setBounds(24, 25, 370, 600);
		panelFuncoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		ativ = new PanelAtividades();
		remove(panelFuncoes);
		PanelPrincipal.add(ativ.panelFuncoes);
		panelFuncoes.setLayout(null);
		
		btnLimpar = new JButton("Limpar Tudo");
		btnLimpar.setBounds(147, 542, 134, 23);
		btnLimpar.addActionListener(this);
		PanelPrincipal.add(btnLimpar);
		mntmEquippe.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object o = arg0.getSource();

		if (o.equals(btnLimpar)){
			PanelDeDesenho2D.limpaDesenho = true;
			panelDesenho2D.repaint();
		} 
		
		//
		if (o.equals(mntmEquippe)){
			String team = "Mayara Victoria\n" + "Mateus\n" + "Ricardo Gabriel";
			JOptionPane.showMessageDialog(null, team);
		}
		
	}
}
