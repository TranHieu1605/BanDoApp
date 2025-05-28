package com.example.mapsearchapp.utils;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

public class highlight {
    // Ham dung de to mau tu khoa tim kiem trong chuoi dia chi
    public static Spannable highlightKeyword(String fullText, String keyword) {
        SpannableString spannable = new SpannableString(fullText);

        if (keyword == null || keyword.isEmpty()) return spannable;

        String lowerFull = fullText.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();

        int start = lowerFull.indexOf(lowerKeyword);
        if (start >= 0) {
            spannable.setSpan(
                    new ForegroundColorSpan(0xFFFF0000), // đỏ
                    start,
                    start + keyword.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            spannable.setSpan(
                    new StyleSpan(Typeface.BOLD), // đậm
                    start,
                    start + keyword.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }

        return spannable;
    }
}
