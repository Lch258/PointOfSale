package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{Item, Sale, SalesTax}

class Task3 extends FunSuite{
  test("with clear") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem2: Item = new Item("big", 10.0)
    checkout.addItemToStore("072", testItem2)
    assert(checkout.displayString() == "")
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    assert(checkout.displayString() == "")
    checkout.enterPressed()
    assert(checkout.displayString() == "")
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    assert(checkout.displayString() == "072")
    checkout.clearPressed()
    assert(checkout.displayString() == "")
    checkout.enterPressed()
    val cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 3)
    assert(cart.head.description() == "big")
    assert(Math.abs(cart.head.price() - 10.0) < 0.0001)
    assert(cart(1).description() == "big")
    assert(Math.abs(cart(1).price() - 10.0) < 0.0001)
    assert(cart(2).description() == "error")
    assert(Math.abs(cart(2).price() - 0.0) < 0.0001)
  }
  test("not with clear") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem2: Item = new Item("big", 10.0)
    checkout.addItemToStore("072", testItem2)
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.enterPressed()
    var cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 3)
    assert(cart.head.description() == "big")
    assert(Math.abs(cart.head.price() - 10.0) < 0.0001)
    assert(cart(1).description() == "big")
    assert(Math.abs(cart(1).price() - 10.0) < 0.0001)
    assert(cart(2).description() == "error")
    assert(Math.abs(cart(2).price() - 0.0) < 0.0001)
    ////cash ceshi
    checkout.cashPressed()
    assert(cart.size == 3)
    checkout.checkoutPressed()
    assert(checkout.displayString()=="cash or credit") //display cash or credit
    checkout.numberPressed(0)
    checkout.numberPressed(7)

    checkout.numberPressed(2)
    checkout.enterPressed()
    assert(cart.size == 3)
    checkout.cashPressed()
    assert(checkout.itemsInCart().size == 0)//clear the cart
    checkout.enterPressed()


  }
  test ("does_not_display_cash_or_credit_on_checkout"){

  }
}
