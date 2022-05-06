package com.joaovicttors.anime.presentation.features.anime_list.view

import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.presentation.MainCoroutineScope
import com.joaovicttors.base.Response
import com.joaovicttors.base.usecase.BaseUseCase
import com.joaovicttors.base.usecase.utilities.NoParam
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class AnimeListViewModelTest {

    @get:Rule
    val mainCoroutineScope = MainCoroutineScope()

    private lateinit var getAnimeListUseCase: BaseUseCase<NoParam, List<Anime>>
    private lateinit var viewModel: AnimeListViewModel

    @Before
    fun before() {
        getAnimeListUseCase = mockk()
        viewModel = AnimeListViewModel(getAnimeListUseCase)
    }

    @Test
    fun `when getAnimeListUseCase is calling should getAnimeList returns loading true on view state`() =
        runTest { ->
            val expectedLoading = true
            val expectedData = emptyList<Anime>()
            val expectedErrorMessage = null

            coEvery { getAnimeListUseCase(any()) } coAnswers { delay(1000); Response.Success(expectedData)}

            viewModel.getAnimeList()

            viewModel.viewState.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }

    @Test
    fun `when getAnimeListUseCase returns error response should getAnimeList returns error message on view state`() =
        runTest { ->
            val expectedLoading = false
            val expectedData = emptyList<Anime>()
            val expectedErrorMessage = "Error"

            coEvery { getAnimeListUseCase(any()) } returns Response.Error(expectedErrorMessage)

            viewModel.getAnimeList()

            viewModel.viewState.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }

    @Test
    fun `when getAnimeListUseCase returns success response should getAnimeList returns anime list on view state`() =
        runTest { ->
            val expectedLoading = false
            val expectedData = listOf<Anime>(mockk())
            val expectedErrorMessage = null

            coEvery { getAnimeListUseCase(any()) } returns Response.Success(expectedData)

            viewModel.getAnimeList()

            viewModel.viewState.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }

    @Test
    fun `when getAnimeListUseCase throws an error should getAnimeList returns error message on view state`() =
        runTest { ->
            val expectedLoading = false
            val expectedData = emptyList<Anime>()
            val expectedErrorMessage = "Error"

            coEvery { getAnimeListUseCase(any()) } throws RuntimeException(expectedErrorMessage)

            viewModel.getAnimeList()

            viewModel.viewState.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }
}