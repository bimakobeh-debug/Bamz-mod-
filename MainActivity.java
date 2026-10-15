package com.bamzmod.demo;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends Activity {
    private LinearLayout root;
    private TextView status;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(16, 7, 7));
        getWindow().setNavigationBarColor(Color.rgb(16, 7, 7));
        buildUi();
    }

    private GradientDrawable bg(int color, int stroke) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(16));
        if (stroke != 0) d.setStroke(dp(1), stroke);
        return d;
    }

    private int dp(float n) { return (int)(n * getResources().getDisplayMetrics().density + 0.5f); }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private void buildUi() {
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(16, 7, 7));
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(24), dp(20), dp(24));
        scroll.addView(root);

        TextView brand = text("BAMZ MOD", 30, Color.WHITE, true);
        brand.setLetterSpacing(0.08f);
        root.addView(brand);
        TextView subtitle = text("GAMING PANEL  •  DEMO EDITION", 11, Color.rgb(255, 75, 75), true);
        LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(-2, -2);
        subLp.topMargin = dp(5);
        root.addView(subtitle, subLp);

        TextView banner = text("UI DEMO ONLY\\nTidak terhubung ke Free Fire atau game lain.", 14, Color.WHITE, false);
        banner.setPadding(dp(16), dp(16), dp(16), dp(16));
        banner.setBackground(bg(Color.rgb(42, 13, 16), Color.rgb(190, 35, 45)));
        LinearLayout.LayoutParams bannerLp = new LinearLayout.LayoutParams(-1, -2);
        bannerLp.topMargin = dp(22);
        root.addView(banner, bannerLp);

        TextView section = text("FEATURE CONTROLS", 13, Color.LTGRAY, true);
        LinearLayout.LayoutParams secLp = new LinearLayout.LayoutParams(-2, -2);
        secLp.topMargin = dp(24);
        secLp.bottomMargin = dp(10);
        root.addView(section, secLp);

        addSwitch("ESP Box", "Kotak target simulasi", false);
        addSwitch("ESP Name", "Label nama dummy", false);
        addSwitch("ESP Line", "Garis target simulasi", false);
        addSwitch("Aim Silent", "Tombol visual demo saja", false);

        status = text("Status: semua fitur demo nonaktif", 13, Color.rgb(255, 130, 130), false);
        status.setPadding(dp(14), dp(14), dp(14), dp(14));
        status.setBackground(bg(Color.rgb(31, 17, 18), 0));
        LinearLayout.LayoutParams statusLp = new LinearLayout.LayoutParams(-1, -2);
        statusLp.topMargin = dp(18);
        root.addView(status, statusLp);

        TextView foot = text("Bamz Mod v1.0  •  Standalone UI sample", 11, Color.GRAY, false);
        foot.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams footLp = new LinearLayout.LayoutParams(-1, -2);
        footLp.topMargin = dp(24);
        root.addView(foot, footLp);
        setContentView(scroll);
    }

    private void addSwitch(String title, String desc, boolean initial) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(16), dp(12), dp(12), dp(12));
        card.setBackground(bg(Color.rgb(30, 19, 20), Color.rgb(74, 35, 38)));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.topMargin = dp(9);
        root.addView(card, lp);

        LinearLayout labels = new LinearLayout(this);
        labels.setOrientation(LinearLayout.VERTICAL);
        TextView titleView = text(title, 16, Color.WHITE, true);
        TextView descView = text(desc, 11, Color.rgb(180, 165, 165), false);
        LinearLayout.LayoutParams descLp = new LinearLayout.LayoutParams(-1, -2);
        descLp.topMargin = dp(4);
        labels.addView(titleView);
        labels.addView(descView, descLp);
        card.addView(labels, new LinearLayout.LayoutParams(0, -2, 1));

        Switch toggle = new Switch(this);
        toggle.setButtonTintList(null);
        toggle.setChecked(initial);
        card.addView(toggle);
        toggle.setOnCheckedChangeListener((CompoundButton button, boolean checked) -> updateStatus());
    }

    private void updateStatus() {
        int count = 0;
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof LinearLayout) {
                LinearLayout row = (LinearLayout) v;
                for (int j = 0; j < row.getChildCount(); j++) {
                    View child = row.getChildAt(j);
                    if (child instanceof Switch && ((Switch) child).isChecked()) count++;
                }
            }
        }
        status.setText("Status: " + count + " fitur demo aktif");
    }
}
