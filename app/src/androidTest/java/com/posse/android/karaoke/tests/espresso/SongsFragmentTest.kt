package com.posse.android.karaoke.tests.espresso

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.posse.android.karaoke.R
import com.posse.android.karaoke.screens.songs.SongsFragment
import com.posse.android.karaoke.screens.songs.adapter.SongsRVAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SongsFragmentTest {

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var scenario: FragmentScenario<SongsFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_testRecycler() {
        testCoroutineRule.runBlockingTest {
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment { fragment ->

                onView(withId(R.id.rvSongs))
                    .perform(
                        RecyclerViewActions.scrollTo<SongsRVAdapter.ViewHolder>(
                            hasDescendant(withText("Starlight - Muse"))
                        )
                    )
                onView(withId(R.id.rvSongs)).check(matches(withText("Starlight - Muse")))
            }
        }
    }

    @After
    fun end() {
        scenario.close()
    }
}