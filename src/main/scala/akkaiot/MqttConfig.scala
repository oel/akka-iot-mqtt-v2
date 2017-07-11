package akkaiot

import scala.concurrent.duration._

import java.io.Serializable
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

import com.sandinh.paho.akka._
import com.sandinh.paho.akka.MqttPubSub._

object MqttConfig {
  val topic = "akka-iot-mqtt-topic"

  // Pub-Sub config
  val psConfig = PSConfig(
    brokerUrl = "tcp://test.mosquitto.org:1883",
    userName = null,
    password = null,
    stashTimeToLive = 1.minute,
    stashCapacity = 8000,
    reconnectDelayMin = 10.millis,
    reconnectDelayMax = 30.seconds,
    cleanSession = false
  )

  // Serialize object to byte array
  def writeToByteArray(obj: Any): Array[Byte] = {
    val baos = new ByteArrayOutputStream
    val oos = new ObjectOutputStream(baos)
    oos.writeObject(obj)

    baos.toByteArray
  }

  // Deserialize object from byte array
  def readFromByteArray[A](bytes: Array[Byte]): A = {
    val bais = new ByteArrayInputStream(bytes)
    val ois = new ObjectInputStream(bais)
    val obj = ois.readObject

    obj.asInstanceOf[A]
  }
}
