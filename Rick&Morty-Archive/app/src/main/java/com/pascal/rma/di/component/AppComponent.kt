package com.pascal.rma.di.component

import com.pascal.rma.di.module.*
import com.pascal.rma.presentation.activity.MainActivity
import com.pascal.rma.presentation.activity.MainPresenter
import com.pascal.rma.presentation.screens.categories.CategoriesPresenter
import com.pascal.rma.presentation.screens.character.list.CharactersPresenter
import com.pascal.rma.presentation.screens.character.list.adapter.CharacterViewHolder
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
        CacheModule::class,
        GlideModule::class
    ]
)
interface AppComponent {

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)

    fun inject(categoriesPresenter: CategoriesPresenter)

    fun inject(charactersPresenter: CharactersPresenter)
    fun inject(characterViewHolder: CharacterViewHolder)
}