# 🚗 Parking-lot

## 📝 Leírás
Ez a projekt egy egyszerű parkolóház-kezelő rendszer implementációja Java nyelven.  
A rendszer lehetővé teszi autók regisztrálását és deregisztrálását a parkolóban, parkolóhelyek kezelését és nyomon követését.  
A parkoló egy kétdimenziós tömbbel modellezett helyeket tartalmaz, amelyek különböző méretű autók fogadására alkalmasak.

## 🧩 Fő komponensek

### 🚙 Car osztály
- Reprezentál egy autót, amely rendelkezik egyedi rendszámmal és mérettel.
- Méret alapján különböztetjük meg az autókat (pl. kis, közepes, nagy).

### 🅿️ Space osztály
- Egyetlen parkolóhelyet reprezentál.
- Tudja, hogy foglalt-e, és ha igen, melyik autó áll rajta.

### 🏢 ParkingLot osztály
- A parkolóházat modellezi, egy kétdimenziós `Space[][]` tömb segítségével.
- Kezeli a parkolóhelyek elérhetőségét és méreteit.

### 🚪 Gate osztály
- Felelős az autók be- és kijelentkeztetéséért.
- Foglalja vagy felszabadítja a parkolóhelyeket az autók érkezése és távozása során.
- Ellenőrzi, hogy van-e elérhető hely a parkolóban az adott autó méretének megfelelően.

## ⚙️ Funkcionalitás
- **Autó regisztrálása (parkolás):** Egy autó foglalhat egy vagy több szomszédos parkolóhelyet, ha elérhető.
- **Autó deregisztrálása (távozás):** Egy autó által foglalt parkolóhelyek felszabadítása.
- **Parkolóhely keresése:** Megkeresi a szabad helyeket az adott autó méretének megfelelően.
- **Parkolóállapot kezelése:** Nyomon követi, mely parkolóhelyek szabadok, melyek foglaltak.

## 🗂️ Csomagok

- `vehicle` csomag: Autókat reprezentáló osztályok.
- `parking` csomag: Parkolóhelyeket és azok kezelését megvalósító osztályok.

## ▶️ Használat

1. Klónozd a repót:  

   ```bash
   git clone https://github.com/dekanmate/shopping