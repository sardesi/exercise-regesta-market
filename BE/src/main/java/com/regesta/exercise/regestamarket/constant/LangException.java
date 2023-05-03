package com.regesta.exercise.regestamarket.constant;

/**
 * The enum used to define a CustomLangException
 * @author ars
 *
 */
public enum LangException {

// Generiche
	ACCOUNT_NOT_AUTHORISED("Utente non autorizzato."),
	COMPANY_NOT_ACTIVE("L'azienda dell'utente risulta disattivata."),
	CONFIGURATION_NOT_FOUND("Sono presenti degli errori di configurazione all'interno dell'applicativo. Contattare l'assistenza."),
	CORRUPT_FILE("Il file selezionato risulta corrotto. Contattare l'assistenza."),
	DOCUMENT_FORMAT_ERROR("Errore formato file."),
	DOCUMENT_INVALID_STATE("L'attuale stato del documento non permette di eseguire l'operazione selezionata."),
	DOCUMENT_NOT_FOUND("Nessun documento trovato con l'id selezionato."),
	DOCUMENT_UPLOAD_ERROR("Errore nel caricamento dell'allegato."),
	INVALID_INPUT("I dati ricevuti non sono validi."),
	INVALID_CHANGE_PASSWORD("La password inserita non combacia con quella attuale."),
	MESSAGE_NOT_FOUND("Nessun messaggio trovato con l'id selezionato."),
	MD5_ERROR("Errore nella creazione dell'md5."),
	NO_ASSOCIATION_FOUND("Nessuna associazione trovata"),
	NO_SETTABLE_LANGUAGE ("Impossibile impostare la lingua scelta"),
	NOT_ALLOWED("Impossibile eseguire la richiesta"),
	NOT_FOUND("Nessun elemento trovato per l'identificativo specificato."),
	TOKEN_EXPIRED("Il periodo di autenticazione è scaduto, è necessario effettuare nuovamente la login."),
	USER_MAIL_NOT_FOUND("Nessun utente trovato per l'indirizzo mail specificato."),
	USER_NOT_ACTIVE("L'utente risulta disattivato."),
	USER_NOT_FOUND("Nessun utente trovato per l'identificativo specificato."),
    UNAUTHORIZED("L'utente non è autorizzato ad eseguire l'operazione."),
	UNKNOWN_LANGUAGE ("Lingua non riconosciuta"),
	USERNAME_ALREADY_TAKEN("Username già in uso per un'altro utente"),
    WRONG_CREDENTIALS("Le credenziali inserite risultano errate."),
    
// Prenotazioni
	ELEMENTI_PRENOTAZIONE_CHIUSI("Non possibile prenotare una postazione se la sede, la stanza o la postazione stessa sono chiuse."),
	GREEN_PASS_NON_VALIDO("La certificazione green pass non è stata caricata oppure non è stata ancora approvata."),
	POSTAZIONE_PIENA("La postazione è già al completo per la data selezionata."),
	POSTAZIONE_PREFERITA_NON_SETTATA("E' necessario impostare una postazione preferita per utilizzare la funzione di prenotazione veloce."),
	PRENOTAZIONE_FESTIVA_NON_POSSIBILE("Non è possibile prenotare un giorno festivo."),
	PRENOTAZIONE_GIA_PRESENTE_PER_GIORNO("E' già presente una tua prenotazione per il giorno specificato."),
	PRENOTAZIONE_GIA_VERIFICATA("La prenotazione risulta già verificata, non è quindi possibile modificarla."),
	PRENOTAZIONE_NON_AMMINISTRATORE("La prenotazione non risulta inserita da un amministratore e non è quindi modificabile."),
	
// Password
	PASSWORD_ERROR_REGEX("La password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, una minuscola ed un numero."),
	PASSWORD_ERROR_NECESSARIA("E' necessario specificare una password per eseguire l'operazione."),
	RESET_PASSWORD_CHIAVE_ERRATA("Il codice per la modifica della password risulta errato. La preghiamo di provare a richiedere un nuovo cambio password, e se l'errore persiste contattare l'assistenza."),
	RESET_PASSWORD_CHIAVE_NON_VERIFICABILE("Codice per la modifica della password non verificabile. Provare a richiedere un nuovo cambio password, e se l'errore persiste contattare l'assistenza."),
	RESET_PASSWORD_GIA_UTILIZZATO("Questo codice per il cambio della password è già stato utilizzato. Se necessario la preghiamo di effettuare una nuova richiesta."),
	RESET_PASSWORD_TEMPO_SCADUTO("Il tempo limite per il cambio password è scaduto. Se necessario la preghiamo di effettuare una nuova richiesta.");
	
	private final String message;

	private LangException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
