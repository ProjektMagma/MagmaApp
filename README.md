# Magma App

### Projekt systemu notatek, stworzony na konkurs "Złota apka".

_Poniżej znajduję się opis projektu, który został wykorzystany w formularzu zgłoszeniowym,
dopakowany markdownem :)_

## Na jakie potrzeby/problem odpowiada Wasze rozwiązanie?

Nasz projekt odpowiada na istotny problem związany z organizacją i przechowywaniem notatek. Wiele
osób, szczególnie uczniów, po roku nauki zmienia zeszyt i zapomina, gdzie odłożyło stary, lub go po
prostu wyrzuca. To prowadzi do utraty cennych informacji i trudności w odnalezieniu potrzebnych
notatek w przyszłości. <br>
Chcieliśmy stworzyć rozwiązanie, które umożliwi długoterminowe przechowywanie treści w formie
cyfrowej, zarówno na urządzeniach mobilnych, jak i stacjonarnych. Nasz system notatek pozwala na
łatwe zarządzanie, organizowanie i dostęp do notatek w dowolnym momencie. Dzięki temu użytkownicy
mogą mieć pewność, że ich notatki są bezpieczne i zawsze dostępne, niezależnie od tego, ile czasu
minęło od ich stworzenia.

## W jakich językach programowania, jakich technologiach powstała aplikacja?

* **Język programowania:** Kotlin
* **Framework:**
    * Jetpack Compose
    * KMP (w planach)
* **Baza danych:**
    * Firebase (chmura)
    * SQLite (lokalna kopia)
* **Biblioteki:**
    * Material3
    * Koin
    * JUnit
    * ComposeRichEditor
* **Inne narzędzia:**
    * Gradle + AGP
    * Android Studio
    * DataGrip
    * Firebase Console
    * Git
    * MS Visio
    * Figma
    * GitHub Projects

## W jaki sposób działa Wasza aplikacja? Co może dzięki niej osiągnąć użytkownik? Jaką ma funkcjonalność?

Nasza aplikacja umożliwia zapisywanie notatek w formie poręcznych zeszytów, które można łatwo
organizować i przeglądać. Użytkownicy mogą udostępniać zeszyty innym osobom do wglądu lub edycji, co
ułatwia współpracę i dzielenie się informacjami. Dodatkowo, aplikacja pozwala na ustawianie
przypomnień do notatek, np. do listy zakupów czy zadania domowego, co pomaga w zarządzaniu czasem i
obowiązkami. <br>
Aplikacja oferuje również funkcje takie jak kategoryzowanie notatek, wyszukiwanie treści,
dodawanie załączników (np. zdjęć czy dokumentów) oraz synchronizację z różnymi urządzeniami, co zapewnia
dostęp do notatek w dowolnym miejscu i czasie. Dzięki temu użytkownicy mogą mieć pewność, że ich
notatki są bezpieczne i zawsze dostępne.

## Jak widzicie dalszy rozwój Waszego rozwiązania?

Dalszy rozwój naszej aplikacji widzimy w kilku kluczowych obszarach. Jednym z pomysłów jest
integracja z systemami dziennika elektronicznego, takimi jak Vulcan czy Librus, co umożliwiłoby
użytkownikom łatwiejsze zarządzanie notatkami związanymi z edukacją i szkolnymi obowiązkami.
Innym kierunkiem rozwoju jest przekształcenie aplikacji w system zwinnego zarządzania projektami (
agile), podobny do GitHub Projects, Jira czy Trello. Chcemy jednak, aby nasza aplikacja była
bardziej przystępna dla zwykłego użytkownika, który nie jest zaznajomiony z technologią. Dzięki temu
moglibyśmy zaoferować narzędzie do zarządzania projektami, które jest intuicyjne i łatwe w użyciu, a
jednocześnie skuteczne w organizacji pracy i współpracy zespołowej.

## W jaki sposób Wasz projekt mógłby zostać wdrożony lub rozwijany? Jacy partnerzy mogliby się zaangażować w jego rozwój?

Jak już wspomnieliśmy wcześniej, naszą aplikację można zintegrować z istniejącymi rozwiązaniami,
które są już od lat przyjęte na rynku. Przykładowo, integracja z systemami dziennika
elektronicznego, takimi jak Vulcan czy Librus, mogłaby znacząco ułatwić zarządzanie notatkami w
kontekście edukacyjnym. <br>
Instytucje, które mogłyby skorzystać z naszego rozwiązania, to między innymi szkoły, uczelnie,
firmy potrzebujące rzetelnego przepływu informacji oraz prywatni odbiorcy. Nasza aplikacja mogłaby
wspierać organizację pracy, współpracę zespołową oraz zarządzanie projektami w sposób bardziej
przystępny dla użytkowników, którzy nie są zaznajomieni z technologią. <br>
Chcielibyśmy, aby nasz projekt pozostał w modelu non-profit i open-source. Dzięki temu, rozwój
aplikacji mógłby być rzetelnie kontynuowany zarówno przez nas, jak i inne podmioty, co zapewniłoby
transparentność i ciągłe doskonalenie naszego rozwiązania.

## Jakie widzicie zagrożenia/ryzyka dla Waszego rozwiązania?

Jeżeli chodzi o ryzyka związane z naszym rozwiązaniem, to głównie dotyczą one kilku kluczowych
kwestii. Istnieje ryzyko, że nasza aplikacja nie zdobędzie wystarczającego rozgłosu, co wpłynie na
jej popularność i przyjęcie na rynku. Aplikacja może stać się zbyt zależna od innych rozwiązań,
takich jak systemy dziennika elektronicznego, a zmiany w tych systemach mogą negatywnie wpłynąć na
jej funkcjonalność.  <br>
Ponadto, istnieje ryzyko, że aplikacja będzie użyteczna w zbyt wąskim zakresie, co może ograniczyć
jej atrakcyjność i zastosowanie w różnych kontekstach. Aby zminimalizować te ryzyka, musimy skupić
się na skutecznej promocji, rozwijaniu niezależnych funkcji aplikacji oraz dostosowywaniu jej do
zmieniających się potrzeb użytkowników.

## Opisz zdiagnozowane zagrożenia jak np. problemy technologiczne czy konieczność zaangażowania innych podmiotów np. urząd miasta

Jeżeli nasza aplikacja miałaby być zintegrowana z dziennikiem elektronicznym, potrzebowałaby
dostępu
do danych osobowych użytkownika, co wiązałoby się z koniecznością przestrzegania przepisów RODO. To
może powodować komplikacje związane z ochroną danych osobowych i wymagać dodatkowych zabezpieczeń
oraz procedur zgodności. Alternatywnie, konto na aplikacji mogłoby być zakładane z domeny szkoły, co
ułatwiłoby zarządzanie danymi i ich ochronę. <br>
Dodatkowo, problemy technologiczne mogą wynikać z korzystania z eksperymentalnych bibliotek,
takich jak ComposeRichEditor, która nie posiada jeszcze stabilnej wersji. Może to prowadzić do problemów z
niezawodnością i kompatybilnością aplikacji, co wymagałoby ciągłego monitorowania i aktualizacji.

## Dlaczego akurat Wy powinniście wygrać?

Uważamy, że jesteśmy dobrymi, pomysłowymi i wartościowymi osobami w dziedzinie IT, do której na
pewno wejdziemy i będziemy wartościowymi pracownikami na rynku pracy. Nawet jeśli nie uda nam się
zająć wysokiego miejsca w tym konkursie, traktujemy to jako proces nauki, który przyczynia się do
naszego rozwoju. W końcu chodzi nie tylko o osiągnięcie celu, ale o drogę, którą do niego
przebywamy.
<br>
_Cytując materiał promocyjny_Reszta wyjdzie w trakcie._

## Napisz, co wyróżnia Wasz pomysł lub jego realizacja np. wybór innowacyjnej technologii.

Nasz projekt wyróżnia się zastosowaniem nowoczesnych technologii, takich jak framework Jetpack
Compose. Jest to dynamicznie rozwijający się zestaw narzędzi do tworzenia aplikacji na system
Android i nie tylko, który od czterech lat zdobywa coraz większą popularność.
Głównym językiem używanym w Jetpack Compose jest Kotlin, a jego twórcy pracują nad Kotlin
MultiPlatform (KMP), co umożliwi portowanie aplikacji na inne systemy operacyjne, takie jak Windows,
Linux, iOS, macOS oraz Web. Dzięki temu nasza aplikacja będzie mogła działać na różnych platformach,
co zwiększy jej uniwersalność i dostępność. <br>
Dodatkowo, Jetpack Compose jest technologią często używaną na rynku, a samo Google zaleca
programowanie aplikacji Androidowych właśnie w tym frameworku. To pokazuje, że nasz projekt jest
zgodny z najnowszymi trendami i standardami w branży IT.


###### Copyright (c) 2025 Projekt Magma