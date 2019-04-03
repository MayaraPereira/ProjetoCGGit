package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.DesenhaDDA2D;
import model.DesenhaPontoMedio;
import model.RecorteCS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class PanelAtividades extends JFrame implements ActionListener, ListSelectionListener{

	JPanel panelFuncoes;
	private	Vector listDataX;
	private	Vector listDataY;
	private JTextField textFieldX;
	JButton btnAdicionar;
	JButton btnLimpar;
	JButton btnVisualizar;
	private JScrollPane scrollPaneX;
	private JList listX;
	private JTextField textFieldY;
	private JScrollPane scrollPaneY;
	private JList listY;
	private JLabel lblInserirCoordenadas;
	JComboBox comboBox;
	JButton btnLimparAll;
	JButton btnAplicarRecorte;
	private JButton btnPlotarRetangulo;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public PanelAtividades() {
		panelFuncoes = new JPanel();
		panelFuncoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelFuncoes.setBounds(24, 25, 370, 449);
		panelFuncoes.setLayout(null);
		
		JLabel lblCoordenadaX = new JLabel("Coordenada X:");
		lblCoordenadaX.setBounds(10, 50, 86, 14);
		panelFuncoes.add(lblCoordenadaX);
		
		JLabel lblCoordenadaY = new JLabel("Coordenada Y:");
		lblCoordenadaY.setBounds(100, 50, 86, 14);
		panelFuncoes.add(lblCoordenadaY);
		
		btnVisualizar = new JButton("Exibir");
		btnVisualizar.setBounds(10, 283, 102, 23);
		panelFuncoes.add(btnVisualizar);
		btnVisualizar.addActionListener(this);
		
		btnLimpar = new JButton("Remover");
		btnLimpar.setBounds(208, 148, 121, 23);
		panelFuncoes.add(btnLimpar);
		btnLimpar.addActionListener(this);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(208, 114, 121, 23);
		panelFuncoes.add(btnAdicionar);
		btnAdicionar.addActionListener(this);
		
		listDataX = new Vector();
		listDataY = new Vector();
		
		textFieldX = new JTextField();
		textFieldX.setBounds(10, 69, 79, 20);
		panelFuncoes.add(textFieldX);
		textFieldX.setColumns(10);
		
		scrollPaneX = new JScrollPane();
		scrollPaneX.setBounds(10, 106, 79, 101);
		panelFuncoes.add(scrollPaneX);
		
		listX = new JList(listDataX );
		scrollPaneX.setViewportView(listX);
		
		textFieldY = new JTextField();
		textFieldY.setBounds(100, 69, 79, 20);
		panelFuncoes.add(textFieldY);
		textFieldY.setColumns(10);
		
		scrollPaneY = new JScrollPane();
		scrollPaneY.setBounds(99, 106, 79, 101);
		panelFuncoes.add(scrollPaneY);
		
		listY = new JList(listDataY);
		scrollPaneY.setViewportView(listY);
		
		btnLimpar.setToolTipText("Selecione o valor, depois clique em remover para retirar uma coordenada."); 
		btnAdicionar.setToolTipText("Adiciona uma coordenada a pilha de coordenadas");
		
		lblInserirCoordenadas = new JLabel("Adicionar coordenadas");
		lblInserirCoordenadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserirCoordenadas.setBounds(12, 13, 348, 14);
		panelFuncoes.add(lblInserirCoordenadas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 350, 8);
		separator.setBackground(Color.RED);
		panelFuncoes.add(separator);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 250, 164, 20);
		comboBox.addItem("Reta - DDA");
		comboBox.addItem("Reta - Ponto médio");
		panelFuncoes.add(comboBox);
		
		JLabel tpAlgoritmo = new JLabel("Algoritmo:");
		tpAlgoritmo.setBounds(10, 229, 64, 14);
		panelFuncoes.add(tpAlgoritmo);
		
		btnLimparAll = new JButton("Remover tudo");
		btnLimparAll.setBounds(208, 184, 121, 23);
		panelFuncoes.add(btnLimparAll);
		btnLimparAll.addActionListener(this);
		
		JLabel lblRecorteDeLinhas = new JLabel("Recorte de linhas & Plot retangulo");
		lblRecorteDeLinhas.setBounds(100, 356, 195, 14);
		panelFuncoes.add(lblRecorteDeLinhas);
		
		btnAplicarRecorte = new JButton("Aplicar recorte");
		btnAplicarRecorte.setBounds(12, 390, 142, 23);
		panelFuncoes.add(btnAplicarRecorte);
		btnAplicarRecorte.addActionListener(this);
		
		btnPlotarRetangulo = new JButton("Plotar retangulo");
		btnPlotarRetangulo.setBounds(203, 390, 155, 23);
		panelFuncoes.add(btnPlotarRetangulo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.RED);
		separator_1.setBounds(10, 433, 350, 8);
		panelFuncoes.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.RED);
		separator_2.setBounds(10, 333, 350, 8);
		panelFuncoes.add(separator_2);
		btnPlotarRetangulo.addActionListener(this);
		
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		
		if( event.getSource() == listX && !event.getValueIsAdjusting() )
		{
			//Pega a selecao atual no combobox
			String stringValue = (String)listX.getSelectedValue();
			if( stringValue != null )
				textFieldX.setText( stringValue );
		}
		

		if( event.getSource() == listY && !event.getValueIsAdjusting() )
		{
			//Pega a selecao atual no combobox
			String stringValue = (String)listY.getSelectedValue();
			if( stringValue != null )
				textFieldY.setText( stringValue );
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if( event.getSource() == btnAdicionar )
		{
			// Obtem o valor do campo de texto
			String stringValue = textFieldX.getText();
			textFieldX.setText( "" );
			
			// Adiciona o item à lista e atualiza se não for null
			if( stringValue != null )
			{
				listDataX.addElement( stringValue );
				listX.setListData( listDataX );
				scrollPaneX.revalidate();
				scrollPaneX.repaint();
			}
			
		}
		
		if( event.getSource() == btnAdicionar )
		{
			// pega o valor do text field
			String stringValue = textFieldY.getText();
			textFieldY.setText( "" );
			// Add o item a lista e atualiza
			if( stringValue != null )
			{
				listDataY.addElement( stringValue );
				listY.setListData( listDataY );
				scrollPaneY.revalidate();
				scrollPaneY.repaint();
			}
			
		}
		if( event.getSource() == btnLimpar )
		{
			// Obtem a selecao atual
			int selection = listX.getSelectedIndex();
			if( selection >= 0 )
			{
				// Adiciona o item a lista e atualiza
				listDataX.removeElementAt( selection );
				listX.setListData( listDataX );
				scrollPaneX.revalidate();
				scrollPaneX.repaint();

			}
		}
		
		if( event.getSource() == btnLimpar )
		{

			// Obtem a selecao atual
			int selection = listY.getSelectedIndex();
			if( selection >= 0 )
			{
				// Adiciona o item a lista e atualiza
				listDataY.removeElementAt( selection );
				listY.setListData( listDataY );
				scrollPaneY.revalidate();
				scrollPaneY.repaint();

			}
		}
		if( event.getSource() == btnLimparAll )
		{
			
				listDataX.removeAllElements();
				listX.setListData( listDataX );
				scrollPaneX.revalidate();
				scrollPaneX.repaint();
				// Add this item to the list and refresh
				listDataY.removeAllElements();
				listY.setListData( listDataY );
				scrollPaneY.revalidate();
				scrollPaneY.repaint();

		}
		//Seleciona qual algoritmo o usuario deseja ativar...
		if ( event.getSource() == btnVisualizar){
			//Pega o indice do algoritmo que sera aplicado ex: dda, ponto médio
			String s = (String) comboBox.getSelectedItem();
			int x1;
        	int y1; 
        	int x2;
        	int y2;
        	int raio;
        	int a;
        	int b;
			switch (s) {//verificando se tem correspondencia
			
		        case "Reta - DDA":
		        	
		        	x1 = Integer.parseInt((String) listDataX.get(0));
		        	y1 = Integer.parseInt((String) listDataY.get(0));
		        	x2 = Integer.parseInt((String) listDataX.get(1));
		        	y2 = Integer.parseInt((String) listDataY.get(1));
		        	
		        	DesenhaDDA2D reta = new DesenhaDDA2D(); 
		        	reta.DDA(x1,y1 ,x2, y2, null);
		        	
		        	break;
		        	
		        case "Reta - Ponto médio":
		        	
		        	x1 = Integer.parseInt((String) listDataX.get(0));
		        	y1 = Integer.parseInt((String) listDataY.get(0));
		        	x2 = Integer.parseInt((String) listDataX.get(1));
		        	y2 = Integer.parseInt((String) listDataY.get(1));
		        	
		        	DesenhaPontoMedio retaPM = new DesenhaPontoMedio();
		        	retaPM.lineBres(x1, y1, x2, y2);
		        	
		        	break;
		        
			}
			
				
		}
		if ( event.getSource() == btnPlotarRetangulo){
			
			plotaTelaRC();
		}
		
		
		if ( event.getSource() == btnAplicarRecorte){
			// Se nao tiver nenhuma reta, apresenta uma mensagem informando
			if(listDataX.isEmpty() || listDataY.isEmpty()){
				JOptionPane.showMessageDialog(null, "Não existe UMA reta para recortar! \n Adicione as coordenadas da Reta");
				return;
			}
			
			int x0 = 0, y0 = 0, x1 = 0,y1 = 0;
			x0 = Integer.parseInt((String) listDataX.get(0));
        	y0 = Integer.parseInt((String) listDataY.get(0));
        	x1 = Integer.parseInt((String) listDataX.get(1));
        	y1 = Integer.parseInt((String) listDataY.get(1));
			
        	RecorteCS recorte = new RecorteCS();
        	
        	PanelPrincipal.panelDesenho2D.limpaPainel();
        	plotaTelaRC();
        	
        	//xmin xmax ymin ymax
        	recorte.CohenSutherlandLineClip(x0, y0, x1, y1, 50, 220, 50, 150);
        	
		}
	}
	private void plotaTelaRC() {
		DesenhaDDA2D dda = new DesenhaDDA2D();
		dda.DDA(50, 50, 50, 150, Color.MAGENTA);
		dda.DDA(50, 150, 220, 150, Color.MAGENTA);
		dda.DDA(50, 50, 220, 50, Color.MAGENTA);
		dda.DDA(220, 50, 220, 150, Color.MAGENTA);
	}
}
