package com.example.gb_libs_lesson6.di.module

import com.example.gb_libs_lesson6.data.cache.GithubCache
import com.example.gb_libs_lesson6.data.remote.GithubService
import com.example.gb_libs_lesson6.domain.interactors.GetUserRepos
import com.example.gb_libs_lesson6.domain.interactors.GetUsers
import com.example.gb_libs_lesson6.domain.interactors.GithubInteractors
import com.example.gb_libs_lesson6.utils.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Provides
    @Singleton
    fun provideGithubInteractors(
        githubService: GithubService,
        networkStatus: INetworkStatus,
        githubCache: GithubCache
    ): GithubInteractors {
        return GithubInteractors.build(githubService, networkStatus, githubCache)
    }

    @Provides
    @Singleton
    fun provideGetUsers(githubInteractors: GithubInteractors): GetUsers {
        return githubInteractors.getUsers
    }

    @Provides
    @Singleton
    fun provideGetUserRepos(githubInteractors: GithubInteractors): GetUserRepos {
        return githubInteractors.getUserRepos
    }

}