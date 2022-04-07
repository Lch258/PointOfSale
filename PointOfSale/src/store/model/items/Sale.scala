package store.model.items


class Sale(val salepercentage:Double) extends Modifier{

  override def updatePrice(oldprice:Double):Double={
    val newprice =(1 - salepercentage/100) * oldprice
    newprice
  }
  override def computeTax(inputprice: Double):Double={
    0.0
  }
  override def a(): Double = {
    this.salepercentage
  }
}

