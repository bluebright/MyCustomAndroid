package kr.co.bluebright.www.myexperiment.common.util;

import android.text.InputFilter;
import android.text.Spanned;

import androidx.databinding.ObservableField;

public class InputFilterValueObserver implements InputFilter {

    public final ObservableField<String> charSequence;
    public final ObservableField<Integer> start;
    public final ObservableField<Integer> end;
    public final ObservableField<Integer> dstart;
    public final ObservableField<Integer> dend;
    public final ObservableField<String> char_start;
    public final ObservableField<String> char_end;
    public final ObservableField<String> char_dstart;
    public final ObservableField<String> char_dend;


    public InputFilterValueObserver() {

        charSequence = new ObservableField<>();
        start = new ObservableField<>();
        end = new ObservableField<>();
        dstart = new ObservableField<>();
        dend = new ObservableField<>();
        char_start = new ObservableField<>();
        char_end = new ObservableField<>();
        char_dstart = new ObservableField<>();
        char_dend = new ObservableField<>();

    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        final int sequenceLen = end - start;

        if (sequenceLen <= 0) {
           this.charSequence.set("Length 0");
            return null;
        }


            this.charSequence.set(String.valueOf(source));
            this.start.set(start);
            this.end.set(end);
            this.dstart.set(dstart);
            this.dend.set(dend);

            try {
                this.char_start.set(String.valueOf(source.charAt(start)));
            } catch (IndexOutOfBoundsException ex) {
                this.char_start.set("Index out of bound");
            }

            try {
                this.char_end.set(String.valueOf(source.charAt(end)));
            } catch (IndexOutOfBoundsException ex) {
                this.char_end.set("Index out of bound");
            }

            try {
                this.char_dstart.set(String.valueOf(source.charAt(dstart)));
            } catch (IndexOutOfBoundsException ex) {
                this.char_dstart.set("Index out of bound");
            }

            try {
                this.char_dend.set(String.valueOf(source.charAt(dend)));
            } catch (IndexOutOfBoundsException ex) {
                this.char_dend.set("Index out of bound");
            }



        return source;
    }

}
