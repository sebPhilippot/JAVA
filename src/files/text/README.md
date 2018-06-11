La documentation générale pour les entrées/Sorties est [ici](https://docs.oracle.com/javase/tutorial/essential/io/streams.html)

## Ecriture dans un fichier texte

Pour  réaliser ce type d'écriture, nous utilisons la classe FileWriter. la documentation se trouve [ici](https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html)

La première étape est de créer une instance de cette classe : 

  `FileWriter fw=new FileWriter("fichier.txt");`

Cette classe a un constructeur déclaré comme ceci : 

`public FileWriter(String string) throws IOException`
Ce qui oblige d'ajouter un block try/catch dans l'instanciation de cette classe. En effet, lorsque nous faisons `new FileWriter("fichier.txt");` nous faisons appel au constructeur ci-dessus.

le code devient donc 

```
try{
FileWriter fw=new FileWriter("fichier.txt");
}
catch(IOExceptions ioex){
  ioex.printStacktrace();
}
```
La seconde étape consiste à insérer des caractères dans  cet objet : 

`fw.write('a');`

le code devient donc : 
```
try{
FileWriter fw=new FileWriter("fichier.txt");
fw.write('a');
}
catch(IOExceptions ioex){
  ioex.printStacktrace();
}
```

La troisième étape consiste à libérer la ressource.
La documentation ([ici](https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html)) insiste bien sur l'importance de libérer les ressources. Elle préconise de le faire dans un bloc Finally à l'aide de la méthode close(). La classe FileWriter hérite de OutputStreamWriter  qui hérite à son tour de Writer. On peut voir qu'elle contient peu de méthodes, principalement des méthodes append(...), write(...) et une autre méthode close().
Les deux premières concernent le contenu du fichier elles sont très similaires, d'ailleurs la documentation de la méthode append(char c) dit : 
> An invocation of this method of the form out.append(c) behaves in exactly the same way as the invocation out.write(c) 

La dernière méthode concerne le flux lui même. 
La méthode close libère le fichier texte; Ainsi un autre processus peut lire ou écrire dans ce fichier.

Remarques : De manière générale la classe Writer sert de base a toutes les classes relatives à un flux de type texte. Sa documentation est [ici](https://docs.oracle.com/javase/7/docs/api/java/io/Writer.html#close())

La code complet devient 

```
FileWriter fw
try{
  fw=new FileWriter("fichier.txt");
  fw.write('a');
}
catch(IOExceptions ioex){
  ioex.printStacktrace();
}finally {
   fw.close();
}
```
Cette classe écrit directement dans le fichier, **ce qui n'est pas très performant**.

Pour pallier ce problème Java propose la classe BufferedWriter qui propose d'utiliser une zone mémoire (buffer) d'une certaine taille afin d'y stocker les caractères en cours d'écriture. Lorsque ce buffer est plein, JAVA fait appel à une méthode flush() de la classe Writer. Cette dernière permet de vider cette zone et demande au système d'exploitation d'écrire ces données dans le fichier, mais l'appel à cette méthode ne garantit pas que les données sont correctement écrites physiquement dans le fichier.
Il est possible d'appeler cette méthode dans le programme lorsque l'on souhaite écrire nos données à un moment du programme spécifique.
La méthode close() fait appel à la méthode flush()

Le code suivant permet d'écrire un fichier texte par l'intermédiaire d'un buffer.

```
BufferedWriter bw=null;
        try {
          FileWriter fw=new FileWriter("test.txt");
            bw=new BufferedWriter(fw);
            bw.write("Bonjour");
            bw.newLine();
            bw.write("Salut");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                bw.close();
                fw.close()
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
```

# Lecture dans un fichier texte caractère par caractère
Pour  réaliser ce type de lecture, nous utilisons la classe FileReader. la documentation se trouve [ici](https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html)

La première étape est de créer une instance de cette classe : 

  `FileReader fr=new FileReader("fichier.txt");`

Les étapes suivantes sont identiques à la classe FileWriter.
Le code final est le suivant : 


```
 FileReader fr=new FileReader("fichier.txt");
 int c=fr.read();
            
 while(c!=-1){
    System.out.println((char) c);
    c=fr.read();
 }finally {
    fr.close();
}
```
