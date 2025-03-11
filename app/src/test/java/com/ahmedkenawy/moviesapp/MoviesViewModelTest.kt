import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedkenawy.moviesapp.features.list.presentaion.viewmodel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        viewModel = mock()
    }

    @Test
    fun `loadInitialData_shouldPerformExpectedBehavior`() = runTest {

    }

    @Test
    fun `fetchMovies_shouldPerformExpectedBehavior`() = runTest {
    }

    @Test
    fun `fetchDataFromLocal_shouldPerformExpectedBehavior`() = runTest {
    }

    @Test
    fun `fetchDataFromRemote_shouldPerformExpectedBehavior`() = runTest {
    }

    @Test
    fun `updateMovie_shouldPerformExpectedBehavior`() = runTest {

    }

}