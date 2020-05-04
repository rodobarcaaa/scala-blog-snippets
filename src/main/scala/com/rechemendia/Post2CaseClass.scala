package com.rechemendia

object Post2CaseClass {

  class MyFirstClass

  val myFirstClass = new MyFirstClass

  //---------------------------------------------//

  class Vehicle(
      var passengers: Int, //número de pasajeros
      var speed: Int,      //velocidad
      val unit: String     //unidad de velocidad
  ) {
    override def toString: String = s"(passengers: $passengers, speed: $speed $unit)"
  }

  val bicycle = new Vehicle(1, 30, "km")
  bicycle.passengers // 1
  bicycle.passengers = 2
  bicycle.passengers // 2
  println(bicycle)   // (passengers: 2, speed: 30 km)

  //---------------------------------------------//

  class Vehicle1(
      var passengers: Int,            //número de pasajeros
      private val speed: Int,         //ó simplemente speed: Int
      private val unit: String = "km" //ó simplemente val unit: String
  ) {
    val speedDescription: String  = s"$speed $unit"
    override def toString: String = s"(passengers: $passengers, speed: $speedDescription)"
  }

  object Vehicle1 {
    def speed220km(passengers: Int): Vehicle1 = new Vehicle1(passengers, 220, "km")
  }

  val motorcycle = new Vehicle1(2, 100)
  motorcycle.passengers       // 2
  motorcycle.speed            // no compila -> error: reassignment to val
  motorcycle.speedDescription // 100 km
  println(motorcycle)         // (passengers: 2, speed: 100 km)

  val car: Vehicle1 = Vehicle1.speed220km(5)
  println(car) // (passengers: 5, speed: 220 km)

  //---------------------------------------------//

  case class Vehicle2(passengers: Int, speed: Int, unit: String) {
    val speedDescription: String = s"$speed $unit"
  }

  // Multiples formas de construir el objeto
  val myCar  = Vehicle2(5, 200, "km")          // Constructor normal
  val myCar1 = Vehicle2.apply(5, 200, "km")    // Usando apply explícitamente
  val myCar2 = Vehicle2.tupled((5, 200, "km")) // Mediante tupla de valores
  val myCar3 = Vehicle2.curried(5)(200)("km")  // Con parámetros modo currying

  // Usando métodos get predeterminados
  myCar.passengers // 5
  myCar.speed      // 200
  myCar.unit       // km

  // Usando el toString predeterminado
  println(myCar) // Vehicle2(5,200,km)

  //Copiando my Car pero con cambio de velocidad
  val myFastCar = myCar.copy(passengers = 2, speed = 320)
  println(myFastCar) // Vehicle2(2,320,km)

  // Comparando por estructura y no por referencia
  myCar == myCar1    // true
  myCar == myCar2    // true
  myCar == myCar3    // true
  myCar == myFastCar // false

  // Usando el unapply en expresiones match
  def recognizeVehicle(x: Vehicle2): String = x match {
    case Vehicle2(10, speed, unit) =>
      s"Minivan de 10 pasajeros con velocidad de $speed $unit"
    case Vehicle2(2, speed, _) if speed > 300 =>
      s"Auto deportivo de alta velocidad ${x.speedDescription}"
    case _ =>
      "Cualquier auto no minivan, ni deportivo:" + x
  }

  val minivan = Vehicle2(10, 100, "km")
  println(recognizeVehicle(minivan))   // Minivan de 10 pasajeros con velocidad de 100 km
  println(recognizeVehicle(myFastCar)) // Auto deportivo de alta velocidad 320 km
  println(recognizeVehicle(myCar))     // Cualquier auto no minivan, ni deportivo:Vehicle2(5,200,km)

  //--------------------------------------------------//

  sealed trait Animal {
    def name: String
  }

  case class Dog(name: String, owner: String) extends Animal
  case class Cat(name: String, color: String) extends Animal

  def recognizeAnimal(a: Animal): String = a match {
    case Dog(name, owner) => s"El perro $name es de $owner."
    case Cat(name, color) => s"${name.capitalize} es un gato $color muy hermoso."
  }

  val bony = Dog("Bony", "Pedrito")
  val tom  = Cat("tom", "negro")

  println(recognizeAnimal(bony)) // El perro Bony es de Pedrito.
  println(recognizeAnimal(tom))  // Tom es un gato negro muy hermoso.

}
