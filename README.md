# Programowanie reaktywne - Przykład z seminarium zaawansowanych technik internetowych

## Zawartość

Katalog src zawiera kod źródłowy aplikacji. Wykorzystanie programowania reaktywnego przedstawiono w dwóch formach:
- na przykładzie tworzenia tradycyjnych endpoint'ów REST'owych (package controller)
- wykorzystując koncepcje Functional Web Framework (package router) 

Dla koncepcji Functional Web Framework przedstawiono wykorzystanie funkcyjnego paradygmatu programowania *(endpoint GET /router/menu)* oraz wykorzystanie
klasy handler'a *(package handlers, klasa ApiHandler)*. Handler odpowiada swoją funkcjonalnością klasą serwisu w tradycyjnych endpoint'ach REST *(package service, klasa DishService)*.
Aplikacja została podpięta do reaktywnej bazy MongoDB. 

## Temat

Aplikacja umożliwia użytkownikowi utworzenie menu poprzez dodawanie dań, usuwanie dań, sprawdzanie zawartości całego menu oraz sprawdzanie poszczególnych dań.

## Instrukcja uruchomienia aplikacji

1. Zaciągnąć projekt z repozytorium. (Code -> Git clone lub Download as zip)
2. Dostosować namiary na bazę danych w pliku *application.properties*.
3. (Krok opcjonalny) Odświeżyć dependencje Maven'a (dla IntelliJ w menu Maven nacisnąć przycisk *Reload*).
4. Uruchomienie z poziomu IntelliJ Idea - na górnym pasku w polu *Select Run/Debug Configuration* należy wybrać opcję ZtiApplication i nacisnąć zieloną strzałkę na prawo od tego pola.
5. Uruchomienie z poziomu linii komend (wymaga zainstalowania Maven oraz ustawienia Maven w ścieżkach w zmiennych środowiskowych systemu). Należy przejść do katalogu, w którym znajduje się kod źródłowy. Następnie wykonać komendę *mvn clean install*. Przejść do katalogu target. Uruchomić aplikację za pomocą komendy *java -jar zti-1.0.jar*.

## Wymagania
Ze względu na wykorzystanie MongoDB wymagane jest instalacja bazy MongoDB (np. MongoDBCompass). 

## Sposób wykorzystania

Aplikacja była testowana za pomocą aplikacji Postman.

Endpointy udostępniane w ramach Functional Web Framework:
```
GET /router/menu - pobranie wszystkich dań (całego menu)
GET /router/menu/{id} - pobranie dania określonego za pomocą {id}
POST /roter/menu - utworzenie nowego dania
DELETE /router/menu/{id} - usunięcie dania z bazy danych przy wykorzystaniu parametru {id}
```

Endpointy udostępniane w ramach serwisu REST:
```
GET /controller/menu - pobranie wszystkich dań (całego menu)
GET /controller/menu/{id} - pobranie dania określonego za pomocą {id}
POST /controller/menu - utworzenie nowego dania
DELETE /controller/menu/{id} - usunięcie dania z bazy danych przy wykorzystaniu parametru {id}
```

## Materiały wykorzystane do utworzenia aplikacji
Functional Web Framework - https://www.baeldung.com/spring-5-functional-web

ErrorHandling - https://github.com/LearningByExample/reactive-ms-example
