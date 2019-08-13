package com.example.imedical.core.usecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
abstract class BaseUseCase<Out, Params> {
    protected abstract suspend fun run(params: Params): Out

     fun execute(params: Params, onResult: (out: Out) -> Unit){
        CoroutineScope(Dispatchers.IO).launch{
            val result = run(params)
            GlobalScope.launch {
                onResult.invoke(result)
            }
        }
    }
}