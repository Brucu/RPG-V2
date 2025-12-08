# ⚔️ Java Console RPG

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Status](https://img.shields.io/badge/Status-Playable-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)

Klasyczna gra RPG typu "Dungeon Crawler" działająca w wierszu poleceń (CMD). Projekt stworzony w celu nauki **Programowania Obiektowego (OOP)**, struktur danych i architektury gier w czystej Javie.

Gra wykorzystuje kody ANSI do kolorowania mapy, posiada system walki turowej, mechanikę zdobywania poziomów oraz losowe generowanie wrogów.

## 📸 Podgląd (ASCII Art)

```text
=== RPG V2: POZIOM 1 ===

# # # # # # # # # # # #
# . . . . . . . . . . #
# . @ . . . . . . . . #   <-- @ To Ty (Gracz)
# . . . . . E . . . . #   <-- E To Wróg (Goblin)
# . . . . . . . . . . #
# # # # # # # # # # # #

Lvl: 1 | XP: 0/100 | HP: 100/100
Ruch (WASD) lub Q(wyjście):

```
🚀 Funkcjonalności

•System Poruszania: Kolizje ze ścianami i interakcje z obiektami.

•System Walki: Turowe starcia z przeciwnikami (Atak / Ucieczka).

•Rozwój Postaci (RPG): Zdobywanie XP, awansowanie na poziomy (Level Up), wzrost statystyk (HP).

•Hordy Wrogów: Losowe pojawianie się przeciwników na mapie przy każdym uruchomieniu.

•Oprawa Graficzna: Wykorzystanie kolorów ANSI (zielony gracz, czerwoni wrogowie) oraz czyszczenie ekranu dla płynności rozgrywki.

•Czysta Architektura: Podział na logikę gry (Game), świat (World) i byty (Entity).

🛠️ Technologie i Architektura

Projekt został napisany z naciskiem na dobre praktyki programowania (Clean Code).

Język: Java (JDK 17+)

OOP (Obiektowość):

•Dziedziczenie: Klasa Entity jako baza dla Player i Enemy.

•Polimorfizm: Różne zachowania onDeath() dla gracza i wroga.

•Enkapsulacja: Użycie private/protected oraz getterów/setterów do ochrony danych (np. HP).

•Struktury Danych: Użycie ArrayList do zarządzania dynamiczną liczbą przeciwników.

•Game Loop: Własna implementacja pętli gry w klasie Game.java.

# 🔮 Plany na przyszłość (Roadmap)
[ ] Dodanie ekwipunku i przedmiotów (Mikstury, Miecze).

[ ] Wczytywanie map z plików tekstowych.

[ ] System zapisu i odczytu gry (Save/Load).

[ ] Bossowie z unikalnymi mechanikami.
