# TP Instrumentation d'Eflamm Ollivier et Bertrand Tissier

## Utilisation

### Exercice 1

Commencer par utiliser la commande suivante:

```shell
$ mvn package
```

� la racine du projet pour compiler le code.

Ensuite il faut faire la commande:

pour Windows :

```bash
$ java -cp ./target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainCount src/main/resources/example src/main/resources/example-instrumented
```
pour Linux:

```shell
$ java -cp .:target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainCount src/main/resources/example src/main/resources/example-instrumented
```
Ensuite aller au r�pertoire `src\main\resources\example-instrumented` du projet:
compiler via la commande : 
```shell
$ mvn package
```

Ensuite executer le Jar via:

pour Windows :

```bash
$ java -cp ./target/example-1.0-SNAPSHOT.jar example.A 2
```
pour Linux:

```shell
$ java -cp .:target/example-1.0-SNAPSHOT.jar example.A 2
```

Cela g�n�re un fichier Log dans le r�pertoire: `src\main\resources\example-instrumented`

### Explication

Nous avons doubl� les classes:
* Main -> MainCount
* Instru -> InstruCount
* LogWriter -> CountMethodCall
* ShutdownHook -> ShutdownHookCount
* LogProcessor -> CountProcessor

La classe LogProcessorCount va appeler la M�thode `out` de la classe CountMethodCall. Cette m�thode va remplir le HashMap de la classe pour enregistrer les m�thodes appel�es et compter le nombre d'appel. A la fin de la proc�dure de spoon, on va appeler la m�thode `getwriter` de CountMethodCall qui va �crire sur le fichier Log.

### Exercice 2

Commencer par utiliser la commande suivante:

```shell
$ mvn package
```

� la racine du projet pour compiler le code.

Ensuite il faut faire la commande:

pour Windows :

```bash
$ java -cp ./target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainTree src/main/resources/example src/main/resources/example-instrumented
```
pour Linux:

```shell
$ java -cp .:target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainTree src/main/resources/example src/main/resources/example-instrumented
```
Ensuite aller au r�pertoire `src\main\resources\example-instrumented` du projet:
compiler via la commande : 
```shell
$ mvn package
```

Ensuite executer le Jar via:

pour Windows :

```bash
$ java -cp ./target/example-1.0-SNAPSHOT.jar example.A 2
```
pour Linux:

```shell
$ java -cp .:target/example-1.0-SNAPSHOT.jar example.A 2
```

Cela g�n�re un fichier Log dans le r�pertoire: `src\main\resources\example-instrumented`

### Explication

Nous avons doubl� les classes:
* Main -> MainTree
* Instru -> InstruTree
* LogWriter -> TreeBuilder
* ShutdownHook -> ShutdownHookTree
* LogProcessor -> TreeProcessor

Nous avons aussi cr�er la classe Tree pour construire plus facilement l'arbre voulu.

La classe LogProcessorCount va appeler la M�thode `begin` de la classe TreeBuilder. Cette m�thode va remplir l'arbre globale de la classe pour enregistrer les m�thodes appel�es. La classe LogProcessorCount va appeler la M�thode `end` de la classe TreeBuilder qui sert � remonter l'arbre afin de le re-parcourir lors de l'affichage. A la fin de la proc�dure de spoon, on va appeler la m�thode `getwriter` de TreeBuilder qui va �crire sur le fichier Log.

## Remerciement

Nous avons sollicit� l'aide de Romain Hunault sur certain point. Nous le remercions chaleureusement.

## Lien

[Github du projet](https://github.com/Enalf/projet-tp-instrumentation-Ollivier-Tissier "Github du projet")