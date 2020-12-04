package bakesale

case class Inventory(products: Map[String, (Double, Int)]) {

  def checkAvailability(product: String, quantity: Int = 1): Option[Double] = products
    .get(product)
    .filter(priceAndStock => priceAndStock._2 >= quantity)
    .map(_._1 * quantity)

  def sellProduct(product: String, quantity: Int = 1): Option[Double] = ???

}
