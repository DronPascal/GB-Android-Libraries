package com.pascal.rma

import com.pascal.rma.presentation.navigation.AppScreens
import com.pascal.rma.presentation.screens.categories.CategoriesPresenter
import com.pascal.rma.presentation.screens.categories.`CategoriesView$$State`
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router


/**
 * Created by dronpascal on 07.12.2021.
 */
class CategoriesPresenterTest {

    private lateinit var presenter: CategoriesPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var viewState: `CategoriesView$$State`

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CategoriesPresenter(router)
        presenter.setViewState(viewState)
    }

    @Test
    fun onBackPressedTest() {
        presenter.onBackPressed()
        Mockito.verify(router, Mockito.times(1)).exit()
    }

    @Test
    fun navigateToCharacterTest() {
        //Вызываем у Презентера метод onNavigateToCharacters()
        presenter.onNavigateToCharacters()
        //Проверяем, что у router вызывается метод navigateTo()
        Mockito.verify(router, Mockito.times(1))
            .navigateTo(any(AppScreens.CharactersScreen::class.java))
    }

    @Test
    fun navigateToEpisodeTest() {
        //Вызываем у Презентера метод onNavigateToCharacters()
        presenter.onNavigateToEpisodes()
        //Проверяем, что у viewState вызывается метод showSnackbar()
        Mockito.verify(viewState, Mockito.times(1)).showSnackbar(anyString())
    }

    @Test
    fun NavigateToLocationsTest() {
        presenter.onNavigateToLocations()
        Mockito.verify(viewState, Mockito.times(1)).showSnackbar(anyString())
    }

}