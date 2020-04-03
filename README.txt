Bonjour,


BUT 
Cette application permet de gérer vos contacts.
Vous pouvez renseigner :
- le nom
- le prénom
- l'adresse (rue, code postal et ville)
- le numéro de téléphone
- l'adresse mail
- la date d'anniversaire
Vous pouvez créer un nouveau contact, modifier ou supprimer un contact existant.
Cette application est relié à un fichier XML.



CONTEXTE
J'ai effectué cette application pour le cours intitulé "INFO803 - Ingénierie systèmes d'information : Méthodes agiles"
Nous devions respecter les points suivants :
- développement de l'application en utilisant Eclipse et en développant votre aisance avec cet outil (Eclipse);
- exécution de l'application ("mon appli. fonctionne !");
- qualité de la conception logicielle et mise en oeuvre des bonnes pratiques (principes et concepts du modèle objet - ce qui a été vu en cours et ce que vous avez appris ces 2 semestres) - l'étudiant-e pourra justifier ses choix;
- utilisation des interfaces graphiques en Java (au moins une interface graphique);
- la prise en compte et le traitement des exceptions;
- au choix: utilisation d'une BD relationnelle avec JDBC ou de fichiers soit au format JSON, soit au format XML pour stocker de l'information;
- mise en place de tests et utilisation de JUnit avec Eclipse;
- utilisation de quelques patrons de conception (vous choisirez ceux qui vous paraissent pertinents en justifiant ce choix);
- respect de la loi de Demeter;
- mise en oeuvre de quelques principes de l'intégration continue et utilisation a minima de Git ou Github (voire d'autres outils et plateformes comme Tuleap Campus ou Maven, ou ....).



INSTALLATION
Pour que l'application fonctionne il faut :
installer Java JDK8. (Vous trouverez le fichier à l'URL ci-contre : https://www.oracle.com/java/technologies/javase-downloads.html)
Posseder Eclipse 4.4 ou supérieur avec le plugin e(fx)clipse.
Scene Builder 8.0 : https://gluonhq.com//products/scene-builder/

CONFIGURATION d’Eclipse
Nous devons paramètrer Eclipse pour qu’il utilise le JDK 8 et qu’il sache où trouver le le Scene Builder :

- Ouvrez les préférences et sélectionnez la partie Java | Installed JREs.
  Cliquez sur le bouton Add… pour ajoutez le JDK 8 puis sur Standard VM et sélectionnez le dossier contenant le JDK 8.
  JDK 8 doit être le JDK par défaut (default) !

- Sélectionnez la partie Java | Compiler.
  Définissez la Compiler compliance level à 1.8 !

- Sélectionnez la partie JavaFX puis spécifiez le chemin de votre exécutable Scene Builder !
