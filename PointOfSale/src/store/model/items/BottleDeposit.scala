package store.model.items

class BottleDeposit(salepercentage:Double) extends Modifier {

  override def updatePrice(newprice:Double):Double={
    newprice:Double
  }
  override def computeTax(inpt:Double):Double={
  this.salepercentage
  }
  override def a(): Double = {
    this.salepercentage
  }
}
