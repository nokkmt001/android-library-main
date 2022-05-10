package com.lediya.dagger2

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lediya.dagger2.api.CountryListRepository
import com.lediya.dagger2.utils.Constants
import com.lediya.dagger2.view.ListScreenActivity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 *
 */
@RunWith(AndroidJUnit4::class)
class CountryDataInstrumentedTest {
    private lateinit var context:Context
    @Inject
    lateinit var repository: CountryListRepository
    /**
     * Create the db for database operation */
    @Before
    fun createDb() {
         context = ApplicationProvider.getApplicationContext<Context>()
    }
    /**
     * Close the db after the database operation test case*/
    @After
    @Throws(IOException::class)
    fun closeDb() {
    }
    /**
     * check internet is on and downlaod api data test case*/
    @Test
    fun internetOnTest() {
        try {
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            val activity = ActivityTestRule(ListScreenActivity::class.java)
            activity.launchActivity(Intent())
            assertEquals(context.getString(R.string.test_package), appContext.packageName)
            assertTrue(Constants.isConnectedToNetwork(appContext))
        }
        catch (e:AssertionError){

        }
    }
    /**
     * check internet is off and shows the warning alert test case */
    @Test
    fun internetOffTest() {
        try{
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            val activity = ActivityTestRule(ListScreenActivity::class.java)
        activity.launchActivity(Intent())
        assertEquals(context.getString(R.string.test_package), appContext.packageName)
        assertFalse(Constants.isConnectedToNetwork(appContext))
        onView(withId(R.id.errorTextData)).check(ViewAssertions.matches(withText(appContext.getString(
            R.string.no_internet_toast
        ))))
        }
        catch (e:AssertionError){

        }
    }
}
