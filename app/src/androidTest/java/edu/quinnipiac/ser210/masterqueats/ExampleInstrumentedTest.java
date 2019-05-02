package edu.quinnipiac.ser210.masterqueats;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.RadioButton;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void getfromDatabase(){
            onView(withId(R.id.nextScreen)).perform(click);
            //,closeSoftKeyboard()
            onView(withId(R.id.pickupButton)).perform(click());
        Button cartBtn = PlaceOrderActivity.cartBtn;
            assertTrue(cartBtn.isEnabled());
    }
}
