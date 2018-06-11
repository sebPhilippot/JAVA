Il est souvent plus fréquent de lire un fichier properties.

Il s'agit d'un fichier de propriétés. Chaque propriété est un fichier Properties est de la forme

clé=valeur ou clé:valeur

> Exemple : 
> 
> nom=PHILIPPOT
>
> prenom=Sébastien

Java propose une classe Properties dont la documentation est [ici](https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html) qui représente l'ensemble des propriétés décrites dans ce fichier.

```
Properties properties = new Properties();
properties.load(new FileInputStream("fichier.properties"));
```

La signature de la méthode load() est la suivante `public void load(InputStream inStream) throws IOException` ce qui signifie qu'il soit propager l'exception IOException, soit placer l'appel à cet méthode dans un bloc try/catch.
De même, la signature du constructeur de la classe FileInputStream est  `public FileInputStream(String name) throws FileNotFoundException`, donc il faudra également propager ou entouré de bloc try/catch l'exception FileNotFoundException

Le code devient : 

```
  Properties properties;
        try {
            properties= new Properties();
            properties.load(new FileInputStream("fichier.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
```
Ce que l'on souhaite faire c'est récupérer la valeur d'une clé spécifique, par exemple le prénom. Pour ce faire, nous allons utiliser la méthode getProperty(String key) qui renvoie la valeur associé a la clé _key_. En fait, la classe Properties hérite de HasMap<Sting,String> c'est donc une collection de type Map qui contient que des String.

Le code devient : 

```
 Properties properties;
        try {
            properties= new Properties();
            properties.load(new FileInputStream("fichier.properties"));
           String prenom=properties.getProperty("prenom");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
```

