package com.example.imedical.login.domain.model

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
data class DataWrapper <T>(val status: Boolean, val data: T?, val error: String)