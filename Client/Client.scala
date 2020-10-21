package Client

import java.rmi.registry.{LocateRegistry, Registry}


class Client (val address: String = "localhost", val port: Int = 9000) {


  val registry: Registry = {
    try {
      val reg = LocateRegistry.getRegistry(address, port)
      println("Connected")
      reg
    } catch {
      case e : java.rmi.ConnectException =>
        println("No server found")
        print("ERROR MSG: \n" + e)
        null
    }
  }

  def listDevices(): Array[String] = {
    registry.list()
  }


}
