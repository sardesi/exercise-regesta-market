# Regesta Market

Regesta market è un progetto creato come risoluzione al test d'ingresso presentato in [questo documento](https://github.com/sardesi/exercise-regesta-market/blob/main/Documenti%26Utility/Test%20d'ingresso%2001%20-%20English%2C%20problem%20solving%2C%20coding%20quality%2C%20TDD.pdf). In questo readme è presente la documentazione tecnica e qualche considerazione sul progetto sviluppato. Per il manuale utente l'analisi delle funzoinalità (in formato TDD/BDD) fare riferimento alla cartella "Documenti&Utility" del progetto.

# Introduzione



# Premesse

Trattandosi solamente di un progetto d'esempio, e per garantire una consegna rispetto alle tempistiche indicate, ho effettuato alcune assunzioni che non avrei fatto in un progetto reale, di seguito le più impattanti:
- Ogni supplier avrà un solo magazzino, e quindi un singolo valore per i giorni di consegna, salvato su DB direttamente sul supplier. In una casitica reale sarebbe corretto valutare il fatto che un supplier possa avere più magazzini, con tempi di consegna diversi impattati anche dall'articolo stesso.
- La tabella degli sconti è stata modellata in base alle singole informazioni presenti nel testo della richiesta. In un ambiente di produzione, al netto di parlarne con il cliente ed avere quindi il tempo e le risorse per farlo, avrei preferito implementare un valutatore di espressioni in modo da avere liberta assoluta sulla definizione dei parametri su cui si basa lo sconto.
- Può essere attivo solo uno sconto alla volta. Nello specifico viene recuperato il più altro.
- I prodotti, i loro prezzi e le immagini sono state generate con uno certo criterio, ma comunque tramite automatismi: potrebbero quindi non essere particolarmente coerenti fra descrizione, immagine e prezzo. Oltretutto gli url delle immagini fanno riferimento a prodotti generici presenti su amazon, quindi potrebbe capitare che non siano più disponibli in base a quando verrà visionato questo progetto.
- Mi sono fermato a quanto descritto nella richiesta, confrontando un singolo prodotto per volta, senza carrelli o confronti multipli.

# Come avviare il progetto

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
