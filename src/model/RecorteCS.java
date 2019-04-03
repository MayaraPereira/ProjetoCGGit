package model;

import java.awt.Color;

public class RecorteCS {
	/**
	 * Método para checar se os pontos de x e y, interceptam o X ou Y min ou máx.  
	 * @param x 	- Valor x
	 * @param y		- Valor y
	 * @param xmin 	- Valor Xmin
	 * @param xmax	- Valor Xmax
	 * @param ymin	- Valor Ymin
	 * @param ymax	- Valor Ymax
	 * @return Array 4 bits
	 */
	public boolean[] code(int x, int y, int xmin, int xmax, int ymin, int ymax){
		boolean[] code = new boolean[4] ;
		
		for (int i = 0; i < code.length; i++) {
			code[i] = false;
		}
		
		
		if (y > ymax) code[0] = true;
		if (y < ymin) code[1] = true;
		if (x > xmax) code[2] = true;
		if (x < xmin) code[3] = true;
		
		return code;
	}
	/**
	 * Verifica se todos os bits do array são verdadeiros
	 * @param bit - Array de bits
	 * @return True caso sejam todos Verdadeiros
	 */
	public boolean vazio(boolean bit[]){
		
		for (int i = 0; i < bit.length; i++) {
			if(bit[i] == true){
				return false;
			}
		}
		
		return true;
		
	}
	/**
	 * Realiza a checagem bit a bit dos arrays
	 * @param bit1 - Array 1 de bits
	 * @param bit2 - Array 2 de bits
	 * @return True caso seja candidata a corte
	 */
	public boolean andi(boolean bit1[], boolean bit2[]){
		
		for (int i = 0; i < bit2.length; i++) {
			if(bit1[i] && bit2[i]){
				return false;
			} 
		}
		return true;
	}
	

	/**
	 *  Método para Recorte de Linha Cohen-Sutherland
	 * @param x0 	- Valor x0
	 * @param y0	- Valor y0
	 * @param x1	- Valor x1
	 * @param y1	- Valor y1
	 * @param xmin 	- Valor Xmin
	 * @param xmax	- Valor Xmax
	 * @param ymin	- Valor Ymin
	 * @param ymax	- Valor Ymax
	 */
	public void CohenSutherlandLineClip(int x0, int y0, int x1, int y1, int xmin, int xmax, int ymin, int ymax){
	
		boolean[] outcode0 = new boolean[4];
		boolean[] outcode1 = new boolean[4];
		boolean[] outcodeOut = new boolean[4];
		double x= 0, y = 0; 
		boolean accept = false, done = false;

	 outcode0 = code(x0, y0, xmin, xmax, ymin, ymax); 
	 outcode1 = code(x1, y1, xmin, xmax, ymin, ymax);

	 do {
	   if (vazio(outcode0)  && vazio(outcode1) ) { 
	       accept = true;  done = true;               /* aceitação trivial */
	       System.out.println("aceitação trivial");
	   } else if(!andi(outcode0, outcode1)) {
	       done = true;                               /* rejeição trivial */
	       System.out.println("rejeição trivial");
	   } else {                                       /* Discarte de uma parte */
	     outcodeOut = (!vazio(outcode0)) ?  outcode0 : outcode1;        /* Escolha do Vértice */
	     System.out.println("Ponto escolhido: " + outcodeOut);
	     if (outcodeOut[0] == true) {                                       /* Discarta o topo */
	       x = x0 + (x1 - x0) * (ymax - y0) / (y1 - y0);  y = ymax;
	     } else if(outcodeOut[1] == true) {                                 /* Discarta o fundo */
	       x = x0 + (x1 - x0) * (ymin - y0) / (y1 - y0);  y = ymin;
	     } else if(outcodeOut[2] == true) {                                 /* Discarta a direita */
	       y = y0 + (y1 - y0) * (xmax - x0) / (x1 - x0);  x = xmax;
	     } else if(outcodeOut[3] == true) {                                 /* Discarta a esquerda */
	       y = y0 + (y1 - y0) * (xmin - x0) / (x1 - x0);  x = xmin;
	     }
	     
	     if (outcodeOut == outcode0) {
	        x0 = (int)x;
	        y0 = (int)y; 
	        outcode0 = code(x0, y0, xmin, xmax, ymin, ymax);
	     } else {
	        x1 = (int)x; 
	        y1 = (int)y; 
	        outcode1 = code(x1, y1, xmin, xmax, ymin, ymax);
	     }

	   }
	  } while (!done);

	   if (accept){
		   DesenhaDDA2D ddaLouco = new DesenhaDDA2D();
		   ddaLouco.DDA(x0, y0, x1, y1, Color.GREEN);
	   }
	}
}
