package bakesale

class Store {
  var inventory = Map(
    "B" -> 0.65,
    "M" -> 1.00,
    "C" -> 1.35,
    "W" -> 1.50
  )
  def calculatePrice(products: String): Option[Double] = {
    products
      .replace(" ", "")
      .split(",")
      .map(inventory.get(_))
      .fold(Some(0.0))((acc, value) => (acc, value) match {
        case (Some(x), Some(y)) => Some(x+y)
        case _ => None
      })
  }

}
