package com.github.lipenathan.teamcreator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val exceptionsHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        _error.value = throwable.message
    }

    protected val _error = SingleLiveEvent<String>()
    val error: LiveData<String> = _error
}