package bakesale

import scala.io.StdIn.readLine

object Application extends App {
  val inventory = Inventory(Seq(
    Item("B", 0.65, 48),
    Item("M", 1.00, 36),
    Item("C", 1.35, 24),
    Item("W", 1.50, 30)
  ))
  val store = new Store(inventory)
  while (true) {
    println("-------------------------")
    print("Items to Purchase > ")
    val items = readLine()
    val price = store.calculatePrice(items) match {
      case Some(x) => f"$$$x%1.2f"
      case None => "Not enough stock"
    }
    println(s"Total > $price")
    if (price.nonEmpty) {
      print("Amount paid > ")
      val amount = readLine().replace("$", "").toDouble
      val change = store.processPurchase(items, amount) match {
        case Some(x) => f"$$$x%1.2f"
        case None => "Not enough money"
      }
      println(s"Change > $change")
    }
  }
}
