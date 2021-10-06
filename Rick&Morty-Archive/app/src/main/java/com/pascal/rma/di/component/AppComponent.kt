package com.pascal.rma.di.component

import com.pascal.rma.di.module.ApiModule
import com.pascal.rma.di.module.AppModule
import com.pascal.rma.di.module.CiceroneModule
import com.pascal.rma.presentation.activity.MainActivity
import com.pascal.rma.presentation.activity.MainPresenter
import com.pascal.rma.presentation.screens.categories.CategoriesPresenter
import com.pascal.rma.presentation.screens.character.list.CharactersFragment
import com.pascal.rma.presentation.screens.character.list.CharactersPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dronpascal on 05.10.2021.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
    ]
)
interface AppComponent {

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)

    fun inject(CategoriesPresenter: CategoriesPresenter)
}