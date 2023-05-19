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
        return ValidationResult(isbn.isNotBlank())
        /*
        man muss schauen obs nur 13 ziffern hat
 besser mit Leons Link: lösen .... Regex ist für Muster da welches in Strings vorhanden ist.
 Scanner App --> gibt es in dem Text ein Paragraphen Zeichen, eines oder keines Leereichen, und dann mindestens eine Zahl

  Das erste im Test :
  1.) ist die Länge 13 Zeichen + 4 Minuszeichen --> insgesamt genau 17 Zeichen
  2.) 123 -1 - 12345 -123-1
         */
    }

}


data class ValidationResult(
    val successful: Boolean
)
