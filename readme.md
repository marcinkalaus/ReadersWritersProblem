# Problem pisarzy i czytelników:
> Problem polega kontrolowaniu dostępu do jednego zasobu dla dwóch różnych rodzajów procesów.

# Założenie:
> Do czytelni może wejść jednocześnie 5 czytelników lub 1 pisarz (pisarz zajmuje czytelnię zawsze na wyłączność). <br />
W metodzie main należy stworzyć 3 pisarzy i 10 czytelników.

# Omówienie rozwiązania:
> Rozwiązanie opiera się na wykorzystaniu monitora - struktury danych i zbioru metod służących do zarządzania dostępem. <br />
W niniejszym rozwiązaniu monitorem jest klasa Library. Rozwiązanie monitorowe faworyzuje pisarzy lub czytelników, <br />
dlatego wspomagane jest przez wykorzystanie kolejki. <br />
Wątek po wystartowaniu trafia do kolejki. Po spełnieniu warunków (max. 5 czytelników lub 1 pisarz oraz
pierwsze miejsce na liście oczekujących) otrzymuje dostęp do instancji library klasy Library.

# Komunikacja:
> Każdy obiekt klasy Writer lub Reader otrzymuje przy tworzeniu swoje ID, odpowiednio: 'W'+'lp.' lub 'R'+'lp.'. <br />
Każda zmiana statusu danego obiektu jest logowana na standardowe wyjście. <br />
Przy okazji otrzymania dostępu do zasobu wyświetlany jest aktualny stan pisarzy i czytelników w czytelni. <br />
Obiekt może znaleźć się w 3 stanach: <br />
> - czeka w kolejce - 'is waiting'
> - czyta/pisze - 'starts reading/writing. Number of writers: 0, Number of readers: 5'
> - zakończył czytanie/pisanie - 'stopped reading/writing'

# Przykładowa sekwencja logów:
> 21:51:28 - Thread[Thread-11,5,main] R8 stopped reading. <br />
21:51:28 - Thread[Thread-0,5,main] W0 starts writing. Number of writers: 1, Number of readers: 0 <br />
21:51:28 - Thread[Thread-11,5,main] R8 is waiting. <br />
21:51:31 - Thread[Thread-0,5,main] W0 stopped writing. <br />
21:51:31 - Thread[Thread-2,5,main] W2 starts writing. Number of writers: 1, Number of readers: 0 <br />
21:51:31 - Thread[Thread-0,5,main] W0 is waiting. <br />
21:51:34 - Thread[Thread-2,5,main] W2 stopped writing. <br />


# Uruchomienie programu:
> - Należy zbudować projekt z pomocą komendy 'mvn clean compile:assembly:single'
> - Nastepnie użyć należy komendy 'java -jar .\main\target\main-1.0.jar'
> - Program działa na nieskończonych pętlach, dlatego aby przerwać działanie należy wykonać kombinację ctrl + c