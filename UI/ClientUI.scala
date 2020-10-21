package UI

/*
 simple console User Interface
*/

object ClientUI {

  //creating server
  val client = new Client.Client("localhost", 9000)

  def main(args: Array[String]): Unit = {

    println("------  Welcome to remote Pilot  ------")
    println()

    while (true) {
      scala.io.StdIn.readLine() match {
        case "-help" => printHelp()
        case "list" => listDev()
        case "device" => inDevice()
        case _ => println("Unknown request")
      }
    }

  }


  def printHelp(): Unit ={
    println("----  HELP  ----")
    println()
    println("* avaible functions :")
    println("-help \t display help")
    println("list \t list all devices")
    println("device \t go into device menu")

  }

  def listDev(): Unit ={
    client.listDevices().foreach(println(_))
  }

  def inDevice(): Unit ={
    print("Device id:   ")
    val devID = scala.io.StdIn.readLine()
    val ob = client.registry.lookup(devID).asInstanceOf[Common.Device]

    ob.getDevType() match {
      case "TV"  => {inTV()}
      case "RADIO" => {inRadio()}
    }

    def inTV(): Unit ={
      val obj = client.registry.lookup(devID).asInstanceOf[Common.TV]
      println("--- menu in device " + obj.getID() + " ---")
      var input = scala.io.StdIn.readLine()
      while (input != "quit") {
        input match {
          case "list" => obj.listMethods().foreach(println(_))
          case "print" => println(obj.print)
          case "getID" => println(obj.getID())
          case "getDevType" => println(obj.getDevType())
          case "getIsOn" => println(obj.getIsOn())
          case "turnOn" => obj.turnOn()
          case "volumeUp" => obj.volumeUp()
          case "changeChannel" => {
            print("Channel nr:  ")
            obj changeChannel scala.io.StdIn.readInt()
          }
          case _ => println("Unknown request")
        }
        input = scala.io.StdIn.readLine()
      }
    }

    def inRadio(): Unit ={
      val obj = client.registry.lookup(devID).asInstanceOf[Common.Radio]
      println("--- menu in device " + obj.getID() + " ---")
      var input = scala.io.StdIn.readLine()
      while (input != "quit") {
        input match {
          case "list" => obj.listMethods().foreach(println(_))
          case "print" => println(obj.print())
          case "getID" => println(obj.getID())
          case "getDevType" => println(obj.getDevType())
          case "getIsOn" => println(obj.getIsOn())
          case "turnOn" => obj.turnOn()
          case "volumeUp" => obj.volumeUp()
          case "changeFreq" => {
            print("Frequency:  ")
            obj changeFreq scala.io.StdIn.readDouble()
          }
          case _ => println("Unknown request")
        }
        input = scala.io.StdIn.readLine()
      }
    }
  }

}
