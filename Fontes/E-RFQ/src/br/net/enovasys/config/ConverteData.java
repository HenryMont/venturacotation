package br.net.enovasys.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe de conversao de Datas. Julianas e Gregorianas. Faixa de alcance fixada entre 1900 e 2099.
 * 
 * @author Israel Ribeiro, Alex Lirio
 *
 */
public class ConverteData {
	
	/**
	 * Metodo Responsavel pela conversao de "Data Gregoriana" para "Data Juliana (Integer)"
	 * 
	 * @param GregorianCalendar
	 * @return Integer
	 */
	public Integer toJulianDate(GregorianCalendar calendar){
		
		if (calendar != null) {
			// Armazena o atributo ANO e remove 1900 de seu valor. Range 1900 ~ 2099
			Integer ano = calendar.get(Calendar.YEAR);
			Integer dia = calendar.get(Calendar.DAY_OF_YEAR) - 31;
			
			ano -= 1900;
			
			calendar.set(Calendar.DAY_OF_YEAR, dia);
			calendar.set(Calendar.YEAR, ano);
			
			DateFormat df = new SimpleDateFormat("yyyDDD");
			
			return Integer.valueOf(df.format(calendar.getTime()));
			
		} else {
			return null;
		}

	}
	
	/**
	 * Metodo Responsavel pela conversao de "Data Gregoriana" para "Data Juliana (Integer)"
	 * 
	 * @param Date
	 * @return Integer
	 */
	@SuppressWarnings("deprecation")
	public Integer toJulianDate(Date calendar){
		
		if (calendar != null) {
			// Armazena o atributo ANO e remove 1900 de seu valor. Range 1900 ~ 2099
			Integer ano = calendar.getYear();
			ano -= 1900;
			calendar.setYear(ano);
			
			DateFormat df = new SimpleDateFormat("yyyDDD");
			df.format(calendar);
			
			return Integer.valueOf(df.format(calendar));
		} else {
			return null;
		}
		

	}
	
	/**
	 * Metodo Responsavel pela conversao de "Data Juliana (Integer)" para "Data Gregoriana"
	 * 
	 * @param Integer
	 * @return Date
	 */
	public Date toDate(Integer julianCalendar){
		if (julianCalendar != null) {
			String date = String.valueOf(julianCalendar);
			String year = date.substring(0, 3);
			String day = date.substring(3, 6);

			
			GregorianCalendar gc = new GregorianCalendar();  
			gc.set(Calendar.DAY_OF_YEAR, Integer.valueOf(day));  
			gc.set(Calendar.YEAR, (Integer.valueOf(year) + 1900));  
			gc.setGregorianChange(gc.getTime());
			
			return gc.getGregorianChange();
		} else {
			return null;
		}
		
	}

	public static void main(String[] args) {
		
		Integer dataJuliana = new ConverteData().toJulianDate(new Date());
		Date dataGregoriana = new ConverteData().toDate(dataJuliana);
		System.out.println(dataJuliana);
		System.out.println(dataGregoriana);
		
	}

}
