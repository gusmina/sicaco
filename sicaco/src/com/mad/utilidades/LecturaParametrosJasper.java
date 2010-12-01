package com.mad.utilidades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * 
 * @author Mauricio Jovel
 * @category Utilidad
 * Clase utilizada para la lectura de parametros para ser mandados a un Jasper.
 *
 */
public class LecturaParametrosJasper {
	private static final Log log = LogFactory.getLog(LecturaParametrosJasper.class);
	
	public static String[] nombresParametrosArray(String pathJrxml) {
		ArrayList<String> s = nombresParametros(pathJrxml);
		String[] s2 = new String[s.size()];
		int i = 0;
		for (Iterator<String> params = s.iterator(); params.hasNext();i++) {
			String name = params.next();
			s2[i] = name;
		}
		return s2;
	}
	
	@SuppressWarnings("unchecked")
	public static String[] nombresClazzArray(String pathJrxml) {
		ArrayList<String> s = nombresClazz(pathJrxml);
		String[] s2 = new String[s.size()];
		int i = 0;
		for (Iterator<String> params = s.iterator(); params.hasNext();i++) {
			String name = params.next();
			s2[i] = name;
		}
		return s2;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> nombresParametros(String pathJrxml) {
		ArrayList<String> list = new ArrayList<String>();
		SAXBuilder builder = new SAXBuilder(false); 
		builder.setValidation(false);
		try {
			Document document = builder.build(pathJrxml);
			Element root = document.getRootElement();
			List<Element> parametros = root.getChildren("parameter");
			for (Iterator<Element> parametrosI = parametros.iterator(); parametrosI.hasNext();) {
				Element parametro = parametrosI.next();
				list.add(parametro.getAttributeValue("name"));
			}
		} catch (JDOMException e) {
			log.fatal("Fallo en la lectura del xml " + pathJrxml, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.fatal("Fallo en la lectura del xml " + pathJrxml, e);
			throw new RuntimeException(e);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> nombresClazz(String pathJrxml) {
		ArrayList<String> list = new ArrayList<String>();
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = builder.build(pathJrxml);
			Element root = document.getRootElement();
			List<Element> parametros = root.getChildren("parameter");
			for (Iterator<Element> parametrosI = parametros.iterator(); parametrosI.hasNext();) {
				Element parametro = parametrosI.next();
				list.add(parametro.getAttributeValue("class"));
			}
		} catch (JDOMException e) {
			log.fatal("Fallo en la lectura del xml " + pathJrxml, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.fatal("Fallo en la lectura del xml " + pathJrxml, e);
			throw new RuntimeException(e);
		} 
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static String nombreSubReporte(String pathJrxml) {
		String s = "";
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = builder.build(pathJrxml);
			Element root = document.getRootElement();
			List<Element> parametros = root.getChildren("parameter");
			for (Iterator<Element> parametrosI = parametros.iterator(); parametrosI.hasNext();) {
				Element parametro = parametrosI.next();
				if(parametro.getAttribute("name").equals("SUBREPORT_DIR")) {
					Element def = parametro.getChild("defaultValueExpression");
					s = def.getTextTrim();
				}
			}
		} catch (JDOMException e) {
			log.fatal("Fallo en la lectura del xml " + pathJrxml, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.fatal("Fallo en la lectura del xml " + pathJrxml, e);
			throw new RuntimeException(e);
		}
		return s;
	}
}
