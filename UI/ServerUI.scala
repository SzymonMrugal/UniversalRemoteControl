package UI

import Common.Device

/*
 simple console User Interface
*/

object ServerUI {

  //creating server
  val server = new Server.Server("localhost", 9000)
  //adding example device
  server.addDev(new Server.TVimpl)

  def main(args: Array[String]): Unit = {

    println("------  Welcome to Pilot  ------")
    println()


    while (true) {
      scala.io.StdIn.readLine() match {
        case "-help" => printHelp()
        case "list" => listDev()
        case "addDev" => addDev()
        case "rmDev" => rmDev()
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
    println("addDev \t add device")
    println("rmDev \t remove device")
    println("device \t go into device menu")


  }

  def listDev(): Unit ={
    server.listDevices().foreach(println(_))
  }

  def addDev(): Unit = {
    print("Device type:  ")
    scala.io.StdIn.readLine() match {
      case "TV" => server.addDev(new Server.TVimpl)
      case "RADIO" => server.addDev(new Server.RadioImpl)
      case _ => println("Unknown type")
    }
    println("Device added")
  }

  def rmDev(): Unit ={
    print("Device id:  ")
    server.rmDev(scala.io.StdIn.readLine())
    println("Device removed")
  }

  def inDevice(): Unit ={
    print("Device id:   ")
    val devID = scala.io.StdIn.readLine()
    val ob = server.registry.lookup(devID).asInstanceOf[Common.Device]

    ob.getDevType() match {
      case "TV"  => {inTV()}
      case "RADIO" => {inRadio()}
    }

    def inTV(): Unit ={
      val obj = server.registry.lookup(devID).asInstanceOf[Common.TV]
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
      val obj = server.registry.lookup(devID).asInstanceOf[Common.Radio]
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
