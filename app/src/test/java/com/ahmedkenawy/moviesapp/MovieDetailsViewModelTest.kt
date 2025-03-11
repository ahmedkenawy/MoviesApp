import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.ahmedkenawy.moviesapp.core.base.BaseCoroutineDispatchers
import com.ahmedkenawy.moviesapp.features.details.presentaion.viewmodel.MovieDetailsViewModel
import com.ahmedkenawy.moviesapp.features.list.data.MoviesRepository
import com.ahmedkenawy.moviesapp.features.list.data.local.MoviesDao
import com.ahmedkenawy.moviesapp.features.list.data.local.MoviesDatabase
import com.ahmedkenawy.moviesapp.features.list.domain.Movie
import com.ahmedkenawy.moviesapp.utils.Constants.Intent.MOVIE_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var moviesDatabase: MoviesDatabase
    private lateinit var moviesDao: MoviesDao

    @Before
    fun setup() = runTest {
        // Mock database and DAO
        moviesDatabase = mock()
        moviesDao = mock()

        whenever(moviesDatabase.moviesDao()).thenReturn(moviesDao)

        whenever(moviesDao.updateFavoriteStatus(anyInt(), anyBoolean())).thenAnswer {
            runBlocking { }
        }

        moviesRepository = mock()
        whenever(moviesRepository.updateMovie(anyInt(), anyBoolean())).thenAnswer {
            runBlocking { }
        }

        // Create a test movie object
        val testMovie = Movie(
            id = 1,
            overview = "Test overview",
            popularity = 9.5,
            poster_path = "/test.jpg",
            title = "Test Movie",
            vote_average = 8.5,
            vote_count = 100,
            releaseDate = "2023-01-01",
            originalLanguage = "en",
            isFavorite = false
        )

        savedStateHandle = SavedStateHandle()
        savedStateHandle[MOVIE_NAME] = testMovie

        viewModel = MovieDetailsViewModel(savedStateHandle, moviesRepository, BaseCoroutineDispatchers())

        Dispatchers.setMain(StandardTestDispatcher())
    }



    @Test
    fun `updateMovie should update favorite status in repository and ViewModel`() = runTest {
        val movieId = 1
        val isFavorite = true
        doNothing().whenever(moviesRepository).updateMovie(anyInt(), anyBoolean())
        viewModel.updateMovie(movieId, isFavorite)
        advanceUntilIdle()
        verify(moviesRepository).updateMovie(movieId, isFavorite)
        assertEquals(true, viewModel.isFavorite.get())
    }

}
