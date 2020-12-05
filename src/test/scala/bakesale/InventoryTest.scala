package bakesale

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class InventoryTest extends AnyWordSpec with Matchers {

  trait Test {
    val inventory = Inventory(Seq(Item("C", 1.50, 2)))
  }

  "The Inventory" when {
    "checking the availability of a product" must {
      "return nothing" when {
        "the product does not exist" in new Test {
          private val price = inventory.checkAvailability("X")
          price mustBe None
        }
        "the product does not have enough stock" in new Test {
          private val price = inventory.checkAvailability("C", 3)
          price mustBe None
        }
      }
      "return the total price of the products" when {
        "quantity is not specified, defaults to unitary price" in new Test {
          private val price = inventory.checkAvailability("C")
          price mustBe Some(1.50)
        }
        "the product has enough stock" in new Test {
          private val price = inventory.checkAvailability("C", 2)
          price mustBe Some(3.00)
        }
      }
    }
    "selling a product" must {
      "return nothing" when {
        "the product does not exist" in new Test {
          private val price = inventory.sellProduct("X")
          price mustBe None
        }
        "the product does not have enough stock" in new Test {
          private val price = inventory.sellProduct("C", 3)
          price mustBe None
        }
      }
      "return the total price and remove the stock amount" when {
        "the product has enough stock" in new Test {
          private val price = inventory.sellProduct("C", 2)
          price mustBe Some(3.00)
          inventory.checkAvailability("C", 1) mustBe None
        }
      }
    }
  }
}
