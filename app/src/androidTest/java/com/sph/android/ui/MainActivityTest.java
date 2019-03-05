package com.sph.android.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.sph.android.R;
import com.sph.android.model.db.RealmManager;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Before
    public void setup() throws  Throwable{
        mActivityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RealmManager.open();
            }
        });
    }

    @After
    public void clearSettings() throws  Throwable{
        mActivityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RealmManager.close();
            }
        });
    }

    @Test
    public void mainActivityTest() throws Exception {
        //wait for view to render
        Thread.sleep(5500);

        //verify fourth row displayed
        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.rvMobileData),
                                4),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        //verify second row does not visible image
        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.ivLowVolume),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rvMobileData),
                                        2),
                                1),
                        isDisplayed()));
        imageView2.check(doesNotExist());

        //verify seventh row displayed imageview
        ViewInteraction imageView = onView(
                allOf(withId(R.id.ivLowVolume),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rvMobileData),
                                        7),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        //verify seventh row image click event
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.ivLowVolume),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rvMobileData),
                                        7),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());
    }

    @Test
    public void checkConnectivity(){
        Assert.assertTrue(MainActivity.isNetworkConnected(mActivityTestRule.getActivity()));
    }
}
