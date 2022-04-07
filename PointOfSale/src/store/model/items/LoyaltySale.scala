package store.model.items

class LoyaltySale(salepercentage:Double) extends Modifier{

  override def computeTax(inputprice: Double): Double = {
    0.0
  }

  override def updatePrice(oldprice: Double): Double = {
    this.state.updatePrice(oldprice)
  }

  override def a(): Double = {
    this.salepercentage
  }
}