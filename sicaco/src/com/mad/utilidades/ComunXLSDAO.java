package com.mad.utilidades;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import org.apache.struts.upload.FormFile;

public class ComunXLSDAO {

	public ComunXLSDAO() {

	}

	/**
	 * Para elaborar la insercion de departamentos de AEROMAN
	 * 
	 * @param archivo
	 * @param numeroHoja
	 * @param numeroPrimeraFila
	 * @param numeroUltimaFila
	 * @param columnaNombre
	 * @param columnaCentroDeCosto
	 * @return
	 */
	
	public int imprimirDepartamentos(
			String archivo, 
			Integer numeroHoja, 
			Integer numeroPrimeraFila, 
			Integer numeroUltimaFila,
			Integer columnaDepartamento,
			Integer columnaCentroDeCosto
	){
		
		ArrayList<String> centrosDeCosto = new ArrayList<String>();
		
		String centroDeCosto = null;
		String depto  = null;
		
		try {
			
			InputStream myxls = new FileInputStream(archivo);
			HSSFWorkbook wb     = new HSSFWorkbook(myxls);
			
			HSSFSheet sheet = wb.getSheetAt(numeroHoja);
			
			HSSFRow row = null;
			
			Integer dptId = 140;
			
			for (int i = numeroPrimeraFila; i <= numeroUltimaFila; i++) {
			
				row     = sheet.getRow(i);
				
				centroDeCosto = row.getCell(columnaCentroDeCosto.shortValue()).getRichStringCellValue().getString().trim();
				
				if (!centrosDeCosto.contains(centroDeCosto)){
					
					depto = row.getCell(columnaDepartamento.shortValue()).getRichStringCellValue().getString().trim();
					
					System.out.println("      ("+dptId+", 2, '"+depto+"', '"+depto+"', '', 'A', '"+centroDeCosto+"'), ");
					dptId++;
					centrosDeCosto.add(centroDeCosto);
				}
				
			}
			
		
			
		} catch (Exception e) {
			System.out.println("imprimirDepartamentos: Se desencadeno una excepcion inesperada: "+e);
			return 1;
		}
		
		return 0;
		
	}
	

	
	public int imprimirFilas(
			FormFile file, 
			Integer numeroHoja, 
			Integer numeroPrimeraFila, 
			Integer numeroUltimaFila
	){
		
		Integer numeroDeCeldas = 0;
		
		try {
			
			InputStream myxls = file.getInputStream();
			HSSFWorkbook wb     = new HSSFWorkbook(myxls);
			
			HSSFSheet sheet = wb.getSheetAt(numeroHoja);
			
			HSSFRow row = null;
			
			HSSFCell cell = null;
			
			for (int i = numeroPrimeraFila; i <= numeroUltimaFila; i++) {
				
				System.out.println("*** Se imprimiran las celdas de la fila: "+i);
			
				row = sheet.getRow(i);
				
				numeroDeCeldas = row.getPhysicalNumberOfCells();
				
				for (Integer j = 0; j < numeroDeCeldas; j++) {
					
					cell = row.getCell(j.shortValue());
					
					if(cell == null){
						System.out.println(" (nulo) ");
					}else{
						this.imprimirCelda(cell);
					}
					
				}
				
			}
			
		
			
		} catch (Exception e) {
			System.out.println("imprimirValores: Se desencadeno una excepcion inesperada: "+e);
			return 1;
		}
		
		return 0;
		
	}	
	
	
	
	public int imprimirCelda(HSSFCell celda){
		
		Object valor = null;
		
		String tipo = "";
		
		try {
			
			switch(celda.getCellType()){
			
				case HSSFCell.CELL_TYPE_BLANK:
					tipo = "blanco";
					valor = "";
					break;
					
				case HSSFCell.CELL_TYPE_BOOLEAN:
					tipo = "booleano";					
					valor = celda.getBooleanCellValue();
					break;				
					
				case HSSFCell.CELL_TYPE_ERROR:
					tipo = "error";					
					valor = celda.getErrorCellValue();
					break;
					
				case HSSFCell.CELL_TYPE_FORMULA:
					tipo = "formula";					
					valor = celda.getCellFormula();
					break;				
					
				case HSSFCell.CELL_TYPE_NUMERIC:
					
					if ( HSSFDateUtil.isValidExcelDate(celda.getNumericCellValue()) ){
						tipo = "fecha ("+celda.getNumericCellValue()+")";						
						valor = celda.getDateCellValue();
					}
					else {
						tipo = "numerico";						
						valor = celda.getNumericCellValue();
					}
					
					break;
					
				case HSSFCell.CELL_TYPE_STRING:
					tipo = "cadena";					
					valor = celda.getRichStringCellValue().getString();
					break;				
				
			}
				
				System.out.println(celda.getCellNum()+". ("+tipo+") "+valor+" ");
			
		} catch (Exception e) {
			System.out.println("imprimirCelda: Se desencadeno una excepcion inesperada: "+e);
			return 1;
		}
	
	return 0;
		
	}
	
	
	

}
