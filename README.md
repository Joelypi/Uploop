# Uploop

Applicazione desktop Java (Swing) per la gestione online degli ordini di stampa
di un'agenzia creativa: locandine e materiale grafico personalizzato.

Progetto sviluppato per il corso di Ingegneria del Software — Università di Pavia
(package `it.unipv.ingsfw.Uploop`).

## Funzionalità

- Registrazione e login utente
- Configurazione guidata di un ordine di stampa (formato, quantità, tipo di carta, grafica su misura)
- Pagamento simulato con scelta tra carta di credito e PayPal
- Storico ordini personali
- Portfolio dei lavori realizzati

## Architettura

Il progetto segue il pattern **MVC**, con accesso ai dati tramite pattern **DAO**,
creazione degli oggetti `Locandina` tramite **Factory Method**, scelta del metodo
di pagamento tramite **Strategy**, e gestione della sessione utente tramite **Singleton**
(`SessionManager`).

```
src/it/unipv/ingsfw/Uploop/
├── controller/   Controller MVC (Login, Registration, Order)
├── dao/          Accesso dati (interfacce UserDAO/OrderDAO + implementazioni MySQL)
├── exception/    Eccezioni applicative (DatabaseException, InvalidInputException)
├── factory/      Factory Method per la creazione delle Locandine
├── frame/        View Swing (Login, Registration, Dashboard, Order, OrderStatus)
├── model/        Entità di dominio (User, Order, Locandina) e SessionManager
├── payment/      Strategy di pagamento (PaymentStrategy, CreditCard, PayPal)
├── test/         Classe Main di avvio
└── view/         Risorse grafiche (loghi, immagini portfolio)
```

Diagrammi UML (classi MVC, classi DAO, casi d'uso, sequenza, stati) e documentazione
di progetto (Documento di Visione, Casi d'Uso, Requisiti Funzionali e Non Funzionali,
Glossario) disponibili nella cartella [`docs/`](docs/).

## Requisiti

- JDK 17 o superiore
- MySQL (locale o remoto) con un database creato per il progetto

## Configurazione

1. Copia `src/db.properties.example` in `src/db.properties`.
2. Compila `db.properties` con i tuoi dati di connessione MySQL:
   ```properties
   db.url=jdbc:mysql://localhost:3306/uploop
   db.user=root
   db.password=la-tua-password
   ```
3. `db.properties` è escluso dal versionamento (vedi `.gitignore`): non verrà mai
   pubblicato su GitHub.

## Avvio

Importa il progetto in Eclipse (o IDE equivalente) come progetto Java esistente,
aggiungi il driver JDBC di MySQL (`mysql-connector-j`) al classpath, ed esegui
`it.unipv.ingsfw.Uploop.test.Main`.

## Pattern di progettazione utilizzati

| Pattern | Dove |
|---|---|
| MVC | Intera architettura (`frame` / `controller` / `model`+`dao`) |
| DAO | `UserDAO`, `OrderDAO` e relative implementazioni MySQL |
| Factory Method | `LocandinaFactory` |
| Strategy | `PaymentStrategy` (`CreditCardPayment`, `PayPalPayment`) |
| Singleton | `SessionManager` |
