package bakesale

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StoreTest extends AnyWordSpec with Matchers {

  "The Store" when {
    val store = new Store()

    "calculating the price of products" can {
      "return the price for an existing product" in {
        val price = store.calculatePrice("B")
        price mustBe Some(0.65)
      }
      "return the price for a comma separated list of existing products" in {
        val price = store.calculatePrice("B, C, W")
        price mustBe Some(3.50)
      }
      "return nothing when the product does not exist" in {
        val price = store.calculatePrice("Inexistent")
        price mustBe None
      }
    }
  }

}
