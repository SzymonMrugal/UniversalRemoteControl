package Common

import java.rmi.{Remote, RemoteException}

trait Device extends Remote {

  @throws(classOf[RemoteException])
  def print(): String

  @throws(classOf[RemoteException])
  def listMethods(): Array[String]

  @throws(classOf[RemoteException])
  def getID(): String

  @throws(classOf[RemoteException])
  def getIsOn(): Boolean

  @throws(classOf[RemoteException])
  def getDevType(): String

  @throws(classOf[RemoteException])
  def turnOn(): Unit


}
