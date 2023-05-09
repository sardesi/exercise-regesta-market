# Regesta Market

Regesta market è un progetto creato come risoluzione al test d'ingresso presentato in [questo documento](https://github.com/sardesi/exercise-regesta-market/blob/main/Documenti%26Utility/Test%20d'ingresso%2001%20-%20English%2C%20problem%20solving%2C%20coding%20quality%2C%20TDD.pdf). In questo readme è presente la documentazione tecnica e qualche considerazione sul progetto sviluppato. Per il manuale utente e l'analisi delle funzionalità (in formato TDD/BDD) fare riferimento alla cartella "Documenti&Utility" del progetto. In più i vari metodo del BE sono documentati da Javadoc presenti nel codice, per deformazione professionale in lingua inglese.

In caso di quasliasi problema sono a disposizione al seguente indirizzo: simoneardesi@outlook.it

# Introduzione e stack tecnologico

Per realizzare il progetto ho deciso, essendo un programmatore full stack ed essendo questo un test per valutare le competenze, di realizzare una webapp utilizzando uno stack tecnologico che mi permettesse di lavorare sia su BE, sia su FE che anche su DB. Ho quindi scelto di creare un progetto con backend basato su framework SpringBoot (java) gestito tramite maven, con frontend Angular (typescript, javascript, html, css) gestito tramite node ed un DB in memory basato su H2; nel pacchetto è presente anche un server Apache Tomcat embedded, in modo che sia utilizzabile as is. Per quanto riguarda il DB H2 ed il server Tomcat embedded non sono ovviamente scelte che farei in un ambiente di produzione, ma mi sembravano adeguate per lo scope di questo esercizio, in quanto mi permettono di rilasciare un WAR standalone eseguibile tramite linea di comando, senza dover far installare nessun tool esterno (al netto di una JDK java) a chi dovrà provare il pacchetto.

Un altro motivo per cui ho scelto questo stack architetturale è che l'ho usato parecchio in passato pre creare poc o piccole applicazioni in modo rapido, grazie all'alto numero di librerie esterne che permettono di velocizzare il lavoro avendo già delle basi di partenza sugli aspetti core del progetto (come per esempio la comunicazione con il DB, un set di componenti grafici standard, la gestione degli unit test, ecc..).

Specifico anche che rispetto al testo inziale del problema ho aggiunto a quanto richiesto tre feature che vengono ormai ritenute fondamentali per una webapp: gestione dell'autenticazione, multilingua e layout responsive. Ho valutato che aggiungerle non mi sarebbe costato un grande dispendio di tempo, anche perché ho utilizzato delle facility che ho creato in passato per alcuni progetti personali e che ho dovuto solo riadattare a questo progetto ed alle nuove versioni delle tecnologie utilizzate. Tali funzionalità sono presenti anche nel documento di analisi funzionalità, indicati nella narrativa come "Product Owner".

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

Per avviare l'applicativo è necessario posizionarsi nella cartella che contiene il WAR, scrivere "cmd" nella barra contenete il percorso e premere invio, per aprire la console (oppure aprire la console e navigare fino alla cartella contenente il WAR). Da li sarà possibile eseguire il comando `java -jar regesta-market.war` per avviare il server applicativo. Una volta completato l'avvio, quando la console visualizza la riga "*Started RegestaMarketApplication*," l'applicativo sarà raggiungibile al link [http://localhost:8080/regesta-market/fe/](http://localhost:8080/regesta-market/fe/).

**N.B.:** La porta 8080 della macchina deve essere inutilizzata, in quanto è la stessa utilzzata dal server embedded.

**N.B.:** Se in qualunque momento il server stesso o le chiamate danno l'impressione di essere freezati potrebbe essersi lockata la visualizzazione della console. Provare a cliccare sulla schermata della console cmd e a premere invio.

## Accesso

Una volta entrati nell'applicativo sarà presentata una schermata di login. Le credenziali attualmente abilitate sono le seguenti: simoneardesi@outlook.it, silvia.cerri@regestaitalia.it, market.user@regestaitalia.it. Per tutti i precedenti la **password** è "password".

# Dettaglio implementazioni

Di seguito il dettaglio dei vari layer che sono stati implementati all'interno del progetto.

## DB

Il database è utilizzato è un H2, scelto per la sua possiblità di essere instanziato in memory all'avvio dell'applicativo. L'inizializzazione avviene all'avvio di spring ed i file di configurazione che vengono eseguiti si trovano sotto `be/src/main/resources/db`. Lo stesso DB viene utilizzato anche dagli unit test.

I file di configurazione dello schema sopra citato è stato semplificato per girare sotto H2, un file completo ed un immagine rappresentante lo schema del DB con tanto di collegamenti ed indici è presente sotto la cartella "DB" del progetto.

## BE

Per quanto rigurda il BE è stato utilizzata l'ultima versione di Springboot, un framework basato su Java che permette di gestire in modo nativo svariati aspetti del ciclo vitale di un applicativo. Per la gestione delle librerie e delle pipeline di build del WAR è invece stato utilizzato Maven. Nello specifico per la build del progetto maven si occupa di far partire sia la build del pacchetto BE che del pacchetto FE (configurata per convergere poi nel pacchetto BE), e generare un WAR contenete tutto il necessario per renderlo un pacchetto standalone.

Di seguito i dettagli sulle principali funzionalità presenti nel pacchetto:
- per quanto riguarda la comunicazione con il database viene utilizzata la libreria Hibernate, che permette di configurare e gestire facilmente le connessioni con il database. Ogni tabella ha una sua entity ed un suo repository (chiamato dao), entrambe che estendono delle interfacce e delle classi astratte che tramite l'utilizzo di generics permettono di avere già a disposizione una gestione minimale del crud di ogni entity senza doverla definire da zero.
- per quanto riguarda la sicurezza ho implementato una versione modificata di una facility che avevo creato per un progetto personale, riadattandola al sistema di security delle nuove cersioni di spring. Si basa su OAuth2.0 e utilizza un token JWT per verificare e filtrare ogni chiamata che arriva sotto root "api/\*", in modo da proteggere ogni chiamata sensibile lasciando aperte la login e le pagine del FE. In più nel token vengono salvate le informazioni dell'utente, in modo da evitare di passarlo in chiaro durante le chiamate e risolvere potenziali problemi di sicurezza in cui un utente prova ad impersonarne un altro.
- per la login e la gestione della password ho riadattato una versione di una facility che avevo creato per un progetto personale, che si occupa di criptare la password e confrontarla con quella a DB, su cui viene salvata già criptata. Per criptarla viene usato un meccanismo che prende in inputi sia il nome utente che la stessa password decriptata, in modo che anche per chi ha l'accesso al DB ed al sistema di crypting sia impossibile risalire alla password originale.
- per quanto rigaurda le API sono configurate tramite le funzionalità base di Spring, e sono inserite all'interno di apposite classi controller. Quest'ultime si occupano solo di ricevere la chiamata ed eventualmente validarne il contenuto, la logica vera e propria è gestita da delle classi service che recuperano i dati in input e li elabarano, utilizzando i dao per la comunicazione con il DB.
- i test sono eseguiti tramite JUnit simulando direttamente delle chiamate ai controller, sicurezza completa, e sono presente sotto il package `src/main/java`.
- per la gestione delle traduzioni è presente un'apposita tabella a DB per i prodotti, mentre per i messaggi di errore è presente una classe custom `CustomLangException`, con un apposito costruttore che si basa su un Enum, che viene intercettata a livello di BaseController e trasformata in una response con stato 422. Questo stato verrà poi letto da un interceptor a FE per mostrare dei messaggi tradotti nella lingua selezionata. 

**N.B.**: il progetto fa utilizzo di lombok per la generazione automatica di getter e setter. Potrebbero quindi essere presenti degli errori se l'editor che si utilizza per visualizzzare il codice non ha il relativo plugin installato.

## FE

Per quanto riguarda il FE è stato utilizzato Angular, un framework basato su TypeScript, e l'applicazione in se fa utilizzo del linguaggio stesso oltre che di JavScript, HTML e CSS (nello specifico SCSS). Le librerie vengono invece gestite tramite NPM.

Per quanto riguarda lo sviluppo mi sono basato sui pattern standard di sviluppo Angular. Per quanto riguarda le varie funzionalità:
- la struttura del progetto è composta da un modulo principale che gestisce la parti core dell'applicativo, una sezione feature contenente vari sottomoduli per ogni funzionalità, un modulo shared contenente le librerie, i servizi ed i componenti base da importare negli altri moduli ed una cartella di assets in cui sono presenti fogli di stile, immagini, ecc.. . A sua volta ogni sottomodulo contiene le proprie views (per rappresentare le vere e proprie pagine), i propri servizi ed i propri componenti.
- per il routing ho utilizzato un modulo principale che carica tramite lazy loading dei sottomoduli solo quando sono effettivamente necessari. Ogni sottomodulo ha poi il proprio routing ed i propri componenti. Questi sottomoduli quando richiamati vengono caricati all'interno di un apposito componente router presente nel layout principale.
- per la gestione della sicurezza ho inserito delle guardie che fungono da interceptor sotto ogni rotta (al netto della login), che bloccano l'accesso e reindirizzano sulla login in caso di mancanza di token, token errato o token scaduto. Questo controllo consiste nell'effettuare una chiamata a BE con solo il token nell'header, che viene poi decodificato e controllato. La stessa chiamata restituisce anche l'utente (contenuto in modo criptato nelle claims del token) che viene riassegnato alla rappresentazione FE dello user ogni volta che si entra in pagina. Questo permette di essere sicuri che l'utente con cui si sta navigando sia sempre associato al token che si passa durante le chiamate a BE.
- per le chiamate API a BE è presente una facility che avevo creato per un progetto personale, che consiste in un apposito apiSservice che permette di chiamare in modo semplice una baseUrl precedentemente definita in una configurazione. E' presente anche un interceptor che si occupa di far apparire e scomparire l'overlay durante il caricamento dei dati ed un altro interceptor che si occupa di gestire eventuali messaggi di errore o codice di errore particolari (come un 401 o un 422, utilizzato dal BE per codificare i messaggi da tradurre in lingua).
- sono presenti dei service intermedi che vengono utilizzati per la comunicazione fra i componenti ed il più generico apiService. In questi service vengono definiti gli elementi per effettuare la chiamata a BE, e questi metodi resituiscono un Observable sul quale il componente potrà sottoscriversi per rimanere in ascolto, in quanto la chiamata in se è asincrona.
- per la gestione delle traduzione viene utilizzata la libreria `ngx-translate`, che permette di gestire facilmente la cosa utilizzando dei file di properties (presenti sotto gli assets) e di tradurre gli elementi tramite delle pipe o dei metodi. E' anche presente una facility che avevo creato per un progetto personale, che consiste in un apposito service che gestisce la lingua corrente ed il cambio lingua, oltre che le traduzioni spot. E' presente anche un ToastService custom che permette di mostrare messaggi tradotti.
- il sistema di animazioni di angular è stato sfruttato per animare gli spostamenti fra una rotta e l'altra, l'apparizione dell'overlay di caricamento e la modifica dei dati in pagina.

Per quanto riguarda la UX ho ipotizzato oltre alla classica pagina di login un'esperienza single page, in cui l'utente può ricercare e selezionare e vederne i dettagli tramite un apposita sidebar con overlay sulla lista stessa. Per quanto riguarda i colori e la favicon ho preso spunto dal vostro sito, mentre per il logo ho ovviamente modificato in maniere abbastanza brutale il logo di RegestaPeople utilizzando Photoshop. Aggiungo alcune specifiche per quanto riguarda il layout:
- per i componenti standard quali input, dropdown, calendar, checkbox, popup ed alcuni dei pulsanti è stata importata una librerie chiamata PrimeNG, di cui ho anche importato il relativo tema material a cui ho aggiunto alcune personalizzazioni. Il resto dei componenti grafici come pannel, visualizzazione prodotti, liste responsive, pulsanti animati, ecc.. sono stati ottenuti tramite l'utilizzo di CSS standard.
- tutto il layout è stato pensato per essere responsive su desktop e tablet. Non è garantita una corretta visualizzazione su mobile.
- per quanto riguarda la schermata di login è un semplice div contente il logo e la form di autenticazione con uno sfondo ripetuto e sfocato.
- per quanto riguarda la pagina di lista prodotti è una pagina con una dimensione massima prefissata contenente sul lato sinistro dei filtri, e sul più ampio lato destro la lista dei prodotti. <br>I filtri sono una form piuttosto semplice con position fixed e sono sempre visibili. Sempre nella sezione fixed è presente un pulsante per lo scrollToTop, la cui apparizione è legata all'ascolto di un evento sulla posizione della scrollbar. <br>La lista prodotti in se invece contiene la lista di prodotti recuperati dalla ricerca filtrata, divisi in card contenenti un'immagine animata, il nome del prodotto, il codice e un pulsante animato. Cliccando sul pulsante o sull'immagine verrà aperto il dettaglio. I prodotti non vengono caricati tutti insieme, ma vengono caricati 12 alla volta tramite l'utilizzo di un infinte scroll (offerto dalla libreria `ngx-infinite-scroll`) che viene triggerato quando ci si avvicina alla fine della pagina.
- per quanto riguarda il dettaglio si tratta di una sidebar che viene aperta dal lato sinistro dello schermo che viene visualizzata come overlay della lista prodotti. Questa sidebar contiene un componente a se stante a cui viene passato il prodotto selezionato, e che si occupa di recuperare sconti e prezzi tramite due chiamate in parallelo. Rappresenta poi i risultati in una lista contente il prezzo, l'eventuale prezzo scontato, la quantità, il nome del fornitore ed i giorni necessari alla spedizione. Sono presenti anche dei filtri per cambiare la quantita di prodotti e la data dell'ordine. I risultati sono ordinati per prezzo, ed il prezzo i migliore ed la data di spedizione migliore vengono evidenziati sia tramite i bordi che le icone.<br>Tutti i calcoli su prezzi e scontistiche vengono effettuati in questo componente, tenendo conto del prezzo totale in base alla quantita di pezzi, del range di date dello sconto e del range di prezzi su cui è attivo lo sconto, per poi scegliere il migliore fra i disponibili. <br>Gli sconti disponibili sono 11:
  1. Il fornitore Tech Store BS offre uno sconto del 5% dal 01/01/2023 al 31/12/2099 per gli acquisti maggiori di 799.99 €.
  2. Il fornitore Tech Store BS offre uno sconto del 10% dal 01/01/2023 al 31/12/2099 per gli acquisti maggiori di 799.99 € e con una quantità di almeno 30 pezzi.
  3. Il fornitore Electronic House offre uno sconto del 5% dal 01/01/2023 al 31/12/2099 per gli acquisti maggiori o uguali a 799.99 €.
  4. Il fornitore Electronic House offre uno sconto del 12.5% dal 01/01/2023 al 31/12/2099 per gli acquisti maggiori o uguali a 1000 €.
  5. Il fornitore Tech Store MI offre uno sconto del 7.5% dal 01/12/2023 al 31/12/2023 per gli acquistii con una quantità di almeno 20 pezzi.
  6. Il fornitore Tech Store MI offre uno sconto del 3% dal 01/01/2023 al 31/12/2099 per gli acquisti con una quantità di almeno 20 pezzi.
  7. Il fornitore Mercatone Dell'Informatica offre uno sconto del 5% dal 01/01/2023 al 31/12/2099 per gli acquisti fra i 500 € ed i 1000€.
  8. Il fornitore Mercatone Dell'Informatica offre uno sconto del 10% dal 01/01/2023 al 31/12/2099 per gli acquisti fra i 1000 € ed i 1500€.
  9. Il fornitore Mercatone Dell'Informatica offre uno sconto del 20% dal 01/01/2023 al 31/12/2099 per gli acquisti maggiori od uguali a 1500€.
  10. Il fornitore Black Wave Attrezzature offre sempre uno sconto del 2.5% dal 01/01/2023 al 31/12/2099.
  11. Il fornitore Black Wave Attrezzature offre uno sconto del 50% solo il giorno 24/12/2023.

