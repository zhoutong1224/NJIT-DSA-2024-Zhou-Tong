package oy.tol.tra;

import java.util.Comparator;

/**
 * A simple array of student grades to be used in testing
 * misbehaving algorithm for reversing the array.
 */
public class Grades<T> {
   
   private T[] grades = null;

   /**
    * A constructor for building Arrays.
    * @param grades the plain Java array with elements to add.
    */
   public Grades(T[] grades) {
      this.grades = (T[]) new Object[grades.length];
      System.arraycopy(grades, 0, this.grades, 0, grades.length);
   }

   /**
    * The method to reverse the internal array.
    */
   public void reverse() {
      Algorithms.reverseArray(grades);
   }

   /**
    * Sorts the array based on a custom comparator.
    * @param comparator the comparator to determine the order of elements.
    */
   public void sort(Comparator<? super T> comparator) {
      Algorithms.sortArrayWithComparator(grades, comparator);
   }

   /**
    * Calculates the average value of elements in the array.
    * @return the average value of elements in the array.
    */
   public double calculateAverage() {
      if (grades == null || grades.length == 0) {
         return 0.0;
      }

      double sum = 0;
      for (T grade : grades) {
         sum += Double.parseDouble(grade.toString());
      }

      return sum / grades.length;
   }

   /**
    * Returns the plain Java array for investigation.
    * @return The array.
    */
   public T[] getArray() {
      return grades;
   }
}