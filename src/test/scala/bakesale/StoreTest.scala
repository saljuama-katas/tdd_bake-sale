package bakesale

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StoreTest extends AnyWordSpec with Matchers with MockFactory {
  trait StoreSetup {
    val inventory: Inventory = mock[Inventory]
    val store: Store = new Store(inventory)
  }

  "The Store" when {
    "calculating prices" must {
      "return the price for products when they all have stock" in new StoreSetup {
        (inventory.checkAvailability _).expects("B", 1).returning(Some(0.65))
        (inventory.checkAvailability _).expects("C", 1).returning(Some(1.35))
        (inventory.checkAvailability _).expects("W", 1).returning(Some(1.50))

        private val price = store.calculatePrice("B, C, W")
        price mustBe Some(3.50)
      }
      "return nothing when for at least one product there is no stock" in new StoreSetup {
        (inventory.checkAvailability _).expects("B", 1).returning(Some(0.65))
        (inventory.checkAvailability _).expects("F", 1).returning(None)

        private val price = store.calculatePrice("B, F")
        price mustBe None
      }
    }
    "processing a purchase" must {
      trait SellingProducts extends StoreSetup {
        (inventory.sellProduct _).expects("B", 1).returning(Some(0.65))
        (inventory.sellProduct _).expects("C", 1).returning(Some(1.35))
        (inventory.sellProduct _).expects("W", 1).returning(Some(1.50))
      }
      "return the change when the amount exceeds the total price" in new SellingProducts {
        private val change = store.processPurchase("B, C, W", 5.00)
        change mustBe Some(1.50)
      }
      "return the 0 change when the amount equals the total price" in new SellingProducts {
        private val change = store.processPurchase("B, C, W", 3.50)
        change mustBe Some(0.00)
      }
      "returns nothing when the amount is insufficient" in new SellingProducts {
        private val change = store.processPurchase("B, C, W", 2.50)
        change mustBe None
      }
    }
  }
}
