package com.mad.utilidades.compresion;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * ZipFile es un utilitario creado con el proposito de generar archivos con
 * compresos con el famoso formato ZIP, esta se encarga de dar una interface
 * limpia y sencilla para la compresi�n de archivos sin llegar a mostar a quien
 * la utiliza todo lo necesario para la compresi�n del archivo.
 * 
 * @author Mauricio
 * @version 1.0
 */
public class ZipFile {
	/**
	 * indica el tama�o del buffer de lectura
	 */
	public static int BUFFER_SIZE = 8192;

	/**
	 * Crea un archivo zip, comprimiendo todo el contenido de un directorio
	 * especificado, es de suma importancia que el path del directorio termine
	 * con el caracter <b><i>/</i></b>, y este archivo lo guarda en la
	 * direcci&oacute;n especificada <br>
	 * por ejemplo podemos ingresar como direccion las siguientes expresiones:<br>
	 * <br>
	 * c:/archivo/archivo/archivo.zip &oacute; /tmp/directorio/archivo.zip
	 * 
	 * @param path
	 *            Directorio con permisos de escritura donde se desea dejar el
	 *            archivo zip
	 * @param origen
	 *            Directorio con permisos de lectura de los archivos que se
	 *            desan comprimir.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void zip(String path, String origen) throws FileNotFoundException,
			IOException {
		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(path);
			CheckedOutputStream checksum = new CheckedOutputStream(dest,
					new Adler32());
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					checksum));
			out.setMethod(ZipOutputStream.DEFLATED);
			byte data[] = new byte[BUFFER_SIZE];

			File f = new File(origen);
			String files[] = f.list();

			if (files != null) {

				for (int i = 0; i < files.length; i++) {
					// System.out.println("Agregando: "+files[i]);
					FileInputStream fi = new FileInputStream(origen + files[i]);
					origin = new BufferedInputStream(fi, BUFFER_SIZE);
					ZipEntry entry = new ZipEntry(files[i]);
					out.putNextEntry(entry);
					int count;
					while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
						out.write(data, 0, count);
					}
					origin.close();
				}
				out.close();
				// System.out.println("checksum:
				// "+checksum.getChecksum().getValue());
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Borra un directorio de manera recursiva.
	 * 
	 * @param dir
	 *            directorio que se desea eliminar.
	 * @return Retorna un valor verdadero si el directorio pudo ser eliminado o
	 *         falso si no se pudo eliminar.
	 */
	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// El directorio ahora esta vacio y puede ser borrado
		return dir.delete();
	}

	/**
	 * Borra un directorio de manera recursiva.
	 * 
	 * @param path
	 *            direcci&oacute;n f&iacute;sica del directorio que se desea
	 *            eliminar
	 * @return Retorna un valor verdadero si el directorio pudo ser eliminado o
	 *         falso si no se pudo eliminar.
	 */
	public boolean deleteDir(String path) {
		return deleteDir(new File(path));
	}
}
