package tasks.adts

import scala.math.abs

/*  Exercise 1: 
 *  Complete the implementation of ComplexADT trait below, so that it passes
 *  the test in ComplexTest.
 */

object Ex1ComplexNumbers:

  trait ComplexADT:
    type Complex
    def complex(re: Double, im: Double): Complex
    extension (complex: Complex)
      def re(): Double
      def im(): Double
      def sum(other: Complex): Complex
      def subtract(other: Complex): Complex
      def asString(): String

  object BasicComplexADT extends ComplexADT:

    case class ComplexNumber(re: Double, im: Double)
    // Change assignment below: should probably define a case class and use it?
    type Complex = ComplexNumber
    def complex(re: Double, im: Double): Complex = ComplexNumber(re, im)
    extension (complex: Complex)
      def re(): Double = complex match
        case ComplexNumber(re, _) => re

      def im(): Double = complex match
        case ComplexNumber(_, im) => im

      def sum(other: Complex): Complex = (complex, other) match
        case (ComplexNumber(re1, im1), ComplexNumber(re2, im2)) => ComplexNumber(re1 + re2, im1 + im2)

      def subtract(other: Complex): Complex = (complex, other) match
        case (ComplexNumber(re1, im1), ComplexNumber(re2, im2)) => ComplexNumber(re1 - re2, im1 - im2)

      def asString(): String = complex match
        case ComplexNumber(0, 0) => "0.0"
        case ComplexNumber(re, 0) => re + ""
        case ComplexNumber(0, im) => im match
          case im if im < 0 => "-" + abs(im) + "i"
          case _ => im + "i"
        case ComplexNumber(re, im) => im match
          case im if im < 0 => re + " - " + abs(im) + "i"
          case _ => re + " + " + im + "i"

