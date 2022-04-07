package store.model.checkout

import store.model.items.Item

class SelfCheckout(){
  var Items:Map[String,Item]=Map()//addItemToStore(barcode: String, item:Item)
  var barcode:String=""//输入的代码
  var cart:List[Item]=List()//enterPressed Item List
  var xianshi:String="" //displayString()
  var lastitem:String=""//lastitem
  var realitem:String=""//realitem
  var state: State = new scanning(this)
  def addItemToStore(barcode: String, item:Item): Unit = {
    // This method adds an item to your store's checkout system. It does not add an item to the customer's cart
    /*Items+=(barcode->item)*/
    // TODO
    Items+=(barcode->item)
    this.state.addItemToStore(barcode: String, item:Item)
  }

  def numberPressed(number: Int): Unit = {
    /*barcode+=number.toString
    lastitem=""
    xianshi=barcode
    displayString()*/
    this.state.numberPressed(number: Int)
  }

  def clearPressed(): Unit = {
    /*barcode=""
    lastitem=""
    realitem=""
    xianshi=barcode
    displayString()*/
    this.state.clearPressed()
  }

  def enterPressed(): Unit = {
    /*realitem=lastitem+barcode
    lastitem=realitem
    val itementery:Item=Items.getOrElse((realitem),new Item("error",0.0))
    cart= cart :+ itementery
    barcode=""
    realitem=""
    xianshi=barcode
    displayString()*/
    println(realitem)
    this.state.enterPressed()
  }

  def checkoutPressed(): Unit = {
    /*xianshi="cash or credit"
    val barcode=""
    val rastitem=""
    this.state.resetmodechange()
    displayString()*/
    this.state.checkoutPressed()
  }

  def cashPressed(): Unit = {
    // TODO
    this.state.cashPressed()
  }

  def creditPressed(): Unit = {

    this.state.creditPressed()
    // TODO
  }

  def loyaltyCardPressed(): Unit = {
    this.state.loyaltyCardPressed()
  }
  def displayString(): String = {
    /*xianshi*/

    // TODO
    this.state.displayString()
  }

  def itemsInCart(): List[Item] = {
    /*cart*/
    this.state.itemsInCart()
  }

  def subtotal(): Double = {
    /*var stotal=0.0
    for(element:Item <- cart){
      stotal+=element.price()
    }
    stotal*/
    this.state.subtotal()
  }

  def tax(): Double = {
    /*var totaltax=0.0
    for(element:Item <- cart){
      totaltax+=element.tax()
    }
    totaltax*/
    this.state.tax()
  }

  def total(): Double = {
    /*tax()+subtotal()*/
    this.state.total()
  }

  def prepareStore(): Unit = {
    // Similar to openMap in the Pale Blue Dot assignment, this method is not required and is
    // meant to help you run manual tests.
    //
    // This method is called by the GUI during setup. Use this method to prepare your
    // items and call addItemToStore to add their barcodes. Also add any sales/tax/etc to your
    // items.
    //
    // This method will not be called during testing and you should not call it in your tests.
    // Each test must setup its own items to ensure compatibility in AutoLab. However, you can
    // write a similar method in your Test Suite classes.

    // Example usage:
    //val testItem: Item = new Item("test item", 100.0)
    //this.addItemToStore("472", testItem)
  }

}
