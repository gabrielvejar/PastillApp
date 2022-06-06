package com.gaverez.pastillapp.utils

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout

class TilValidator constructor(til: TextInputLayout) {
    private val til: TextInputLayout = til
    private val value: String = til.editText?.text.toString()
    private var required: Boolean = false
    private var invalid: Boolean = false

    private fun setError(invalid: Boolean, message: String) {
        if (invalid) {
            this.invalid = true
            til.error = message
        } else {
            til.error = null
            til.isErrorEnabled = false
        }
    }

    private fun mustValidate(): Boolean {
        return (!this.required && this.value.isNotEmpty() || !invalid)
    }

    fun required(): TilValidator {
        this.required = true
        val invalidField = this.value.isEmpty()
        this.setError(invalidField, "Campo requerido")
        return this
    }

    fun minLength(int: Int): TilValidator {
        if (mustValidate()) {
            val invalidField = this.value.length < int
            this.setError(invalidField, "Debe tener al menos $int caracteres")
        }
        return this
    }

    fun email(): TilValidator {
        if (mustValidate()) {
            val invalidField = !Patterns.EMAIL_ADDRESS.matcher(this.value).matches()
            this.setError(invalidField, "El valor debe tener un email válido")
        }
        return this
    }

    fun time(): TilValidator {
        if (mustValidate()) {
            val timePattern = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])\$"
            val timeMatcher = Regex(timePattern)

            val invalidField = timeMatcher.find(this.value) == null
            this.setError(invalidField, "Hora incorrecta. Ingrese hora con formato HH:mm")
        }
        return this
    }

    fun days(): TilValidator {
        if (mustValidate()) {
            val invalidField = this.value == "0"
            this.setError(invalidField, "Mínimo 1 día")
        }
        return this
    }

    fun isValid(): Boolean {
        return !this.invalid
    }
}