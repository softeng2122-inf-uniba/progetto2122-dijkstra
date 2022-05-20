# Report

## Indice
- [1. Introduzione](#introduzione)
- [2. Modello di dominio](#il-modello-di-dominio)
- [3. Requisiti specifici](#requisiti-specifici)
    - [3.1 Requisiti funzionali](#requisiti-funzionali)
    - [3.2 Requisiti non funzionali](#requisiti-non-funzionali)
- [5. OO Design](#oo-design)
    - [5.1 Diagrammi delle classi](#diagrammi-delle-classi)
    - [5.2 Diagrammi di sequenza](#diagrammi-di-sequenza)
    - [5.3 Design pattern](#design-pattern)
    - [5.4 Commenti decisioni prese](#design-pattern)



## **Introduzione**

Wordle è un gioco in cui, il giocatore ha un numero limitato di tentativi - solitamente 6 - per indovinare una parola, con un feedback fornito per ogni tentativo effettuato sotto forma di variazione di colore che indicano quando le lettere corrispondono o occupano la posizione corretta rispetto alla parola da indovinare.

L'applicativo è una versione semplificata che rispetta specifici requisiti funzionali.

## **Il modello di dominio**

  ![Modello di dominio](./img/Diagramma_di_dominio.png)


## **Requisiti specifici**

### **Requisiti funzionali**

- Come paroliere voglio impostare una parola segreta manualment.

        Al comando /nuova <parola>
        l’applicazione risponde:

        · Parola segreta troppo corta se i caratteri sono inferiori a quelli del gioco

        · Parola segreta troppo lunga se i caratteri sono superiori a quelli del gioco

        · Parola segreta non valida se ci sono caratteri che non corrispondono a lettere dell’alfabeto 
        
        altrimenti l’applicazione risponde con OK e memorizza la parola fino a chiusura applicazione.
        È possibile cambiare la parola durante una sessione di gioco anche senza uscire dall’applicazione.

- Come paroliere voglio mostrare la parola segreta.

        Al comando /mostra
        l’applicazione risponde visualizzando la parola segreta.

- Come giocatore voglio mostrare l'help con elenco comandi.

        Al comando /help o invocando l'app con flag --help o -h
        il risultato è una descrizione concisa, che normalmente appare all'avvio del programma, seguita dalla lista di comandi disponibili, uno per riga, come da esempio successivo:

        · gioca
        · esci
        · ...

- Come giocatore voglio iniziare una nuova partita.

        Al comando /gioca
        se nessuna partita è in corso l'app mostra la matrice dei tentativi vuota, ma senza mostrare la tastiera, e si predispone a ricevere il primo tentativo o altri comandi

- Come giocatore voglio abbandonare la partita.

        Al comando /abbandona
        l'app chiede conferma

        · se la conferma è positiva, l'app comunica l’abbandono
        · se la conferma è negativa, l'app si predispone a ricevere un altro tentativo o altri comandi

- Come giocatore voglio chiudere il gioco.

        Al comando /esci
        l'applicazione chiede conferma

        · se la conferma è positiva, l'app si chiude restituendo un zero exit code
        · se la conferma è negativa, l'app si predispone a ricevere nuovi tentativi o comandi

- Come giocatore voglio effettuare un tentativo per indovinare la parola segreta.

        Digitando caratteri sulla tastiera e invio

        l’applicazione risponde:

        · Tentativo incompleto se i caratteri sono inferiori a quelli della parola segreta
        · Tentativo eccessivo se i caratteri sono superiori a quelli della parola segreta
        · Tentativo non valido se ci sono caratteri che non corrispondono a lettere dell’alfabeto

        altrimenti riempiendo la prima riga libera della matrice dei tentativi con i caratteri inseriti e colorando lo sfondo di verde se la lettera è nella parola segreta
        e nel posto giusto, di giallo se la lettera è nella parola segreta ma nel posto sbagliato e di grigio se la lettera non è nella parola segreta.

        Se le lettere sono tutte verdi l’applicazione risponde

        · Parola segreta indovinata Numero tentativi: <…> e si predispone a nuovi comandi

        Se il tentativo fallito è l’ultimo possibile , l’applicazione risponde

        · Hai raggiunto il numero massimo di tentativi. La parola segreta è <…> e si predispone a nuovi comandi

        Se la parola segreta non è stata impostata l’applicazione risponde
        Parola segreta mancante


### Requisiti non funzionali
- RNF1: il container docker dell’app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16.



## **OO Design**

___
### **Diagrammi delle classi**

![Modello delle classi](./img/diagramma_prospettiva_software.png)

### **La classe APP**

![Classe App](./img/app.png)

La classe **App** implementa le seguenti user story:
- Come paroliere voglio impostare una parola segreta manualment.
- Come paroliere voglio mostrare la parola segreta.
- Come giocatore voglio mostrare l'help con elenco comandi.
- Come giocatore voglio iniziare una nuova partita.
- Come giocatore voglio chiudere il gioco.

    
### **La classe Partita**

![Classe App](./img/partita.png)

    
La classe **Partita** implementa le seguenti user story:
- Come giocatore voglio abbandonare la partita.
- Come giocatore voglio effettuare un tentativo per indovinare la parola segreta

La classe **Analizzatore** implementa i controlli necessari sull'input inserito dall'utente.

![Classe App](./img/annalizzatore.png)

La classe **Giocatore** identifica i permessi del giocatore.

![Classe App](./img/annalizzatore.png)


___
### **Diagrammi di sequenza**

- **Diagramma di sequenza di 'Come paroliere voglio impostare una parola segreta manualment'**

    ![Diagramma nuova parola](./img/diagramma_di_sequenza_nuova.png)

- **Diagramma di sequenza di 'Come paroliere voglio mostrare la parola segreta'**

    ![Diagramma nuova parola](./img/diagramma_di_sequenza_mostra_parola.png)

- **Diagramma di sequenza di 'Come giocatore voglio mostrare l'help con elenco comandi'**

    ![Diagramma nuova parola](./img/diagramma_di_sequenza_mostra_parola.png)

- **Diagramma di sequenza di 'Come giocatore voglio iniziare una nuova partita'**

    ![Diagramma nuova parola](./img/diagramma_di_sequenza_gioca.png)

- **Diagramma di sequenza di 'Come giocatore voglio abbandonare la partita'**

    ![Diagramma nuova parola](./img/diagramma_di_sequenza_abbandona.png)


- **Diagramma di sequenza di 'Come giocatore voglio chiudere il gioco'**

    ![Diagramma nuova parola](./img/diagramma_di_sequenza_esci.png)


- **Diagramma di sequenza di 'Come giocatore voglio effettuare un tentativo per indovinare la parola segreta'**

    ![Diagramma nuova parola](./img/diagramma_di_sequenza_tentativo.png)



___
### **Design pattern**

___
### **Commenti decisioni prese**





