package model;

public class SistemaDeCoordenadas {
	
	public double ndcx;
	public double ndcy;
	public double dcx;
	public double dcy;
	
	//Para coordenadas normalizadas
	/**
	 * Conversão para NDCX
	 * @param x - Valor x
	 * @return NDCX
	 */
	public double paraNDCX(double x){
		
		
		ndcx = (x - 0)/ (600 - 0);

		return ndcx;
	}
	/**
	 * Conversão para NDCY
	 * @param y - Valor Y
	 * @return NDCY
	 */
	public double paraNDCY(double y){
		
		
		ndcy = (y - 0)/( 600 - 0);

		return ndcy;
	}
	//Para coordenadas do dispositivo
	/**
	 * Conversão para NCX
	 * @param X - Valor X
	 * @return NCX
	 */
	public int paraNCX(){
		
		dcx = ((600-1)* ndcx); 
		dcx = Math.round(dcx);
		
		return (int) dcx;
		
	}
	/**
	 * Conversão para NCY
	 * @param Y - Valor Y
	 * @return NCY
	 */
	public int paraNCY(){

		
		dcy = ((600-1)* ndcy); 
		dcy = Math.round(dcy);
		
		return (int) dcy;
	}
	
}
