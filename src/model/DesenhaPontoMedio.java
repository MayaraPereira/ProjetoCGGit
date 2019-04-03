package model;

import view.PanelPrincipal;

public class DesenhaPontoMedio {
	

	   //  Bresenham line-drawing procedure for |m| < 1.0.  
	  public void lineBres (int x0, int y0, int xEnd, int yEnd)
	   {
	      int dx = Math.abs(xEnd - x0),  dy = Math.abs(yEnd - y0);
	      int p = 2 * dy - dx;
	      int twoDy = 2 * dy,  twoDyMinusDx = 2 * (dy - dx);
	      int x, y;

	      // Determine which endpoint to use as start position.  
	      if (x0 > xEnd) {
	         x = xEnd;
	         y = yEnd;
	         xEnd = x0;
	      }
	      else {
	         x = x0;
	         y = y0;
	      }
	      setpixel (x, y);
	      while (x < xEnd) {
	          x++;
	          if (p < 0)
	             p += twoDy;
	          else {
	             y++;
	             p += twoDyMinusDx;
	          }
	          setpixel (x, y);
	       }
	    }
	   
	   public void setpixel(int x, int y){
			PanelPrincipal.panelDesenho2D.setPixel(x, y);
		}
}