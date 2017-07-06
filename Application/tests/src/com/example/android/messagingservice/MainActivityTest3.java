package com.example.android.messagingservice;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest3() {
        ViewInteraction button = onView(
                allOf(withId(R.id.send_1_conversation), withText("Send 1 conversation with 1 message"), isDisplayed()));
        button.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.data_port), withText("\n\n2017. 7. 5. 오후 11:02:28: Sending notification 2110828150 conversation: [Conversation: conversationId=2110828150, participantName=James Smith, messages=[Can you give me a call?], timestamp=1499295748900]"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                3),
                        isDisplayed()));
        textView.check(matches(withText("  2017. 7. 5. 오후 11:02:28: Sending notification 2110828150 conversation: [Conversation: conversationId=2110828150, participantName=James Smith, messages=[Can you give me a call?], timestamp=1499295748900]")));

    }

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
}
