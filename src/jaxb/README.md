La documentation pour ce genre d'opération est [ici](https://docs.oracle.com/javase/tutorial/jaxb/intro/)

Enregistrer l'état d'un objet s'appelle la  **sérialisation**, c'est possible de le faire en plusieurs format dont l'XML. C'est ce dernier qui est traité ici à l'aide d'un outil fournit par le JDK nommé JAXB.

Cette librairie permet de générer des classes depuis un fichier XML, enregistrer l'état d'un objet dans un fichier XML ou bien récupérer l'état depuis un fichier XML

La première étape consiste à créer un contexte d'exécution de JAXB.Ce contexte permet d'avoir un package dans lequel les classes seront générées et les objets manipulés (sauvegarde et récupération de leurs états).

voici le code permettant de créer un contexte JAXB.

JAXBContext.newInstance( "com.acme.foo:com.acme.bar" ) la documentation pour cette méthode est [ici](https://docs.oracle.com/javaee/5/api/javax/xml/bind/JAXBContext.html#newInstance), Cette méthode renvoie un objet de type JAXBContext.

Voici le code pour créer un contexte JAXB

`JAXBContext jaxb = JAXBContext.newInstance("fr.formation.cookbook");`

La méthode newInstance propage l'exception JAXBException. le code devient donc : 

```
  try {
      JAXBContext jaxb = JAXBContext.newInstance("fr.formation.cookbook");
  } catch (JAXBException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
  }
```

## Sérialisation d'un objet dans un fichier XML

JAVA nous donne une classe Marchaller, qui fournit la capacité de transformer un objet JAVA en fichier XML.
Un objet de type Marshaller est créé depuis le contexte JaxB.

Le code permettant de créer un objet Marshaller est le suivant : 

` Marshaller m=jaxb.createMarshaller();`

Ensuite, il suffit d'appeler la méthode marshall() de la classe Marshaller.

La documentation [[ici]](https://docs.oracle.com/javaee/5/api/javax/xml/bind/Marshaller.html) dit : 
> The first parameter of the overloaded Marshaller.marshal(java.lang.Object, ...) methods must be a JAXB element as computed by JAXBIntrospector.isElement(java.lang.Object); otherwise, a Marshaller.marshal method must throw a MarshalException. There exist two mechanisms to enable marshalling an instance that is not a JAXB element. One method is to wrap the instance as a value of a JAXBElement, and pass the wrapper element as the first parameter to a Marshaller.marshal method. For java to schema binding, it is also possible to simply annotate the instance's class with @XmlRootElement.

Ce qui signifie que lorsque l'on va faire `m.marshal(notreObjet,...)` notreObjet doit être un élément JAXB. Pour rendre un objet élément de JAXB nous avons deux solutions : 
* Annoter la classe de _notreObjet_ par @XmlRootElement
* Englober _notreObjet_ dans un JAXBElement

Nous avons seulement vu le premier point, une classe annotée.

Le second paramètre est la sortie fichier XML ou console. Dans le cas d'un fichier XML le code est le suivant : 

`m.marshal(notreObjet,new FileOutputStream("example.xml"));`

L'appel du constructeur new FileOutputStream("example.xml") nous oblige à se préoccuper de l'exception FileNotFoundException en ajoutant la clause catch par exemple.

Le code complet est : 

```
    try{
             JAXBContext jaxb = JAXBContext.newInstance("fr.formation.cookbook.model");
             FOO f = new FOO();
             f.setFoo("TEST");
             FileOutputStream out=new FileOutputStream("foo.xml");
             Marshaller m=jaxb.createMarshaller();
             m.marshal(f, out);
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
```
Lire dans un fichier XML

La récupération de l'état d'un objet s'appelle la **désérialisation**. Elle suit extament le même principe. Ce n'est plus un FIleOutputStream mais un FIleINputSTream et on n'utilise plus une objet de type Marshaller mais un objet de type Unmarchaller.

Ce qui donne le code suivant : 

```
 try {
            JAXBContext jaxb = JAXBContext.newInstance(FOO.class);
            
            FileInputStream out=new FileInputStream("foo.xml");
            Unmarshaller m=jaxb.createUnmarshaller();
             FOO f=(FOO) m.unmarshal(out);
             
             System.out.println("f = "+f.getFoo());
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
```

