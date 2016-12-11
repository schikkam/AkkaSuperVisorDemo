package com.avis.Main

/**
  * Created by shiva on 11/12/16.
  */

import akka.actor.Actor

//Produce Worker
class VillonActor extends Actor {
  def receive = {

    case nh: NewVillon => println(s"New Villon Actor Names : ${nh.name}")
    case VillonAge => println(s"Hero Age Received")

    case _ => println("IN VillonActor Method - Unhandled")
  }
}

//Consume Worker
class HeroActor extends Actor {

  def receive = {

    case nv: NewHero => println(s"New Hero Named${nv.name}")
    case HeroAge => println(s"Villon Age Received")
    case _ => println("IN HeroActor Method - Unhandled")
  }
}



