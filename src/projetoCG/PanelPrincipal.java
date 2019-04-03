package projetoCG;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


import circunferencia.*;
import operacoes3d.*;
import operacoes2D.*;
import cisalhamento.*;
import rotacao.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class PanelPrincipal extends JFrame {


	private JPanel contentPane;
	/**
	 * Itens definidos
	 */
	public static JMenuItem mntmTranslacao2D, mntmEscala2D, mntmRotacao2D,
	mntmCisalhamento2D, mntmReflexaoEmX2D, mntmReflexoEmY,
	mntmReflexaoEmXeY2D, mntmRotacaoEmX3D, mntmReflexaoEmY2D,
	mntmReflexaoEmY3D, mntmRotacaoEmX2D;
	public PanelReta panelReta;

	/**
	 * PLano cartesiano.
	 */
	public static PanelPlanoCartesiano planoCartesiano = new PanelPlanoCartesiano();
	public static PanelNormalizacao panelNormalizacao = new PanelNormalizacao();

	/**
	 * Lista de {@link Ponto} em 2D (duas dimensÃµes)
	 */
	protected static List<Ponto> listaGLOBAL = new ArrayList<Ponto>();
	/**
	 * Lista de {@link Ponto3D} em 3D (trÃªs dimensÃµes).
	 */
	protected static List<Ponto3D> listaGLOBAL3D = new ArrayList<Ponto3D>();

	public static JPanel PanelPrincipal;
	public static JLabel lblX;
	public static JLabel lblY;
	public static JLabel lblNdcx;
	public static JLabel lblNdcy;
	public static JLabel lblDcx;
	public static JLabel lblDcy;
	boolean flagPanelA = true;
	boolean flagPanelB = true;
	JMenuItem mntmSair;
	protected int Posx;
	protected int Posy;
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
		setSize(857, 914);
		setTitle("Projeto Computação Gráfica");
		planoCartesiano = new PanelPlanoCartesiano();
		//************************LABELS***************************************************
				JLabel lblCoordenadaX = new JLabel("Coordenada X:");
				lblCoordenadaX.setBounds(64, 646, 176, 14);
				PanelPrincipal.add(lblCoordenadaX);

				JLabel lblCoordenadaY = new JLabel("Coordenada Y:");
				lblCoordenadaY.setBounds(64, 672, 154, 14);
				PanelPrincipal.add(lblCoordenadaY);

				lblX = new JLabel();
				lblX.setBounds(223, 646, 28, 14);
				PanelPrincipal.add(lblX);

				lblY = new JLabel();
				lblY.setBounds(224, 672, 32, 14);
				PanelPrincipal.add(lblY);

				JLabel lblCoordenadaNdcx = new JLabel("Coordenada NDCX:");
				lblCoordenadaNdcx.setBounds(303, 646, 169, 14);
				PanelPrincipal.add(lblCoordenadaNdcx);

				JLabel lblCoordenadaNdcy = new JLabel("Coordenada NDCY:");
				lblCoordenadaNdcy.setBounds(303, 672, 169, 14);
				PanelPrincipal.add(lblCoordenadaNdcy);

				JLabel lblCoordenadaDcx = new JLabel("Coordenada DCX:");
				lblCoordenadaDcx.setBounds(482, 646, 181, 14);
				PanelPrincipal.add(lblCoordenadaDcx);

				JLabel lblCoordenadaDcy = new JLabel("Coordenada DCY:");
				lblCoordenadaDcy.setBounds(482, 671, 181, 14);
				PanelPrincipal.add(lblCoordenadaDcy);

				lblNdcx = new JLabel();
				lblNdcx.setBounds(411, 646, 46, 14);
				PanelPrincipal.add(lblNdcx);

				lblNdcy = new JLabel();
				lblNdcy.setBounds(411, 672, 46, 14);
				PanelPrincipal.add(lblNdcy);

				lblDcx = new JLabel();
				lblDcx.setBounds(617, 646, 46, 14);
				PanelPrincipal.add(lblDcx);

				lblDcy = new JLabel();
				lblDcy.setBounds(617, 671, 46, 14);
				PanelPrincipal.add(lblDcy);
				//Obter posicao do mouse no Plano Cartesiano
				planoCartesiano.addMouseMotionListener(new MouseMotionAdapter() {
			FuncoesDeNormalizacao funcoesDeNormalizacao = new FuncoesDeNormalizacao();
			@SuppressWarnings("static-access")
			@Override
			public void mouseMoved(MouseEvent eventoDeMouse) {
				Posx = (int) getMousePosition().getX();
				Posy = (int) getMousePosition().getY();
				int dcx = eventoDeMouse.getX() - 300;
				int dcy = (eventoDeMouse.getY() - 300) * -1;
				
				double ndcx = funcoesDeNormalizacao.calcularNDCX(planoCartesiano.LARGURA, dcx);
				double ndcy = funcoesDeNormalizacao.calcularNDCX(planoCartesiano.ALTURA, dcy);
				
				lblCoordenadaNdcx.setText("Ndc X :  "+String.format("%.4f", ndcx));
				lblCoordenadaNdcy.setText("Ndc Y :  "+String.format("%.4f", ndcy));
				
				lblCoordenadaDcx.setText("DcX:  "+Integer.toString(funcoesDeNormalizacao.calcularDCX(600, ndcx)));
				lblCoordenadaDcy.setText("DcY:  "+Integer.toString(funcoesDeNormalizacao.calcularDCY(600, ndcy)));
								
				lblCoordenadaX.setText("x: "+Integer.valueOf(Posx));
				lblCoordenadaY.setText("y: "+Integer.valueOf(Posy));
				
			}
		});
		planoCartesiano.setLocation(63, 30);
		PanelPrincipal.add(planoCartesiano);

		PanelPrincipal.repaint();
		flagPanelA = true;
		flagPanelB = false;



		//**********BARRA DE MENUS*********************

		JMenuBar barraDeMenu = new JMenuBar();
		setJMenuBar(barraDeMenu);


		JMenu menuCoordenadas = new JMenu("Desenhar");
		menuCoordenadas.setMargin(new Insets(0, 5, 0, 0));
		menuCoordenadas.setMaximumSize(new Dimension(100, 120));
		barraDeMenu.add(menuCoordenadas);

		JMenu mnCircunferencia = new JMenu("Circunferencia");
		mnCircunferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getContentPane().removeAll();
				validate();
				repaint();
			}
		});

		JMenu mnRetas = new JMenu("Retas");
		menuCoordenadas.add(mnRetas);

		JMenuItem mntmDda = new JMenuItem("DDA");
		mntmDda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Retas("dda");
			}
		});
		mnRetas.add(mntmDda);

		JMenuItem mntmPontoMdio = new JMenuItem("Ponto Medio");
		mntmPontoMdio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Retas("pm");
			}
		});
		mnRetas.add(mntmPontoMdio);
		menuCoordenadas.add(mnCircunferencia);

		JMenuItem mntmPontoMedio = new JMenuItem("Ponto Medio");
		mntmPontoMedio.setToolTipText("Ctrl + m");
		mntmPontoMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CircunferenciaPontoMedio();
			}
		});
		mnCircunferencia.add(mntmPontoMedio);

		JMenuItem mntmEquaoExplicita = new JMenuItem(
				"Equacao explicita");
		mntmEquaoExplicita.setToolTipText("Ctrl + e");
		mntmEquaoExplicita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CircunferenciaExplicita();
			}
		});
		mnCircunferencia.add(mntmEquaoExplicita);

		JMenuItem mntmTrigonometrica = new JMenuItem("Trigonometrica");
		mntmTrigonometrica.setToolTipText("Ctrl + t");
		mnCircunferencia.add(mntmTrigonometrica);
		mntmTrigonometrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CircunferenciaTrigonometrica();
			}
		});

		JMenuItem mntmRetangulo = new JMenuItem("Retangulo");
		mntmRetangulo.setMnemonic('R');
		mntmRetangulo.setToolTipText("Retangulo");
		mntmRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listaGLOBAL.clear();
					new Retangulo();
					validate();
					repaint();
				} catch (Exception e2) {
				}
			}
		});
		menuCoordenadas.add(mntmRetangulo);

		JMenuItem mntmCubo = new JMenuItem("Cubo");
		mntmCubo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaGLOBAL.clear();
				new Cubo();
				validate();
				repaint();
			}
		});

		JMenuItem mntmElipse = new JMenuItem("Elipse");
		mntmElipse.setName("Elipse");
		mntmElipse.setMnemonic('E');
		mntmElipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaGLOBAL.clear();
				new Elipse();
				validate();
				repaint();
			}
		});
		menuCoordenadas.add(mntmElipse);
		menuCoordenadas.add(mntmCubo);
		JMenu mnTransformaes = new JMenu("Transformacoes");
		mnTransformaes.setMargin(new Insets(0, 5, 0, 0));
		barraDeMenu.add(mnTransformaes);

		JMenu mnd2D = new JMenu("2D");
		mnd2D.setOpaque(true);
		mnd2D.setMinimumSize(new Dimension(200, 60));
		mnd2D.setMaximumSize(new Dimension(200, 32767));
		mnTransformaes.add(mnd2D);

		mntmCisalhamento2D = new JMenuItem("Cisalhamento");
		mnd2D.add(mntmCisalhamento2D);
		mntmCisalhamento2D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ValoresCisalhamento();
				} catch (Exception e2) {
					e2.getCause();
				}
			}
		});

		mntmEscala2D = new JMenuItem("Escala");
		mntmEscala2D.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EscalaInterface();
				repaint();
			}
		});
		mnd2D.add(mntmEscala2D);

		mntmRotacao2D = new JMenuItem("Rotacao");
		mntmRotacao2D.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getLista().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Imagem nao criada.");
				} else {
					new ValoresRotacao();
					repaint();
					validate();
				}
			}
		});
		mnd2D.add(mntmRotacao2D);

		JMenu mnReflexao = new JMenu("Reflexao");
		mnd2D.add(mnReflexao);

		mntmReflexaoEmX2D = new JMenuItem("Reflexao em X");
		mntmReflexaoEmX2D.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<Ponto> lstP = new Operacoes().reflexaoX(getLista());

				PanelReta.panelPlanoCartesiano.limparImagem();
				setLista(lstP);
				povoarRetas(getLista(), Color.BLUE);
				panelNormalizacao.repaint();
			}
		});
		mnReflexao.add(mntmReflexaoEmX2D);

		mntmReflexaoEmY2D = new JMenuItem("Reflexao em Y");
		mntmReflexaoEmY2D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Ponto> lista = getLista();

				List<Ponto> lstP = new Operacoes().reflexaoY(lista);

				PanelReta.panelPlanoCartesiano.limparImagem();
				setLista(lstP);
				povoarRetas(getLista(), Color.BLUE);
				panelNormalizacao.repaint();
			}
		});
		mnReflexao.add(mntmReflexaoEmY2D);

		mntmReflexaoEmXeY2D = new JMenuItem("Reflexao em X e Y");
		mntmReflexaoEmXeY2D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Ponto> lista = getLista();

				List<Ponto> lstP = new Operacoes().reflexaoXY(lista);

				PanelReta.panelPlanoCartesiano.limparImagem();
				setLista(lstP);
				povoarRetas(getLista(), Color.BLUE);
				PanelPrincipal.repaint();
			}
		});
		mnReflexao.add(mntmReflexaoEmXeY2D);

		mntmTranslacao2D = new JMenuItem("Translacao");
		mntmTranslacao2D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TranslacaoInterface();
				panelNormalizacao.repaint();
				repaint();
			}
		});
		mnd2D.add(mntmTranslacao2D);

		/*JMenu mnd3D = new JMenu("3D");
		mnd3D.setMinimumSize(new Dimension(100, 0));
		mnTransformaes.add(mnd3D);

		JMenuItem mntmEscala3D = new JMenuItem("Escala");
		mntmEscala3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ValoresEscala3D();
			}
		});

		JMenu mnCisalhamento = new JMenu("Cisalhamento");
		mnd3D.add(mnCisalhamento);

		JMenuItem mntmCisalhamentoEmX3D = new JMenuItem("Cisalhamento em X");
		mntmCisalhamentoEmX3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ValoresCisalhamento3D("X");
			}
		});
		mnCisalhamento.add(mntmCisalhamentoEmX3D);

		JMenuItem mntmCisalhamentoEmY3D = new JMenuItem("Cisalhamento em Y");
		mntmCisalhamentoEmY3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ValoresCisalhamento3D("Y");
			}
		});
		mnCisalhamento.add(mntmCisalhamentoEmY3D);

		JMenuItem mntmCisalhamentoEmZ3D = new JMenuItem("Cisalhamento em Z");
		mntmCisalhamentoEmZ3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ValoresCisalhamento3D("Z");
			}
		});
		mnCisalhamento.add(mntmCisalhamentoEmZ3D);

		mnd3D.add(mntmEscala3D);

		JMenu mnRotao = new JMenu("Rotacao");
		mnd3D.add(mnRotao);

		JMenuItem mntmRotacaoEmX3D_1 = new JMenuItem(
				"Rotacao em torno de X");
		mntmRotacaoEmX3D_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ValoresRotacao3D("x");
			}
		});
		mnRotao.add(mntmRotacaoEmX3D_1);

		JMenuItem mntmRotaoEmY3D = new JMenuItem(
				"Rotacao em torno de Y");
		mntmRotaoEmY3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ValoresRotacao3D("y");
			}
		});
		mnRotao.add(mntmRotaoEmY3D);

		JMenuItem mntmRotaoEmZ3D = new JMenuItem(
				"Rotacao em torno de Z");
		mntmRotaoEmZ3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ValoresRotacao3D("z");
			}
		});
		mnRotao.add(mntmRotaoEmZ3D);

		JMenu mnReflexao3d = new JMenu("Reflexao");
		mnd3D.add(mnReflexao3d);

		JMenuItem mntmReflexaoXY3D = new JMenuItem("Reflexao em XY");
		mntmReflexaoXY3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelReta.panelPlanoCartesiano.limparImagem();
				PanelPlanoCartesiano.add3D(true);

				List<Ponto3D> lst = new Cubo3D().reflexaoXY(listaGLOBAL3D);

				setListaGLOBAL3D(lst);
				povoar3D(Color.BLUE);
				panelNormalizacao.repaint();
				repaint();
				validate();
			}
		});
		mnReflexao3d.add(mntmReflexaoXY3D);

		JMenuItem mntmReflexaoEmYZ3D = new JMenuItem("Reflexao em YZ");
		mntmReflexaoEmYZ3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelReta.panelPlanoCartesiano.limparImagem();
				PanelPlanoCartesiano.add3D(true);

				List<Ponto3D> lst = new Cubo3D().reflexaoYZ(listaGLOBAL3D);

				setListaGLOBAL3D(lst);
				povoar3D(Color.BLUE);
				panelNormalizacao.repaint();
				repaint();
				validate();
			}
		});
		mnReflexao3d.add(mntmReflexaoEmYZ3D);

		JMenuItem mntmReflexaoEmXZ3D = new JMenuItem("Reflexao em XZ");
		mntmReflexaoEmXZ3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelReta.panelPlanoCartesiano.limparImagem();
				PanelPlanoCartesiano.add3D(true);

				List<Ponto3D> lst = new Cubo3D().reflexaoXZ(listaGLOBAL3D);

				setListaGLOBAL3D(lst);
				povoar3D(Color.BLUE);
				panelNormalizacao.repaint();
				repaint();
				validate();
			}
		});
		mnReflexao3d.add(mntmReflexaoEmXZ3D);

		JMenuItem mntmTranslao3D = new JMenuItem("Translacao");
		mntmTranslao3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Valores3D("translacao");
				panelNormalizacao.repaint();
				revalidate();
				repaint();
			}
		});
		mnd3D.add(mntmTranslao3D);*/
		PanelPrincipal.setLayout(null);
		

		JMenu mnAjuda = new JMenu("Sobre");
		barraDeMenu.add(mnAjuda);

		JMenuItem mntmEquippe = new JMenuItem("Equipe");
		mnAjuda.add(mntmEquippe);
		mntmEquippe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String team = "Mayara Victoria\n" + "Mateus\n" + "Ricardo Gabriel";
				JOptionPane.showMessageDialog(null, team);
			}
		});
		mntmReflexaoEmY3D = new JMenuItem("3D");
		mntmReflexaoEmY3D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelReta.panelPlanoCartesiano.limparImagem();
				PanelPlanoCartesiano.add3D(true);

				List<Ponto3D> lst = new Cubo3D().reflexaoYZ(listaGLOBAL3D);

				setListaGLOBAL3D(lst);
				;
				povoar3D(Color.BLUE);
				panelNormalizacao.repaint();
				repaint();
				validate();
			}
		});
	}


	public static void povoarRetas(List<Ponto> pontos, Color cor) {

		for (Ponto ponto : pontos) {
			try {
				PanelReta.panelPlanoCartesiano.desenharPixel(
						ponto.getX() + 300, -ponto.getY() + 300, cor);

			} catch (Exception e) {

				System.out.println("Erro ao povoar os valores."+e.getCause());
			}
		}

		planoCartesiano.repaint();

	}

	/**
	 * @param listaPontos
	 */
	public static void povoar3D(Color cor) {

		for (Ponto3D ponto : getListaGLOBAL3D()) {
			try {
				if (ponto.getZ() == 0) {
					PanelReta.panelPlanoCartesiano
					.desenharPixel(ponto.getX() + 300,
							-ponto.getY() + 300, cor);
				} else {
					PanelReta.panelPlanoCartesiano.desenharPixel(ponto.getX()
							+ 300 - ponto.getZ() / 2, -ponto.getY() + 300
							+ ponto.getZ() / 2, cor);
				}

			} catch (Exception e) {
				System.out
				.println("Erro ao povoar os valores nas 3 dimensoes.");
			}
		}

		planoCartesiano.repaint();
	}

	public static List<Ponto> getLista() {
		return listaGLOBAL;
	}

	/**
	 * 
	 * @param lista
	 */
	public static void setLista(List<Ponto> lista) {
		listaGLOBAL = lista;
	}

	/**
	 * Lista de coordenadas em 3D (Tres dimensoes)
	 * 
	 * @return the listaGLOBAL3D
	 */
	public static List<Ponto3D> getListaGLOBAL3D() {
		return listaGLOBAL3D;
	}

	/**
	 * @param listaGLOBAL3D
	 *            the listaGLOBAL3D to set
	 */
	public static void setListaGLOBAL3D(List<Ponto3D> lista) {
		listaGLOBAL3D = lista;
	}
}