package Anagramas
object Package {


  type Palabra = String
  type Ocurrencias = List[(Char, Int)]
  type Frase = List[String]
  val diccionario = List("roma", "amor", "ramo", "mora", "aroma", "bar")

  def lOcPal(p: Palabra): Ocurrencias = {
    (for {
      (char, group) <- p.toLowerCase.toList.groupBy(c => c)
    } yield (char, group.length)).toList.sortBy(_._1)
  }

  def lOcFrase(f: Frase): Ocurrencias = {
    lOcPal(f.mkString)
  }

  lazy val diccionarioPorOcurrencias: Map[Ocurrencias, List[Palabra]] = {
    diccionario.groupBy(palabra => lOcPal(palabra))
  }

  def anagramasDePalabra(palabra: Palabra): List[Palabra] = {
    diccionarioPorOcurrencias.getOrElse(lOcPal(palabra), List())
  }


  def main(args: Array[String]): Unit = {
    println(lOcPal("penetreitor")) //Se espera: List(('a',2), ('c',1), ('l',1), ('s',1))
    println("---------------------")
    println(lOcFrase(List("Hola", "Scala"))) //Junta todas las palabras y las vuelve ocurrencias
    println("---------------------")
    val ocs1 = lOcPal("amor")
    println(diccionarioPorOcurrencias(ocs1)) //No busca con palabras sino con ocurrencias
    println("---------------------")
    println(anagramasDePalabra("aroma")) //porque cada palabra es anagrama de si mismo
    println(anagramasDePalabra("concha")) //ni siquiera debe aparecer entoces es vacio
    print(anagramasDePalabra("amor")) //aqui si aparecen varios

    //nota: anagramasDePalabra es parecido a lazy pero no te dejes engañar, tienen diferencias


  }


}