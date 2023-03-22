package com.amandaluz.wallpaperapplication.ui.fragments.popular.viewmodel

import androidx.paging.PagingData
import com.amandaluz.core.usecase.popularusecase.GetPopularUseCase
import com.amandaluz.testing.MainCoroutinesRule
import com.amandaluz.testing.model.WallpapersFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class PopularViewModelTest {

    @get: Rule
    var mainCoroutinesRule = MainCoroutinesRule()

    @Mock
    lateinit var popularUseCase : GetPopularUseCase
    private lateinit var popularViewModel : PopularViewModel

    @Before
    fun setup() {
        popularViewModel = PopularViewModel(popularUseCase)
    }

    @Test
    fun `Should validate pagination data`() = runTest {

        whenever(popularUseCase(any())).thenReturn(flowOf(getPagingDataMock))

        val result = popularViewModel.popularWallpapers()

        Assert.assertNotNull(result.first())
    }

    @Test(expected = RuntimeException::class)
    fun `Should return an empty PagingData When an error occurred`() = runTest {
        //Arrange
        whenever(popularUseCase(any())).thenThrow(RuntimeException())

        //Act
        popularViewModel.popularWallpapers()
    }

    private val wallpapersFactory = WallpapersFactory()

    private val getPagingDataMock =
        PagingData.from(
            listOf(
                wallpapersFactory.create(WallpapersFactory.Photo.PhotDomainSuccess) ,
                wallpapersFactory.create(WallpapersFactory.Photo.PhotDomainSuccess)
            )
        )
}