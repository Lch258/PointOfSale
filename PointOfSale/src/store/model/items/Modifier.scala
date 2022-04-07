package store.model.items

abstract class Modifier() {
  var state:loyState=new NItem(this)
  def statereturn():loyState={
    state
  }
  def updatePrice(oldprice: Double): Double
  def computeTax(inputprice: Double): Double
  def a():Double
}
