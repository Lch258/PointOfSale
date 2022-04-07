package store.model.checkout

import store.model.items.{Item, SItem}

class loy(resetmode:SelfCheckout) extends State(resetmode) {
  var Items:Map[String,Item]=resetmode.Items//addItemToStore(barcode: String, item:Item)
  var barcode:String=""//输入的代码
  var cart:List[Item]=List()//enterPressed Item List
  var xianshi:String="" //displayString()
  var lastitem:String=""//lastitem
  var realitem:String=""//realitem
  override def addItemToStore(barcode: String, item: Item): Unit= {
    resetmode.Items += (barcode -> item)
    Items+=(barcode -> item)
  }
  override def numberPressed(number: Int): Unit={
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    barcode+=number.toString
    lastitem=""
    xianshi=barcode
    displayString()
  }

  override def clearPressed(): Unit={
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    barcode=""
    lastitem=""
    realitem=""
    xianshi=barcode
    displayString()
  }

  override def enterPressed(): Unit= {
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    realitem=lastitem+barcode
    lastitem=realitem
    val itementery:Item=resetmode.Items.getOrElse((realitem),new Item("error",0.0))
    resetmode.cart= resetmode.cart :+ itementery
    cart=cart:+itementery
    barcode=""
    realitem=""
    xianshi=barcode
    displayString()
  }
  override def checkoutPressed(): Unit= {
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    resetmode.state=new loy2(resetmode)
  }
  override def cashPressed(): Unit= {

  }

  override def creditPressed(): Unit={

  }

  override def loyaltyCardPressed(): Unit={

  }

  override def displayString(): String={
    xianshi
  }

  override def itemsInCart(): List[Item]={
    resetmode.cart

  }

  override def subtotal(): Double={
    var stotal=0.0

    for(element:Item <- resetmode.cart){
      stotal+=element.price()
    }
    stotal
    stotal
  }

  override def tax(): Double= {
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    var totaltax=0.0
    var ttotaltax=0.0
    for(element:Item <- cart){
      ttotaltax+=element.tax()
    }
    for(element:Item <- resetmode.cart){
      totaltax+=element.tax()
    }
    totaltax
  }

  override def total(): Double= {
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    tax()+subtotal()
  }

  override def prepareStore(): Unit={

  }
}
