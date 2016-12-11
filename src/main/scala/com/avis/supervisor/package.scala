package com.avis

import akka.actor.{Props, Actor, ActorRef}

import scala.reflect.ClassTag

/**
  * Created by shiva on 11/12/16.
  */
package object Main {

  sealed  trait ActorRequests

  sealed trait HMsgs extends ActorRequests

  case class NewHero(name: String) extends HMsgs
  case object HeroName extends HMsgs
  case object HeroRenumeration extends HMsgs
  case object HeroAge extends HMsgs

  sealed trait VMsgs extends ActorRequests
  case class NewVillon(name: String) extends VMsgs
  case object VillonName extends VMsgs
  case object VillonRenumeration extends VMsgs
  case object VillonAge extends VMsgs


  case class ForwardMsg(msg: ActorRequests, actorName: String)

  case class IsChildExist(name: String)

  type VillonActorType = VillonActor
  type HeroActorType = HeroActor


  trait Helpers {

    def createChild(props: Props, actorName: String): ActorRef

    def getChild(actorName: String): Option[ActorRef]

    def props(name: String): Props = Props(classOf[HeroActor])

    def props1[T <: Actor : ClassTag](clazz: ClassTag[T], name: String): Props = Props(clazz.runtimeClass)

    //def props1[T](clazz: ClassTag[T], name: String): Props = Props(clazz.runtimeClass)
    def forward[T <: ActorRequests](msg: T, actorName: String) : Unit

  }

}
