package com.igorgiroti.login_sample.di

import android.content.Context
import android.content.SharedPreferences
import com.igorgiroti.login_sample.ui.viewModel.LoginViewModel
import com.igorgiroti.login_sample.utils.LOGIN_PREFS
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LoginModule {
    val loginModule = module {
        viewModel {
            LoginViewModel(
                dispatcher = Dispatchers.IO,
                sharedPreferences = get()
            )
        }

        single { provideSharedPreferences(context = androidContext()) }
    }


    private fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_PREFS, Context.MODE_PRIVATE)
    }
}

