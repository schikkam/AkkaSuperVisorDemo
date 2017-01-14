package com.avis.algos

/**
  * Created by shiva on 14/1/17.
  */

import scala.collection.mutable.ArrayBuffer

object KMP extends App {

  def computerArray(patt: Array[Char]): Array[Int] = {
    val pattLen = patt.length
    val lps = new Array[Int](pattLen)
    var i = 1
    var j = 0
    lps(j) = 0
    while (i < pattLen) {
      patt(i) == patt(j) match{
        case true =>
          lps(i) = j  + 1
          j += 1
          i += 1
        case false =>
          if (j!=0) j = lps(j - 1)
          else{
            lps(i) = 0
            i += 1
          }
      }
    }
    lps
  }

  def hasSubStringExist(mainString: String, subString: String): Boolean ={
    val lps = computerArray(subString.toCharArray)
    var (i,j)= (0,0)
    while( i < mainString.length && j < subString.length) {
      if (mainString(i) == subString(j)){
        i += 1; j+=1
      }else{
        if (j!=0){
          j = lps(j-1)
        }else i+=1
      }
    }
    if (j == subString.length) true else false
  }

  val mainString = "ababababababababababcd"
  val subString = "ababcd"
  val found = hasSubStringExist(mainString, subString)
  println(s"Match FOund =${found}")
}


