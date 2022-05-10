package com.example.simpleapp

import android.widget.ScrollView
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.scroll.KScrollView
import io.github.kakaocup.kakao.text.KTextView

object SampleScreen : KScreen<SampleScreen>() {

    override val layoutId: Int = R.layout.fragment_privacy_report_page

    override val viewClass: Class<*> = PrivacyReportPageFragment::class.java

    val firstView = KTextView {
        withText("1")
        isDescendantOfA {
            withIndex(0) { isInstanceOf(ScrollView::class.java) }
        }
    }

    val secondView = KTextView {
        withText("2")
        isDescendantOfA {
            withIndex(0) { isInstanceOf(ScrollView::class.java) }
        }
    }

    val thirdView = KTextView {
        withText("3")
        isDescendantOfA {
            withIndex(0) { isInstanceOf(ScrollView::class.java) }
        }
    }

    val fourthView = KTextView {
        withText("4")
        isDescendantOfA {
            withIndex(0) { isInstanceOf(ScrollView::class.java) }
        }
    }

    val fifthView = KTextView {
        withText("5")
        isDescendantOfA {
            withIndex(0) { isInstanceOf(ScrollView::class.java) }
        }
    }
}