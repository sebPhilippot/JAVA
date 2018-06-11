La classe InputStream sert de base à toutes les classes qui souhaitent lire des flux binaires. La classe 

Pour écrire ou lire des des données binaires dans une fichier, nous utilisons respectivement les classes FileOutPutStream ( [doc](https://docs.oracle.com/javase/7/docs/api/java/io/FileOutputStream.html)) et FileInputStream ([doc](https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html)).

## Lire dans un fichier
FileInputStream hérite de InputStream et est dédiée à la lecture des fichiers de données binaires. Toutefois, cette classe est assez rudimentaire et contient des méthodes de lectures basiques.
Il existe une autre classe avec des méthodes de lectures plus évoluées qui n'est pas spécifique aux fichiers mais pour tout type de flux binaire. Cette classe à un constructeur qui prend un objet de type FileInputStream en paramètre.

`FileInputStream fis=new FileInputStream("fichier.dat");`

Ici on attache un fichier à un l'objet _fis_

`DataInputStream dis=new DataInputStream(fis);`

Ici, on attache l'objet _dis_ au fichier par l'intermédiaire de _fis_. On bénéficie alors des méthodes de lectures plus évoluées de DataInputStream pour parcourir le fichier binaire.

> Si on souhaite écrire dans un fichier à l'aide d'un buffer il faut passer par une classe BufferedInputStream. Cette classe prend un FileInputStream en paramètre.C'est ce buffer qui sera passé en paramètre du DataInputStream
`new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));`

L'objet _dis_ a maintenant la capacité de parcourir le fichier jusqu'à la fin. Cette fin est déterminée par la levée d'une exception EOFException. 

```
boolean isEOF=false;
while(!isEOF){
  try{
      //Lecture du fichier   
  }catch(EOFExcetpion eofex){
      isEOF=true;
   }
}
```

Dans le code ci-dessus on déclare une variable a false, tant que cette variable est fausse on essaye de lire le fichier, lorsque on sera à la fin du fichier il y aura le déclenchement de l'exception et la variable deviendra vraie, la boucle s'arrêtera et on aura finit de lire le fichier.
La lecture se fait avec les méthodes suivantes : 

| boolean | readBoolean() | Reads one input byte and returns true if that byte is nonzero, false if that byte is zero.
 |
| byte | readByte() | Reads and returns one input byte. |
| char | readChar()| Reads two input bytes and returns a char value. |
| double | readDouble()|Reads eight input bytes and returns a double value. |
| float | readFloat()| Reads four input bytes and returns a float value. |
| void | readFully(byte[] b) | Reads some bytes from an input stream and stores them into the buffer array b. |
| void | readFully(byte[] b, int off, int len)| Reads len bytes from an input stream. |
| int | readInt() |Reads four input bytes and returns an int value. |
| long | readLong() |Reads eight input bytes and returns a long value. |
| short | readShort() | Reads two input bytes and returns a short value.|
| int | readUnsignedByte() | Reads one input byte, zero-extends it to type int, and returns the result, which is therefore in the range 0 through 255. |
| int | readUnsignedShort() | Reads two input bytes and returns an int value in the range 0 through 65535.|
| String | readUTF() | Reads in a string that has been encoded using a modified UTF-8 format.|
| int | skipBytes(int n) |Makes an attempt to skip over n bytes of data from the input stream, discarding the skipped bytes. |
 
```
boolean isEOF=false;
int unit;
String desc;
while(!isEOF){
  try{
         unit = dis.readInt();
        desc = dis.readUTF(); 
        System.out.println("unit : "+unit+" desc : "+desc);
 }catch(EOFExcetpion eofex){
      isEOF=true;
   }
}
```

## Ecrire dans un fichier

Pour  réaliser ce type d'écriture, nous utilisons la classe FileWriter. la documentation se trouve [ici](https://docs.oracle.com/javase/10/docs/api/java/io/FileOutputStream.html)

La première étape est de créer une instance de cette classe : 

  `FileOutputStream fw=new FileOutputStream("fichier.txt");`

Cette classe a un constructeur déclaré comme ceci : 

`public FileOutputStream​(String name) throws FileNotFoundException`
Ce qui oblige d'ajouter un block try/catch dans l'instanciation de cette classe. En effet, lorsque nous faisons `new FileOutputStream("copy.png");` nous faisons appel au constructeur ci-dessus.

le code devient donc 

```
try{
FileOutputStream fos=new FileOutputStream("copy.png");
}
catch(IOExceptions ioex){
  ioex.printStacktrace();
}
```
La seconde étape consiste à insérer un tableau d'octet dans  cet objet. Ceci est rendu possible par l'appel à la méthode _write_ 

Exemple :  récupération des données binaires d'une image
```
  File f =new File("java.png");
  byte[] arrayByte = Files.readAllBytes(f.toPath());
```
Avec la méthode write sur l'objet arrayByte on fait une copie de l'image _java.png_
`fos.write(arrayByte);`

le code devient donc : 
```
FileOutputStream fos=null;
try{
 File f =new File("java.png");
  byte[] array = Files.readAllBytes(f.toPath());
  fos=new FileOutputStream("copy.png");
  fos.write(arrayByte);
}
catch(IOExceptions ioex){
  ioex.printStacktrace();
}finally{
  try {
    fos.close();
   } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
   }
```
