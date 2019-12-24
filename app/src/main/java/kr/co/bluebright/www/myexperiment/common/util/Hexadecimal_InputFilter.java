/*
 * +---------------------------------------------------------------------------+
 * |       01000010 01101001 01101110 01000011 01100001 01101100 01100011      |
 * |       B        i        n        C        a        l        c             |
 * +---------------------------------------------------------------------------+
 * Copyright 2012 - 2015 Scott A Dixon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.co.bluebright.www.myexperiment.common.util;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * @see <a href="https://gist.github.com/scottdarch/bdd9a7abebd1bf6dc9b8">Gist</a>
 */
public final class Hexadecimal_InputFilter implements InputFilter {
    public Hexadecimal_InputFilter(boolean forceUpperCase) {
        mUpperCase = forceUpperCase;
    }

    // +-----------------------------------------------------------------------+
    // | InputFilter
    // +-----------------------------------------------------------------------+
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        char[] cleanSequence = null;
        final int sequenceLen = end - start;
        int nextOpenSlot = 0;
        int i = start;

        // defensive posture to prevent taking action on bad input.
        if (sequenceLen <= 0) {
            return null;
        }

        // Force valid hex characters which are all upper-case.
        for (; i < end; ++i) {
            final char testChar = source.charAt(i);
            final char testCharUpperCase = Character.toUpperCase(testChar);
            if (testCharUpperCase != 'A' &&
                    testCharUpperCase != 'B' &&
                    testCharUpperCase != 'C' &&
                    testCharUpperCase != 'D' &&
                    testCharUpperCase != 'E' &&
                    testCharUpperCase != 'F' &&
                    !Character.isDigit(testChar)) {
                // not a valid hex character. Redact.
                if (null == cleanSequence) {
                    cleanSequence = new char[sequenceLen];
                    TextUtils.getChars(source, start, i, cleanSequence, 0);
                }
            } else if ((mUpperCase && testChar != testCharUpperCase) ||
                    (!mUpperCase && testChar == testCharUpperCase)) {
                // valid but not the right case. Make this upper-case.
                if (null == cleanSequence) {
                    cleanSequence = new char[sequenceLen];
                    TextUtils.getChars(source, start, i, cleanSequence, 0);
                }
                cleanSequence[nextOpenSlot++] = (mUpperCase) ? testCharUpperCase : Character.toLowerCase(testChar);
            } else if (null != cleanSequence) {
                // a valid character but we already found an invalid character
                // so we're forming a replacement.
                cleanSequence[nextOpenSlot++] = testCharUpperCase;
            } else {
                // keep the nextOpenSlot index updated in-case we find an invalid
                // character later on and end up doing a mass copy of all the valid
                // characters to this point from the source.
                ++nextOpenSlot;
            }
        }

        if (null != cleanSequence) {
            // We are filtering the source. Create a string from our clean
            // sequence but remember that this array may contain less values
            // if we found invalid characters.
            final String cleanString;
            if (nextOpenSlot >= sequenceLen) {
                cleanString = String.valueOf(cleanSequence);
            } else {
                cleanString = String.valueOf(cleanSequence, 0, nextOpenSlot);
            }

            if (source instanceof Spanned) {
                final SpannableString cleanSpannable = new SpannableString(cleanString);
                TextUtils.copySpansFrom((Spanned) source,
                        start, nextOpenSlot, null, cleanSpannable, 0);
                return cleanSpannable;
            } else {
                return cleanString;
            }
        } else {
            // we were okay with the source string. Neat.
            return null;
        }
    }

    // +-----------------------------------------------------------------------+
    // | PRIVATE
    // +-----------------------------------------------------------------------+
    private final boolean mUpperCase;

}
