package com.rooney.bean

class Node(var name_ : String , var position_ : Int) {

	var name : String = name_
	var position : Int = position_

	override def toString() = {
		"name : "+ name +" position : "+position
	}

}