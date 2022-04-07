package store.model.items
import store.model.checkout.{State, scanning}
import store.model.items.Modifier

class Item(var basedescription: String,var baseprice:Double) {
  // TODO: Complete this class according to the features listed in the HW document
  var modifierlist:List[Modifier]=List()
  def addModifier(modifier: Modifier): Unit ={
    modifierlist=modifierlist:+modifier
  }
  def description(): String = {
    this.basedescription
  }
  def setBasePrice(newprice:Double):Unit={
    this.baseprice=newprice
  }
  def price(): Double = {
    var cprice=baseprice
    for (amodififier:Modifier<-modifierlist){
      cprice=amodififier.updatePrice(cprice)
    }
    cprice

  }
  def tax():Double={
    var taxx:Double=0.0
    val tprice=price()
    for (amodififier<-modifierlist){
      taxx+=amodififier.computeTax(tprice)
    }
    taxx
  }
}
