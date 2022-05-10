package com.lediya.dagger2

import android.content.Context
import android.os.Build
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lediya.dagger2.utils.Constants
import com.lediya.dagger2.view.ListScreenActivity
import com.lediya.dagger2.view.ListScreenFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class CountryDataUnitTest {
    private lateinit var context: Context
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @After
    fun tearDown() {
    }
    /**
     * checks title use case*/
    @Test
    fun checkTitle()=testDispatcher.runBlockingTest {
        testScope.launch(Dispatchers.IO) {
            val activity = Robolectric.setupActivity(ListScreenActivity::class.java)
            assertNotEquals(
                context.getString(R.string.title),
                activity.supportActionBar?.title.toString().trim()
            )
            assertEquals(
                context.getString(R.string.app_name),
                activity.supportActionBar?.title.toString().trim()
            )
        }
    }
    /**check connectivity not available use case*/
    @Test
    fun checkNotInternet() = testDispatcher.runBlockingTest {
        testScope.launch (Dispatchers.IO ){
            val fragment =  ListScreenFragment()
            assertFalse(Constants.isConnectedToNetwork(context))
            assertEquals(context.getString(R.string.no_internet_toast),
                fragment.activity?.findViewById<AppCompatTextView>(R.id.errorTextData)?.text
            )
        }
    }
    /**check connectivity available use case*/
    @Test
    fun checkInternet() = testDispatcher.runBlockingTest {
        testScope.launch (Dispatchers.IO ){
            assertTrue(Constants.isConnectedToNetwork(context))
        }
    }
    /**check connectivity not available and failed error message */
    @Test
    fun checkFailedToast() = testDispatcher.runBlockingTest {
        testScope.launch (Dispatchers.IO ){
            val fragment =  ListScreenFragment()
            assertFalse(Constants.isConnectedToNetwork(context))
            assertEquals(context.getString(R.string.failure_toast),
                fragment.activity?.findViewById<AppCompatTextView>(R.id.errorTextData)?.text
            )
        }
    }
}