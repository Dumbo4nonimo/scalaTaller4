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

  def anagramasDePalabra(pal: Palabra): List[Palabra] = {
    diccionarioPorOcurrencias.getOrElse(lOcPal(pal), List())
  }

  def combinaciones(IOcurrencias: Ocurrencias): List[Ocurrencias] = {
    var resultado = List(List.empty[(Char, Int)])
    for ((char, max) <- IOcurrencias) {
      val nuevos = for {
        cantidad <- 1 to max
        combinacion <- resultado
      } yield (char, cantidad) :: combinacion

      resultado = resultado ++ nuevos
    }
    resultado
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
    println("---------------------")
    val entrada = List(('a', 2), ('b', 2))
    val resultado = combinaciones(entrada)
    resultado.foreach(println)
    println("---------------------")
    val entrada2 = List(('m', 1), ('n', 1), ('o', 1))
    val resultado2 = combinaciones(entrada2)
    resultado2.foreach(println)
    println("---------------------")




    //nota: anagramasDePalabra es parecido a lazy pero no te dejes engañar, tienen diferencias


  }


}