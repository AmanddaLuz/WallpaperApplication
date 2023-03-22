package com.amandaluz.wallpaperapplication.framework.repository

import androidx.paging.PagingSource
import com.amandaluz.core.data.RemoteDataSource
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.testing.MainCoroutinesRule
import com.amandaluz.wallpaperapplication.framework.network.response.DataWrapperResponse
import com.amandaluz.wallpaperapplication.framework.paging.PopularPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PopularRepositoryImplTest {

    @get: Rule
    var mainCoroutinesRule = MainCoroutinesRule()

    @Mock
    var mockRemoteDataSource =  mock<RemoteDataSource<DataWrapperResponse>>()

    private lateinit var popularPagingSource: PopularPagingSource

   lateinit var popularRepository: PopularRepositoryImpl

    @Before
    fun setup(){
        popularPagingSource = PopularPagingSource(mockRemoteDataSource, 40)
        popularRepository = PopularRepositoryImpl(mockRemoteDataSource)
    }

    @Test
    fun `fetchPopular should return PagingSource when called`() {
        val result = popularRepository.fetchPopular(40)

        Assert.assertNotNull(result)
    }

    @Test
    fun `should validate paging data error when invoke from repository is called`() = runTest {
        val exception = RuntimeException()

        whenever(mockRemoteDataSource.fetchPopular(any(), any()))
            .thenThrow(exception)

        val result = popularPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 40,
                placeholdersEnabled = false
            )
        )

        Assert.assertEquals(
            PagingSource.LoadResult.Error<Int, PhotoDomain>(exception),
            result
        )
    }
}
