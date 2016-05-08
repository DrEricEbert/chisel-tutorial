package TutorialExamples

import Chisel._
import Chisel.swtesters._

object VecSearchTest {
  val pattern = Array(0, 4, 15, 14, 2, 5, 13)
}

class VecSearch extends Module {
  val io = new Bundle {
    val out = UInt(OUTPUT,  4)
  }
  val index = Reg(init = UInt(0, width = 3))
  val elts  = Wire(init = Vec(VecSearchTest.pattern.map(UInt(_, 4))))
  index := index + UInt(1)
  io.out := elts(index)
}

class VecSearchTests(c: VecSearch) extends ClassicTester(c) {
  val list = VecSearchTest.pattern
  for (elt <- list) {
    expect(c.io.out, elt)
    step(1)
  }
}
