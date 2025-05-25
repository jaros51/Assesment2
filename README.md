# Knižničný systém (Zadanie 2)

Táto aplikácia predstavuje manažment knižničného systému postavený na Spring Boot, ktorý umožňuje správu kníh a ich výtlačkov.

## Popis projektu

Aplikácia je postavená ako REST API, ktoré umožňuje:
- Správu kníh (pridávanie, aktualizácia, mazanie, získavanie)
- Správu výtlačkov kníh
- Vyhľadávanie podľa rôznych kritérií

## Technológie

Projekt používa nasledujúce technológie:
- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- Spring MVC
- Flyway pre migrácie databázy
- MySQL (produkčná DB) / H2 (vývojová DB)
- Lombok
- MapStruct a ModelMapper pre mapovanie objektov

## Požiadavky pre spustenie

Pre spustenie aplikácie potrebujete:
- JDK 21
- Maven 3.6+ 
- MySQL server (pre produkčné prostredie)

## Inštalácia a spustenie

### Klónovanie repozitára

