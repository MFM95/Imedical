package com.example.imedical.core.api

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
data class ApiResponse<T>(val status: Boolean, val data: T?, val error: String)