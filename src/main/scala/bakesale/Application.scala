package bakesale

import scala.io.StdIn.readLine

object Application extends App {
  val inventory = Inventory(Map(
    "B" -> (0.65, 48),
    "M" -> (1.00, 36),
    "C" -> (1.35, 24),
    "W" -> (1.50, 30)
  ))
  val store = new Store(inventory)
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
