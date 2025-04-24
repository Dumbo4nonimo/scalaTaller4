import Anagramas.Package._

// Casos para lOcPal
lOcPal("mora")           // List(('a',1), ('m',1), ('o',1), ('r',1))
lOcPal("reverdecer")     // List(('c',1), ('d',1), ('e',4), ('r',3), ('v',1))
lOcPal("Scala")          // List(('a',2), ('c',1), ('l',1), ('s',1))
lOcPal("zzZZ")           // List(('z',4))
lOcPal("Camion")         // List(('a',1), ('c',1), ('i',1), ('m',1), ('n',1), ('o',1))

// Casos para lOcFrase
lOcFrase(List("verde", "rojo"))  // List(('d',1), ('e',2), ('j',1), ('o',2), ('r',2), ('v',1))
lOcFrase(List("ver", "de"))      // List(('d',1), ('e',2), ('r',1), ('v',1))
lOcFrase(List("perro", "rep"))   // List(('e',2), ('o',1), ('p',2), ('r',3))
lOcFrase(List("camion"))         // List(('a',1), ('c',1), ('i',1), ('m',1), ('n',1), ('o',1))
lOcFrase(List("red", "de"))      // List(('d',2), ('e',2), ('r',1))

// Casos para diccionarioPorOcurrencias
diccionarioPorOcurrencias.getOrElse(List(('a',1), ('m',1), ('o',1), ('r',1)), List())   // List("roma", "amor", "ramo", "mora")
diccionarioPorOcurrencias.getOrElse(List(('j',1), ('o',2), ('r',1)), List())           // List("rojo", "joro")
diccionarioPorOcurrencias.getOrElse(List(('d',1), ('e',1), ('r',1)), List())           // List("red")
diccionarioPorOcurrencias.getOrElse(List(('o',1), ('p',1), ('r',1), ('a',1)), List())   // List("ropa", "paro")
diccionarioPorOcurrencias.getOrElse(List(('e',1), ('p',1), ('r',2), ('o',1)), List())   // List("perro")

// Casos para anagramaDePalabra
anagramasDePalabra("roma")    // List("amor", "mora", "ramo", "roma")
anagramasDePalabra("rojo")    // List("joro", "rojo")
anagramasDePalabra("ropa")    // List("paro", "ropa")
anagramasDePalabra("verde")   // List("verde")
anagramasDePalabra("de")      // List("de")

// Casos para complemento
complemento(List(('a',2), ('b',2)), List(('a',1), ('b',1)))  // List(('a',1), ('b',1))
complemento(List(('a',2), ('b',2)), List(('a',2)))           // List(('b',2))
complemento(List(('a',2), ('b',2)), List(('b',2)))           // List(('a',2))
complemento(List(('a',2), ('b',2)), List())                 // List(('a',2), ('b',2))
complemento(List(('a',2), ('b',2)), List(('a',2), ('b',2)))  // List()

// Casos para combinaciones
combinaciones(List(('a',1)))  // List(List(), List(('a',1)))
combinaciones(List(('a',2)))  // List(List(), List(('a',1)), List(('a',2)))
combinaciones(List(('a',1), ('b',1)))  // List(List(), List(('a',1)), List(('b',1)), List(('a',1), ('b',1)))
combinaciones(List(('x',2), ('y',1)))  // Todas combinaciones con x1, x2, y
combinaciones(List())  // List(List())

// Casos para anagramasDeFrase
anagramasDeFrase(List("verde", "rojo"))  // Variaciones que formen las mismas ocurrencias (según diccionario)
anagramasDeFrase(List("ropa", "ver"))    // Ej: List(List("ropa", "ver"), List("paro", "ver"))
anagramasDeFrase(List("de", "rep"))      // List(List("de", "rep"), ...)
anagramasDeFrase(List("perro"))          // List(List("perro"))
anagramasDeFrase(List("ver", "rojo", "de")) // List of frases anagramas válidas según el diccionario




