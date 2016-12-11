package com.avis.supervisor

/**
  * Created by shiva on 11/12/16.
  */

import akka.actor.{ActorRef, Props, ActorContext, Actor}

import scala.reflect._
import com.avis.Main._

trait SuperVisor extends Actor with Helpers {

  def receive = {
    case NewHero(x) =>
      println(s"NewHero Creation In ParentActor ${x}")
      val propz = props1(classTag[HeroActorType], x)
      createChild(propz, x)

    case NewVillon(x) =>
      println(s"NewVillon Creation In ParentActor ${x}")
      //createChild(props(x),x)
      val propz = props1(classTag[VillonActorType], x)
      createChild(propz, x)

    case IsChildExist(x) =>
      val a = getChild(x)
      a match {
        case Some(child) =>
          println(s"ChildExist = ${child}")
        case None => println(s"NO Child Found Named ${x}")
      }

    case ForwardMsg(msg: ActorRequests, actorName: String) =>
      forward(msg, actorName)
    case _ => println("Unknown Messages Received")

  }
}


trait Creation {
  this: SuperVisor =>

  def context: ActorContext

  def createChild(props: Props, actorName: String): ActorRef = {
    context.actorOf(props, actorName)
  }

  def getChild(actorName: String): Option[ActorRef] = {
    context.child(actorName) match {
      case Some(x) => Some(x)
      case _ => println("Child Actor Not Found"); None
    }
  }

  def forward[T<: ActorRequests](msg: T, actorName: String) = {
    getChild(actorName) match{
      case Some(act) =>
        println(s"Match actor Name Found = ${act}" );
        act ! msg
      case _ => println(s"No Child Actor Found to Honour The Msg, Msg =${msg}")
    }
  }
}

