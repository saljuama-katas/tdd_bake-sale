package bakesale

class Store(inventory: Inventory) {
  def calculatePrice(products: String): Option[Double] = {
    processProducts(products, product => inventory.checkAvailability(product))
  }

  def processPurchase(products: String, amount: Double): Option[Double] = {
    processProducts(products, key => inventory.sellProduct(key))
      .flatMap {
        case x if amount - x >= 0 => Some(amount - x)
        case _ => None
      }
  }

  private def processProducts(products: String, processingFunction: String => Option[Double]) = {
    products
      .replace(" ", "")
      .split(",")
      .map(processingFunction)
      .fold(Some(0.0))((acc, value) => (acc, value) match {
        case (Some(x), Some(y)) => Some(x + y)
        case _ => None
      })
  }

}
