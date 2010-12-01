package com.mad.utilidades;

/**
 * 
 * @author Carlos Ramirez
 * @category Utilidad
 * Clase que contiene metodos para calcular la cuota de un prestamo normal
 * 
 */
public class Calculadora {
	
	/**
	 * 
	 * @param capital (el monto solicitado o a solicitar)
	 * @param interes (porcentaje de interes anual, Ej: 13.5)
	 * @param periodos (Meses de duracion del prestamo)
	 * @return float cuota
	 * 
	 */
	public static float calcularCuotaFloat(Double capital,Double interes,Integer periodos) {
		float respuesta;
		if(interes != 0){
			interes = (interes/12)/100;
			double  tmp = Math.pow(((interes)+1), periodos);
			double tmp2 = tmp-1;
			respuesta=(float) (capital.floatValue()*((interes)*tmp)/tmp2);
		}else{
			respuesta = capital.floatValue()/periodos;
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @param capital (el monto solicitado o a solicitar)
	 * @param interes (porcentaje de interes anual, Ej: 13.5)
	 * @param periodos (Meses de duracion del prestamo)
	 * @return float cuota
	 * 
	 */
	public static Double calcularCuotaDouble(Double capital,Double interes,Integer periodos) {
		Double respuesta;
		if(interes != 0){
			interes = (interes/12)/100;
			double  tmp = Math.pow(((interes)+1), periodos);
			double tmp2 = tmp-1;
			respuesta= capital*((interes)*tmp)/tmp2;
		}else{
			respuesta = capital/periodos;
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @param capital
	 * @param interes
	 * @param cuota
	 * @return plazos restantes
	 */
	public static Integer calcularPeriodosRestantes(Double capital,Double interes,Double cuota) {
		Integer respuesta;
		Double respuestaDouble;
		if(interes != 0){
			interes = (interes/12)/100;
			double tmp = (capital*interes)/cuota;
			double tmp2 = 1 - tmp;
			double superior = Math.log(tmp2);
			double inferior = Math.log(1 + interes);
			double calculo = -1 * (superior/inferior);
			respuestaDouble = calculo;
			respuesta = Redondeo.roundDouble(respuestaDouble, 0).intValue();
		}else{
			respuestaDouble = capital/cuota;
			respuesta = Redondeo.roundDouble(respuestaDouble,0).intValue();
		}
		return respuesta;
	}
	
	/*
var nuevocapital;
nuevocapital=capitalpendiente-capitalamortiza;
 
var plazo;
x=(nuevocapital*interes)/cuota;
xx=1-x;
sup=Math.log (xx);
 
y=1+interes;
inf=Math.log (y);
 
z=-1*(sup/inf);
plazo=z/12;
 
<!-- expresamos el plazo en años --> 
var plazoanos;
plazoanos=parseInt (plazo, 10);
 
<!-- expresamos el plazo en meses --> 
var plazomeses;
meses=(plazo-plazoanos)*12;
plazomeses=Math.round (meses);
 
<!-- devolvemos el resultado de la operacion --> 
form.nuevoplazo.value=plazoanos+" años, y "+plazomeses+" meses";
 
}

	 */
}
