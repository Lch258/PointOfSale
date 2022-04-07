package store.model.checkout

import store.model.items.{Item, NItem, SItem, Sale}

class checkouting(resetmode: SelfCheckout) extends State(resetmode){

  override def addItemToStore(barcode: String, item: Item): Unit={}

  override def numberPressed(number: Int): Unit={}

  override def clearPressed(): Unit={}

  override def enterPressed(): Unit={}

  override def checkoutPressed(): Unit={}

  override def cashPressed(): Unit={
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new NItem(mod)
      }
    }
    resetmode.cart=List()
    resetmode.state=new scanning(resetmode)
  }

  override def creditPressed(): Unit={
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new NItem(mod)
      }
    }
    resetmode.cart=List()
    resetmode.state=new scanning(resetmode)
  }

  override def loyaltyCardPressed(): Unit= {
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    subtotal()
    total()
    resetmode.state=new loy2(resetmode)
  }
  override def displayString(): String={"cash or credit"}

  override def itemsInCart(): List[Item]={resetmode.cart}

  override def subtotal(): Double={
    var stotal=0.0
    for(element:Item <- resetmode.cart){
      stotal+=element.price()
    }
    stotal
  }

  override def tax(): Double={
    var ttotaltax=0.0
    for(element:Item <- resetmode.cart){
      ttotaltax+=element.tax()
    }
    ttotaltax
  }

  override def total(): Double={
    tax()+subtotal()
  }

  override def prepareStore(): Unit={}
}
