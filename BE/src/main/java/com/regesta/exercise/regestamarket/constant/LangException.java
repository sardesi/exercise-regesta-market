package com.regesta.exercise.regestamarket.constant;

/**
 * The enum used to define a CustomLangException
 * @author ars
 *
 */
public enum LangException {

// Generic
	ACCOUNT_NOT_AUTHORISED("Utente non autorizzato."),
	CORRUPT_FILE("Il file selezionato risulta corrotto. Contattare l'assistenza."),
	DOCUMENT_FORMAT_ERROR("Errore formato file."),
	DOCUMENT_UPLOAD_ERROR("Errore nel caricamento dell'allegato."),
	INVALID_INPUT("I dati ricevuti non sono validi."),
	MESSAGE_NOT_FOUND("Nessun messaggio trovato con l'id selezionato."),
	MD5_ERROR("Errore nella creazione dell'md5."),
	NOT_ALLOWED("Impossibile eseguire la richiesta"),
	NOT_FOUND("Nessun elemento trovato per l'identificativo specificato."),
	TOKEN_EXPIRED("Il periodo di autenticazione è scaduto, è necessario effettuare nuovamente la login."),
	USER_MAIL_NOT_FOUND("Nessun utente trovato per l'indirizzo mail specificato."),
	USER_NOT_ACTIVE("L'utente risulta disattivato."),
	USER_NOT_FOUND("Nessun utente trovato per l'identificativo specificato."),
    UNAUTHORIZED("L'utente non è autorizzato ad eseguire l'operazione."),
	USERNAME_ALREADY_TAKEN("Username già in uso per un'altro utente"),
    WRONG_CREDENTIALS("Le credenziali inserite risultano errate."),
	
// Validations
    MISSING_LANGUAGE("Lingua necessaria"),
	UNKNOWN_ORDER ("Campo di ordinamento non riconosciuto"),
	UNKNOWN_LANGUAGE ("Lingua non riconosciuta");
	
	private final String message;

	private LangException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
