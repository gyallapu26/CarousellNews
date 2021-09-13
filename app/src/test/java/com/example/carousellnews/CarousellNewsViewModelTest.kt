package com.example.carousellnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.carousellnews.core.util.Result
import com.example.carousellnews.entities.News
import com.example.carousellnews.repository.CarousellNewsRepository
import com.example.carousellnews.viewmodel.CarousellNewsViewModel
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class CarousellNewsViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = TestCoroutineRule()

    private lateinit var carousellNewsViewModel: CarousellNewsViewModel

    private lateinit var carousellNewsRepository: CarousellNewsRepository
    private lateinit var newsResponse: Response<List<News>>


    @Before
    fun setUp() {
        carousellNewsRepository = Mockito.mock(CarousellNewsRepository::class.java)
        newsResponse = Response.success(200, listOf<News>())
        carousellNewsViewModel = CarousellNewsViewModel(repository = carousellNewsRepository)
    }
    @After
    fun tearDown() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun assertNewsDataAvailable()  = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(carousellNewsRepository.fetchNews()).thenReturn(newsResponse)

        val result = mutableListOf<Result<List<News>?>>()
        val job = launch {
            carousellNewsViewModel.resultMutableStateFlow.toList(result)
        }
        carousellNewsViewModel.fetchCarousellNews(TestCoroutineDispatcher())

        assert(result.first() is Result.InProgress)
        assert(result.last() is Result.Success)
        job.cancel()
    }


}