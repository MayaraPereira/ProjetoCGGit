package model;

import java.awt.Color;

import view.PanelPrincipal;

public class DesenhaDDA2D {
	long x = 0;
	long y = 0;
	Integer xin = 0;
	Integer yin = 0;
	
	public DesenhaDDA2D() {
		
	
	}
	/**
	 * Método pela equação DDA - Digital Differential Analyzer 
	 * @param X1 - Ponto X1
	 * @param Y1 - Ponto Y1
	 * @param X2 - Ponto X2
	 * @param Y2 - Ponto X2
	 */
	public void DDA(int X1, int Y1, int X2, int Y2, Color cor){

		if (cor == null) {
			cor = Color.MAGENTA;}
		
		if(X1>X2 && Y1>Y2 ){
			
			int aux = Y1;
			Y1=Y2;
			Y2=aux;
			
			aux = X1;
			X1=X2;
			X2=aux;
			
		}
		else if(X1>X2){
			int aux = Y1;
			Y1=Y2;
			Y2=aux;
			
			aux = X1;
			X1=X2;
			X2=aux;
		}
	//**********************************************************************************************	
		int Length;
		float X,Y,Xinc,Yinc;
		Length = Math.abs(X2 - X1);
		if (Math.abs(Y2 - Y1) > Length)
			Length = Math.abs(Y2-Y1);
		Xinc = (float)(X2 - X1)/Length;
		Yinc = (float)(Y2 - Y1)/Length;
		X = X1;
		Y = Y1;
		setpixel(Math.round(X), Math.round(Y), cor);
		while(X<X2){
		X = X + Xinc;
		Y = Y + Yinc;
		setpixel(Math.round(X), Math.round(Y), cor);
		}
		//Caso a reta a ser desenhada seja paralela ao eixo x
		if(X1==X2){
			if(Y1>Y2){
				int aux = Y1;
				Y1=Y2;
				Y2=aux;
			}
				
			for(int i=Y1;i<=Y2;i++){
				setpixel(X1, i, cor);
			}
		}
	}
	
	public void setpixel(int x, int y){
		PanelPrincipal.panelDesenho2D.setPixel(x, y);
	}
	
	public void setpixel(int x, int y, Color cor){
		PanelPrincipal.panelDesenho2D.setPixel(x, y, cor);
	}
	
}