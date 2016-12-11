package com.avis

import akka.actor.{Props, ActorSystem}
import com.avis.Main._
import com.avis.supervisor.{Creation, SuperVisor}

/**
  * Created by shiva on 11/12/16.
  */
object Init extends  App{

  class SupervisorActorInstance extends SuperVisor with Creation

  println("Main In TestActorParent Child")

  val actorSystem = ActorSystem("TestingParentChildSystem")

  val mainActor = actorSystem.actorOf(Props[SupervisorActorInstance])

  val firstHeroName = "First-Hero-John"
  val secondHeroName = "First-Hero-Rock"

  val firstVillonName = "First-Villon-Ronald"
  val secondVillonName = "Second-Villon-Roads"

  //First Hero/Villon
  mainActor ! NewHero(firstHeroName)
  mainActor ! NewVillon(firstVillonName)

  mainActor ! ForwardMsg(HeroAge, firstHeroName)
  mainActor ! ForwardMsg(VillonAge, firstVillonName)
  /*
    mainActor ! NewVillon(firstVillonName)
    mainActor ! IsChildExist(firstVillonName)

    //Second Hero/Villon
    mainActor ! NewHero(secondHeroName)
    mainActor ! IsChildExist(secondHeroName)

    mainActor ! NewVillon(secondVillonName)
    mainActor ! IsChildExist(secondVillonName)
  */


  //Forward Msg to Parent Actor which in turn forward to respecive Child

  //mainActor ! ForwardMsg(HeroAge, firstHeroName)
  //mainActor ! ForwardMsg(VillonAge, firstVillonName)

}
