package lbrands.com.lbrandsassignment;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lbrands.com.ui.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkViewsDisplay() {

        onView(withId(R.id.etUserName))
                .check(matches(isDisplayed()));

        onView(withId(R.id.btnLogin))
                .check(matches(isDisplayed()));

    }

    @Test
    public void activityLaunch() {
        onView(withId(R.id.btnLogin)).perform(click());
    }

    @Test
    public void textInputOutput() {
        onView(withId(R.id.etUserName)).perform(typeText("Sherry"));
        onView(withId(R.id.btnLogin)).perform(click());
    }
}