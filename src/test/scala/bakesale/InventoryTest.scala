package bakesale

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class InventoryTest extends AnyWordSpec with Matchers {

  "The Inventory" when {
    val inventory = Inventory(Map("C" -> (1.50, 2)))

    "checking the availability of a product" must {
      "return nothing" when {
        "the product does not exist" in {
          val price = inventory.checkAvailability("X")
          price mustBe None
        }
        "the product does not have enough stock" in {
          val price = inventory.checkAvailability("C", 3)
          price mustBe None
        }
      }
      "return the total price of the products" when {
        "quantity is not specified, defaults to unitary price" in {
          val price = inventory.checkAvailability("C")
          price mustBe Some(1.50)
        }
        "the product has enough stock" in {
          val price = inventory.checkAvailability("C", 2)
          price mustBe Some(3.00)
        }
      }
    }
  }
}
