package com.rooney.bean

class Node(var name_ : String , var position_ : (Int, Int)) {

	var name : String = name_
	var (x, y) : (Int, Int) = position_

	override def toString() = {
		"name : "+ name +" position,  x: "+x+", y:"+y 
	}

}