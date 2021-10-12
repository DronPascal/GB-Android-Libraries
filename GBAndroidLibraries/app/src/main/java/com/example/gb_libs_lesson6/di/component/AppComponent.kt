package com.example.gb_libs_lesson6.di.component

import com.example.gb_libs_lesson6.di.module.*
import com.example.gb_libs_lesson6.presentation.activity.MainActivity
import com.example.gb_libs_lesson6.presentation.activity.MainPresenter
import com.example.gb_libs_lesson6.presentation.screens.repo.RepoPresenter
import com.example.gb_libs_lesson6.presentation.screens.repos.UserReposPresenter
import com.example.gb_libs_lesson6.presentation.screens.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        InteractorsModule::class,
    ]
)
interface AppComponent {

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)
    fun inject(userReposPresenter: UserReposPresenter)
    fun inject(repoPresenter: RepoPresenter)
}