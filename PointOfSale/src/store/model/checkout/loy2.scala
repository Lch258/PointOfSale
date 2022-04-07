package store.model.checkout

import store.model.items.{Item, NItem, SItem}

class loy2(resetmode:SelfCheckout) extends State(resetmode){

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

  }
  override def displayString(): String={"cash or credit"}

  override def itemsInCart(): List[Item]={resetmode.cart}

  override def subtotal(): Double={
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    var stotal=0.0
    for(element:Item <- resetmode.cart){
      stotal+=element.price()
    }
    stotal
  }

  override def tax(): Double={
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    var ttotaltax=0.0
    for(element:Item <- resetmode.cart){
      ttotaltax+=element.tax()
    }
    ttotaltax
  }

  override def total(): Double={
    for ((key,value)<-resetmode.Items){
      for (mod <- value.modifierlist){
        mod.state=new SItem(mod)
      }
    }
    tax()+subtotal()
  }

  override def prepareStore(): Unit={}

}
