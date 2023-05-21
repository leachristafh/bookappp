package com.example.bookapp.common


object Validator {
    fun validateBookTitle(title: String): ValidationResult {
        return ValidationResult(title.isNotBlank())
    }

    fun validateBookYear(year: Int): ValidationResult { //eine Zahl kann nicht blank/leer sein
        return ValidationResult(year in 0..2023)

    }
    fun validateBookAuthor(author: String): ValidationResult {
        return ValidationResult(author.isNotBlank())
    }

    fun validateBookIsbn(isbn: String): ValidationResult {
        // Remove any non-digit characters Fakt: insgesamt sind 17 Zeichen erlaubt: 13 Zahlen + 4 Trennungen
        val zahlenIsbn = isbn.replace("-".toRegex(), "")


        //man muss es von den - reinigen
        if (zahlenIsbn.length != 13) {  // Check if the cleaned ISBN has 13 digits
            return ValidationResult(false)
        } else {
            val erstenZwoelfZiffern = zahlenIsbn.take(12).map { it.toString().toInt() }.toMutableList()
            // Multiply each digit by the corresponding multiplier
            for (i in 0 until 12) {
                erstenZwoelfZiffern[i] *= if (i % 2 == 0) 1 else 3
                //wie mache ich es damit es quasi die position %2 und nicht die zahl an sich nimmt
            }
            // Sum all the multiplied digits
            val sum = erstenZwoelfZiffern.sum()
            // Perform modulo 10 division
            val moduloRest = sum % 10
            // wir erstellen die check digit und vergleichen sie mit der last digit...
            //9781681972712
            val vergleichsZahl = if (moduloRest == 0) 0 else 10 - moduloRest
            // Get the last digit of the ISBN
            val lastDigit = zahlenIsbn[12].toString().toInt()
            // Compare the check digit with the last digit of the ISBN
            if (vergleichsZahl != lastDigit) {
                return ValidationResult(false)
            } else {
                val result = isbn.isNotBlank() && vergleichsZahl == lastDigit
                return ValidationResult(result)
            }
            //978-3-16-148410-0
            //978-3958457829
        }
    }
    /*
118 modulo 10 = 110 remainder 8
Work out the check digit:
8 doesn't equal zero, so check digit = 10 - 8 = 2
--> in diesem Fall muss es dann 2 sein
*/


    /*
Take the first 12 digits of the 13-digit ISBN
Multiply each number in turn, from left to right by a number. The first digit is multiplied by 1, the second by 3,
 the third by 1 gain, the fourth by 3 again,
 and so on to the eleventh which is multiplied by 1 and the twelfth by 3.
Add all of the 12 answers.
Do a modulo 10 division on the result from step 2.
(Don't know what a modulo 10 division is? It's easy. It's just the remainder
 from a whole number division by 10. I bet you learned to do that in junior school, before you even learned about decimal fractions.)
Take that remainder result from step 4.If it's a zero, then the check digit is zero.
If the remainders isn't zero then subtract the remainder from 10. The answer to that is your check digit.
 */

    data class ValidationResult(
        val successful: Boolean
    )
}