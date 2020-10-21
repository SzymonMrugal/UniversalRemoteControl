package Server

class RadioImpl extends Common.Radio {

  val id = "RADIO_" + java.util.UUID.randomUUID.toString
  val devType = "RADIO"
  var isOn = false
  var volume = 10
  var freq = 101.2

  override def print: String = {
    s"""\t\t[ID] \t$id
        [device type] \t$devType
        [isOn] \t$isOn
        [volume] \t$volume
        [frequency] \t$freq""".stripMargin
  }

  override def volumeUp(): Int = {
    volume += 1
    volume
  }

  override def changeFreq(freq: Double): Double = {
    this.freq = freq
    freq
  }

  override def listMethods(): Array[String] = {
    Array(
      "print",
      "getID",
      "getDevType",
      "getIsOn",
      "turnOn",
      "volumeUp",
      "changeFreq")
  }

  override def getID(): String = {
    id
  }

  override def getIsOn(): Boolean = {
    isOn
  }

  override def getDevType(): String = {
    devType
  }

  override def turnOn(): Unit = {
    isOn = true
  }
}
