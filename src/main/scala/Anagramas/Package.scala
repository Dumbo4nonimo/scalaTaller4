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


  lazy val diccionarioPorOcurrencias: Map[Ocurrencias, List[Palabra]] = {
    diccionario.groupBy(lOcPal).withDefaultValue(List())
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
    val ocurrencias = lOcFrase(frase)

    def generarAnagramas(oc: Ocurrencias): List[Frase] = {
      if (oc.isEmpty) List(Nil)
      else {
        for {
          combo <- combinaciones(oc)
          if combo.nonEmpty
          palabra <- diccionarioPorOcurrencias(combo.sortBy(_._1))
          resto <- generarAnagramas(complemento(oc, combo).sortBy(_._1))
        } yield palabra :: resto
      }
    }

    generarAnagramas(ocurrencias).distinct
  }

}

