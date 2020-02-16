package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Navegador {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String entradaTexte = "";
		File directoriAnterior = new File("");
		File directoriActual = new File("");

		tractarEntrada.tipusEntrada darreraEntrada = null;

		File auxDirectori;
		tractarEntrada tractarTexteEntrada;

		while (darreraEntrada != tractarEntrada.tipusEntrada.EXIT) {
			System.out.print(directoriActual.getAbsolutePath() + ">");
			entradaTexte = sc.nextLine();
			tractarTexteEntrada = new tractarEntrada(entradaTexte);

			switch (tractarTexteEntrada.obtenirTipusEntrada()) {
			case GOTO:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				if (auxDirectori.exists() && auxDirectori.isDirectory()) {
					directoriAnterior = directoriActual;
					directoriActual = auxDirectori;
				} else {
					System.out.println("Directori no trobat.");
				}
				break;

			case GOLAST:
				auxDirectori = new File(directoriAnterior.getPath());
				directoriActual = directoriAnterior;
				directoriAnterior = auxDirectori;
				break;

			case LIST:
				String[] llistatElements = directoriActual.list();
				if (llistatElements != null && llistatElements.length > 0) {
					for (int i = 0; i < llistatElements.length; i++) {
						File archivos = new File(directoriActual.getAbsolutePath(), llistatElements[i]);
						System.out.println(llistatElements[i]);
						if (archivos.isDirectory()) {
							String[] archivoSubcapeta = archivos.list();
							for (int j = 0; j < archivoSubcapeta.length; j++) {
								System.out.println(archivoSubcapeta[j]);
							}
						}
					}
				} else {
					System.out.println("Directori sense elements.");
				}
				break;

			case UP:
				if (directoriActual.getAbsoluteFile().getParentFile() != null) {
					directoriActual = new File(directoriActual.getAbsoluteFile().getParent());

				} else {
					directoriActual.getAbsoluteFile().getParentFile();
				}
				break;

			case INFOFILE:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				if (auxDirectori.exists() && auxDirectori.isFile()) {
					String[] archivos = directoriActual.list();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

					for (int i = 0; i < archivos.length; i++) {
						File archivo = new File(directoriActual.getAbsolutePath(), archivos[i]);
						if (!archivo.isDirectory() && archivos[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
							System.out.println(archivo.getName() + " - (Archivo) - " + archivo.length() + " bytes - "
									+ sdf.format(archivo.lastModified()));
						}
					}
				}

				break;

			case INFODIR:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				if (auxDirectori.exists() && auxDirectori.isDirectory()) {
					String[] directorios = directoriActual.list();
					SimpleDateFormat sdfDir = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					if (directorios.length > 0) {
						for (int i = 0; i < directorios.length; i++) {
							File archivo = new File(directoriActual.getAbsolutePath(), directorios[i]);
							if (archivo.isDirectory() && directorios[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
								System.out.println(archivo.getName() + " - (Archivo) - " + archivo.length() + " bytes - "
										+ sdfDir.format(archivo.lastModified()));
							}
						}
					}
				}
				break;

			case HELP:
				System.out.println("\nAyuda de terminal \nComandos de la terminal");
				System.out.println("-------------------");
				System.out.print("GOTO, Ir a la ruta especifica.\n" + "GOLAST, Ir al ultimo directorio especificado.\n"
						+ "LIST, Listar elementos del directorio.\n" + "UP, Ir al directorio padre.\n"
						+ "INFOFILE, Información del fichero indicado.\n" + "INFODIR, Información del directorio indicado.\n"
						+ "HELP, Ayuda de los comandos.\n" + "CREATEDIR, Crear un directorio.\n"
						+ "CREATEFILE, Crear un archivo.\n"
						+ "SORTBY, Como el LIST, pero ordenado por fecha de modificación.\n"
						+ "DELETEDIR, Eliminar el o los directorios indicados.\n\n");
				break;

			case CREATEDIR:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				if (!auxDirectori.exists()) {
					String[] directorios = directoriActual.list();
					for (int i = 0; i < directorios.length; i++) {
						File archivo = new File(directoriActual.getAbsolutePath(), directorios[i]);
						if (!directorios[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
							auxDirectori.mkdir();
							System.out.println("Carpeta creada");
							break;
						} else if (directorios[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
							System.out.println("La carpeta ya existe");
							break;
						}
					}
				}
				break;

			case CREATEFILE:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				if (!auxDirectori.exists()) {
					String[] directorios = directoriActual.list();
					for (int i = 0; i < directorios.length; i++) {
						if (!directorios[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
							try {
								auxDirectori.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("El fichero se ha creado");
							break;
						} else if (directorios[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
							System.out.println("El fichero ya existe");
							break;
						}
					}
				}
				break;
			case DELETEDIR:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				if (auxDirectori.exists() && auxDirectori.isDirectory()) {
					String[] directorios = directoriActual.list();
					for (int i = 0; i < directorios.length; i++) {
						if (directorios[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
							if (auxDirectori.isDirectory()) {
								auxDirectori.delete();
								System.out.println("El directorio se ha borrado");
								break;
							}
						}
					}
				}
				break;
			case DELETEFILE:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				if (auxDirectori.exists() && auxDirectori.isFile()) {
					String[] directorios = directoriActual.list();
					for (int i = 0; i < directorios.length; i++) {
						File archivo = new File(directoriActual.getAbsolutePath(), directorios[i]);
						if (directorios[i].contains(tractarTexteEntrada.obtenirParametres()[0])) {
							if (auxDirectori.isFile()) {
								archivo.delete();
								System.out.println("El archivo se ha borrado");
								break;
							}
						}
					}
				}
				break;
			case SORTBY:
				SimpleDateFormat sdfDir = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				File[] archivos = directoriActual.listFiles();
				String entrada = tractarTexteEntrada.obtenirParametres()[0].toLowerCase();
				switch (entrada) {
				case "name":
					try {
						Files.list(Paths.get(directoriActual.getPath())).sorted().forEach(System.out::println);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				case "date":
					File[] files = directoriActual.listFiles();
					File curr;
					BasicFileAttributes attr;
					Arrays.sort(files, Comparator.comparingLong(File::lastModified));
					try {
						for (short i = 0; i < files.length; ++i) {
							curr = files[i];
							attr = Files.readAttributes(Paths.get(curr.getPath()), BasicFileAttributes.class);
							System.out.println(curr.getName() + " " + archivos[i].length() + " bytes - LastModified: "
									+ sdfDir.format(archivos[i].lastModified()));
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}

					break;
				default:
					System.out.println("Sorting criteria not recognized");
					for (short i = 0; i < archivos.length; ++i) {
						System.out.println(archivos[i].getName() + " " + archivos[i].length() + " bytes - "
								+ sdfDir.format(archivos[i].lastModified()));
					}
				}
				break;
			case LOADFILE:
				if (directoriActual.getPath() == "") {
					auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
				} else {
					auxDirectori = new File(directoriActual.getPath() + directoriActual.separator
							+ tractarTexteEntrada.obtenirParametres()[0]);
				}
				try {
					FileReader archivoDatos = new FileReader(auxDirectori);
					BufferedReader miBuffer = new BufferedReader(archivoDatos);
					String linea = "";
					if (miBuffer != null) {
						System.out.println("Comandos:");
						System.out.println("--------");
						while (linea != null) {

							linea = miBuffer.readLine();
							if (linea != null) {
								System.out.print(linea);
							}
						}
					}
					System.out.println();
					archivoDatos.close();

				} catch (IOException e) {
					System.out.println("Error: " + e);
				}
				break;
			case LOG:

				break;
			case CLEARLOG:

				break;
			case IDIOMA:

				break;
			case SAVEXMLTREE:

				break;
			case LOADXMLTREE:

				break;
			case GETERRORS:

				break;
			case GETWARNINGS:

				break;
			case SETPERMISSION:

				break;
			case SAVEJSONTREE:

				break;
			case SETLITERAL:

				break;
			case EXIT:
				break;
			}

		}
	}

}
