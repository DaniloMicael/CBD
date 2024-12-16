// NMEC: 113384

# Lab04 - Ex 4.4

**Note:** All results are saved in the `results` folder, in JSON format.

## 1. Find all listings located in the neighbourhood of Campanhã
```sql
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood {name: 'Campanhã'})
RETURN listing.id AS ListingID, listing.name AS ListingName, listing.price AS Price, neighbourhood.name AS Neighbourhood
```

Result:
```txt
Listings in Campanhã:
Listing ID: 9756653, Name: Maria's Apartment, Price: 56.0, Neighbourhood: Campanhã
Listing ID: 4588483, Name: Wine Apartments lll - Ribeira Studio by LovelyStay, Price: 65.0, Neighbourhood: Campanhã
Listing ID: 9147272, Name: OPO'attics - Loft #1, Price: 90.0, Neighbourhood: Campanhã
Listing ID: 127208, Name: B & B  Room Yoga Garden's Place, Price: 38.0, Neighbourhood: Campanhã
Listing ID: 9149350, Name: OPO'attics - Loft #6, Price: 80.0, Neighbourhood: Campanhã
Listing ID: 3907554, Name: Charming Vanessa's House, Price: 50.0, Neighbourhood: Campanhã
Listing ID: 4845090, Name: Douro Balcony Apartment, Price: 179.0, Neighbourhood: Campanhã
Listing ID: 8629316, Name: A good place to discover Oporto...!, Price: 65.0, Neighbourhood: Campanhã
Listing ID: 9511619, Name: OPO'attics - Loft #4, Price: 70.0, Neighbourhood: Campanhã
Listing ID: 10288807, Name: Modern  studio in Porto., Price: 99.0, Neighbourhood: Campanhã
```
**Result File**: [query1.json](results/query1.json)

## 2. Find the 3 neighbourhoods with the highest number of listings
```sql
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood)
RETURN neighbourhood.name AS Neighbourhood, count(listing) AS NumberOfListings
ORDER BY NumberOfListings DESC
LIMIT 3
```

Result:
```txt
Top 3 neighbourhoods with the highest number of listings:
Neighbourhood: Cedofeita, Ildefonso, Sé, Miragaia, Nicolau, Vitória, Number of Listings: 479
Neighbourhood: Bonfim, Number of Listings: 56
Neighbourhood: Lordelo do Ouro e Massarelos, Number of Listings: 25
```
**Result File**: [query2.json](results/query2.json)

## 3. Find the cheapest listings in each neighbourhood
```sql
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood)
WITH neighbourhood, MIN(listing.price) AS minPrice
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood)
WHERE listing.price = minPrice
RETURN neighbourhood.name AS Neighbourhood, listing.id AS ListingID, listing.name AS ListingName, listing.price AS Price
```

Result:
```txt
Cheapest listings in each neighbourhood:
Neighbourhood: Lordelo do Ouro e Massarelos, Listing ID: 6715214, Name: Agradable Room Style, Price: 39.0
Neighbourhood: Bonfim, Listing ID: 7231817, Name: Guerra´s House, Price: 30.0
Neighbourhood: Canidelo, Listing ID: 3071032, Name: Breathtaking views of the Douro outlet 2-room apt, Price: 69.0
Neighbourhood: Gulpilhares e Valadares, Listing ID: 1119084, Name: Ar do mar e olhar a Natureza., Price: 72.0
Neighbourhood: Oliveira do Douro, Listing ID: 105347, Name: Minha casinha / My little house, Price: 76.0
Neighbourhood: Anta e Guetim, Listing ID: 108153, Name: Rooms-Piscina-Praia, Price: 70.0
Neighbourhood: Paranhos, Listing ID: 2905644, Name: Private room 4 with private wc and closed balcony, Price: 16.0
Neighbourhood: Mafamude e Vilar do Paraíso, Listing ID: 128557, Name: B&B-High Quality room 10min. walk from WineCellars, Price: 36.0
Neighbourhood: Campanhã, Listing ID: 127208, Name: B & B  Room Yoga Garden's Place, Price: 38.0
Neighbourhood: Madalena, Listing ID: 2525651, Name: Casa de praia muito charme#, Price: 130.0
Neighbourhood: São Mamede de Infesta e Senhora da Hora, Listing ID: 415993, Name: All Porto, Metro, University, Garage, Price: 52.0
Neighbourhood: Cedofeita, Ildefonso, Sé, Miragaia, Nicolau, Vitória, Listing ID: 1332238, Name: Welcome to So Cool Hostel Porto!, Price: 18.0
Neighbourhood: Cedofeita, Ildefonso, Sé, Miragaia, Nicolau, Vitória, Listing ID: 6462610, Name: So Cool Hostel Porto!, Price: 18.0
Neighbourhood: Santa Marinha e São Pedro da Afurada, Listing ID: 1277754, Name: River&Bridges apartments 2 in Porto, Price: 51.0
Neighbourhood: Fornelo e Vairão, Listing ID: 208147, Name: Casa do Ciríaco, Casa Rural, Portugal, Price: 40.0
Neighbourhood: Ramalde, Listing ID: 708484, Name: Little and nice inner room, Oporto, Price: 21.0
Neighbourhood: Alvarenga, Listing ID: 322277, Name: Secluded mountain house, 50m River Paiva, Price: 175.0
Neighbourhood: Vila do Conde, Listing ID: 10117617, Name: A Casa Alegre é um apartamento T1., Price: 40.0
Neighbourhood: São Pedro Fins, Listing ID: 399584, Name: Rustic House, Price: 180.0
Neighbourhood: Melres e Medas, Listing ID: 3206477, Name: A relaxed and sweet stay by the Douro river, Price: 80.0
Neighbourhood: São Félix da Marinha, Listing ID: 6167473, Name: Home sweet Home!, Price: 69.0
Neighbourhood: Matosinhos e Leça da Palmeira, Listing ID: 5008643, Name: BESTofNature:Porto CityPark/ocean Matosinho, METRO, Price: 47.0
Neighbourhood: Alfena, Listing ID: 486268, Name: Solar Piedade - FANTASTIC 5 bedroom villa, Price: 430.0
Neighbourhood: Canelas e Espiunca, Listing ID: 829780, Name: Casa do Paúl - Confort &Nature in peaceful village, Price: 185.0
Neighbourhood: Aldoar, Foz do Douro e Nevogilde, Listing ID: 5009652, Name: #Near the City Park,  the Serralves, and the Ocean, Price: 31.0
Neighbourhood: Parada de Todeia, Listing ID: 1391373, Name: 2º- Dream corner by waterfalls, 20 min to Porto, Price: 105.0
Neighbourhood: Perafita, Lavra e Santa Cruz do Bispo, Listing ID: 597262, Name: Lavra Stay Beach & SPA (sea/pool view), Price: 36.0
Neighbourhood: Arcozelo, Listing ID: 6429358, Name: Sea man house, Price: 90.0
Neighbourhood: Santa Maria da Feira, Travanca, Sanfins e Espargo, Listing ID: 1437344, Name: Chalé encantador, de estilo rural, Price: 186.0
Neighbourhood: Macieira de Cambra, Listing ID: 1459519, Name: T3 com piscina e jacuzzi - "Tristão e Isolda", Price: 117.0
Neighbourhood: Roge, Listing ID: 5634327, Name: Traços D'Outrora - Casinha da Matilde, Price: 73.0
Neighbourhood: Mindelo, Listing ID: 5927865, Name: T2 Recuado praia, Price: 64.0
Neighbourhood: AVer-o-Mar, Amorim e Terroso, Listing ID: 6988711, Name: Mar, Sol, Natureza e História, Price: 73.0
Neighbourhood: São Pedro de Castelões, Listing ID: 1543906, Name: Casa de Coelhosa - Alojamento local, Price: 496.0
Neighbourhood: Espinho, Listing ID: 8004371, Name: Enjoy a Blue Local Experience, Price: 60.0
Neighbourhood: Espinho, Listing ID: 7996802, Name: Enjoy a Green Local Experience, Price: 60.0
Neighbourhood: Campo e Sobrado, Listing ID: 1849074, Name: Watermillhouse  Cottage in Nature Reserve, Price: 71.0
Neighbourhood: Foz do Sousa e Covelo, Listing ID: 4295305, Name: Amazing Villa in Porto w/ Pool, Price: 395.0
Neighbourhood: Alvarelhos e Guidões, Listing ID: 2083027, Name: Casa da Adega _ Quinta do Arco QA, Price: 170.0
Neighbourhood: Vila Chã, Listing ID: 5409513, Name: SeaBreezeApartment-Wonderfull 1stline at the beach, Price: 75.0
Neighbourhood: Sandim, Olival, Lever e Crestuma, Listing ID: 3365178, Name: Maison avec Piscine proximité PORTO, Price: 60.0
Neighbourhood: Tropeço, Listing ID: 3426424, Name: SABERAMAR - COUNTRY, Price: 350.0
Neighbourhood: Ossela, Listing ID: 2971940, Name: Casa do Porto Carreiro, Price: 86.0
Neighbourhood: Junqueira, Listing ID: 3079995, Name: Maria’s Home, Price: 220.0
Neighbourhood: Santa Eulália, Listing ID: 4091889, Name: Casa Mosteiro - Quinta Do Pomar Maior, Price: 440.0
Neighbourhood: Rossas, Listing ID: 4337394, Name: Rustic House with Pool & Jacuzzi -Arouca Portugal, Price: 300.0
Neighbourhood: Balazar, Listing ID: 3533135, Name: Own Places | Casa de Campo com Piscina, Price: 248.0
Neighbourhood: O. Azeméis, Riba-Ul, Ul, Macinhata da Seixa, Madail, Listing ID: 4765818, Name: Cosy farm on the middle of nature, Price: 80.0
Neighbourhood: Póvoa de Varzim, Beiriz e Argivai, Listing ID: 8283374, Name: NEW! Apartment Carvalhido, Price: 140.0
Neighbourhood: Vila de Cucujães, Listing ID: 6299220, Name: Casa da Trapa- casa de campo, Price: 191.0
Neighbourhood: Ermesinde, Listing ID: 6930074, Name: ARACELLI APARTMENT with TERRACE/ BALCONY, Price: 82.0
Neighbourhood: Árvore, Listing ID: 7081211, Name: Little Country house and beach for two P., Price: 52.0
Neighbourhood: Lomba, Listing ID: 7085199, Name: Unique Spot by the River Douro, Price: 250.0
Neighbourhood: Castêlo da Maia, Listing ID: 7515930, Name: Suite Mil E Uma Noites, Price: 25.0
Neighbourhood: Aguçadoura e Navais, Listing ID: 7758778, Name: Holiday House - Beach, Price: 32.0
Neighbourhood: Arouca e Burgo, Listing ID: 9782756, Name: Bóco Manor House, Price: 245.0
Neighbourhood: Canedo, Vale e Vila Maior, Listing ID: 9981559, Name: L'Orchidée, Price: 144.0
```
**Result File**: [query3.json](results/query3.json)

## 4. Find the listings in the neighbourhood of Lordelo do Ouro e Massarelos that have a licence and price above 100
```sql
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood {name: 'Lordelo do Ouro e Massarelos'})
MATCH (listing)-[:HAS_LICENSE]->(license:License)
WHERE listing.price > 100 AND license IS NOT NULL
RETURN listing.id AS ListingID, listing.name AS ListingName, listing.price AS Price, neighbourhood.name AS Neighbourhood, license.license_id AS LicenseID
```

Results:
```txt
Listings in Lordelo do Ouro e Massarelos with a license:
Listing ID: 7179596, Name: Sailor's House - Holiday's House, Price: 101.0, Neighbourhood: Lordelo do Ouro e Massarelos, License ID: 91991/AL
Listing ID: 10605132, Name: River&Seaview House Casa do Gólgota, Price: 621.0, Neighbourhood: Lordelo do Ouro e Massarelos, License ID: 20542;20629;20630;20631;20632;20633;20634/AL
Listing ID: 3547240, Name: Apartment Vista Mar and Douro, Price: 102.0, Neighbourhood: Lordelo do Ouro e Massarelos, License ID: 20655/AL
Listing ID: 9011865, Name: Porto Douro River Guest House I, Price: 145.0, Neighbourhood: Lordelo do Ouro e Massarelos, License ID: 71549/AL
Listing ID: 6945437, Name: Amazing Riverside Balcony 2Br Apartment, Price: 194.0, Neighbourhood: Lordelo do Ouro e Massarelos, License ID: 68413/AL
```
**Result File**: [query4.json](results/query4.json)

## 5. Find the average price of listings by room type
```sql
MATCH (listing:Listing)
RETURN listing.room_type AS RoomType, round(avg(listing.price), 2) AS AveragePrice
```

Results:
```txt
Average price of listings by room type:
Room Type: Entire home/apt, Average Price: 126.34
Room Type: Private room, Average Price: 60.65
Room Type: Shared room, Average Price: 30.83
```
**Result File**: [query5.json](results/query5.json)

## 6. Find the host with the highest number of listings
```sql
MATCH (host:Host)-[:HOSTS]->(listing:Listing)
RETURN host.host_id AS HostID, host.host_name AS HostName, COUNT(listing) AS NumberOfListings
ORDER BY NumberOfListings DESC
LIMIT 1
```

Results:
```txt
Host with the highest number of listings:
Host ID: 1266562, Name: Lovely, Number of Listings: 12
```
**Result File**: [query6.json](results/query6.json)

## 7. Find the listing with the highest number of reviews that is located in the neighbourhood of Anta e Guetim and is available 365 days a year
```sql
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood {name: 'Anta e Guetim'})
WHERE listing.availability_365 = 365
RETURN listing.id AS ListingID, listing.name AS ListingName, listing.number_of_reviews AS NumberOfReviews
ORDER BY NumberOfReviews DESC
LIMIT 1
```

Results:
```txt
Listing with the highest number of reviews in Anta e Guetim and available 365 days a year:
Listing ID: 108153, Name: Rooms-Piscina-Praia, Number of Reviews: 15
```
**Result File**: [query7.json](results/query7.json)

## 8. Find the hosts with the sum of the prices of their listings above 1000
```sql
MATCH (host:Host)-[:HOSTS]->(listing:Listing)
WITH host, SUM(listing.price) AS total
WHERE total > 1000
RETURN host.host_id AS HostID, host.host_name AS HostName, total AS TotalPrice
```

Results:
```txt
Hosts with the sum of the prices of their listings above 1000:
Host ID: 1266562, Name: Lovely, Total Price: 1206.0
Host ID: 1675750, Name: YoursPorto, Total Price: 1376.0
Host ID: 2686177, Name: Citybreak, Total Price: 1305.0
Host ID: 3565697, Name: Eugenia And Luis, Total Price: 1530.0
Host ID: 7474213, Name: Olívia, Total Price: 1230.0
Host ID: 2228036, Name: Rui, Total Price: 2648.0
Host ID: 3356481, Name: Joana, Total Price: 1752.0
Host ID: 15554002, Name: Pedro, Total Price: 2498.0
Host ID: 27267172, Name: Marcos, Total Price: 1130.0
Host ID: 34994986, Name: Baumhaus, Total Price: 1140.0
```
**Result File**: [query8.json](results/query8.json)

## 9. Find the number of listings for each room type in each neighbourhood
```sql
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood)
RETURN neighbourhood.name AS Neighbourhood, listing.room_type AS RoomType, COUNT(listing) AS NumberOfListings
ORDER BY Neighbourhood, RoomType
```

Results:
```txt
Number of listings for each room type in each neighbourhood:
Neighbourhood: AVer-o-Mar, Amorim e Terroso, Room Type: Entire home/apt, Number of Listings: 4
Neighbourhood: Aguçadoura e Navais, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Aldoar, Foz do Douro e Nevogilde, Room Type: Entire home/apt, Number of Listings: 16
Neighbourhood: Aldoar, Foz do Douro e Nevogilde, Room Type: Private room, Number of Listings: 3
Neighbourhood: Alfena, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Alvarelhos e Guidões, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Alvarenga, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Anta e Guetim, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Anta e Guetim, Room Type: Private room, Number of Listings: 1
Neighbourhood: Arcozelo, Room Type: Entire home/apt, Number of Listings: 5
Neighbourhood: Arouca e Burgo, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Balazar, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Bonfim, Room Type: Entire home/apt, Number of Listings: 53
Neighbourhood: Bonfim, Room Type: Private room, Number of Listings: 3
Neighbourhood: Campanhã, Room Type: Entire home/apt, Number of Listings: 5
Neighbourhood: Campanhã, Room Type: Private room, Number of Listings: 5
Neighbourhood: Campo e Sobrado, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Canedo, Vale e Vila Maior, Room Type: Entire home/apt, Number of Listings: 2
Neighbourhood: Canelas e Espiunca, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Canidelo, Room Type: Entire home/apt, Number of Listings: 7
Neighbourhood: Castêlo da Maia, Room Type: Private room, Number of Listings: 1
Neighbourhood: Cedofeita, Ildefonso, Sé, Miragaia, Nicolau, Vitória, Room Type: Entire home/apt, Number of Listings: 424
Neighbourhood: Cedofeita, Ildefonso, Sé, Miragaia, Nicolau, Vitória, Room Type: Private room, Number of Listings: 49
Neighbourhood: Cedofeita, Ildefonso, Sé, Miragaia, Nicolau, Vitória, Room Type: Shared room, Number of Listings: 6
Neighbourhood: Cete, Room Type: Entire home/apt, Number of Listings: 3
Neighbourhood: Custóias, Leça do Balio e Guifões, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Ermesinde, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Espinho, Room Type: Entire home/apt, Number of Listings: 4
Neighbourhood: Espinho, Room Type: Private room, Number of Listings: 3
Neighbourhood: Fornelo e Vairão, Room Type: Private room, Number of Listings: 1
Neighbourhood: Foz do Sousa e Covelo, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Gulpilhares e Valadares, Room Type: Entire home/apt, Number of Listings: 4
Neighbourhood: Junqueira, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Lomba, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Lordelo do Ouro e Massarelos, Room Type: Entire home/apt, Number of Listings: 21
Neighbourhood: Lordelo do Ouro e Massarelos, Room Type: Private room, Number of Listings: 4
Neighbourhood: Macieira de Cambra, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Madalena, Room Type: Entire home/apt, Number of Listings: 4
Neighbourhood: Mafamude e Vilar do Paraíso, Room Type: Entire home/apt, Number of Listings: 8
Neighbourhood: Mafamude e Vilar do Paraíso, Room Type: Private room, Number of Listings: 2
Neighbourhood: Matosinhos e Leça da Palmeira, Room Type: Entire home/apt, Number of Listings: 5
Neighbourhood: Matosinhos e Leça da Palmeira, Room Type: Private room, Number of Listings: 1
Neighbourhood: Melres e Medas, Room Type: Entire home/apt, Number of Listings: 2
Neighbourhood: Mindelo, Room Type: Entire home/apt, Number of Listings: 2
Neighbourhood: O. Azeméis, Riba-Ul, Ul, Macinhata da Seixa, Madail, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Oliveira do Douro, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Ossela, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Parada de Todeia, Room Type: Entire home/apt, Number of Listings: 2
Neighbourhood: Paranhos, Room Type: Entire home/apt, Number of Listings: 10
Neighbourhood: Paranhos, Room Type: Private room, Number of Listings: 13
Neighbourhood: Perafita, Lavra e Santa Cruz do Bispo, Room Type: Entire home/apt, Number of Listings: 2
Neighbourhood: Perafita, Lavra e Santa Cruz do Bispo, Room Type: Private room, Number of Listings: 5
Neighbourhood: Póvoa de Varzim, Beiriz e Argivai, Room Type: Entire home/apt, Number of Listings: 3
Neighbourhood: Ramalde, Room Type: Entire home/apt, Number of Listings: 10
Neighbourhood: Ramalde, Room Type: Private room, Number of Listings: 3
Neighbourhood: Roge, Room Type: Entire home/apt, Number of Listings: 4
Neighbourhood: Rossas, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Sandim, Olival, Lever e Crestuma, Room Type: Entire home/apt, Number of Listings: 2
Neighbourhood: Santa Eulália, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Santa Maria da Feira, Travanca, Sanfins e Espargo, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Santa Marinha e São Pedro da Afurada, Room Type: Entire home/apt, Number of Listings: 22
Neighbourhood: Santa Marinha e São Pedro da Afurada, Room Type: Private room, Number of Listings: 3
Neighbourhood: São Félix da Marinha, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: São Mamede de Infesta e Senhora da Hora, Room Type: Entire home/apt, Number of Listings: 3
Neighbourhood: São Mamede de Infesta e Senhora da Hora, Room Type: Private room, Number of Listings: 1
Neighbourhood: São Pedro Fins, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: São Pedro de Castelões, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Tropeço, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Vila Chã, Room Type: Entire home/apt, Number of Listings: 4
Neighbourhood: Vila de Cucujães, Room Type: Entire home/apt, Number of Listings: 1
Neighbourhood: Vila do Conde, Room Type: Entire home/apt, Number of Listings: 7
Neighbourhood: Árvore, Room Type: Entire home/apt, Number of Listings: 2
```
**Result File**: [query9.json](results/query9.json)

## 10. Find all neighbourhoods and its listings
```sql
MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood)
WITH collect(listing.name) AS Listings, neighbourhood
WHERE size(Listings) > 0
RETURN neighbourhood.name AS Neighbourhood, Listings
ORDER BY Neighbourhood
```

Results:
```txt
All neighbourhoods and its listings:
Neighbourhood: AVer-o-Mar, Amorim e Terroso, Listings: [Our HOMEinLAND of Terroso l Pool, Grill & Seaview, Mar, Sol, Natureza e História, Villa with private pool and garden by Go2oporto, POVOA DE VARZIM "Em cima da areia"]
Neighbourhood: Aguçadoura e Navais, Listings: [Holiday House - Beach]
Neighbourhood: Aldoar, Foz do Douro e Nevogilde, Listings: [Liiiving in Porto | Garden Pool House, Foz Gardens, Top condominium with ocean view!, Casa da Foz, Apartment by the Beach - 20282/AL, Dream View In Trendy Pvt Condo Penthouse Apt, Apartamento da Foz, #Near the City Park,  the Serralves, and the Ocean, Casa da Belavista 2 - Foz Velha- Porto, Near the City Park and the sea, Enjoy a Beautiful Sunset in Porto, OceanView Oporto Foz, LightHouse Apartment with Terrace, Flattered to be in Porto, Apartment, House Foz - Ocean/River view, Foz Sea Apartment Porto, Airy & Modern Beachfront Apartment, Sea view apartment - Foz do Douro, Design Sea House]
Neighbourhood: Alfena, Listings: [Solar Piedade - FANTASTIC 5 bedroom villa]
Neighbourhood: Alvarelhos e Guidões, Listings: [Casa da Adega _ Quinta do Arco QA]
Neighbourhood: Alvarenga, Listings: [Secluded mountain house, 50m River Paiva]
Neighbourhood: Anta e Guetim, Listings: [House w/pool Near the Beach-Espinho, Rooms-Piscina-Praia]
Neighbourhood: Arcozelo, Listings: [Comfy beach house- amazing sea view, Seaside Surf Golf City - 3 Bedrooms, Sea man house, Beach House (Plage/Praia) Aguda PRT, Casa do Plátano]
Neighbourhood: Arouca e Burgo, Listings: [Bóco Manor House]
Neighbourhood: Balazar, Listings: [Own Places | Casa de Campo com Piscina]
Neighbourhood: Bonfim, Listings: [APARTAMENTO DA BAIXA - 5636/AL, Spacious and Bright Ap, OX Apartments  - B. Arts II, Space Alegria | Oporto Históric Center, Porto apartament from 25€, Home Made Guest Studios - Arrábida, Oporto Apartment Triplex, Big TERRACE APARTMENT / city center, Duplex Penthouse Apartament - 4 Bedrooms, tranquility in downtown Porto, Tiles House-Lima5, Ilda's Home - Big Families & Groups, OX Apartments  - B. Arts III, OPorto • The top floor of my house, I love Paris Apartment- Porto City, Vintage Apt. 2 /  4 - 12 People - 2 Free Parking, Downtown Terrace Apartment, Apartment with pool view, Espaçoso e luminoso Apartamento perto de belas artes I, Charming & Sunny Mezzanine Loft, class & confort downtown / poss parking, Guerra´s House, Porto Centro SI4, Espaçoso E Luminoso Apartamento perto de Belas Artes, MyLoft-Charming Apartment!!! - Metro Bolhão, Studio Porto Riad - oriental style, Ilda's Home - Garden, Home Made Guest Studios - D. Luis, Pretty flat terrace, ar cond, free parking, Host 6, Modern apartment Porto city center, Duplex Flat w/ terrace! near Dom Luís Bridge!, Apartamento em casa de charme - Porto 181, LUXURY apartment t3 oporto antas, OPO'attics - Loft #3, Home Made Guest Studios - S.João, Vintage Apt I 10-12 People - 2 Free Parking, Ilda's Home - Balcony, Oporto's heArt (PRIVATE PARKING), Minimalist House 2 bedrooms Porto, Home Made Guest Studios - Freixo, Downtown Apartment, Casa do Porto, Bairro Alegre, Home Made Guest Studios - D. Maria, Garden House Downtown with Garage, cozy studio 2 in downtown Porto, Apartment D João IV T4 (Downtown), Cozy flat- air cond, rooftop terrace, free parking, Charming. Family friendly. Central., OX Apartments  - B. Arts I, Lovely Flat in the Porto Center, Panoramic Douro view | Porto center, cozy studio 3 in downtown Porto, Downtown Terrace Apartment II, Private room in 19th cent House, I love Tokyo, Spacious suite with sunny terrace and bathroom 1T]
Neighbourhood: Campanhã, Listings: [Maria's Apartment, Wine Apartments lll - Ribeira Studio by LovelyStay, OPO'attics - Loft #1, B & B  Room Yoga Garden's Place, OPO'attics - Loft #6, Charming Vanessa's House, Douro Balcony Apartment, A good place to discover Oporto...!, OPO'attics - Loft #4, Modern  studio in Porto.]
Neighbourhood: Campo e Sobrado, Listings: [Watermillhouse  Cottage in Nature Reserve]
Neighbourhood: Canedo, Vale e Vila Maior, Listings: [L'Orchidée, Casa de Fagilde]
Neighbourhood: Canelas e Espiunca, Listings: [Casa do Paúl - Confort &Nature in peaceful village]
Neighbourhood: Canidelo, Listings: [Big and luminous 4-room apartment, Beach front luxury apartment, 10 min from Porto., Canidelo Beach Apartment, Porto south, Beachfront apartment with balcony facing the sea, Breathtaking views of the Douro outlet 2-room apt, 5 room house, next to golf course(Quinta do fojo), Boat Blue Days]
Neighbourhood: Castêlo da Maia, Listings: [Suite Mil E Uma Noites]
Neighbourhood: Cedofeita, Ildefonso, Sé, Miragaia, Nicolau, Vitória, Listings: [Poveiros duplex apartment, Casa Fonte das Oliveiras, Casa da Paz | Vintage House with garden, Studio with Balcony with Garden View, Mouzinho 134 - Historical Center - Yellow, Great 4 bedroom near Casa da Música, Room "HAPPY", private residence., Clérigos microstudio/suite 1st floor, Home Sweet Home... Bolhão - 26753/AL, Feeling Oporto 931, BATALHA CONTEMPORARY LOFT, Adorable Apartment In Great Area by PortoCityHosts, apartment 10min walking to downtown, City center, Free Parking, fantastic view, Duplex with Balcony@ City centre, Romantic apartment in Oportodowntown, ORM - Ribeira Apartment, Belomonte Stylish apartment@ city center, CLÉRIGOS BLUE (Double Small Studio), Aposentus - Lapa Downtown, Sunny Porto, A place for 2, CLÉRIGOS ORANGE (Double Studio), Explore Porto from this Classic 3-Bedroom Apt, Room in Ribeira Porto, Douro River & D.Luis Bridge, Wine Hostel -  4 Bed Mixed Dorm Ensuite, Citybreak - Apartments - Bolhão, Charming apt. at "Arts District", Explore Porto From a Charming Flat in Se!, Casas de Sant'Ana Oporto Apt 2, Bolhão 2 Pátio, Feel Porto Downtown Couture, River Balcony Downtown, OPO.APT - Pateo Apartment 110-0, Old city! River View! Indoor Parking!, Casa da Bica Porto, Citybreak - Apartments - Bolhão, GuestReady - Belle Epoque Apartments, Basic Studio With Top Location and View . Carmo 4, Baixa24 - City Center Private Sun Deck Apt, Feel Porto Ribeira Vintage Duplex, Citybreak - Apartments - Bolhão, Urban Oasis: Central Residence with Garden, Apartreboleira. Terrace with a view, GuestReady - Belle Epoque Apartments, Apartamento centro hístorico Porto, Victoria Project - House I - Private Parking, OPO.APT - Pateo Apartment 114-0, Aida's Haven | Bolhão | Room #1, GuestReady - Innvict Building, Catarina´s Flat, Apartment with a shared garden, Romantic for 2@ city center, CASAS DO PORTO - Deluxe Apartment in Downtown, OX Apartments - B Places III, Yolga´s Apart, Pensão Favorita, Porto townhouse flat-walk to center, Mouzinho 134 - Historical Center - White, Domum | Turistic Apart.  T0 - P2, Welcome to my city, Suite in the center of Oporto, Porto Holiday Penthouse, BBA Apartments Boavista - Casa da Música w/parking, ALEGRIA 319 | 2D @ Porto Downtown, JF Cozy Apartment DOWNTOWN, Luxury for 2@ City Centre, Cordoaria Guest House 2 - Downtown, Apartment in Ribeira (in need of works), Cedofeita Downtown | Historic Center, Studio in the center with balcony, Pensão Favorita, Historical center beautiful ap, Bolhão - One bedroom apartment with terrace, LVPremier Clerigos RE2- space, lift, AC, balcony, 2 suite - Downtown metro, crowntown apartment 2a, Casa da Catedral - historic centre, charming apartment in downtown Porto 2, Portodouro - Ribeira T3, Spacious Apartment with Private Garden - Downtown, Feel Porto Downtown City Break, Urban apartment - Casa da Portela 5105/AL, Charm & Cosy Virtudes Apartment, Mouzinho 134 - Historical Center - Orange, Downtown apartment, Heritage Art&Design Luxury Studio, ♥* In the mood for... Oporto*♥, Cordoaria Guest House 1 - Downtown, Baixa24 - Bolhao Apt Downtown Soulful Room 1, Oporto "Trindade" Romantic Studio, Large and Charming apt Porto Downtown (3 Bedrooms), studio apartment downtown 2 balcony, Go2oporto @ Ribeira do Porto 3, Mouzinho 134 - Historical Center - Pink, Mouzinho 134 - Historical Center - Green, Portodouro - Ribeira T3 with Terrace, Nice vacation home; downtown Rua das Flores Porto, OPO DOMUS - Porto's Historic Center, Almada - Cozy Apartment, Rooftop Studio with Terrace, DOWNTOWN DESIGN APARTMENT – S. Domingos, Apartment in the historic center 1c, Domum  | Turistic Apart. T1 Duplex, Casa do Souto a home with a soul in the city heart, HEART OF THE CITY - SPACIOUS FLAT, Rosário Arts 2, Poveiros Apartment, O´Porto Torrinha Flat, dowtown vintage apartment & patio, Bright Open Space, Apartamento São João, OX Apartments- Taipas 6, Wine Hostel - Private Room Ensuit, Fabulous Old World in Historic Center, FLH Porto Charming Studio, PATH STUDIO, RIVERTOP by YoursPorto, Musical Porto, Goabio apartment 3 riverview, the 96.1 apartment at Cedofeita | FAST WI-FI, Basic Studio With Top Location . Carmo 2, Studio Deluxe with City View, Cosy flat at 5min from center Porto, Go2oporto @ Ribeira do Porto 2, Porto Downtown River 1, CASAS DO PORTO - Classic Apartment with Balcony, ORM - Guindais Apartment, See you Around - Loft Taipas, Apartamento no centro do Porto. Terraços, PORTO DOWNTOWN FLATS-RIBEIRA STUDIO, Carolina M Apt  in front of Metro w/Patio+Parking, 1 Oporto City Center - sleeps 6, 4 beds, Feel Porto Downtown Poets Flat, OX Apartments - B Places I, Studio in Oporto, Ribeira Oporto Apartment II (Renewed 2021), Top floor balcony flat in Porto, OX Apartments- Taipas 1, O´Porto Fontinha Flat, CLÉRIGOS PURPLE (Double micro studio), traditional and Charming house, PENSÃO FAVORITA, Oporto FR Cativo Flat, House in the city centre of Porto, free WiFi, Light Studio - Historic center, Sweet and quiet, historical center, Bonjardim OportoStay Loft, Flowerstreet54 - Attractive flat at centre, Casa do Mirante, Apartment in newly renewed building, Central  charming Top floor - nice views, Clérigos Room | Historic Centre, Baixa24 - Bolhao Apt w/ Sunset Terrace, Central Luxurious Room with private WC, São Nicolau - Studio wtih balcony and garden view, Sweet roomfor2 - Cedofeita Duplex, CdC I Apartments-Balcony, CLERIGOS white small studio, 4 Bedroom Apartment  - Porto, Pensão Favorita, Studio 65   |   Flat + Terrace, My Own Private Studio, Porto Insight Apartment - Bainharia free wi-fi, Wine Hostel - 6 Bed Mixed Dorm / Shared bathroom, Duplex with Terrace | Ace Location - Clean & Safe, Wonderful Apart Center Oporto!, PENSÃO FAVORITA, Guest-House in the centre of Porto, CASAS DO PORTO - Cosy Studio with Balcony, Cedofeita Mezzanine with Terrace, Go2oporto @Students - Quarto 2, PentHouse Apartment in a Central Building, WHITE Fabrica House WIFI Downtown, Musical View, Feel Porto Downtown City Roofs, OX Apartments- Taipas 8, Apartamento do Bolhão 1, Big private bedroom with private WC, Porto back apartment-walk to center, Central Loft with AC and Garden, SBV perfect river view, Citybreak - Apartments -  Mercados, Casas de Sant'Ana Oporto Apt 1, Ribeira Smart Flat, Páteo, Riverfront Porto & Douro, Spectacular Views!, ORM - Santa Clara Apartment, Wine Hostel - 8 Bed  Mixed Dorm / Shared bathroom, Baixa Ap-Vintage Spacious-Downtown, PortoWhite (01) city lofts, CLÉRIGOS RED (Double Studio), Rosário Arts 1, Loft with Garden View, Welcome to So Cool Hostel Porto!, Casais da Bandeirinha, PipaD'oro - Superior Apartment With River View, "Vintage" Flat in Porto city center, 17 Largo Viriato Historic Center, Porto Downtown Studio w/ free wifi, Loft Clérigos | Historic center, Historical City Center Apartment, CASAS DO PORTO - Romantic Apartment with Balcony, City Silhouette, Portodouro - Santa Catarina T8, Your FLAT in Oporto, Sun Room at Fatima's House - Cedofeita, Porto Life Studio 2ºF, Pensão Favorita, Charm 2 bedroom flat in the center, Bonjardim 466-Historic Townhouse, Feel Porto Downtown Essence, Bedroom in the centre-Trindade, Basic Studio With Top Location and View . Carmo 1, Loft Almada 4 guests, studio apartment downtown 3 balcony, Domum | Turistic Apart.  T1, Forrester Apartment, ORM - Cristal Apartment, CASAS DO PORTO - Duplex Vintage House in Downtown, Art & City II, Aida's Haven | Bolhão | Room #3, Feeling Oporto 932, Carregal Apartment, New Boavista Exclusive Apartment, Vintage Downtown Studio, Quarto privado com cama de casal, Welcome to my city 2, Top Design Apartment in Boavista, Clerigos.H - Apartment T1 (1st Floor), OPO.APT - Art Deco Apartment 114-1, Wine Apartments II - Ribeira 1BR by LovelyStay, CardosasAparFloch, ORM - Bolhão 01 Apartment, Guest-House in Trindade - Center, ChillHouse_Porto Bonjardim, Óporto.INguest, " Unique experience residence ", Gold Oporto Home River Front - Ribeira, Flores Studio near S. Bento Station, Porto Center - Indian Soul, room2duplexapartment, Cedofeita Duplex, Citybreak - Apartments - Pátio, Oporto Best View, Flowerstreet54 - Special flat next to Train& Metro, OX Apartments - B Places V, Portodouro - Santa Catarina T1, CLÉRIGOS GREEN (Double Studio), Ribeira Cinema Apartments - 2 Bedrooms (1interior), Studio at Alfandega Apartments, Your Place Porto II - Estúdio, Art&Design apartment, kids friendly, ORM - Bolhão 2 Apartment, Apartment with balcony and city view by YoursPorto, Portodouro - Santa Catarina T1, Private room in the center of city, Foz do Douro - Apartment with garden, Citybreak - Apartments - Lapa, apartment downtown 1 terrace, Terrace Apart.Deluxe for 2/4 Guests, Luxury Apartment in Cardosas, ExtendALL, PORTO Studio 2, Eiffel Riverside Garden (Ribeira), Red Oporto Home River Front - Ribeira, Vitória 392 - Central Loft, Av Apartments I, Oporto Downtown Loft - Clérigos, O'Porto Studio | Historic Center, Citybrea -Apartments - Lapa, Ribeira Vintage Flat 1, Alegria 2º TRS, Your Apartment in Oporto center, São Bento - One bedroom apartment with balcony, Av Apartments Ii, charming apartment in downtown Porto 3, Studio flat in Porto city centre, Stylish Studio walk-up in lively City Center, Ribeira Cinema Apartments _ Superior Studios, OX Apartments- Taipas 4, Porto city centre serviced studio, PipaD'oro - Apartment with garden view, Vintage Downtown Apartment, Stunning Location, fully equipped, Spacious Apartment with views, O'Porto Main Street St Catarina 1, Loft histórico, elegante e acolhedor, Lulapartment, OX Apartments- Taipas 7, Apartment in the historic center, Citybreak - Apartments - Lapa, Eiffel Riverside Garden, Citybreak-Apartments  -  623, #1 | OPO | Torrinha | "Old Radio", Roof terrace apartment in Porto's city centre, Fabrica House downtown cozy WIFI, Baixa24 - City Center Private Sun Deck Apt Room 3, Happy Street, Porto Downtown Flats - Sé Cathedral View Balcony, Mouzinho 134 - Historical Center - Blue, Baixa24 - Bolhao Apt w/ Sunset Terrace Room 2, Clérigos Charming Apartment - Great City View, Imperium Apartments - Aliados, Apartment TRAVELS - Poeta Apartments 6517 / AL, OX Apartments - B Places II, MiguelBombarda260 by PORTO-HOUSES AND SUITES, Go2oporto @ Ribeira do Porto 1, Cosy studio near Clérigos tower, PENSÃO FAVORITA, PortoPath- Duplex with mezanine very bright, Toc Toc Studio, Fun Bijou Retreat in a Historic Building, Belomonte River view @city center_4, OPO.APT -  Art Déco Apartment 110-1, GuestReady - Historic Courtyard Townhouse, Dream_Casa Sol_double bed_private bathroom_Terrace, Alegria 319 | 3D @ Porto Downtown, City Double View, Apartamento amplo e bem localizado, CASAS DO PORTO - Elegance Apartment, One Bedroom Apartment at Alfandega, Feel Porto Downtown Luxury Retreat, Azulejo Porto Riverside, Aida's Haven | Bolhão | Room #2, Bolhão 1 Cidade, Almond Biscuit APT  Historic Center, Wine Hostel - 6 Bed Mixed Dorm  Ensuite, Feel Porto Downtown Heaven, Feel Porto Historic Cozy Flat, Ground floor flat in Garden, Ribeira - Porto/ Duplex Apartment, Walk Through Downtown From a Peaceful Apartment, Egg Nuts Apt - Historic Center, OX Apartments- Taipas 5, Riverfront Apartment in center of Porto, Citybreak - Apartments - DouroView, Heartmade loft 1, Your Place Porto I - Apartament, "Ruby" Flat in Porto city center, Apartment in the historic center, Blue Oporto Home River Front - Ribeira, ExtendALL, PORTO Studio 1, Great room in Oporto's centre WIFI, Apart. Camões for 2 to 4 guests, Family Room/ Suite - River View, Apartment  Douro Ribeira Porto, apt 2 bedrooms ideal for a family/couples/p3, Modern Flat with Patio in Historic Jewish Quarter, Studio 45 - Balcony and AC in Historical Old Town, Porto Invictus, Studio with balcony in Clérigos Tower, Great View, O'Porto Main Street St Catarina 2 DOWNTOWN, Casa do Patio a home with a soul in the city heart, So Cool Hostel Porto!, Mouzinho 134 - Historical Center - Grey, Porto Luxury Suite, "Casa do Sol" Apartment, Baixa24 - City Center Private Sun Deck Apt Room 4, Oporto Center SI2, SãoMiguelApartments HistoricalCenter SuperiorApart, Apartamento do Bolhão 2, DESIGN HOUSE OLD TOWN | BALCONY | AIRCO | RIBEIRA, Hungarian Biscuits  Historic Center, Beautiful/Sunny  (center), Vitória 392 - Central  Apartment, Charming Historic Center, Stylish Mezzanine @City Center, Sótão acolhedor num prédio do século XVIII, Luxury Apartment Downtown - Porto, LV Premier Clerigos RE1- AC, elevator, river view, A HOUSE WITH A VIEW-Historic centre, ALMADA APARTMENT DELUXE, Baixa24 - P3R - Double Studio Historic Center, Feel Porto Historic Boutique Flat, PortoWhite (02) city lofts, Ikebana VI - Charming apart  downtown Porto, Green Oporto Home River Front - Ribeira, Suite com jardim partilhado, Porto center-Priv. room w/ WC&WI-FI, ABEL'S IN DOWNTOWN - THE PLACE TO BE 2 OF 2, PipaD'oro - Studio with balcony, Downtown Classic Apartment, Domum | Turistic Apart. T0 - P1, Dazzling City Center Apart in Lively Neighborhood, 2 bedroom apartment near Lello Bookshop - balcony, Inside Porto Apartments @ Flores T2, Charm Loft Duplex, Go2oporto @Students - Quarto 1, Baixa24 - City Center Private Sun Deck Apt Room 2, Purple Oporto Home River Front - Ribeira, Trendy Apartment in Downtown Porto, charming apartment in downtown Porto 1, Sunny apartment in city center, Room "Time", private residence., OX Apartments- Taipas 3, Stylish Studio on Santa Catarina Street, Cosy Loft Duplex with balcony, Feel Porto Downtown Art Tile, Loft with river view by YoursPorto, Cedofeita Studio - City view, Enjoy Porto Apartment by Porto City Hosts, Wine Apartments I - Ribeira 1BR by LovelyStay, HAVEN 249 Passos Manuel, Studio - River View, River View 2 bedroom apartment, Central, Modern, University, Metro, Baixa24 - City Center Private Sun Deck Apt Room 5, Pin Porto City Center, Baixa24 - City Center Private Sun Deck Apt Room 1, Large Studio with great views - Clérigos, OX Apartments - B Places IV, Musical View II, Porto's main square penthouse apartment, Picaria Rooftop Apartment, Porto Downtown Apartment, One-Bedroom Ap for 3 in Clérigos Tower, RIVERTOPVIEW by YoursPorto, No coração da cidade, Be Happy in Porto, S. Bento Apart - Duplex, Charming & Unique City Flat, Classy Apartment in Boavista, Spacious and Bright @ City Center, Belomonte sunny @ city center 3, Kitchnette Studio in Porto's Downtown, Porto Ribeira Moments - Apartamento, Inside Porto Apartments @ Flores T1, Aida's Haven | apartment | Bolhão |, Mouzinho 134 - Historical Center - Brown, Belly Angel - Porto Historic Center, Kitchnette Studio in Porto's Downtown II, Alegria 1º, Spacious top floor apartment in the heart of Porto, Almada Studio Flat - Porto, Good vibe apartment center Porto:), Aniki Porto Riverside, Estudio economico na Lapa, GuestReady - Belle Epoque Apartments, GuestReady - Innvict Building, Sunny with Balcony @ City Center, PORTO DOWNTOWN FLATS-RIBEIRA VIEW (renovated 2019), Studio Cedofeita  3tr, Guest-House in the centre of Porto, Studio with terrace @ historic centre, Double room ensuite in the center, Go2oporto @ A-Portoments III, Cardosas Design Flat, ExtendALL, PORTO Loft, S. Bento Apart - Studio Ii, Alegria Apartment Porto Downtown, Almada 329 Apartment, Central Apartment in Porto, ABEL'S IN DOWNTOWN - THE PLACE TO BE 1 OF 2, Go2oporto @Students - Quarto 3, Clérigos Downtown | Historic Centre, Cosy apartment and central -Tourism, Ribeira Oporto Apartment (renovated 2022), BY CLÉRIGOS YELLOW (Double Studio), "Tawny" Studio in Porto city center, Feel Porto Photo Downtown Flat, Ribeira Cinema Apartments _ 1 Bedroom Apartment, OX Apartments- Taipas 9, 9 Julho - Apartamento junto Metro, A Beautiful Place in Boavista, Basic Studio With Top Location and View . Carmo 3, Apartment Sao Nicolau Porto, Cedofeita - BednBike, New Apartment with a small terrace, O´Porto Fontainhas Flat, Clérigos - One bedroom apartment, OX Apartments - B Places VI, Room "Zen", private residence., Porto Life Studio 3ºF, Azulejos, casabonjardim - historic center, OPO | Torrinha | “Old Radio” apartment, ORM - Sé Apartment, Apt Stay in Fontainhas - With Free Parking & Wi-Fi, O'Porto Main Street St Catarina 2 Studio, Pensão Favorita, Charming attic room & WC + Garden, Casa da Hera - The Ivy House]
Neighbourhood: Cete, Listings: [Fontielas Houses | House T1 with shared Pool, Fontielas Houses |Floor0 in House with shared Pool, Fontielas Houses |Floor1 in House with shared Pool]
Neighbourhood: Custóias, Leça do Balio e Guifões, Listings: [Make yourself at home!]
Neighbourhood: Ermesinde, Listings: [ARACELLI APARTMENT with TERRACE/ BALCONY]
Neighbourhood: Espinho, Listings: [Historical House. Walk to the beach, SweetGetaway-PORTUGAL-BeachBike PORTO/Espinho City, Enjoy a Blue Local Experience, PORTO TERRACE ❤️ 70m2 Apartment w/ lovely Balcony, Casa D' Avó, Cosy Room on the beach - Espinho, Enjoy a Green Local Experience]
Neighbourhood: Fornelo e Vairão, Listings: [Casa do Ciríaco, Casa Rural, Portugal]
Neighbourhood: Foz do Sousa e Covelo, Listings: [Amazing Villa in Porto w/ Pool]
Neighbourhood: Gulpilhares e Valadares, Listings: [Casa da Praia em Valadares, "Only Sleep" room in magnificient house, Apartamento  a 50 metros da praia, Ar do mar e olhar a Natureza.]
Neighbourhood: Junqueira, Listings: [Maria’s Home]
Neighbourhood: Lomba, Listings: [Unique Spot by the River Douro]
Neighbourhood: Lordelo do Ouro e Massarelos, Listings: [Lodge em Jardim Panorâmico - Porto, Sailor's House - Holiday's House, View over the mouth of the Douro, Loft Duplex Com Terraço Vista Rio, Oporto friendly apartment! (0,7km Botanic Garden), River&Seaview House Casa do Gólgota, Serralves Park Apartment with Garage, Duplex espaçoso com acesso a jardim, Charming Family House with Garden (6981/AL), #2 | OPO | Torrinha | "Old Radio", Apartment Vista Mar and Douro, The perfect location to visit Porto, Green House - Quarto 1, Estúdio C/Amplo Terraço E Vista Rio, Fantastic Apartment close to Center, Porto Douro River Guest House I, Ambiente Agradável/Nice Place, Suite Luxuosa com vista panorâmica, Luxury Apartment Riverwiew Garden, Lodge com terraço vista rio e mar, Amazing Riverside Balcony 2Br Apartment, River & Ocean– Cozy independent Studio, Douro's romantic apartment, Agradable Room Style, Porto city flat near the sea]
Neighbourhood: Macieira de Cambra, Listings: [T3 com piscina e jacuzzi - "Tristão e Isolda"]
Neighbourhood: Madalena, Listings: [Beachfront apartment with pool., Charming Apartement, Luxury OPO Beachfront Penthouse w/Pools, Casa de praia muito charme#]
Neighbourhood: Mafamude e Vilar do Paraíso, Listings: [Central Low Cost (Av. da República - VNGaia ), Nice studio with patio and garden in Gaia, 1 bedroom in historic neighborhood, Nice studio with balcony in Gaia, Home away from home...in Porto!, B&B-High Quality room 10min. walk from WineCellars, Casa da Torre, Porto/Gaia Apartment with swimming pool, Discovery | Porto-Gaia, B&B - Luxury Suite with HQ private bath]
Neighbourhood: Matosinhos e Leça da Palmeira, Listings: [Porto Leça Beach Flat in Matosinhos, Apartment in Matosinhos near the beach, Sailing boat at the beach, Villa by the Porto Beach, Modern house & garden-Porto seaside, BESTofNature:Porto CityPark/ocean Matosinho, METRO]
Neighbourhood: Melres e Medas, Listings: [A relaxed and sweet stay by the Douro river, Holiday Villa - Casa do Sobreiro]
Neighbourhood: Mindelo, Listings: [Casa De Praia ,Mindelo ,Vila Do Conde, Porto, T2 Recuado praia]
Neighbourhood: O. Azeméis, Riba-Ul, Ul, Macinhata da Seixa, Madail, Listings: [Cosy farm on the middle of nature]
Neighbourhood: Oliveira do Douro, Listings: [Minha casinha / My little house]
Neighbourhood: Ossela, Listings: [Casa do Porto Carreiro]
Neighbourhood: Parada de Todeia, Listings: [3º- Dream corner by waterfalls, 20 min to Porto, 2º- Dream corner by waterfalls, 20 min to Porto]
Neighbourhood: Paranhos, Listings: [Suite Ribeira - Uporto House, Boavista - Uporto House, Light and colorful cosy studio, Modern and quiet T1 with private garage, Splendid flat w/ patio, Porto Center-Indian Soul Apartment, Aliados - Uporto House, Batalha - Uporto House, Private room 4 with private wc and closed balcony, Arrábida - Uporto House, * Free Street Parking * Ground Floor with washer, Lovely Room | Private Bathroom :), D. Luís - Uporto House, Porto Architecture and Cool Design, D. Henrique - Uporto House, Porto -Center Indian Soul 2, Foz - Uporto House, Garden House, Casa de Salgueiros, Suite Clérigos - Uporto House, Green Bell Oporto City with parking, Costa Cabral - Porto Apartment, Private house in Oporto - Portugal]
Neighbourhood: Perafita, Lavra e Santa Cruz do Bispo, Listings: [VillaMaria, Porto, Beach & Holidays, Lavra Stay Beach & SPA (sea/pool view), Bright and cossy, Beach Room Paraíso up to 3, with sea view, Beach room good for families (up to 4 people), Beach room near Airport and Porto, Room on the beach near Porto,Airpor]
Neighbourhood: Póvoa de Varzim, Beiriz e Argivai, Listings: [NEW! Apartment Carvalhido, Cozy apartment next to the beach, PanoramicPlace Premar]
Neighbourhood: Ramalde, Listings: [Apartamento T1 Kit-Boavista-Porto, Porto Center Apartement - Beach 2 KM, Casa privada, óptima localização, registo 55860/AL, PortoFLAT Self contained studioflat, Peaceful cottage near the centre of Porto, Red Room | Grace House, Apartamento 4 quartos (2) - Porto, Francos' Relaxing Apt by metro w/ private parking, Apartamento Galerias, Apartment at Ramalde metro station, Sunny Room With Private Balcony, Lux&Love Casa da Musica Apartment, Little and nice inner room, Oporto]
Neighbourhood: Roge, Listings: [Traços D'Outrora - Casinha da Matilde, Traços D'Outrora - Casa Custódio, Traços D'Outrora - Casa Rosalina, Traços d'Outrora - Casa Paço de Mato]
Neighbourhood: Rossas, Listings: [Rustic House with Pool & Jacuzzi -Arouca Portugal]
Neighbourhood: Sandim, Olival, Lever e Crestuma, Listings: [Maison 2 Chambres accès piscine, Maison avec Piscine proximité PORTO]
Neighbourhood: Santa Eulália, Listings: [Casa Mosteiro - Quinta Do Pomar Maior]
Neighbourhood: Santa Maria da Feira, Travanca, Sanfins e Espargo, Listings: [Chalé encantador, de estilo rural]
Neighbourhood: Santa Marinha e São Pedro da Afurada, Listings: [Viewpoint of Douro riverfronts, Cozy studio w/free parking - Oporto, HEART OF THE CITY 1 !, Studio with river view by YoursPorto, Apartamento Caves O'Porto, River&Bridges apartments 1 in Porto, Suite Privativa com Casa de Banho Privativa, Zona Histórica de V.N.Gaia, River&Bridges apartments 2 in Porto, Balcony of Douro riverfronts, FLH Porto Vista House, Superb balcony over Porto & river, Douro Sailing - Noite a Bordo, In a nice quiet fishing village, MyRiverPlace N.2  Oporto Apartments, Douro Marina Studios, "Casa das Azenhas", Porto RiverView flat, Douro Sunset House, Apartment center w/free parking, Breath Taking Views, Time 2 Porto Pilar House, Amazing River Views - charming Studio, Uber Flat Low Cost, Fresh New @ Gaia Main Artery]
Neighbourhood: São Félix da Marinha, Listings: [Home sweet Home!]
Neighbourhood: São Mamede de Infesta e Senhora da Hora, Listings: [Yellow House -  Casa CF, Meet Porto Apartment, FLH Porto Charming Flat, All Porto, Metro, University, Garage]
Neighbourhood: São Pedro Fins, Listings: [Rustic House]
Neighbourhood: São Pedro de Castelões, Listings: [Casa de Coelhosa - Alojamento local]
Neighbourhood: Tropeço, Listings: [SABERAMAR - COUNTRY]
Neighbourhood: Vila Chã, Listings: [Fisherman's Shack, Serena's House - 1st line of the beach, Corredoura House, near Oporto, SeaBreezeApartment-Wonderfull 1stline at the beach]
Neighbourhood: Vila de Cucujães, Listings: [Casa da Trapa- casa de campo]
Neighbourhood: Vila do Conde, Listings: [3 Room Luxury Apartment in City Centre - nr96, Cozy beachfront in Vila do Conde, Vue sur la mer à couper le souffle, A Casa Alegre é um apartamento T1., 2min walk from beach - 20min from Porto City, 3 Room Luxury Apartment in City Centre - nr98, Facing the sea and harbor boats]
Neighbourhood: Árvore, Listings: [Little Country house and beach for two P., Casa com piscina]
```
**Result File**: [query10.json](results/query10.json)