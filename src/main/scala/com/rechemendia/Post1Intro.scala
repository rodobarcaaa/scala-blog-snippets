package com.rechemendia

object Post1Intro {

  object HelloWorld {

    /** Como insertamos un comentario en un inicio de un método y que se incluye en el ScalaDoc
      *
      * @param args describimos la entrada de argumentos o otro input value
      */
    def main(args: Array[String]): Unit = {
      //Comentario interno y creación de una variable o valiable
      //como algunos le llamamos a los val inmutables
      val message = "¡Hola, mundo!"
      println(message) //¡Hola, mundo!
    }
  }

  //---------------------------------------------//

  object HelloWorldMini extends App {
    println("¡Hola, mundo!") //¡Hola, mundo!
  }

  //---------------------------------------------//

  object HelloWorldVar extends App {
    // Declaramos una variable mutable vacía de tipo String
    var value: String = ""
    // Le asignamos un valor
    value = "mundo"
    // Imprimimos el valor y acá podemos ver como usar string interpolation
    println(s"¡Hola, $value!") //¡Hola, mundo!
  }

  //---------------------------------------------//

  object HelloWorldIfElse extends App {
    // Podemos ver como vimos antes que si no ponemos tipo scala lo infiere por nosotros
    val language = "en"

    // podemos notar que si no tiene parámetros la función no necesita paréntesis
    def message = {
      if (language == "es") "¡Hola, mundo!"
      else if (language == "pt") "¡Olá, mundo!"
      else "Hello, world!"
    }

    println(message) //Hello, world!
  }

  //---------------------------------------------//

  object HelloWorldPatternMatching extends App {
    val language = "pt"

    //no hay necesidad de colocar break el rompe donde primero encuentra
    val message = language match {
      case "es" => "¡Hola, mundo!"
      case "pt" => "¡Olá, mundo!"
      case _    => "Hello, world!"
    }
    println(message) //¡Olá, mundo!
  }

  //---------------------------------------------//

  object Numbers extends App {
    // Podemos inicializamos una lista y al mismo tiempo le pre-populamos valores
    val numbers = List(1, 2, 3, 4, 5)

    // Recorremos la lista de números y filtramos los pares
    def pairNumbers: List[Int] =
      for {
        number <- numbers
        if number % 2 == 0
      } yield number

    // Imprimimos el resultado separados por ", "
    println(pairNumbers.mkString(", ")) // 2, 4

    //Plus: Ejemplo similar usando filter para los impares
    println(numbers.filter(_ % 2 != 0).mkString(", ")) // 1, 3, 5
  }

  //---------------------------------------------//

  object Functions extends App {
    //Existen dos formas de declarar las funciones

    // Una función básica y conocida acá como callByValue
    def sum(x: Int, y: Int) = x + y
    // Como la invocamos
    sum(1, 2) // 3

    // Otra conocida acá como callByName donde pasamos otra función
    def product(x: => Int, y: => Int) = x * y
    // Como la invocamos
    def two() = 2
    product(two(), 6) // 12

    // U otro un poco más avanzado donde pasamos una función a ejecutar
    // Y además notemos que podemos poner parámetros por default
    def productF(f: Int => Int, x: Int, y: Int = 2) = f(x * y)
    // Como la invocamos
    // Definimos una función val
    val sum5: Int => Int = x => x + 5 // ó simplemente _ + 5
    // Pasamos la misma por parametros
    productF(sum5, 6, 6) // (6 * 6) + 5 = 41
    // Invocamos sin el ultimo valor para que tome 2 po default
    productF(sum5, 5) // (5 * 2) + 5 = 15

    // Podemos pasar como en otros lenguajes un array de parámetros
    def sumAll(x: Int, y: Int, others: Int*) = {
      x + y + others.sum
    }
    // Como la invocamos
    sumAll(1, 2, 3, 4, 5, 6, 7, 8, 9) // 45

    // O podemos implementar el método product con un ejemplo de forma recursiva
    def productRecursive(numbers: List[Int]): Int = numbers match {
      case Nil       => 1                          // Si la lista es vacía retornamos 1
      case x :: tail => x * productRecursive(tail) // Sino multiplicamos
    }
    productRecursive(List(1, 2, 3, 4, 5)) // 120

    //Cerramos con un ejemplo de currying
    def sumCurrying(x: Int)(y: Int): Int = x + y
    // Como la invocamos
    sumCurrying(1)(2) // 3
    // Pero podemos entonces almacenar una suma parcial en una variable
    val sum2 = sumCurrying(2) _
    // y luego invocarla con ese adelanto
    sum2(3) // 5

    //O que el segundo parámetro se inplicito de un contexto particular
    def productCurrying(x: Int)(implicit y: Int): Int = x * y
    // Definimos el implicito
    implicit val number3: Int = 3
    // Ahora consumimos con un solo parámetro y el otro lo encontrará y usará number3
    productCurrying(5) // 15
  }

}
