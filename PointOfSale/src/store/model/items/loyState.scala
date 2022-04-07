package store.model.items

import store.model.checkout.State

abstract class loyState{
  def updatePrice(oldprice: Double): Double
}