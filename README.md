RentCarServer project.

Toto je jednoduchý projekt, který má základní funkcionalitu a byl vytvořen pouze za účelem demonstrování zvládnutí témat:

   - Java Core
   - SQL PostgreSQL
   - Java Database Connectivity
   - Servlets

Projekt je postaven na vrstvách, což umožňuje jednodušší rozšiřování kódu a navigaci.

Knihovny použité v projektu jsou:

    'bcrypt-0.10.2'
    'commons-codec-1.15'
    'lombok'
    'postgresql-42.6.0'
    'servlet-api'

Web Server: apache-tomcat-10.1.15

 1)Dokumentace
   - Dokumentace k celému projektu byla vytvořena formou JavaDoc a je umístěna na: RentCarServer/blob/main/javaDoc/index.html.
   - Pro otevření je potřeba stáhnout celou složku RentCarServer/blob/main/javaDoc a otevřít soubor index.html.

 2)Větve
   - Větev 'main' obsahuje celou strukturu projektu (RentCarServer/tree/main/src/com), zdroje (RentCarServer/tree/main/Resources), soubory ve formátu .jsp (RentCarServer/tree/main/web).
   - Větev 'production_WAR' obsahuje importované závislosti (RentCarServer/tree/production_WAR/lib) a také připravený ke spuštění WAR archiv (RentCarServer/blob/production_WAR/out/artifacts/RentCarService_Web/RentCarService_Web.war).

 3)Spuštění aplikace
    - Pro spuštění aplikace je potřeba importovat WAR archiv do apache-tomcat-10.1.15\webapps a spustit server pomocí apache-tomcat-10.1.15\bin\startup.bat.
