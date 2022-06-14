## Auteurs
5 étudiants participent au projet : 
 - Belarabi Othman
 - Brarou Bilal
 - Chetouani Mohamed
 - Laity Mounir
 - Nguyen Philippe

## Logiciels et outils utilisés
 - Netbeans 11
 - JDBC
 - JUnit 4.12
 - JavaFX

## Description
Notre projet consiste au développement du jeu [Démineur](https://fr.wikipedia.org/wiki/D%C3%A9mineur_%28genre_de_jeu_vid%C3%A9o%29) en interface graphique. Une base de données SQLite est utilisée pour sauvegarder les scores effectués à chaque partie. Le but du jeu est de découvrir toutes les cases ne contenant pas de mine et de marquer toutes les mines présentes dans le plateau. La partie est considérée comme gagnée si toutes les mines sont couvertes de drapeau et que toutes les autres cases sont découvertes. Si une mine est découverte, la partie est perdue. Les cases ne contenant pas de mines indiquent au joueur combien de mines il y a autour d'elles.

## Mode d'emploi
Pour jouer à notre démineur, il faut donner un nom et sélectionner une taille de plateau et un nombre de mines avant de valider. Ensuite, il suffit de cliquer sur une case du plateau de jeu pour la révéler. Un clic gauche révèle la case et un clic droit la marque comme étant une mine (on y pose un drapeau). Pour enlever un drapeau déjà posé, il suffit de refaire un clic droit dessus. La partie continue jusqu'à ce que le joueur révèle une mine (il perd la partie) ou jusqu'à ce qu'il marque toutes les mines et révèle les autres cases.
![Déroulement d'une partie](https://media2.giphy.com/media/1DISvyXOaCQsVfJTme/giphy.gif)
## Répartition des tâches

Bilal, Mounir et Mohamed se sont occupés de développer la partie console et l'interface graphique tandis que Philippe et Othman se sont occupés d'adapter le projet pour y inclure une base de données pour les scores.

## Description des classes
**Board**

Cette classe représente le plateau de cases du jeu. Parmi ses méthodes on retrouve isInside qui vérifie qu'une case se trouve bien dans le plateau ou encore getSquareAt qui retourne la case à la position demandée.

**Direction**

Cette classe représente les directions possibles pour les cases du plateau. Elle est utilisée pour connaître la case suivante dans une direction donnée pour obtenir le nombre de mines autour d'une case notamment.

**Position**

Cette classe représente la position d'une case dans le plateau de jeu. La méthode next renvoie la prochaine position dans une direction demandée. Une position possède une ligne et une colonne.

**State**

Cette énumération représente les états possibles pour une case du plateau. Une case peut être un nombre indiquant le nombre de mines autour, une mine ou un état vide qui sert lors de leur construction.

**Square**

Cette classe représente une case du plateau de jeu. La case est l'élément interactif de notre jeu. Elle possède un état et plusieurs autres attributs permettant d'indiquer si elle est révélée ou non, si elle possède un drapeau etc. Elle possède également une valeur indiquant le nombre de mines autour d'elle. Cette classe est observée par la classe SquareView. Elle la notifie lors de la méthode reveal.

**Game**

Cette classe représente la partie en cours. Elle possède toutes les méthodes permettant de régir le déroulement de la partie (comme play, init etc.). Elle possède plusieurs attributs comme le nombre de mines, le plateau, le score etc. Cette classe est observée par InfoView.
