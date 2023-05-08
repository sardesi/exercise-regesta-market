# Regesta Market

Regesta market è un progetto creato come risoluzione al test d'ingresso presentato in [questo documento](https://github.com/sardesi/exercise-regesta-market/blob/main/Documenti%26Utility/Test%20d'ingresso%2001%20-%20English%2C%20problem%20solving%2C%20coding%20quality%2C%20TDD.pdf). In questo readme è presente la documentazione tecnica e qualche considerazione sul progetto sviluppato. Per il manuale utente l'analisi delle funzoinalità (in formato TDD/BDD) fare riferimento alla cartella "Documenti&Utility" del progetto. In più i vari metodo del BE sono documentati da Javadoc presenti nel codice, per deformazione professionale in lingua inglese.

In caso di quasliasi problema sono a disposizione al seguente indirizzo: simoneardesi@outlook.it

# Introduzione e stack tecnologico

Per realizzare il progetto ho deciso, essendo un programmatore full stack ed essendo questo un test per valutare le competenze, di realizzare una webapp utilizzando  uno stack tecnologico che mi permettesse di lavorare sia su BE, sia su FE che anche su DB. Ho quindi scelto di creare un progetto con backend basato su framework SpringBoot (java) gestito tramite maven, con frontend Angular (typescript/javascript, html, css) gestito tramite node ed un DB in memory basato su H2; nel pacchetto è presente anche un server Apache Tomcat embedded, in modo che sia utilizzabile as is. Per quanto riguarda il DB H2 ed il server Tomcat embedded non sono ovviamente scelte che farei in un ambiente di produzione, ma mi sembravano adeguate per lo scope di questo esercizio, in quanto mi permettono di rilasciare un WAR standalone eseguibile tramite linea di comando, senza dover far installare nessun tool esterno (al netto di di una JDK java) a chi dovrà provare il pacchetto.

Aggiungo anche che rispetto al testo inziale del problema ho aggiunto a quanto richiesto tre feature che vengono ormai ritenute fondamentali per una webapp: gestione dell'autenticazione, multilingua e layout responsive. Ho valutato che aggiungerle non mi sarebbe costato un grande dispendio di tempo, anche perché ho utilizzato delle facility che ho creato in passato per alcuni progetti personali e che ho dovuto solo riadattare a questo progetto ed alle nuove versioni delle tecnologie utilizzate. Tali funzionalità sono presenti anche nel documento di analisi funzionalità, indicati nella narrativa come "Product Owner".

# Premesse

Trattandosi solamente di un progetto d'esempio, e per garantire una consegna rispetto alle tempistiche indicate, ho effettuato alcune assunzioni che non avrei fatto in un progetto reale, di seguito le più impattanti:
- Ogni supplier avrà un solo magazzino, e quindi un singolo valore per i giorni di consegna, salvato su DB direttamente sul supplier. In una casitica reale sarebbe corretto valutare il fatto che un supplier possa avere più magazzini, con tempi di consegna diversi impattati anche dall'articolo stesso.
- La tabella degli sconti è stata modellata in base alle singole informazioni presenti nel testo della richiesta. In un ambiente di produzione, al netto di parlarne con il cliente ed avere quindi il tempo e le risorse per farlo, avrei preferito implementare un valutatore di espressioni in modo da avere liberta assoluta sulla definizione dei parametri su cui si basa lo sconto.
- Può essere attivo solo uno sconto alla volta. Nello specifico viene recuperato il più altro.
- I prodotti, i loro prezzi e le immagini sono state generate con uno certo criterio, ma comunque tramite automatismi: potrebbero quindi non essere particolarmente coerenti fra descrizione, immagine e prezzo. Oltretutto gli url delle immagini fanno riferimento a prodotti generici presenti su amazon, quindi potrebbe capitare che non siano più disponibli in base a quando verrà visionato questo progetto.
- Mi sono fermato a quanto descritto nella richiesta, confrontando un singolo prodotto per volta, senza carrelli o confronti multipli.

# Come avviare l'applicativo

Per avviare il progetto le uniche necessità sono il relativo WAR (vi verrà mandato già compilato tramite WeTransfer via mail separata) e la presenza sul computer della **JDK 17** (o superiore), **compresa la configruazione del path per quest'ultima**. Se tutto ciò è già disponibile è possibile passare direttamente alla sezione di avvio dell'applicativo, in caso contrario proseguire alla prossima sezione.

## Installazione e configurazione della JDK 17

Per prima cosa è necessario scaricare ed installare la versione della JDK 17 compatibile con il proprio sistema operativo. La pagina di download è disponibile a [questo indirzzo](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html). 

Una volta completata l'installazione è necessario configurare due variabili d'ambiente per far riconoscere al pc il comando necessario ad avviare l'applicativo. Andare quindi nel menu start e cercare il pannello di controllo; da lì cercare "variabili" e selezionare "modifica le variabili d'ambiente relative al sistema". Selezioanre poi il puslante "Variabili d'ambiente..." nel tab "Avanzate" e nella sezione in basso "Variabili di sistema" aggiungere una nuova variabile (se non presente, in caso modificarla) chiamata `JAVA_HOME`, con valore il percorse che punta alla home di java appena installata (di solito `C:\Program Files\Java\jdk-17`). Proseguire poi cercando sempre nella sezione "Variabili di sistema" la variabile `Path` e modificarla aggiungendo una nuova voce con valore `%JAVA_HOME%\bin`. **Attenzione** a mettere questa voce in prima posizione nella lista, in quanto voci successive potrebbero invalidare il valore inserito. Successivamente salvare ed uscire dal menu confermando le modifiche.

Se tutto è andato a buon fine aprendo la console (andando nel menu start e scrivendo "cmd" e dando poi invio) e scrivendo il comando `java -version` dovrebbe rispondere con la versione 17 di java. In caso risponda con commando non riconsciuto verificare i passaggi di cui sopra.

## Avvio dell'applicativo

Per avviare l'applicativo è necessario posizionarsi nella cartella che contiene il WAR, scrivere "cmd" nella barra contenete il percorso e premere invio, per aprire la console (oppure aprire la console e navigare fino alla cartella contenente il WAR). Da li sarà possibile eseguire il comando `java -jar regesta-market.war` per avviare il server applicativo che sara poi raggiungibile al link [http://localhost:8080/regesta-market/fe/](http://localhost:8080/regesta-market/fe/).

**N.B.:** La porta 8080 della macchina deve essere inutilizzata, in quanto è la stessa utilzzata dal server embedded.

## Accesso

Una volta entrati nell'applicativo sarà presentata una schermata di login. Le credenziali attualmente abilitate sono le seguenti: simoneardesi@outlook.it, silvia.cerri@regestaitalia.it, market.user@regestaitalia.it. Per tutti i precedenti la password è "password".

# Dettaglio implementazioni

Di seguito il dettaglio dei vari layer che sono stati implementati all'interno del progetto.

## DB

Il database è utilizzato è un H2, scelto per la sua possiblità di essere instanziato in memory all'avvio dell'applicativo. L'inizializzazione avviene all'avvio di spring ed i file di configurazione che vengono eseguiti si trovano sotto `be/src/main/resources/db`. Lo stesso DB viene utilizzato anche dagli unit test.

I file di configurazione dello schema sopra citato è stato semplificato per girare sotto H2, un file completo ed un immagine rappresentante lo schema del DB con tanto di collegamenti ed indici è presente sotto la cartella "DB" del progetto.

## BE

Per quanto rigurda il BE è stato utilizzata l'ultima versione di Springboot, un framework basato su Java che permette di gestire in modo nativo svariati aspetti del ciclo vitale di un applicativo. Per la gestione delle libreire e delle pipeline di build del WAR è invece stato utilizzato Maven. Nello specifico per la build del progetto maven si occupa di far partire sia la build del pacchetto BE che del pacchetto FE (configurata per convergere poi nel pacchetto BE), e generare un WAR contenete tutto il necessario per renderlo un pacchetto standalone.

Di seguito i dettagli sulle principali funzionalità presenti nel pacchetto:
- per quanto riguarda la comunicazione con il database viene utilizzata la libreria Hibernate, che permette di configurare e gestire facilmente le connessioni con il database. Ogni tabella ha una sua entity ed un suo repository (chiamato dao), entrambe che estendono delle interfacce e delle classi astratta che tramite l'utilizzo di generics permettono di avere già a disposizione una gestione minimale del crud di ogni entity senza doverla definire da zero.
- per quanto riguarda la sicurezza ho implementato una versione modificata di una facility che avevo creato per un progetto personale, riadattandola al sistema di security delle nuove cersioni di spring. Si basa su OAuth2.0 e utilizza un token JWT per verificare e filtrare ogni chiamata che arriva sotto root "api/\*", in modo da proteggere ogni chiamata sensibile lasciando aperte la login e le pagine del FE. In più nel token vengono salvate le informazioni dell'utente, in modo da evitare di passarlo in chiaro durante le chiamate e risolvere potenziali problemi di sicurezza in cui un utente prova ad impersonarne un altro.
- per la login e la gestione della password ho riadattato una versione di una facility che avevo creato per un progetto personale, che si occupa di criptare la password e confrontarla con quella a DB, su cui viene salvata già criptata. Per criptarla viene usato un meccanismo che prende in inputi sia il nome utente che la stessa password decriptata, in modo che anche per chi ha l'accesso al DB ed al sistema di crypting sia impossibile risalire alla password originale.
- per quanto rigaurda le API sono configurate tramite le funzionalità base di Spring, e sono inserite all'interno di apposite classi controller. Quest'ultime si occupano solo di ricevere la chiamata ed eventualmente validarne il contenuto, la logica vera e propria è gestita da delle classi service che recuperano i dati in input e li elabarano, utilizzando i dao per la comunicazione con il DB.
- 

**WIP**

## FE

**WIP**
