package principal;

public class tractarEntrada {

	private String _entrada;
	private String _error;
	private tipusEntrada _tipusEntrada;
	private String[] _parametres;
	private tipusOrdenacio tipusOrdre;

	public tipusOrdenacio getTipusOrdre() {
		return tipusOrdre;
	}

	public void setTipusOrdre(tipusOrdenacio tipusOrdre) {
		this.tipusOrdre = tipusOrdre;
	}

	enum tipusEntrada {
		LOGIN, GOTO, // ANAR A UNA RUTA
		GOLAST, // ANAR AL DARRER DIRECTORI VISITAT
		LIST, // LLISTAR ELEMENTS DEL DIRECTORI
		UP, // ANAR AL DIRECTORI PARE
		INFOFILE, // INFORMACIO DEL FITXER INDICAT
		INFODIR, // INFORMACIO DEL DIRECTORI INDICAT
		HELP, // PINTAR PER PANTALLA TOTES LES COMANDES
		CREATEDIR, // CREAR UN DIRECTORI
		CREATEFILE, // CREAR UN FITXER
		SORTBY, // COM EL LIST, PERO ORDENANT ELS FITXERS PER ALGUN CRITERI.
		DELETEDIR, // ELIMINAR EL O ELS DIRECTORIS INDICATS
		DELETEFILE, // ELIMINAR EL O ELS FITXERS INDICATS
		LOADFILE,
		// ERROR,
		LOG, // PINTA POR PANTALLA EL LOG DE LA BASE DE DATOS.
		CLEARLOG, // ELIMINA EL LOG DE LA BASE DE DATOS.
		EXIT, // FINALIZA LA APLICACION.
		IDIOMA, SAVEXMLTREE, LOADXMLTREE, GETERRORS, GETWARNINGS, SETPERMISSION, SAVEJSONTREE, LOADJSONTREE, SETLITERAL
	}

	public enum tipusOrdenacio {
		NAME, DATE
	}

	public tractarEntrada(String _entrada) {
		this._entrada = _entrada;
		this._parametres = new String[2];
		carregarObjecte();
	}

	public tipusEntrada obtenirTipusEntrada() {
		return this._tipusEntrada;
	}

	public String[] obtenirParametres() {
		return this._parametres;
	}

	public String obtenirError() {
		return this._error;
	}

	private void carregarObjecte() {
		String auxTexte = this._entrada;
		auxTexte = auxTexte.toLowerCase();
		if (auxTexte != null) {
			String[] elements = auxTexte.split(" ");
			switch (elements[0]) {

			case "login":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.IDIOMA;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}

				break;

			case "goto":
				if (elements.length == 2) {
					this._parametres = new String[1];
					this._parametres[0] = elements[1];
					this._tipusEntrada = tipusEntrada.GOTO;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "golast":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.GOLAST;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				this._tipusEntrada = tipusEntrada.GOLAST;
				break;

			case "list":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.LIST;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "up":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.UP;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "infofile":
				if (elements.length == 2) {
					this._tipusEntrada = tipusEntrada.INFOFILE;
					this._parametres[0] = elements[1].toLowerCase();
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "infodir":
				if (elements.length == 2) {
					this._tipusEntrada = tipusEntrada.INFODIR;
					this._parametres[0] = elements[1].toLowerCase();
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "help":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.HELP;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "createdir":
				if (elements.length == 2) {
					this._tipusEntrada = tipusEntrada.CREATEDIR;
					this._parametres[0] = elements[1].toLowerCase();
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "createfile":
				if (elements.length == 2) {
					this._tipusEntrada = tipusEntrada.CREATEFILE;
					this._parametres[0] = elements[1].toLowerCase();
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "deletedir":
				if (elements.length == 2) {
					this._tipusEntrada = tipusEntrada.DELETEDIR;
					this._parametres[0] = elements[1].toLowerCase();
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "deletefile":
				if (elements.length == 2) {
					this._tipusEntrada = tipusEntrada.DELETEFILE;
					this._parametres[0] = elements[1].toLowerCase();
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "sortby":
				if (elements.length == 2) {
					this._tipusEntrada = tipusEntrada.SORTBY;
					this._parametres[0] = elements[1].toLowerCase();
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "loadfile":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.IDIOMA;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "log":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.LOG;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "clearlog":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.CLEARLOG;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "idioma":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.IDIOMA;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "savexmltree":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.SAVEXMLTREE;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "loadxmltree":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.LOADXMLTREE;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "geterrors":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.GETERRORS;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "getwarnings":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.GETWARNINGS;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "setpermission":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.SETPERMISSION;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "savejsontree":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.SAVEJSONTREE;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "loadjsontree":

				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.LOADJSONTREE;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			case "setliteral":

				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.SETLITERAL;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;
			case "exit":
				if (elements.length == 1) {
					this._tipusEntrada = tipusEntrada.EXIT;
				} else {
					this._tipusEntrada = tipusEntrada.GETERRORS;
					this._error = "Error";
				}
				break;

			default:
				this._tipusEntrada = tipusEntrada.GETERRORS;
				break;
			}

		} else {
			this._tipusEntrada = tipusEntrada.GETERRORS;
			this._error = "Error";
		}

	}

}