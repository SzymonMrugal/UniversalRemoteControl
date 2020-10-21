package Common

import java.rmi.RemoteException


trait TV extends Device {

  @throws(classOf[RemoteException])
  def volumeUp() : Int

  @throws(classOf[RemoteException])
  def changeChannel(channel: Int) : Int

}