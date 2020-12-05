package bakesale

case class Inventory(var products: Seq[Item]) {

  def checkAvailability(product: String, quantity: Int = 1): Option[Double] = products
    .find(_.identifier == product)
    .filter(_.stock >= quantity)
    .map(_.price * quantity)

  def sellProduct(product: String, quantity: Int = 1): Option[Double] = {
    val price = checkAvailability(product, quantity)
    products = products.map(item => {
      val updatedStock = if (item.identifier == product) item.stock - quantity else item.stock
      item.copy(stock = updatedStock)
    })
    price
  }

}

case class Item(identifier: String, price: Double, stock: Int)