# TP Instrumentation d'Eflamm Ollivier et Bertrand Tissier

## Utilisation

### Exercice 1

Commencer par utiliser la commande suivante:

```shell
$ mvn package
```

à la racine du projet pour compiler le code.

Ensuite il faut faire la commande:

pour Windows :

```bash
$ java -cp ./target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainCount src/main/resources/example src/main/resources/example-instrumented
```
pour Linux:

```shell
$ java -cp .:target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainCount src/main/resources/example src/main/resources/example-instrumented
```
Ensuite aller au répertoire `src\main\resources\example-instrumented` du projet:
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

Cela génère un fichier Log dans le répertoire: `src\main\resources\example-instrumented`

### Explication

Nous avons doublé les classes:
* Main -> MainCount
* Instru -> InstruCount
* LogWriter -> CountMethodCall
* ShutdownHook -> ShutdownHookCount
* LogProcessor -> CountProcessor

La classe LogProcessorCount va appeler la Méthode `out` de la classe CountMethodCall. Cette méthode va remplir le HashMap de la classe pour enregistrer les méthodes appelées et compter le nombre d'appel. A la fin de la procédure de spoon, on va appeler la méthode `getwriter` de CountMethodCall qui va écrire sur le fichier Log.

### Exercice 2

Commencer par utiliser la commande suivante:

```shell
$ mvn package
```

à la racine du projet pour compiler le code.

Ensuite il faut faire la commande:

pour Windows :

```bash
$ java -cp ./target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainTree src/main/resources/example src/main/resources/example-instrumented
```
pour Linux:

```shell
$ java -cp .:target/tp-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar vv.spoon.MainTree src/main/resources/example src/main/resources/example-instrumented
```
Ensuite aller au répertoire `src\main\resources\example-instrumented` du projet:
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

Cela génère un fichier Log dans le répertoire: `src\main\resources\example-instrumented`

### Explication

Nous avons doublé les classes:
* Main -> MainTree
* Instru -> InstruTree
* LogWriter -> TreeBuilder
* ShutdownHook -> ShutdownHookTree
* LogProcessor -> TreeProcessor

Nous avons aussi créer la classe Tree pour construire plus facilement l'arbre voulu.

La classe LogProcessorCount va appeler la Méthode `begin` de la classe TreeBuilder. Cette méthode va remplir l'arbre globale de la classe pour enregistrer les méthodes appelées. La classe LogProcessorCount va appeler la Méthode `end` de la classe TreeBuilder qui sert à remonter l'arbre afin de le re-parcourir lors de l'affichage. A la fin de la procédure de spoon, on va appeler la méthode `getwriter` de TreeBuilder qui va écrire sur le fichier Log.

## Remerciement

Nous avons sollicité l'aide de Romain Hunault sur certain point. Nous le remercions chaleureusement.

## Lien

[Github du projet](https://github.com/Enalf/projet-tp-instrumentation-Ollivier-Tissier "Github du projet")