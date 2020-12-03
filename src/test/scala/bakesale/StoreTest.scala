package bakesale

import org.scalamock.scalatest.MockFactory
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StoreTest extends AnyWordSpec with Matchers with MockFactory with BeforeAndAfterEach {

  var store: Store = _
  var inventory: Inventory = _

  override def beforeEach() {
    inventory = mock[Inventory]
    store = new Store(inventory)
  }

  "The Store" when {

    "calculating the price of products" can {
      "return the price for products when they all have stock" in {
        inAnyOrder {
          (inventory.checkAvailability _).expects("B", 1).returning(Some(0.65))
          (inventory.checkAvailability _).expects("C", 1).returning(Some(1.35))
          (inventory.checkAvailability _).expects("W", 1).returning(Some(1.50))
        }
        val price = store.calculatePrice("B, C, W")
        price mustBe Some(3.50)
      }
      "return nothing when for at least one product there is no stock" in {
        inAnyOrder {
          (inventory.checkAvailability _).expects("B", 1).returning(Some(0.65))
          (inventory.checkAvailability _).expects("C", 1).returning(None)
        }
        val price = store.calculatePrice("B, C")
        price mustBe None
      }
    }
  }

}
