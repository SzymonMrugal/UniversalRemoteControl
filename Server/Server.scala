package Server

import java.rmi.{Naming, Remote}
import java.rmi.registry.{LocateRegistry, Registry}
import java.rmi.server.UnicastRemoteObject


class Server (val address: String = "localhost", val port: Int = 9000) {


  //creating registry for remote objects
  val registry: Registry = {
    try {
      val reg = LocateRegistry.createRegistry(port)
      println("Server ready, java.RMI server listening on " + "//" + address + ":" + port.toString)
      reg
    } catch {
      case e: java.rmi.server.ExportException => {
        println("Server is already running")
        print("ERROR MSG: \n" + e)
        null
      }
    }
  }


  def addDev(dev: Common.Device ) = {
    registry.bind(dev.getID(), UnicastRemoteObject.exportObject(dev, port))
  }

  def rmDev(id: String) = {
    registry.unbind(id)
  }

  def listDevices(): Array[String] = {
    registry.list()
  }

}
