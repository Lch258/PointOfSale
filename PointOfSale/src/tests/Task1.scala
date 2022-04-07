package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.Item

class Task1 extends FunSuite {

  test("always_add_same_item") {
    //    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    //
    //    var testItem: Item = new Item("test item", 100.0)
    //
    //    testSelfCheckout.addItemToStore("123", testItem)
    // TODO
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472", testItem1)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    var cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart(0).description()=="cheese")
    assert(Math.abs(cart(0).price()-12.0)<0.0001)
    assert(cart(1).description()=="cheese")
    assert(Math.abs(cart(1).price()-12.0)<0.0001)

  }

  test("breaks_on_invalid_barcodes"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472", testItem1)
    checkout.numberPressed(5)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    var cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()=="error")
    assert(Math.abs(cart.head.price()-0.0)<0.0001)
  }

  test("cant_change_price"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    testItem1.setBasePrice(11.0)
    assert(Math.abs(testItem1.price()-11.0)<0.0001)
  }

  test("clear_doesnt_clear"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472", testItem1)

    checkout.numberPressed(5)
    checkout.clearPressed()
    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()

    var cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()=="cheese")
    assert(Math.abs(cart.head.price()-12.0)<0.0001)

  }

  test("display_broken"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472", testItem1)
    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.displayString()

    assert(checkout.displayString()=="472")

  }

  test("does_not_accept_leading_zeros"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("072", testItem1)

    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    var cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()=="cheese")
    assert(Math.abs(cart.head.price()-12.0)<0.0001)
  }

  test("does_not_initially_display_empty_string"){
    val checkout: SelfCheckout = new SelfCheckout()
    checkout.displayString()

    assert(checkout.displayString()=="")
  }

  test("price_change_doesnt_update_items_in_cart"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472",testItem1)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    var cart: List[Item] = checkout.itemsInCart()
    testItem1.setBasePrice(11.0)
    assert(cart.size == 1)
    assert(cart(0).description()=="cheese")
    assert(Math.abs(cart(0).price()-11.0)<0.0001)
  }

  test("receipt_order_reversed"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem1: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472",testItem1)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    var testItem2: Item = new Item("dog", 3.0)
    checkout.addItemToStore("172",testItem2)

    checkout.numberPressed(1)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    var cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart(0).description()=="cheese")
    assert(Math.abs(cart(0).price()-12.0)<0.0001)
    assert(cart(1).description()=="dog")
    assert(Math.abs(cart(1).price()-3.0)<0.0001)
  }

}
