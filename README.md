# Regesta Market

Regesta market è un progetto creato come risoluzione al test d'ingresso presentato in [questo documento](https://github.com/sardesi/exercise-regesta-market/blob/main/Documenti%26Utility/Test%20d'ingresso%2001%20-%20English%2C%20problem%20solving%2C%20coding%20quality%2C%20TDD.pdf). In questo readme è presente la documentazione tecnica e qualche considerazione sul progetto sviluppato. Per il manuale utente l'analisi delle funzoinalità (in formato TDD/BDD) fare riferimento alla cartella "Documenti&Utility" del progetto.

In caso di quasliasi problema sono a disposizione al seguente indirizzo: simoneardesi@outlook.it

# Introduzione e stack tecnologico

Per realizzare il progetto ho deciso, essendo un programmatore full stack ed essendo questo un test per valutare le competenze, di realizzare una webapp utilizzando  uno stack tecnologico che mi permettesse di lavorare sia su BE, sia su FE che anche su DB. Ho quindi scelto di creare un progetto con backend basato su framework SpringBoot (java) con frontend Angular (typescript/javascript, html, css) ed un DB in memory basato su H2; nel pacchetto è presente anche un server Apache Tomcat embedded, in modo che sia utilizzabile as is. Per quanto riguarda il DB H2 ed il server Tomcat embedded non sono ovviamente scelte che farei in un ambiente di produzione, ma mi sembravano adeguate per lo scope di questo esercizio, in quanto mi permettono di rilasciare un WAR eseguibile tramite linea di comando, senza dover far installare nessun tool esterno (al netto di di una JDK java) a chi dovrà provare il pacchetto.

Aggiungo anche che rispetto al testo inziale del problema ho aggiunto a quanto richiesto tre feature che vengono ormai ritenute fondamentali per una webapp: gestione dell'autenticazione, multilingua e layout responsive. Ho valutato che aggiungerle non mi sarebbe costato un grande dispendio di tempo, anche perché ho utilizzato delle facility che ho creato in passato per alcuni progetti personali e che ho dovuto solo riadattare a questo progetto ed alle nuove versioni delle tecnologie utilizzate.

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

Se tutto è andato a buon fine aprendo la console (andando nel menu start e scrivendo "cmd" e dando poi invio) e scrivendo il comando `java -version` dovrebbe rispondere con la versione 17 di java. In caso risponda con commando non riconsciuto i passaggi di cui sopra potrebbero non essere stati seguiti correttamente.

## Avvio dell'applicativo

# Dettaglio implementazioni

Di seguito il dettaglio dei vari layer che sono stati implementati all'interno del progetto.

## DB

## BE

## FE




# List & Links

- [**testLink**](http://test.com): Test for link [TestLink2](http://test.com) .


`test lib`

```shell
test shell command
```
