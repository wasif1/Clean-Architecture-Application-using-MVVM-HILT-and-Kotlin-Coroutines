package com.example.dubizzletest.presentation

import android.os.Looper
import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.dubizzletest.api.FakeApiService
import com.example.dubizzletest.model.ListResponse
import com.example.dubizzletest.model.ResultData
import com.example.dubizzletest.model.ResultsItem
import com.example.dubizzletest.presentation.viewmodel.ListViewModel
import com.example.dubizzletest.usecase.ListUsecase
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class MainActivityUnitTest: TestCase() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userCase: ListUsecase

    @BindValue
    @JvmField
    val fakeApiService: FakeApiService = FakeApiService()

    @Mock
    private lateinit var listViewModel: ListViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        listViewModel = ListViewModel(userCase)
    }

    @Test
    fun testListData() = runBlocking {
        listViewModel.listItems()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = listViewModel.response.value
        assertTrue(value is ResultData.Success)
        assertNotNull(value)
        listViewModel.response.observeForever {
            run {
                when (it) {
                    is ResultData.Success -> {
                        assertEquals("4878bf592579410fba52941d00b62f94", it.data?.results?.get(0)?.uid)
                        assertEquals("Notebook", it.data?.results?.get(0)?.name)
                    }
                }
            }
        }
    }


    @Test
    fun testListDataFailure() = runBlocking {
        fakeApiService.failUserApi = true
        listViewModel.listItems()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = listViewModel.response.value
        assertTrue(value is ResultData.Error)
        assertNotNull(value)
    }

    @Test
    fun testListWrongData() = runBlocking {
        fakeApiService.wrongResponse = true
        listViewModel.listItems()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = listViewModel.response.value
        assertTrue(value is ResultData.Success)
        assertNotNull(value)
        listViewModel.response.observeForever {
            run {
                when (it) {
                    is ResultData.Success -> {
                        assertEquals(null, it.data?.results)
                    }
                }
            }
        }
    }
}