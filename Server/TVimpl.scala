package Server

class TVimpl extends Common.TV {

  //parameters
  val id = "TV_" + java.util.UUID.randomUUID.toString
  val devType = "TV"
  var isOn = false
  var volume : Int = 10
  var channel : Int = 1


  override def print: String = {
    s"""\t\t[ID] \t$id
        [device type] \t$devType
        [isOn] \t$isOn
        [volume] \t$volume
        [channel] \t$channel""".stripMargin
  }

  override def listMethods(): Array[String] ={
    Array(
    "print",
    "getID",
    "getDevType",
    "getIsOn",
    "turnOn",
    "volumeUp",
    "changeChannel")
  }

  override def getID(): String = id

  override def getDevType(): String = devType

  override def getIsOn() : Boolean = {
    isOn
  }

  override def turnOn(): Unit = {
    isOn = true
  }

  override def volumeUp(): Int = {
    volume += 1
    volume
  }

  override def changeChannel(channel: Int): Int = {
    this.channel = channel
    this.channel
  }
}
