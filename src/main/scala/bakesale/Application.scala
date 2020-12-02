package bakesale

import scala.io.StdIn.readLine

object Application extends App {
  val store = new Store()
  while (true) {
    print("Items to Purchase > ")
    val items = readLine()
    val price = store.calculatePrice(items) match {
      case Some(x) => f"$$$x%1.2f"
      case None => "Not enough stock"
    }
    println(s"Total > $price")
  }
}
