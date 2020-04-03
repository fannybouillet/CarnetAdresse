Bonjour,


BUT 
Cette application permet de g�rer vos contacts.
Vous pouvez renseigner :
- le nom
- le pr�nom
- l'adresse (rue, code postal et ville)
- le num�ro de t�l�phone
- l'adresse mail
- la date d'anniversaire
Vous pouvez cr�er un nouveau contact, modifier ou supprimer un contact existant.
Cette application est reli� � un fichier XML.



CONTEXTE
J'ai effectu� cette application pour le cours intitul� "INFO803 - Ing�nierie syst�mes d'information : M�thodes agiles"
Nous devions respecter les points suivants :
- d�veloppement de l'application en utilisant Eclipse et en d�veloppant votre aisance avec cet outil (Eclipse);
- ex�cution de l'application ("mon appli. fonctionne !");
- qualit� de la conception logicielle et mise en oeuvre des bonnes pratiques (principes et concepts du mod�le objet - ce qui a �t� vu en cours et ce que vous avez appris ces 2 semestres) - l'�tudiant-e pourra justifier ses choix;
- utilisation des interfaces graphiques en Java (au moins une interface graphique);
- la prise en compte et le traitement des exceptions;
- au choix: utilisation d'une BD relationnelle avec JDBC ou de fichiers soit au format JSON, soit au format XML pour stocker de l'information;
- mise en place de tests et utilisation de JUnit avec Eclipse;
- utilisation de quelques patrons de conception (vous choisirez ceux qui vous paraissent pertinents en justifiant ce choix);
- respect de la loi de Demeter;
- mise en oeuvre de quelques principes de l'int�gration continue et utilisation a minima de Git ou Github (voire d'autres outils et plateformes comme Tuleap Campus ou Maven, ou ....).



INSTALLATION
Pour que l'application fonctionne il faut :
installer Java JDK8. (Vous trouverez le fichier � l'URL ci-contre : https://www.oracle.com/java/technologies/javase-downloads.html)
Posseder Eclipse 4.4 ou sup�rieur avec le plugin e(fx)clipse.
Scene Builder 8.0 : https://gluonhq.com//products/scene-builder/

CONFIGURATION d�Eclipse
Nous devons param�trer Eclipse pour qu�il utilise le JDK 8 et qu�il sache o� trouver le le Scene Builder :

- Ouvrez les pr�f�rences et s�lectionnez la partie Java | Installed JREs.
  Cliquez sur le bouton Add� pour ajoutez le JDK 8 puis sur Standard VM et s�lectionnez le dossier contenant le JDK 8.
  JDK 8 doit �tre le JDK par d�faut (default) !

- S�lectionnez la partie Java | Compiler.
  D�finissez la Compiler compliance level � 1.8 !

- S�lectionnez la partie JavaFX puis sp�cifiez le chemin de votre ex�cutable Scene Builder !
