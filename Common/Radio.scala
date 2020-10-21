package Common

import java.rmi.RemoteException


trait Radio extends Common.Device {

  @throws(classOf[RemoteException])
  def volumeUp() : Int

  @throws(classOf[RemoteException])
  def changeFreq(freq: Double) : Double

}