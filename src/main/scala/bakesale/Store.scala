package bakesale

class Store(inventory: Inventory) {

  def calculatePrice(products: String): Option[Double] = {
    products
      .replace(" ", "")
      .split(",")
      .map(key => inventory.checkAvailability(key))
      .fold(Some(0.0))((acc, value) => (acc, value) match {
        case (Some(x), Some(y)) => Some(x+y)
        case _ => None
      })
  }

}
