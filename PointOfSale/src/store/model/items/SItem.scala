package store.model.items

class SItem(mode:Modifier) extends loyState {
  override def updatePrice(oldprice: Double): Double = {
    val present=this.mode.a()
    oldprice*((100-present)*0.01)
  }

}
