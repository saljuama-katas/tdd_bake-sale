package bakesale

case class Inventory(products: Map[String, (Double, Int)]) {

  def checkAvailability(key: String, quantity: Int = 1): Option[Double] = products
    .get(key)
    .filter(product => product._2 >= quantity)
    .map(_._1 * quantity)
}
