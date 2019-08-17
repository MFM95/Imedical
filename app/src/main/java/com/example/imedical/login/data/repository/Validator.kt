package com.example.imedical.login.data.repository

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
object Validator {
    fun validateLogin(user: String, password: String): String{
        var error: String = ""
        if(user.isBlank())
            error = "Please enter email or mobile number"
        else if(password.isBlank())
            error = "Please enter your password"

        return error
    }
}