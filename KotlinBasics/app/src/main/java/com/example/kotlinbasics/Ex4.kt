package com.example.kotlinbasics

data class Rectangle(val x: Int, val y: Int, val w: Int, val h: Int)

class Paint {
    var color: Long = 0x00FF00
    var strokeWidth: Int = 5
    fun drawRectangle(rect: Rectangle) {
        println("Drawing $rect color: $color stroke: $strokeWidth")
    }
}

fun render(paint: Paint?, rectangles: List<Rectangle?>) {
    rectangles.filterNotNull().forEach { paint?.drawRectangle(it) }
//    if (paint != null) {
//        paint.color = 0xFF0000
//        for (rect in rectangles) {
//            if (rect != null) {
//                paint.drawRectangle(rect)
//            }
//        }
//    }
}

fun main() {
    render(Paint(), listOf(Rectangle(1,2,3,4), null, Rectangle(5,6,7,8)))
}
