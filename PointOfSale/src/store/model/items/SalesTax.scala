package store.model.items

class SalesTax(val salepercentage:Double) extends Modifier {
  override def updatePrice(newprice:Double): Double ={
    newprice:Double
  }
  override def computeTax(inputprice: Double):Double= {
    inputprice * (this.salepercentage/100)
  }
  override def a(): Double = {
    this.salepercentage
  }
}
