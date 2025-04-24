package Anagramas
object Package {


  type Palabra = String
  type Ocurrencias = List[(Char, Int)]
  type Frase = List[String]
  // Datos base
  val diccionario: List[String] = List(
    "roma", "amor", "mora", "ramo", "rojo", "joro", "verde", "ver", "de", "red", "perro", "rep", "ropa", "paro"
  )

  def lOcPal(p: Palabra): Ocurrencias = {
    (for {
      (char, group) <- p.toLowerCase.toList.groupBy(c => c)
    } yield (char, group.length)).toList.sortBy(_._1)
  }

  def lOcFrase(f: Frase): Ocurrencias = {
    lOcPal(f.mkString)
  }

  //lazy val diccionarioPorOcurrencias: Map[Ocurrencias, List[Palabra]] = {
  //  diccionario.groupBy(palabra => lOcPal(palabra))
  //}
  lazy val diccionarioPorOcurrencias: Map[Ocurrencias, List[Palabra]] = {
    diccionario.groupBy(x => lOcPal(x).toSet).map(xy => (xy._1.toList, xy._2))
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

  def complemento(lista: Ocurrencias, subLista: Ocurrencias): Ocurrencias = {
    val mapaSubLista = subLista.toMap
    val resultado = for {
      (char, cantlista) <- lista
      cantSubLista = mapaSubLista.getOrElse(char, 0)
      nuevaCant = cantlista - cantSubLista
      if nuevaCant > 0
    } yield (char, nuevaCant)

    resultado.sortBy(_._1)
  }

  def anagramasDeFrase(frase: Frase): List[Frase] = {
    def aux(ocurrencias: Ocurrencias): List[Frase] = {
      if (ocurrencias.isEmpty) List(Nil)
      else {
        for {
          combinacion <- combinaciones(ocurrencias)
          palabras <- diccionarioPorOcurrencias.getOrElse(combinacion, Nil)
          resto <- aux(complemento(ocurrencias, combinacion))
        } yield palabras :: resto
      }
    }

    aux(lOcFrase(frase))
  }

  /*
  def anagramasDeFrase(frase: Frase): List[Frase] = {
    def auxiliar(oc: Ocurrencias): List[Frase] = {
      if (oc.isEmpty) List(Nil)
      else {
        for {
          combo <- combinaciones(oc)
          if combo.nonEmpty
          palabras <- diccionarioPorOcurrencias.getOrElse(combo, Nil)
          resto <- auxiliar(complemento(oc, combo))
        } yield palabras :: resto
      }
    }

    auxiliar(lOcFrase(frase))
  }*/

}